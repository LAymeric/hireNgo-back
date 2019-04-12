package hireNgo.webservices.api.users;

import hireNgo.db.dao.UserDao;
import hireNgo.db.generated.User;
import hireNgo.services.configuration.ConfigurationService;
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
	
}
