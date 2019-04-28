package hireNgo.db.dao;

import com.coreoz.plume.db.querydsl.crud.CrudDaoQuerydsl;
import com.coreoz.plume.db.querydsl.transaction.TransactionManagerQuerydsl;
import hireNgo.db.generated.Command;
import hireNgo.db.generated.QCommand;

import javax.inject.Inject;

public class CommandDao extends CrudDaoQuerydsl<Command> {
    @Inject
    public CommandDao(TransactionManagerQuerydsl transactionManager){
        super(transactionManager, QCommand.command);
    }

}
