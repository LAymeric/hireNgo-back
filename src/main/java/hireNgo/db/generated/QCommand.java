package hireNgo.db.generated;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QCommand is a Querydsl query type for Command
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCommand extends com.querydsl.sql.RelationalPathBase<Command> {

    private static final long serialVersionUID = 1770940552;

    public static final QCommand command = new QCommand("command");

    public final StringPath distance = createString("distance");

    public final StringPath duration = createString("duration");

    public final StringPath end = createString("end");

    public final StringPath endTime = createString("endTime");

    public final StringPath finalPrice = createString("finalPrice");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idUserDriver = createNumber("idUserDriver", Long.class);

    public final NumberPath<Long> idUserFront = createNumber("idUserFront", Long.class);

    public final StringPath start = createString("start");

    public final StringPath startTime = createString("startTime");

    public final StringPath status = createString("status");

    public final com.querydsl.sql.PrimaryKey<Command> primary = createPrimaryKey(id);

    public QCommand(String variable) {
        super(Command.class, forVariable(variable), "null", "command");
        addMetadata();
    }

    public QCommand(String variable, String schema, String table) {
        super(Command.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCommand(String variable, String schema) {
        super(Command.class, forVariable(variable), schema, "command");
        addMetadata();
    }

    public QCommand(Path<? extends Command> path) {
        super(path.getType(), path.getMetadata(), "null", "command");
        addMetadata();
    }

    public QCommand(PathMetadata metadata) {
        super(Command.class, metadata, "null", "command");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(distance, ColumnMetadata.named("distance").withIndex(8).ofType(Types.VARCHAR).withSize(50));
        addMetadata(duration, ColumnMetadata.named("duration").withIndex(9).ofType(Types.VARCHAR).withSize(50));
        addMetadata(end, ColumnMetadata.named("end").withIndex(5).ofType(Types.VARCHAR).withSize(50));
        addMetadata(endTime, ColumnMetadata.named("end_time").withIndex(7).ofType(Types.VARCHAR).withSize(50));
        addMetadata(finalPrice, ColumnMetadata.named("final_price").withIndex(11).ofType(Types.VARCHAR).withSize(50));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(idUserDriver, ColumnMetadata.named("id_user_driver").withIndex(2).ofType(Types.BIGINT).withSize(19));
        addMetadata(idUserFront, ColumnMetadata.named("id_user_front").withIndex(3).ofType(Types.BIGINT).withSize(19));
        addMetadata(start, ColumnMetadata.named("start").withIndex(4).ofType(Types.VARCHAR).withSize(50));
        addMetadata(startTime, ColumnMetadata.named("start_time").withIndex(6).ofType(Types.VARCHAR).withSize(50));
        addMetadata(status, ColumnMetadata.named("status").withIndex(10).ofType(Types.VARCHAR).withSize(50));
    }

}

