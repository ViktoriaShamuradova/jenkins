package com.epam.esm.entity.metamodel;

import com.epam.esm.entity.Tag;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Set;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tag.class)
public class Tag_ {

    public static volatile SingularAttribute<Tag, String> name;
    public static volatile SingularAttribute<Tag, Set<Tag>> certificates;
    public static volatile SingularAttribute<Tag, Long> id;

    public static final String NAME = "name";
    public static final String CERTIFICATES = "certificates";
    public static final String ID = "id";

}
