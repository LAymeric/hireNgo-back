package hireNgo.db.dao;

import com.coreoz.plume.db.querydsl.crud.CrudDaoQuerydsl;
import com.coreoz.plume.db.querydsl.transaction.TransactionManagerQuerydsl;
import hireNgo.db.generated.*;
import hireNgo.webservices.api.users.bean.CommandStatus;

import javax.inject.Inject;
import java.util.List;

public class AssoCommandServiceDao extends CrudDaoQuerydsl<AssoCommandService> {
    @Inject
    public AssoCommandServiceDao(TransactionManagerQuerydsl transactionManager){
        super(transactionManager, QAssoCommandService.assoCommandService);
    }

    public void addAccopanistToService(Long idAccompanist, Long idCommand, Long idService){
        AssoCommandService assoCommandService = selectFrom()
                .where(QAssoCommandService.assoCommandService.idCommand.eq(idCommand))
                .where(QAssoCommandService.assoCommandService.idService.eq(idService))
                .fetchFirst();
        assoCommandService.setIdUserAccompanist(idAccompanist);
        save(assoCommandService);
    }

}
