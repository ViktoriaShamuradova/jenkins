package com.epam.esm.entity.metamodel;


import com.epam.esm.entity.Order;
import com.epam.esm.entity.OrderItem;
import com.epam.esm.entity.User;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public class Order_ {

    public static volatile SingularAttribute<Order, User> user;
    public static volatile SingularAttribute<Order, BigDecimal> totalSum;
    public static volatile SingularAttribute<Order, Integer> count;
    public static volatile SingularAttribute<Order, Instant> createDate;
    public static volatile SingularAttribute<Order, Set<OrderItem>> orderItems;
    public static volatile SingularAttribute<Order, Long> id;

    public static final String USER = "user";
    public static final String TOTAL_SUM = "totalSum";
    public static final String COUNT = "count";
    public static final String CREATE_DATE = "createDate";
    public static final String ORDER_ITEMS = "orderItems";
    public static final String ID = "id";

}