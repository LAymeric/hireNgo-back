package hireNgo.db.generated;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QUser extends com.querydsl.sql.RelationalPathBase<User> {

    private static final long serialVersionUID = 1373385646;

    public static final QUser user = new QUser("user");

    public final StringPath email = createString("email");

    public final StringPath firstname = createString("firstname");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastname = createString("lastname");

    public final com.querydsl.sql.PrimaryKey<User> primary = createPrimaryKey(id);

    public QUser(String variable) {
        super(User.class, forVariable(variable), "null", "user");
        addMetadata();
    }

    public QUser(String variable, String schema, String table) {
        super(User.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QUser(String variable, String schema) {
        super(User.class, forVariable(variable), schema, "user");
        addMetadata();
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata(), "null", "user");
        addMetadata();
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata, "null", "user");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(email, ColumnMetadata.named("email").withIndex(4).ofType(Types.VARCHAR).withSize(45).notNull());
        addMetadata(firstname, ColumnMetadata.named("firstname").withIndex(3).ofType(Types.VARCHAR).withSize(45).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(lastname, ColumnMetadata.named("lastname").withIndex(2).ofType(Types.VARCHAR).withSize(45).notNull());
    }

}

