package hireNgo.webservices.api.services;

import com.coreoz.plume.jersey.errors.WsException;
import hireNgo.db.dao.ServiceDao;
import hireNgo.db.dao.UserDao;
import hireNgo.db.generated.Service;
import hireNgo.db.generated.User;
import hireNgo.webservices.exeptions.ProjectWsError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/services")
@Api("Manage services web-services") //pour le swagger
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Singleton //cela veut dire qu'il ne peut y avoir qu'une seule instance de ServicesWs
public class ServicesWs {

    private final UserDao userDao;
    private final ServiceDao serviceDao;

    @Inject
    public ServicesWs(UserDao userDao, ServiceDao serviceDao){
        this.userDao = userDao;
        this.serviceDao = serviceDao;
    }

    @GET
    @Path("/{email}")
    @ApiOperation("Get services by user email)")
    public List<Service> getServicesByUserMail(@PathParam("email") String email) {
        if(email == null){
            throw new WsException(ProjectWsError.NO_EMAIL);
        }
        User user = userDao.findByEmail(email);
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        return serviceDao.fetchByUserId(user.getId());
    }

    @GET
    @Path("/accompanist")
    @ApiOperation("Get services by user email)")
    public List<Service> getServicesForAccompanists() {
        return serviceDao.fetchServicesByIsAccompanist(true);
    }

}
