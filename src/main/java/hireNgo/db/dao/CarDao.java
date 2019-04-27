package hireNgo.db.dao;

import com.coreoz.plume.db.querydsl.crud.CrudDaoQuerydsl;
import com.coreoz.plume.db.querydsl.transaction.TransactionManagerQuerydsl;
import hireNgo.db.generated.Car;
import hireNgo.db.generated.QCar;
import javax.inject.Inject;

public class CarDao extends CrudDaoQuerydsl<Car> {

    @Inject
    public CarDao(TransactionManagerQuerydsl transactionManager){
        super(transactionManager, QCar.car);
    }

    public Car fetchFirstCarFromUserId(Long userId){
        return selectFrom()
                .where(QCar.car.idUser.eq(userId))
                .fetchFirst();
    }

}