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

    public final StringPath address = createString("address");

    public final StringPath birthdate = createString("birthdate");

    public final StringPath city = createString("city");

    public final StringPath country = createString("country");

    public final StringPath email = createString("email");

    public final StringPath firstname = createString("firstname");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isPremium = createBoolean("isPremium");

    public final StringPath lastname = createString("lastname");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final StringPath postalCode = createString("postalCode");

    public final StringPath type = createString("type");

    public final com.querydsl.sql.PrimaryKey<User> primary = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<AssoUserService> _assoUserServiceIbfk1 = createInvForeignKey(id, "id_user");

    public final com.querydsl.sql.ForeignKey<Command> _commandIbfk2 = createInvForeignKey(id, "id_user_front");

    public final com.querydsl.sql.ForeignKey<Command> _commandIbfk1 = createInvForeignKey(id, "id_user_driver");

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
        addMetadata(address, ColumnMetadata.named("address").withIndex(8).ofType(Types.VARCHAR).withSize(50));
        addMetadata(birthdate, ColumnMetadata.named("birthdate").withIndex(6).ofType(Types.VARCHAR).withSize(50));
        addMetadata(city, ColumnMetadata.named("city").withIndex(9).ofType(Types.VARCHAR).withSize(50));
        addMetadata(country, ColumnMetadata.named("country").withIndex(11).ofType(Types.VARCHAR).withSize(50));
        addMetadata(email, ColumnMetadata.named("email").withIndex(4).ofType(Types.VARCHAR).withSize(50));
        addMetadata(firstname, ColumnMetadata.named("firstname").withIndex(2).ofType(Types.VARCHAR).withSize(50));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(isPremium, ColumnMetadata.named("is_premium").withIndex(13).ofType(Types.BIT).withSize(3));
        addMetadata(lastname, ColumnMetadata.named("lastname").withIndex(3).ofType(Types.VARCHAR).withSize(50));
        addMetadata(password, ColumnMetadata.named("password").withIndex(7).ofType(Types.VARCHAR).withSize(50));
        addMetadata(phone, ColumnMetadata.named("phone").withIndex(5).ofType(Types.VARCHAR).withSize(50));
        addMetadata(postalCode, ColumnMetadata.named("postal_code").withIndex(10).ofType(Types.VARCHAR).withSize(50));
        addMetadata(type, ColumnMetadata.named("type").withIndex(12).ofType(Types.VARCHAR).withSize(50));
    }

}

