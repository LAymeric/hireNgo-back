package hireNgo.webservices.api.users;

import com.coreoz.plume.jersey.errors.WsException;
import hireNgo.db.dao.UserDao;
import hireNgo.db.generated.User;
import hireNgo.utils.Utils;
import hireNgo.webservices.api.users.bean.UserBean;
import hireNgo.webservices.exeptions.ProjectWsError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Api("Manage users web-services")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class UserWs {

    private final UserDao userDao;

    @Inject
    public UserWs(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    @Path("/all")
    @ApiOperation("Get all users (order by name)")
    public List<User> getAllUsers() {
        return userDao.fetchUserOrderByName();
    }

    @POST
    @Path("/create")
    @ApiOperation("Create New User")
    public User createUser(UserBean userBean) {
        if (userBean == null) {
            throw new WsException(ProjectWsError.NO_USER);
        }
        if (userBean.getFirstname() == null) {
            throw new WsException(ProjectWsError.WRONG_FIRSTNAME);
        }
        if (userBean.getLastname() == null) {
            throw new WsException(ProjectWsError.WRONG_LASTNAME);
        }
        if (userBean.getEmail() == null) {
            throw new WsException(ProjectWsError.WRONG_EMAIL);
        }
        if(!Utils.validateEmail(userBean.getEmail())){
            throw new WsException(ProjectWsError.EMAIL_INVALID);
        }
        User user = new User();
        user.setFirstname(userBean.getFirstname());
        user.setLastname(userBean.getLastname());
        user.setEmail(userBean.getEmail());
        return userDao.save(user);
    }

}
