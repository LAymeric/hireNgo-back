package hireNgo.services.command;

import hireNgo.db.dao.ServiceDao;
import hireNgo.db.dao.UserDao;
import hireNgo.db.generated.Command;
import hireNgo.db.generated.Service;
import hireNgo.db.generated.User;
import hireNgo.webservices.api.command.bean.ReturnedCommandBean;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class CommandService {

    private final UserDao userDao;
    private final ServiceDao serviceDao;

    @Inject
    public CommandService(UserDao userDao, ServiceDao serviceDao){

        this.userDao = userDao;
        this.serviceDao = serviceDao;
    }

    public ReturnedCommandBean buildReturnedCommandBean(Command command){
        ReturnedCommandBean returnedCommandBean = new ReturnedCommandBean();
        returnedCommandBean.setDistance(command.getDistance());
        returnedCommandBean.setDuration(command.getDuration());
        returnedCommandBean.setStart(command.getStart());
        returnedCommandBean.setEnd(command.getEnd());
        returnedCommandBean.setStartTime(command.getStartTime());
        returnedCommandBean.setId(command.getId().toString());
        returnedCommandBean.setPrice(command.getFinalPrice());
        User user = userDao.findById(command.getIdUserFront());
        returnedCommandBean.setUserName(user.getFirstname() + " " + user.getLastname());
        List<Service> services = serviceDao.fetchAllForCommand(command.getId());
        returnedCommandBean.setServices(services.stream().map(Service::getName).collect(Collectors.toList()));
        return returnedCommandBean;
    }

}
