package hireNgo.db.dao;

import com.coreoz.plume.db.querydsl.crud.CrudDaoQuerydsl;
import com.coreoz.plume.db.querydsl.transaction.TransactionManagerQuerydsl;
import hireNgo.db.generated.Command;
import hireNgo.db.generated.QCommand;
import hireNgo.webservices.api.users.bean.CommandStatus;

import javax.inject.Inject;
import java.util.List;

public class CommandDao extends CrudDaoQuerydsl<Command> {
    @Inject
    public CommandDao(TransactionManagerQuerydsl transactionManager){
        super(transactionManager, QCommand.command);
    }

    public List<Command> findAllByStatus(CommandStatus commandStatus){
        return selectFrom()
                .where(QCommand.command.status.eq(commandStatus.name()))
                .fetch();
    }

    public List<Command> findAllByStatusAndUserDriver(CommandStatus commandStatus, Long idDriver){
        return selectFrom()
                .where(QCommand.command.status.eq(commandStatus.name()))
                .where(QCommand.command.idUserDriver.eq(idDriver))
                .fetch();

    }
}
