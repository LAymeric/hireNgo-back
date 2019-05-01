package hireNgo.db.dao;

import com.coreoz.plume.db.querydsl.crud.CrudDaoQuerydsl;
import com.coreoz.plume.db.querydsl.transaction.TransactionManagerQuerydsl;
import hireNgo.db.generated.*;
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

    public List<Command> findWithCorrespondingServiceWithNoValidation(Service service){
        return selectFrom()
                .from(QAssoCommandService.assoCommandService)
                .join(QAssoCommandService.assoCommandService)
                .where(QAssoCommandService.assoCommandService.idService.eq(service.getId()))
                .where(QAssoCommandService.assoCommandService.idCommand.eq(QCommand.command.id))
                .where(QAssoCommandService.assoCommandService.idUserAccompanist.isNull())
                .where(QCommand.command.status.ne(CommandStatus.FINISHED.name()))
                .where(QCommand.command.status.ne(CommandStatus.PAID.name()))
                .where(QCommand.command.status.ne(CommandStatus.INCOMPLETE.name()))
                .fetch();
    }

    public List<Command> findAllByStatusAndUserDriver(CommandStatus commandStatus, Long idDriver){
        return selectFrom()
                .where(QCommand.command.status.eq(commandStatus.name()))
                .where(QCommand.command.idUserDriver.eq(idDriver))
                .fetch();

    }
    public List<Command> findAllByStatusAndUserAccompanist(CommandStatus commandStatus, Long idAccompanist){
        return selectFrom()
                .from(QAssoCommandService.assoCommandService)
                .join(QAssoCommandService.assoCommandService)
                .where(QAssoCommandService.assoCommandService.idUserAccompanist.eq(idAccompanist))
                .where(QAssoCommandService.assoCommandService.idCommand.eq(QCommand.command.id))
                .where(QCommand.command.status.eq(commandStatus.name()))
                .fetch();

    }
    public List<Command> findAllAndUserAccompanist(Long idUserAccompanist){
        return selectFrom()
                .from(QAssoCommandService.assoCommandService)
                .join(QAssoCommandService.assoCommandService)
                .where(QAssoCommandService.assoCommandService.idUserAccompanist.eq(idUserAccompanist))
                .where(QAssoCommandService.assoCommandService.idCommand.eq(QCommand.command.id))
                .fetch();

    }
}
