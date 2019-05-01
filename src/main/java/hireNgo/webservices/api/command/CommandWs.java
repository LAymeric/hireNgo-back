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
import hireNgo.webservices.api.command.bean.ChooseCommandBean;
import hireNgo.webservices.api.command.bean.CommandBean;
import hireNgo.webservices.api.command.bean.ReturnedCommandBean;
import hireNgo.webservices.api.command.bean.UpdateCommandBean;
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
    private final AssoCommandServiceDao AssoCommandServiceDao;

    @Inject
    public CommandWs(UserDao userDao, CommandDao commandDao, CommandService commandService, ServiceDao serviceDao, hireNgo.db.dao.AssoCommandServiceDao assoCommandServiceDao){

        this.userDao = userDao;
        this.commandDao = commandDao;
        this.commandService = commandService;
        this.serviceDao = serviceDao;
        AssoCommandServiceDao = assoCommandServiceDao;
    }

    @GET
    @Path("/available")
    @ApiOperation("Get commands available")
    public List<ReturnedCommandBean> getAvailableCommands() {
        return commandDao.findAllByStatus(CommandStatus.WAITING).stream().map(commandService::buildReturnedCommandBean).collect(Collectors.toList());
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
    public Command creatComande(CommandBean commandBean){
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

        if(user.getIsPremium()){
            newCommand.setStatus(CommandStatus.INCOMPLETE.name());
            //Todo services
        }else{
            newCommand.setStatus(CommandStatus.WAITING.name());
        }

        newCommand.setIdUserFront(user.getId());
        newCommand.setDuration(commandBean.getDuration());
        newCommand.setDistance(commandBean.getDistance());
        newCommand.setEnd(commandBean.getEnd());
        newCommand.setEndTime("");//todo calculer avec start time + duration
        newCommand.setStart(commandBean.getStart());
        newCommand.setStartTime(commandBean.getStartTime());
        //todo calculate price with services
        Double price = Double.valueOf(newCommand.getDistance()) * 2.5;
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
        toUptadeCommand.setStatus(CommandStatus.IN_PROGRESS.name());
        toUptadeCommand.setIdUserDriver(user.getId());
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
        toUptadeCommand.setStatus(CommandStatus.WAITING.name());
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
        List<Service> correspondingServices = serviceDao.fetchServiceForAccompanistAndThisCommand(user.getId(), toUptadeCommand.getId());
        for(Service service : correspondingServices){
            AssoCommandServiceDao.addAccopanistToService(user.getId(), toUptadeCommand.getId(), service.getId());
        }
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
