package hireNgo.db.generated;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QAccompanistType is a Querydsl query type for AccompanistType
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAccompanistType extends com.querydsl.sql.RelationalPathBase<AccompanistType> {

    private static final long serialVersionUID = 1201645379;

    public static final QAccompanistType accompanistType = new QAccompanistType("accompanist_type");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final com.querydsl.sql.PrimaryKey<AccompanistType> primary = createPrimaryKey(id);

    public QAccompanistType(String variable) {
        super(AccompanistType.class, forVariable(variable), "null", "accompanist_type");
        addMetadata();
    }

    public QAccompanistType(String variable, String schema, String table) {
        super(AccompanistType.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAccompanistType(String variable, String schema) {
        super(AccompanistType.class, forVariable(variable), schema, "accompanist_type");
        addMetadata();
    }

    public QAccompanistType(Path<? extends AccompanistType> path) {
        super(path.getType(), path.getMetadata(), "null", "accompanist_type");
        addMetadata();
    }

    public QAccompanistType(PathMetadata metadata) {
        super(AccompanistType.class, metadata, "null", "accompanist_type");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(2).ofType(Types.VARCHAR).withSize(255).notNull());
    }

}

