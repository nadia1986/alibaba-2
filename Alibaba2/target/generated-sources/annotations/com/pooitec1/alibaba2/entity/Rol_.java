package com.pooitec1.alibaba2.entity;

import com.pooitec1.alibaba2.entity.User;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-12-08T22:16:22", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Rol.class)
public class Rol_ { 

    public static volatile SingularAttribute<Rol, String> description;
    public static volatile SingularAttribute<Rol, Long> id;
    public static volatile ListAttribute<Rol, User> users;

}