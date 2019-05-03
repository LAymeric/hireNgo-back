package hireNgo.db.dao;

import com.coreoz.plume.db.querydsl.crud.CrudDaoQuerydsl;
import com.coreoz.plume.db.querydsl.transaction.TransactionManagerQuerydsl;
import hireNgo.db.generated.*;
import hireNgo.webservices.api.users.bean.UserType;

import javax.inject.Inject;
import java.util.List;

public class UserDao extends CrudDaoQuerydsl<User> {

    @Inject
    public UserDao(TransactionManagerQuerydsl transactionManager){
        super(transactionManager, QUser.user);
    }

    public List<User> fetchUserOrderByName(){
        return selectFrom()
                .orderBy(QUser.user.lastname.desc())
                .fetch();
    }

    public User findByEmail(String email){
        return selectFrom()
                .where(QUser.user.email.eq(email))
                .fetchFirst();
    }

    public List<User> findAllDrivers(){
        return selectFrom()
                .where(QUser.user.type.eq(UserType.DRIVER.name()))
                .fetch();
    }

    public User findByEmailAndPassword(String email, String password){
        return selectFrom()
                .where(QUser.user.email.eq(email))
                .where(QUser.user.password.eq(password))
                .fetchFirst();
    }

}