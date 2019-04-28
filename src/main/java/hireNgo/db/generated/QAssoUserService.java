package hireNgo.db.generated;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAssoUserService is a Querydsl query type for AssoUserService
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAssoUserService extends com.querydsl.sql.RelationalPathBase<AssoUserService> {

    private static final long serialVersionUID = -2114666055;

    public static final QAssoUserService assoUserService = new QAssoUserService("asso_user_service");

    public final NumberPath<Long> idService = createNumber("idService", Long.class);

    public final NumberPath<Long> idUser = createNumber("idUser", Long.class);

    public QAssoUserService(String variable) {
        super(AssoUserService.class, forVariable(variable), "null", "asso_user_service");
        addMetadata();
    }

    public QAssoUserService(String variable, String schema, String table) {
        super(AssoUserService.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAssoUserService(String variable, String schema) {
        super(AssoUserService.class, forVariable(variable), schema, "asso_user_service");
        addMetadata();
    }

    public QAssoUserService(Path<? extends AssoUserService> path) {
        super(path.getType(), path.getMetadata(), "null", "asso_user_service");
        addMetadata();
    }

    public QAssoUserService(PathMetadata metadata) {
        super(AssoUserService.class, metadata, "null", "asso_user_service");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(idService, ColumnMetadata.named("id_service").withIndex(2).ofType(Types.BIGINT).withSize(19));
        addMetadata(idUser, ColumnMetadata.named("id_user").withIndex(1).ofType(Types.BIGINT).withSize(19));
    }

}

