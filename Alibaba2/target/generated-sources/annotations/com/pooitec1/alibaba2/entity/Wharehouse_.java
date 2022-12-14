package com.pooitec1.alibaba2.entity;

import com.pooitec1.alibaba2.entity.Employee;
import com.pooitec1.alibaba2.entity.Sector;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-12-09T21:17:13", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Wharehouse.class)
public class Wharehouse_ { 

    public static volatile ListAttribute<Wharehouse, Sector> sectors;
    public static volatile SingularAttribute<Wharehouse, String> phone;
    public static volatile SingularAttribute<Wharehouse, Long> id;
    public static volatile ListAttribute<Wharehouse, Employee> employees;
    public static volatile SingularAttribute<Wharehouse, String> email;
    public static volatile SingularAttribute<Wharehouse, String> direction;

}