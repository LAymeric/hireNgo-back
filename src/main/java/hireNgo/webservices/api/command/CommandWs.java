package hireNgo.webservices.api.command;

import com.coreoz.plume.jersey.errors.WsException;
import hireNgo.db.dao.CommandDao;
import hireNgo.db.dao.UserDao;
import hireNgo.db.generated.Command;
import hireNgo.db.generated.User;
import hireNgo.webservices.api.command.bean.CommandBean;
import hireNgo.webservices.api.users.bean.CommandStatus;
import hireNgo.webservices.exeptions.ProjectWsError;
import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/command")
@Api("Manage command web-services") //pour le swagger
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Singleton //cela veut dire qu'il ne peut y avoir qu'une seule instance de CommandWs
public class CommandWs {

    private final UserDao userDao;
    private final CommandDao commandDao;

    @Inject
    public CommandWs(UserDao userDao, CommandDao commandDao){

        this.userDao = userDao;
        this.commandDao = commandDao;
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

       return commandDao.save(newCommand);
    }

}
