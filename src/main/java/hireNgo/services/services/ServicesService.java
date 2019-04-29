package hireNgo.services.services;

import hireNgo.db.dao.CarDao;
import hireNgo.db.dao.ServiceDao;
import hireNgo.db.dao.UserDao;
import hireNgo.db.generated.Car;
import hireNgo.db.generated.Service;
import hireNgo.db.generated.User;
import hireNgo.webservices.api.services.bean.ReturnedServiceBean;
import hireNgo.webservices.api.users.bean.ReturnedUserBean;
import hireNgo.webservices.api.users.bean.UserType;
import org.apache.commons.codec.binary.Base64;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ServicesService {

	private final ServiceDao serviceDao;

	@Inject
	public ServicesService(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	public ReturnedServiceBean buildReturnedServicerBean(Service service){
		ReturnedServiceBean serviceBean = new ReturnedServiceBean();
		serviceBean.setId(service.getId().toString());
		serviceBean.setName(service.getName());
		serviceBean.setBase64Image(Base64.encodeBase64String(service.getPicture()));
		serviceBean.setQuantity(service.getQuantity().toString());
		serviceBean.setPrice(service.getPrice() + " €");
		return serviceBean;
	}


}

