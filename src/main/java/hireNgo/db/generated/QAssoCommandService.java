package hireNgo.db.generated;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAssoCommandService is a Querydsl query type for AssoCommandService
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAssoCommandService extends com.querydsl.sql.RelationalPathBase<AssoCommandService> {

    private static final long serialVersionUID = 2109207867;

    public static final QAssoCommandService assoCommandService = new QAssoCommandService("asso_command_service");

    public final NumberPath<Long> idCommand = createNumber("idCommand", Long.class);

    public final NumberPath<Long> idService = createNumber("idService", Long.class);

    public QAssoCommandService(String variable) {
        super(AssoCommandService.class, forVariable(variable), "null", "asso_command_service");
        addMetadata();
    }

    public QAssoCommandService(String variable, String schema, String table) {
        super(AssoCommandService.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAssoCommandService(String variable, String schema) {
        super(AssoCommandService.class, forVariable(variable), schema, "asso_command_service");
        addMetadata();
    }

    public QAssoCommandService(Path<? extends AssoCommandService> path) {
        super(path.getType(), path.getMetadata(), "null", "asso_command_service");
        addMetadata();
    }

    public QAssoCommandService(PathMetadata metadata) {
        super(AssoCommandService.class, metadata, "null", "asso_command_service");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(idCommand, ColumnMetadata.named("id_command").withIndex(1).ofType(Types.BIGINT).withSize(19));
        addMetadata(idService, ColumnMetadata.named("id_service").withIndex(2).ofType(Types.BIGINT).withSize(19));
    }

}

