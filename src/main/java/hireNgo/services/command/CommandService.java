package hireNgo.services.command;

import hireNgo.db.dao.UserDao;
import hireNgo.db.generated.Command;
import hireNgo.db.generated.User;
import hireNgo.webservices.api.command.bean.ReturnedCommandBean;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CommandService {

    private final UserDao userDao;

    @Inject
    public CommandService(UserDao userDao){

        this.userDao = userDao;
    }

    public ReturnedCommandBean buildReturnedCommandBean(Command command){
        ReturnedCommandBean returnedCommandBean = new ReturnedCommandBean();
        returnedCommandBean.setDistance(command.getDistance());
        returnedCommandBean.setDuration(command.getDuration());
        returnedCommandBean.setStart(command.getStart());
        returnedCommandBean.setEnd(command.getEnd());
        returnedCommandBean.setStartTime(command.getStartTime());
        returnedCommandBean.setId(command.getId().toString());
        User user = userDao.findById(command.getIdUserFront());
        returnedCommandBean.setUserName(user.getFirstname() + " " + user.getLastname());
        return returnedCommandBean;
    }

}
