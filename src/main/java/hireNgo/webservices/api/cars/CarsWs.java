package hireNgo.webservices.api.cars;

import com.coreoz.plume.jersey.errors.WsException;
import hireNgo.db.dao.CarDao;
import hireNgo.db.dao.UserDao;
import hireNgo.db.generated.Car;
import hireNgo.db.generated.User;
import hireNgo.webservices.api.cars.bean.CarBean;
import hireNgo.webservices.exeptions.ProjectWsError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/cars")
@Api("Manage cars web-services") //pour le swagger
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Singleton //cela veut dire qu'il ne peut y avoir qu'une seule instance de CarsWs
public class CarsWs {

    private final CarDao carDao;
    private final UserDao userDao;

    @Inject
    public CarsWs(CarDao carDao, UserDao userDao){
        this.carDao = carDao;
        this.userDao = userDao;
    }

    @POST
    @Path("/create")
    @ApiOperation("Get services by user email)")
    public Car getUserById(CarBean car) {
        if(car == null){
            throw new WsException(ProjectWsError.NO_CAR);
        }
        User user = userDao.findByEmail(car.getUserEmail());
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        Car newCar = new Car();
        newCar.setBrand(car.getBrand());
        newCar.setName(car.getName());
        newCar.setDescritpion(car.getDescription());
        newCar.setIdUser(user.getId());
        if(car.getBase64() != null){
            byte[] byteArray = Base64.decodeBase64(car.getBase64().getBytes());
            newCar.setImage(byteArray);
        }
        return carDao.save(newCar);
    }

}
