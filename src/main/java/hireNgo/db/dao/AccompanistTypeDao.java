package hireNgo.db.dao;

import com.coreoz.plume.db.querydsl.crud.CrudDaoQuerydsl;
import com.coreoz.plume.db.querydsl.transaction.TransactionManagerQuerydsl;
import hireNgo.db.generated.*;

import javax.inject.Inject;

public class AccompanistTypeDao extends CrudDaoQuerydsl<AccompanistType> {

    @Inject
    public AccompanistTypeDao(TransactionManagerQuerydsl transactionManager){
        super(transactionManager, QAccompanistType.accompanistType);
    }

    public AccompanistType fetchFirstAccompanistTypeFromUserId(Long userId){
        return selectFrom()
                .from(QAssoUserAccompanistType.assoUserAccompanistType)
                .join(QAssoUserAccompanistType.assoUserAccompanistType)
                .where(QAssoUserAccompanistType.assoUserAccompanistType.idUser.eq(userId))
                .where(QAccompanistType.accompanistType.id.eq(QAssoUserAccompanistType.assoUserAccompanistType.idAccompanistType))
                .fetchFirst();
    }

}