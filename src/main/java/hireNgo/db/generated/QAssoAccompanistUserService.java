package hireNgo.db.generated;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAssoAccompanistUserService is a Querydsl query type for AssoAccompanistUserService
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAssoAccompanistUserService extends com.querydsl.sql.RelationalPathBase<AssoAccompanistUserService> {

    private static final long serialVersionUID = 1043283567;

    public static final QAssoAccompanistUserService assoAccompanistUserService = new QAssoAccompanistUserService("asso_accompanist_user_service");

    public final NumberPath<Long> idAccompanistUser = createNumber("idAccompanistUser", Long.class);

    public final NumberPath<Long> idService = createNumber("idService", Long.class);

    public QAssoAccompanistUserService(String variable) {
        super(AssoAccompanistUserService.class, forVariable(variable), "null", "asso_accompanist_user_service");
        addMetadata();
    }

    public QAssoAccompanistUserService(String variable, String schema, String table) {
        super(AssoAccompanistUserService.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAssoAccompanistUserService(String variable, String schema) {
        super(AssoAccompanistUserService.class, forVariable(variable), schema, "asso_accompanist_user_service");
        addMetadata();
    }

    public QAssoAccompanistUserService(Path<? extends AssoAccompanistUserService> path) {
        super(path.getType(), path.getMetadata(), "null", "asso_accompanist_user_service");
        addMetadata();
    }

    public QAssoAccompanistUserService(PathMetadata metadata) {
        super(AssoAccompanistUserService.class, metadata, "null", "asso_accompanist_user_service");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(idAccompanistUser, ColumnMetadata.named("id_accompanist_user").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(idService, ColumnMetadata.named("id_service").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
    }

}

