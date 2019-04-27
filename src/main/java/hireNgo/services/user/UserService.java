package hireNgo.services.user;

import hireNgo.db.dao.AccompanistTypeDao;
import hireNgo.db.dao.CarDao;
import hireNgo.db.dao.UserDao;
import hireNgo.db.generated.AccompanistType;
import hireNgo.db.generated.Car;
import hireNgo.db.generated.User;
import hireNgo.webservices.api.users.bean.ReturnedUserBean;
import hireNgo.webservices.api.users.bean.UserType;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserService {

	private final UserDao userDao;
	private final AccompanistTypeDao accompanistTypeDao;
	private final CarDao carDao;

	@Inject
	public UserService(UserDao userDao, AccompanistTypeDao accompanistTypeDao, CarDao carDao) {
		this.userDao = userDao;
		this.accompanistTypeDao = accompanistTypeDao;
		this.carDao = carDao;
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
			AccompanistType accompanistType = accompanistTypeDao.fetchFirstAccompanistTypeFromUserId(user.getId());
			if(accompanistType != null && accompanistType.getName() != null){
				hasFilledData = true;
			}
		}

		return new ReturnedUserBean(user.getEmail(), user.getFirstname(), user.getType(), hasFilledData);
	}


}

