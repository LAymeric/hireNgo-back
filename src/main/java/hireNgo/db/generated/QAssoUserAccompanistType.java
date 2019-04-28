package hireNgo.db.generated;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAssoUserAccompanistType is a Querydsl query type for AssoUserAccompanistType
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAssoUserAccompanistType extends com.querydsl.sql.RelationalPathBase<AssoUserAccompanistType> {

    private static final long serialVersionUID = 1352229386;

    public static final QAssoUserAccompanistType assoUserAccompanistType = new QAssoUserAccompanistType("asso_user_accompanist_type");

    public final NumberPath<Long> idAccompanistType = createNumber("idAccompanistType", Long.class);

    public final NumberPath<Long> idUser = createNumber("idUser", Long.class);

    public QAssoUserAccompanistType(String variable) {
        super(AssoUserAccompanistType.class, forVariable(variable), "null", "asso_user_accompanist_type");
        addMetadata();
    }

    public QAssoUserAccompanistType(String variable, String schema, String table) {
        super(AssoUserAccompanistType.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAssoUserAccompanistType(String variable, String schema) {
        super(AssoUserAccompanistType.class, forVariable(variable), schema, "asso_user_accompanist_type");
        addMetadata();
    }

    public QAssoUserAccompanistType(Path<? extends AssoUserAccompanistType> path) {
        super(path.getType(), path.getMetadata(), "null", "asso_user_accompanist_type");
        addMetadata();
    }

    public QAssoUserAccompanistType(PathMetadata metadata) {
        super(AssoUserAccompanistType.class, metadata, "null", "asso_user_accompanist_type");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(idAccompanistType, ColumnMetadata.named("id_accompanist_type").withIndex(2).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(idUser, ColumnMetadata.named("id_user").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
    }

}

