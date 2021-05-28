package com.epam.esm.entity.metamodel;

import com.epam.esm.Role;
import com.epam.esm.Status;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import java.util.List;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public class User_ {

    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, String> surname;
    public static volatile SingularAttribute<User, Instant> createDate;
    public static volatile SingularAttribute<User, Instant> lastUpdateDate;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Role> role;
    public static volatile SingularAttribute<User, Status> status;
    public static volatile SingularAttribute<User, List<Order>> orders;
    public static volatile SingularAttribute<User, Long> id;

    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String CREATE_DATE = "createDate";
    public static final String LAST_UPDATE_DATE = "lastUpdateDate";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String STATUS = "status";
    public static final String ORDERS = "orders";
    public static final String ID = "id";

}
