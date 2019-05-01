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

    public List<Service> fetchAllForUser(Long idUser){
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
                .from(QAssoAccompanistUserService.assoAccompanistUserService)
                .join(QAssoAccompanistUserService.assoAccompanistUserService)
                .where(QService.service.isAccompanist.eq(true))
                .where(QAssoAccompanistUserService.assoAccompanistUserService.idAccompanistUser.eq(userId))
                .where(QService.service.id.eq(QAssoAccompanistUserService.assoAccompanistUserService.idService))
                .fetchFirst();
    }

    public List<Service> fetchServiceForAccompanist(Long userId){
        return selectFrom()
                .from(QAssoAccompanistUserService.assoAccompanistUserService)
                .join(QAssoAccompanistUserService.assoAccompanistUserService)
                .where(QService.service.isAccompanist.eq(true))
                .where(QAssoAccompanistUserService.assoAccompanistUserService.idAccompanistUser.eq(userId))
                .where(QService.service.id.eq(QAssoAccompanistUserService.assoAccompanistUserService.idService))
                .fetch();
    }
    public List<Service> fetchServiceForAccompanistAndThisCommand(Long userId, Long commandId){
        return selectFrom()
                .from(QAssoCommandService.assoCommandService)
                .join(QAssoCommandService.assoCommandService)
                .join(QAssoAccompanistUserService.assoAccompanistUserService)
                .where(QAssoCommandService.assoCommandService.idCommand.eq(commandId))
                .where(QAssoAccompanistUserService.assoAccompanistUserService.idAccompanistUser.eq(userId))
                .where(QAssoAccompanistUserService.assoAccompanistUserService.idService.eq(QService.service.id))
                .where(QService.service.id.eq(QAssoCommandService.assoCommandService.idService))
                .fetch();
    }
    public List<Service> fetchAllForCommand(Long commandId){
        return selectFrom()
                .from(QAssoCommandService.assoCommandService)
                .join(QAssoCommandService.assoCommandService)
                .where(QAssoCommandService.assoCommandService.idCommand.eq(commandId))
                .where(QService.service.id.eq(QAssoCommandService.assoCommandService.idService))
                .fetch();
    }

    public void addServiceToUserAccompanist(Service service, User user){
        transactionManager.insert(QAssoAccompanistUserService.assoAccompanistUserService)
                .columns(QAssoAccompanistUserService.assoAccompanistUserService.idService, QAssoAccompanistUserService.assoAccompanistUserService.idAccompanistUser)
                .values(service.getId(), user.getId()).execute();
    }

    public void addServiceToUserDriver(Service service, User user){
        transactionManager.insert(QAssoAccompanistUserService.assoAccompanistUserService)
                .columns(QAssoAccompanistUserService.assoAccompanistUserService.idService, QAssoAccompanistUserService.assoAccompanistUserService.idAccompanistUser)
                .values(service.getId(), user.getId()).execute();
    }

}