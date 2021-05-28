package com.epam.esm.entity.metamodel;

import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Certificate.class)
public abstract class Certificate_ {

    public static volatile SingularAttribute<Certificate, String> description;
    public static volatile SingularAttribute<Certificate, String> name;
    public static volatile SingularAttribute<Certificate, BigDecimal> price;
    public static volatile SingularAttribute<Certificate, Integer> duration;
    public static volatile SingularAttribute<Certificate, Instant> updateLastDate;
    public static volatile SingularAttribute<Certificate, Instant> createDate;
    public static volatile SingularAttribute<Certificate, Set<Tag>> tags;
    public static volatile SingularAttribute<Certificate, Long> id;

    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";
    public static final String DURATION = "duration";
    public static final String CREATE_DATE = "createDate";
    public static final String UPDATE_DATE = "updateLastDate";
    public static final String NAME = "name";
    public static final String TAGS = "tags";
    public static final String ID = "id";
}
