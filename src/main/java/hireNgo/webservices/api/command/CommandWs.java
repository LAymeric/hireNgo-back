package hireNgo.webservices.api.command;

import com.coreoz.plume.jersey.errors.WsException;
import hireNgo.db.dao.AssoCommandServiceDao;
import hireNgo.db.dao.CommandDao;
import hireNgo.db.dao.ServiceDao;
import hireNgo.db.dao.UserDao;
import hireNgo.db.generated.Command;
import hireNgo.db.generated.Service;
import hireNgo.db.generated.User;
import hireNgo.services.command.CommandService;
import hireNgo.services.command.NotificationService;
import hireNgo.services.pdf.ExportService;
import hireNgo.services.pdf.PdfService;
import hireNgo.webservices.api.command.bean.*;
import hireNgo.webservices.api.services.bean.ServiceBean;
import hireNgo.webservices.api.users.bean.CommandStatus;
import hireNgo.webservices.exeptions.ProjectWsError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/command")
@Api("Manage command web-services") //pour le swagger
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Singleton //cela veut dire qu'il ne peut y avoir qu'une seule instance de CommandWs
public class CommandWs {

    private final UserDao userDao;
    private final CommandDao commandDao;
    private final CommandService commandService;
    private final ServiceDao serviceDao;
    private final PdfService pdfService;
    private final NotificationService notificationService;
    private final ExportService exportService;
    private final AssoCommandServiceDao AssoCommandServiceDao;

    @Inject
    public CommandWs(UserDao userDao, CommandDao commandDao, CommandService commandService, ServiceDao serviceDao, PdfService pdfService, NotificationService notificationService, ExportService exportService, hireNgo.db.dao.AssoCommandServiceDao assoCommandServiceDao){

        this.userDao = userDao;
        this.commandDao = commandDao;
        this.commandService = commandService;
        this.serviceDao = serviceDao;
        this.pdfService = pdfService;
        this.notificationService = notificationService;
        this.exportService = exportService;
        AssoCommandServiceDao = assoCommandServiceDao;
    }

    @GET
    @Path("/available")
    @ApiOperation("Get commands available")
    public List<ReturnedCommandBean> getAvailableCommands() {
        return commandDao.findAllByStatus(CommandStatus.WAITING).stream().map(commandService::buildReturnedCommandBean).collect(Collectors.toList());
    }

    @GET
    @Path("/pdf/{commandId}")
    @ApiOperation("Get commands available")
    public FileBean getPdf(@PathParam("commandId") String commandId) {
        Command command = commandDao.findById(Long.parseLong(commandId));
        pdfService.createBill(command);
        return pdfService.getPdfFileBean(command);
    }

    @GET
    @Path("/export")
    @ApiOperation("Get commands available")
    public FileBean getExportXls() {
        return exportService.sendFile();
    }

    @GET
    @Path("/validate/{commandId}")
    @ApiOperation("Get commands available")
    public void validateCommand(@PathParam("commandId") String commandId) {
        Command command = commandDao.findById(Long.parseLong(commandId));
        command.setStatus(CommandStatus.WAITING.name());
        commandDao.save(command);
    }

    @GET
    @Path("/paid/{commandId}")
    @ApiOperation("Get commands available")
    public void paidCommand(@PathParam("commandId") String commandId) {
        Command command = commandDao.findById(Long.parseLong(commandId));
        command.setStatus(CommandStatus.WAITING.name());
        User user = userDao.findById(command.getIdUserFront());
        notificationService.sendNotif(user.getPhone(), "Vous avez finalisé votre course. Nous vous préviendrons dès qu'on chauffeur la prendra en charge");
        List<User> userDrivers = userDao.findAllDrivers();
        for(User userDriver : userDrivers){
            notificationService.sendNotif(userDriver.getPhone(), "Une nouvelle course est disponible ! Consultez votre dashboard.");
        }
        commandDao.save(command);
    }

    @GET
    @Path("/availableAccompanist/{email}")
    @ApiOperation("Get commands available")
    public List<ReturnedCommandBean> getAvailableCommandsForAccompanist(@PathParam("email") String email) {
        if(email == null){
            throw new WsException(ProjectWsError.NO_EMAIL);
        }
        User user = userDao.findByEmail(email);
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        List<Service> servicesOfAccompanist = serviceDao.fetchServiceForAccompanist(user.getId());
        List<Command> commands = new ArrayList<>();
        for(Service service : servicesOfAccompanist){
            commands.addAll(commandDao.findWithCorrespondingServiceWithNoValidation(service));
        }
        return commands.stream().map(commandService::buildReturnedCommandBean).collect(Collectors.toList());
    }

    @GET
    @Path("/history/{email}")
    @ApiOperation("Get commands available")
    public List<ReturnedCommandBean> getHistoryCommands(@PathParam("email") String email) {
        if(email == null){
            throw new WsException(ProjectWsError.NO_EMAIL);
        }
        User user = userDao.findByEmail(email);
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        return commandDao.findAllByStatusAndUserDriver(CommandStatus.FINISHED, user.getId()).stream().map(commandService::buildReturnedCommandBean).collect(Collectors.toList());
    }

    @GET
    @Path("/historyAccompanist/{email}")
    @ApiOperation("Get commands available")
    public List<ReturnedCommandBean> getHistoryAccompanistCommands(@PathParam("email") String email) {
        if(email == null){
            throw new WsException(ProjectWsError.NO_EMAIL);
        }
        User user = userDao.findByEmail(email);
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        return commandDao.findAllByStatusAndUserAccompanist(CommandStatus.FINISHED, user.getId()).stream().map(commandService::buildReturnedCommandBean).collect(Collectors.toList());
    }

    @GET
    @Path("/current/{email}")
    @ApiOperation("Get commands available")
    public List<ReturnedCommandBean> getCurrentCommands(@PathParam("email") String email) {
        if(email == null){
            throw new WsException(ProjectWsError.NO_EMAIL);
        }
        User user = userDao.findByEmail(email);
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        return commandDao.findAllByStatusAndUserDriver(CommandStatus.IN_PROGRESS, user.getId()).stream().map(commandService::buildReturnedCommandBean).collect(Collectors.toList());
    }
    @GET
    @Path("/currentForAccompanist/{email}")
    @ApiOperation("Get commands available")
    public List<ReturnedCommandBean> getCurrentCommandsForAccompanist(@PathParam("email") String email) {
        if(email == null){
            throw new WsException(ProjectWsError.NO_EMAIL);
        }
        User user = userDao.findByEmail(email);
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        return commandDao.findAllAndUserAccompanist(user.getId()).stream().map(commandService::buildReturnedCommandBean).collect(Collectors.toList());
    }

    @POST
    @Path("/new")
    public Command createComande(CommandBean commandBean){
        //Check si le front a envoyé une commande
        if(commandBean == null){
            throw new WsException(ProjectWsError.NO_COMMAND);
        }
        User user = userDao.findByEmail(commandBean.getEmail());
        //Check si l'utilisateur existe
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        Command newCommand = new Command();
        newCommand.setStatus(CommandStatus.INCOMPLETE.name());
        newCommand.setIdUserFront(user.getId());
        newCommand.setDuration(commandBean.getDuration());
        newCommand.setDistance(commandBean.getDistance());
        newCommand.setEnd(commandBean.getEnd());
        newCommand.setEndTime("");//todo calculer avec start time + duration
        newCommand.setStart(commandBean.getStart());
        newCommand.setStartTime(commandBean.getStartTime());
        Double price = Double.valueOf(newCommand.getDistance()) * 2.5; //todo calculate price
        newCommand.setFinalPrice(String.valueOf(price));

       return commandDao.save(newCommand);
    }

    @POST
    @Path("/choose")
    public Command chooseCommand(ChooseCommandBean commandBean){
        //Check si le front a envoyé une commande
        if(commandBean == null){
            throw new WsException(ProjectWsError.NO_COMMAND);
        }
        User user = userDao.findByEmail(commandBean.getEmail());
        //Check si l'utilisateur existe
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        Command toUptadeCommand = commandDao.findById(Long.parseLong(commandBean.getCommandId()));

        if(toUptadeCommand == null){
            throw new WsException(ProjectWsError.NO_COMMAND);
        }
        User userFront = userDao.findById(toUptadeCommand.getIdUserFront());
        toUptadeCommand.setStatus(CommandStatus.IN_PROGRESS.name());
        toUptadeCommand.setIdUserDriver(user.getId());
        notificationService.sendNotif(userFront.getPhone(), "Votre course a été sélectionnée par un chauffeur, soyez prêt à partir!");
       return commandDao.save(toUptadeCommand);
    }


    @POST
    @Path("/update")
    public ReturnedCommandBean updateCommand(UpdateCommandBean updateCommandBean){
        //Check si le front a envoyé une commande
        if(updateCommandBean == null){
            throw new WsException(ProjectWsError.NO_COMMAND);
        }
        User user = userDao.findById(Long.valueOf(updateCommandBean.getIdUser()));
        //Check si l'utilisateur existe
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        Command toUptadeCommand = commandDao.findById(Long.parseLong(updateCommandBean.getIdCommand()));

        if(toUptadeCommand == null){
            throw new WsException(ProjectWsError.NO_COMMAND);
        }
        for(ServiceBean serviceBean : updateCommandBean.getServices()){
            serviceDao.addServiceToCommand(toUptadeCommand.getId(), Long.parseLong(serviceBean.getIdService()), serviceBean.getQuantity());
        }
        //todo calculate price

        return commandService.buildReturnedCommandBean(commandDao.save(toUptadeCommand));
    }


    @POST
    @Path("/chooseForAccompanist")
    public Command chooseForAccompanist(ChooseCommandBean commandBean){
        //Check si le front a envoyé une commande
        if(commandBean == null){
            throw new WsException(ProjectWsError.NO_COMMAND);
        }
        User user = userDao.findByEmail(commandBean.getEmail());
        //Check si l'utilisateur existe
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        Command toUptadeCommand = commandDao.findById(Long.parseLong(commandBean.getCommandId()));
        if(toUptadeCommand == null){
            throw new WsException(ProjectWsError.NO_COMMAND);
        }
        User userFront = userDao.findById(toUptadeCommand.getIdUserFront());
        List<Service> correspondingServices = serviceDao.fetchServiceForAccompanistAndThisCommand(user.getId(), toUptadeCommand.getId());
        for(Service service : correspondingServices){
            AssoCommandServiceDao.addAccopanistToService(user.getId(), toUptadeCommand.getId(), service.getId());
        }
        notificationService.sendNotif(userFront.getPhone(), "Votre course a été sélectionnée par un accompagnateur, soyez prêt à partir!");
        return toUptadeCommand;
    }

    @POST
    @Path("/end")
    public Command endCommand(ChooseCommandBean commandBean){
        //Check si le front a envoyé une commande
        if(commandBean == null){
            throw new WsException(ProjectWsError.NO_COMMAND);
        }
        User user = userDao.findByEmail(commandBean.getEmail());
        //Check si l'utilisateur existe
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        Command toUptadeCommand = commandDao.findById(Long.parseLong(commandBean.getCommandId()));

        if(toUptadeCommand == null){
            throw new WsException(ProjectWsError.NO_COMMAND);
        }
        toUptadeCommand.setStatus(CommandStatus.FINISHED.name());

       return commandDao.save(toUptadeCommand);
    }

}
