package hireNgo.webservices.api.services;

import com.coreoz.plume.jersey.errors.WsException;
import hireNgo.db.dao.ServiceDao;
import hireNgo.db.dao.UserDao;
import hireNgo.db.generated.Service;
import hireNgo.db.generated.User;
import hireNgo.services.services.ServicesService;
import hireNgo.webservices.api.services.bean.ReturnedServiceBean;
import hireNgo.webservices.api.services.bean.ServicesBean;
import hireNgo.webservices.exeptions.ProjectWsError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Path("/services")
@Api("Manage services web-services") //pour le swagger
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Singleton //cela veut dire qu'il ne peut y avoir qu'une seule instance de ServicesWs
public class ServicesWs {

    private final UserDao userDao;
    private final ServiceDao serviceDao;
    private final ServicesService servicesService;

    @Inject
    public ServicesWs(UserDao userDao, ServiceDao serviceDao, ServicesService servicesService){
        this.userDao = userDao;
        this.serviceDao = serviceDao;
        this.servicesService = servicesService;
    }

    @POST
    @Path("/update")
    @ApiOperation("Get services by user email)")
    public List<Service> createServicesForUserEmail(ServicesBean servicesBean) {
        if(servicesBean == null || servicesBean.getServiceIds() == null || servicesBean.getServiceIds().size() == 0){
            throw new WsException(ProjectWsError.NO_SERVICES);
        }
        User user = userDao.findByEmail(servicesBean.getUserEmail());
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        List<Service> services = new ArrayList<>();
        for(String idService : servicesBean.getServiceIds()){
            Service service = serviceDao.findById(Long.parseLong(idService));
            if(service != null){
                serviceDao.addServiceToUserDriver(service, user);
                services.add(service);
            }
        }
        return services;
    }

    @POST
    @Path("/updateAccompanist")
    @ApiOperation("Get services by user email)")
    public List<Service> createServicesForUserAccompanistEmail(ServicesBean servicesBean) {
        if(servicesBean == null || servicesBean.getServiceIds() == null || servicesBean.getServiceIds().size() == 0){
            throw new WsException(ProjectWsError.NO_SERVICES);
        }
        User user = userDao.findByEmail(servicesBean.getUserEmail());
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        List<Service> services = new ArrayList<>();
        for(String idService : servicesBean.getServiceIds()){
            Service service = serviceDao.findById(Long.parseLong(idService));
            if(service != null){
                serviceDao.addServiceToUserAccompanist(service, user);
                services.add(service);
            }
        }
        return services;
    }

    @GET
    @Path("/{email}")
    @ApiOperation("Get services by user email)")
    public List<ReturnedServiceBean> getServicesByUserMail(@PathParam("email") String email) {
        if(email == null){
            throw new WsException(ProjectWsError.NO_EMAIL);
        }
        User user = userDao.findByEmail(email);
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        return serviceDao.fetchByUserId(user.getId()).stream().map(servicesService::buildReturnedServicerBean).collect(Collectors.toList());
    }

    @GET
    @Path("/available/{email}")
    @ApiOperation("Get services by user email)")
    public List<ReturnedServiceBean> getServicesAvailableForUser(@PathParam("email") String email) {
        if(email == null){
            throw new WsException(ProjectWsError.NO_EMAIL);
        }
        User user = userDao.findByEmail(email);
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        List<Service> alreadyHas = serviceDao.fetchAllForUser(user.getId());
        List<Service> all = serviceDao.findAll();
        List<Service> finalList = all.stream().filter(item -> !alreadyHas.contains(item)).collect(Collectors.toList());
        return finalList.stream().map(servicesService::buildReturnedServicerBean).collect(Collectors.toList());
    }

    @GET
    @Path("/accompanist/available/{email}")
    @ApiOperation("Get services by user email)")
    public List<ReturnedServiceBean> getMyServicesAvailableForAccompanist(@PathParam("email") String email) {
        if(email == null){
            throw new WsException(ProjectWsError.NO_EMAIL);
        }
        User user = userDao.findByEmail(email);
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        List<Service> alreadyHas = serviceDao.fetchAllServiceForAccompanist(user.getId());
        List<Service> all = serviceDao.fetchAllAvailableServiceForAccompanist();
        List<Service> finalList = all.stream().filter(item -> !alreadyHas.contains(item)).collect(Collectors.toList());
        return finalList.stream().map(servicesService::buildReturnedServicerBean).collect(Collectors.toList());
    }

    @GET
    @Path("/accompanist/{email}")
    @ApiOperation("Get services by user email)")
    public List<ReturnedServiceBean> getServicesAvailableForAccompanist(@PathParam("email") String email) {
        if(email == null){
            throw new WsException(ProjectWsError.NO_EMAIL);
        }
        User user = userDao.findByEmail(email);
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        return serviceDao.fetchAllServiceForAccompanist(user.getId()).stream().map(servicesService::buildReturnedServicerBean).collect(Collectors.toList());
    }

    @GET
    @Path("/accompanist")
    @ApiOperation("Get services by user email)")
    public List<Service> getServicesForAccompanists() {
        return serviceDao.fetchServicesByIsAccompanist(true);
    }

}
