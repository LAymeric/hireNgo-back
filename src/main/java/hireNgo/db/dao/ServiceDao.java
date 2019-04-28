package hireNgo.db.dao;

import com.coreoz.plume.db.querydsl.crud.CrudDaoQuerydsl;
import com.coreoz.plume.db.querydsl.transaction.TransactionManagerQuerydsl;
import hireNgo.db.generated.*;

import javax.inject.Inject;
import java.util.List;

public class ServiceDao extends CrudDaoQuerydsl<Service> {

    @Inject
    public ServiceDao(TransactionManagerQuerydsl transactionManager){
        super(transactionManager, QService.service);
    }

    public List<Service> fetchByUserId(Long idUser){
        return selectFrom()
                .from(QAssoUserService.assoUserService)
                .join(QAssoUserService.assoUserService)
                .where(QAssoUserService.assoUserService.idUser.eq(idUser))
                .where(QService.service.id.eq(QAssoUserService.assoUserService.idService))
                .fetch();
    }

    public List<Service> fetchServicesByIsAccompanist(boolean isAccompanist){
        return selectFrom()
                .where(QService.service.isAccompanist.eq(isAccompanist))
                .fetch();
    }
    public Service fetchFirstServiceForAccompanist(Long userId){
        return selectFrom()
                .from(QAssoUserService.assoUserService)
                .join(QAssoUserService.assoUserService)
                .where(QService.service.isAccompanist.eq(true))
                .where(QAssoUserService.assoUserService.idUser.eq(userId))
                .where(QService.service.id.eq(QAssoUserService.assoUserService.idService))
                .fetchFirst();
    }


    public void addServiceToUserAccompanist(Service service, User user){
        transactionManager.insert(QAssoUserService.assoUserService)
                .columns(QAssoUserService.assoUserService.idService, QAssoUserService.assoUserService.idUser)
                .values(service.getId(), user.getId());
    }

}