package hireNgo.webservices.api.users;

import com.coreoz.plume.jersey.errors.WsException;
import hireNgo.db.dao.UserDao;
import hireNgo.db.generated.User;
import hireNgo.utils.Utils;
import hireNgo.webservices.api.users.bean.UserBean;
import hireNgo.webservices.api.users.bean.UserType;
import hireNgo.webservices.exeptions.ProjectWsError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Api("Manage users web-services") //pour le swagger
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Singleton //cela veut dire qu'il ne peut y avoir qu'une seule instance de UserWs
public class UserWs {

    private final UserDao userDao; //notre DAO gère les appels à notre base de donnée

    @Inject
    public UserWs(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET //annotation REST => pour dire que ce endPoint (path) ne s'appelle que en GET (sinon on aura une 404)
    @Path("/all")
    @ApiOperation("Get all users (order by name)")
    public List<User> getAllUsers() {
        return userDao.fetchUserOrderByName();
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Get user by id)")
    public User getUserById(@PathParam("id") Long id) {
        if(id == null){
            throw new WsException(ProjectWsError.NO_ID_SPECIFIED); //on renverra cette erreur. On peut ensuite intérpréter l'erreur dans l'interface avec une notification personnalisée
        }
        User user = userDao.findById(id);
        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }
        return user;
    }

    @POST
    @Path("/create")
    @ApiOperation("Create New User")
    public User createUser(UserBean userBean) {
        //on utilise en paramètre un UserBean car utiliser l'entité ici c'est mal
        //en effet, l'interface qui appelle notre WebService n'a pas à manipuler nos entités (comme la classe User)
        checkData(userBean);

        User user = new User(); //on utilise l'entité (classe User) car c'est elle qui représentera l'objet qu'on insère en base
        user.setFirstname(userBean.getFirstname());
        user.setLastname(userBean.getLastname());
        user.setEmail(userBean.getEmail());
        user.setPassword(userBean.getPassword());
        user.setType(userBean.getType());
        return userDao.save(user); //sauvegarde en BDD notre utilisateur
    }

    @POST
    @Path("/update")
    @ApiOperation("UpdateUser User")
    public User updateUser(UserBean userBean){
        if (userBean == null) {
            throw new WsException(ProjectWsError.NO_USER);
        }
        //Userbean => l'utilisateur que j'envoie a l'api
        //UserDao => class qui communique avec ma base de donnée
        //User => Entité qui represente la donnée en base renvoyé par le Dao

        User user = userDao.findById(userBean.getId());

        if(user == null){
            throw new WsException(ProjectWsError.USER_NOT_FOUND);
        }

        checkData(userBean);


        user.setLastname(userBean.getLastname());
        user.setFirstname(userBean.getFirstname());
        user.setEmail(userBean.getEmail());
        user.setPassword(userBean.getPassword());
        user.setAddress(userBean.getAddress());
        user.setBirthdate(userBean.getBirthday());
        user.setCity(userBean.getCity());
        user.setPhone(userBean.getPhone());
        user.setCountry(userBean.getCountry());
        user.setPostalCode(userBean.getPostalCode());


        return userDao.save(user);


    }

    private void checkData(UserBean userBean){

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
        if(userDao.findByEmail(userBean.getEmail()) != null){
            throw new WsException(ProjectWsError.EMAIL_ALREADY_USED);
        }

    }

}
