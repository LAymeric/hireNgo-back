package hireNgo.services.user;

import hireNgo.db.dao.CarDao;
import hireNgo.db.dao.ServiceDao;
import hireNgo.db.dao.UserDao;
import hireNgo.db.generated.Car;
import hireNgo.db.generated.Service;
import hireNgo.db.generated.User;
import hireNgo.webservices.api.users.bean.ReturnedUserBean;
import hireNgo.webservices.api.users.bean.UserType;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserService {

	private final UserDao userDao;
	private final CarDao carDao;
	private final ServiceDao serviceDao;

	@Inject
	public UserService(UserDao userDao, CarDao carDao, ServiceDao serviceDao) {
		this.userDao = userDao;
		this.carDao = carDao;
		this.serviceDao = serviceDao;
	}

	public ReturnedUserBean buildReturnedUserBean(User user){
		boolean hasFilledData = false;

		//on va vérifier si il a renseigné ses données (si c'est un chauffeur, les données de sa voiture, si un accompagnant son service type)
		if(user.getType().equals(UserType.DRIVER.name())){
			Car car = carDao.fetchFirstCarFromUserId(user.getId());
			if(car != null && car.getBrand() != null && car.getDescritpion() != null && car.getName() != null){
				hasFilledData = true;
			}
		}else if(user.getType().equals(UserType.ACCOMPANIST.name())){
			Service service = serviceDao.fetchFirstServiceForAccompanist(user.getId());
			if(service != null){
				hasFilledData = true;
			}
		}

		return new ReturnedUserBean(user.getEmail(), user.getFirstname(), user.getType(), hasFilledData);
	}


}

