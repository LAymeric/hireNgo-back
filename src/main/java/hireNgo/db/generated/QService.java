package hireNgo.db.generated;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QService is a Querydsl query type for Service
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QService extends com.querydsl.sql.RelationalPathBase<Service> {

    private static final long serialVersionUID = -1490268174;

    public static final QService service = new QService("service");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAccompanist = createBoolean("isAccompanist");

    public final StringPath name = createString("name");

    public final StringPath price = createString("price");

    public final StringPath productLibelle = createString("productLibelle");

    public final com.querydsl.sql.PrimaryKey<Service> primary = createPrimaryKey(id);

    public QService(String variable) {
        super(Service.class, forVariable(variable), "null", "service");
        addMetadata();
    }

    public QService(String variable, String schema, String table) {
        super(Service.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QService(String variable, String schema) {
        super(Service.class, forVariable(variable), schema, "service");
        addMetadata();
    }

    public QService(Path<? extends Service> path) {
        super(path.getType(), path.getMetadata(), "null", "service");
        addMetadata();
    }

    public QService(PathMetadata metadata) {
        super(Service.class, metadata, "null", "service");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(isAccompanist, ColumnMetadata.named("isAccompanist").withIndex(5).ofType(Types.BIT).withSize(3).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(2).ofType(Types.VARCHAR).withSize(50));
        addMetadata(price, ColumnMetadata.named("price").withIndex(3).ofType(Types.VARCHAR).withSize(50));
        addMetadata(productLibelle, ColumnMetadata.named("productLibelle").withIndex(4).ofType(Types.VARCHAR).withSize(50));
    }

}

