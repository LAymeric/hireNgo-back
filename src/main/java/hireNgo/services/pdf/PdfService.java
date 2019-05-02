package hireNgo.services.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import hireNgo.db.dao.ServiceDao;
import hireNgo.db.dao.UserDao;
import hireNgo.db.generated.Command;
import hireNgo.db.generated.Service;
import hireNgo.db.generated.User;
import hireNgo.webservices.api.command.bean.FileBean;
import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.util.List;

import javax.inject.Inject;

public class PdfService {

    private final ServiceDao serviceDao;
    private final UserDao userDao;

    @Inject
    public PdfService(ServiceDao serviceDao, UserDao userDao){
        this.serviceDao = serviceDao;
        this.userDao = userDao;
    }

    public FileBean getPdfFileBean(Command command){
        FileBean fileBean = new FileBean();
        String fileName = command.getId() + "_bill.pdf";
        fileBean.setFileName(fileName);
        try {
            fileBean.setBase64(encodeFileToBase64Binary(fileName));
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

    public void createBill(Command command){
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(command.getId() + "_bill.pdf"));
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
        try {
            StringBuilder services = new StringBuilder();
            List<Service> serviceList = serviceDao.fetchAllForCommand(command.getId());
            for(Service s : serviceList){
                services.append(s.getName());
                if(serviceList.indexOf(s) != serviceList.size() -1){
                    services.append(", ");
                }
            }

            User frontUser = userDao.findById(command.getIdUserFront());
            String frontUserName = frontUser.getFirstname() + " " + frontUser.getLastname();
            document.add(new Paragraph("Bill For Commmand " + command.getId()));
            document.add(new Paragraph("For User " + frontUserName));
            document.add(new Paragraph("Start : " + command.getStart()));
            document.add(new Paragraph("Start Time : " + command.getStartTime()));
            document.add(new Paragraph("End : " + command.getEnd()));
            document.add(new Paragraph("Duration : " + command.getDuration()));
            document.add(new Paragraph("Distance : " + command.getDistance()));
            document.add(new Paragraph("Services : " + services));
            if(command.getIdUserDriver() != null){
                User driver = userDao.findById(command.getIdUserDriver());
                String driverName = driver.getFirstname() + " " + driver.getLastname();
                document.add(new Paragraph("Driver : " + driverName));
            }
            document.add(new Paragraph("Price : " + command.getFinalPrice()));

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        document.close();

    }

}
