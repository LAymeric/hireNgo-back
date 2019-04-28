package hireNgo.db.generated;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QCar is a Querydsl query type for Car
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCar extends com.querydsl.sql.RelationalPathBase<Car> {

    private static final long serialVersionUID = 1014116241;

    public static final QCar car = new QCar("car");

    public final StringPath brand = createString("brand");

    public final StringPath descritpion = createString("descritpion");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idUser = createNumber("idUser", Long.class);

    public final SimplePath<byte[]> image = createSimple("image", byte[].class);

    public final StringPath name = createString("name");

    public final com.querydsl.sql.PrimaryKey<Car> primary = createPrimaryKey(id);

    public QCar(String variable) {
        super(Car.class, forVariable(variable), "null", "car");
        addMetadata();
    }

    public QCar(String variable, String schema, String table) {
        super(Car.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCar(String variable, String schema) {
        super(Car.class, forVariable(variable), schema, "car");
        addMetadata();
    }

    public QCar(Path<? extends Car> path) {
        super(path.getType(), path.getMetadata(), "null", "car");
        addMetadata();
    }

    public QCar(PathMetadata metadata) {
        super(Car.class, metadata, "null", "car");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(brand, ColumnMetadata.named("brand").withIndex(3).ofType(Types.VARCHAR).withSize(45).notNull());
        addMetadata(descritpion, ColumnMetadata.named("descritpion").withIndex(4).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(19).notNull());
        addMetadata(idUser, ColumnMetadata.named("id_user").withIndex(5).ofType(Types.BIGINT).withSize(19));
        addMetadata(image, ColumnMetadata.named("image").withIndex(6).ofType(Types.LONGVARBINARY).withSize(65535));
        addMetadata(name, ColumnMetadata.named("name").withIndex(2).ofType(Types.VARCHAR).withSize(45).notNull());
    }

}

