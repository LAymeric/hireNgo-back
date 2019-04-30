package hireNgo.webservices.api.command;

import com.coreoz.plume.jersey.errors.WsException;
import hireNgo.db.dao.CommandDao;
import hireNgo.db.dao.UserDao;
import hireNgo.db.generated.Command;
import hireNgo.db.generated.User;
import hireNgo.services.command.CommandService;
import hireNgo.webservices.api.command.bean.ChooseCommandBean;
import hireNgo.webservices.api.command.bean.CommandBean;
import hireNgo.webservices.api.command.bean.ReturnedCommandBean;
import hireNgo.webservices.api.users.bean.CommandStatus;
import hireNgo.webservices.exeptions.ProjectWsError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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

    @Inject
    public CommandWs(UserDao userDao, CommandDao commandDao, CommandService commandService){

        this.userDao = userDao;
        this.commandDao = commandDao;
        this.commandService = commandService;
    }

    @GET
    @Path("/available")
    @ApiOperation("Get commands available")
    public List<ReturnedCommandBean> getAvailableCommands() {
        return commandDao.findAllByStatus(CommandStatus.WAITING).stream().map(commandService::buildReturnedCommandBean).collect(Collectors.toList());
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
        Double price = Long.valueOf(newCommand.getDistance()) * 2.5;
        newCommand.setFinalPrice(String.valueOf(price));

       return commandDao.save(newCommand);
    }

    @POST
    @Path("/choose")
    public Command chooseComande(ChooseCommandBean commandBean){
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
