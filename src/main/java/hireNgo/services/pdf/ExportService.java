package hireNgo.services.pdf;

import hireNgo.db.dao.CommandDao;
import hireNgo.services.command.CommandService;
import hireNgo.webservices.api.command.bean.FileBean;
import hireNgo.webservices.api.command.bean.ReturnedCommandBean;
import hireNgo.webservices.api.users.bean.CommandStatus;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.inject.Inject;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class ExportService {

    private final CommandDao commandDao;
    private final CommandService commandService;

    @Inject
    public ExportService(CommandDao commandDao, CommandService commandService){

        this.commandDao = commandDao;
        this.commandService = commandService;
    }

    public FileBean sendFile(){
        String path = createExportFile();
        FileBean fileBean = new FileBean();
        fileBean.setFileName(path);
        try {
            fileBean.setBase64(encodeFileToBase64Binary(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileBean;
    }

    private String encodeFileToBase64Binary(String fileName)
            throws IOException {

        File file = new File(fileName);
        byte[] bytes = loadFile(file);
        byte[] encoded = Base64.encodeBase64(bytes);
        return new String(encoded);
    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }

    private String createExportFile(){
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Courses");
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        Row headerRow = sheet.createRow(0);
        String[] columns = {
            "Course Id",
            "Départ",
            "Arrivée",
            "Date de départ",
            "Distance",
            "Durée",
            "Services",
            "Utilisateur",
            "Conducteur",
            "Prix Total",
        };
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        int rowNum = 1;
        List<ReturnedCommandBean> returnedCommandBeanList = commandDao.findAllByStatus(CommandStatus.FINISHED).stream().map(commandService::buildReturnedCommandBean).collect(Collectors.toList());
        for (ReturnedCommandBean returnedCommandBean : returnedCommandBeanList) {
            if(returnedCommandBean != null){
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(returnedCommandBean.getId());
                row.createCell(1).setCellValue(returnedCommandBean.getStart());
                row.createCell(2).setCellValue(returnedCommandBean.getEnd());
                row.createCell(3).setCellValue(returnedCommandBean.getStartTime());
                row.createCell(4).setCellValue(returnedCommandBean.getDistance());
                row.createCell(5).setCellValue(returnedCommandBean.getDuration());
                StringBuilder servicesStr = new StringBuilder();
                returnedCommandBean.getServices().stream().map(servicesStr::append);
                row.createCell(6).setCellValue(servicesStr.toString());
                row.createCell(7).setCellValue(returnedCommandBean.getUserName());
                row.createCell(8).setCellValue(returnedCommandBean.getUserDirverName());
                row.createCell(9).setCellValue(returnedCommandBean.getPrice());
            }

        }

// Resize all columns to fit the content size
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        try {
            String path ="export.xlsx";
            FileOutputStream fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
            return path;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
