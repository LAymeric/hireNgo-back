package hireNgo.db.dao;

import com.coreoz.plume.db.querydsl.crud.CrudDaoQuerydsl;
import com.coreoz.plume.db.querydsl.transaction.TransactionManagerQuerydsl;
import hireNgo.db.generated.QAssoUserService;
import hireNgo.db.generated.QService;
import hireNgo.db.generated.QUser;
import hireNgo.db.generated.Service;

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

}