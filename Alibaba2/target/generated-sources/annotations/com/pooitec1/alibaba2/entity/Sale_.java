package com.pooitec1.alibaba2.entity;

import com.pooitec1.alibaba2.entity.Buyer;
import com.pooitec1.alibaba2.entity.Employee;
import com.pooitec1.alibaba2.entity.SaleLine;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-12-14T20:47:07", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Sale.class)
public class Sale_ { 

    public static volatile ListAttribute<Sale, SaleLine> saleLines;
    public static volatile SingularAttribute<Sale, Long> saleId;
    public static volatile SingularAttribute<Sale, Employee> employee;
    public static volatile SingularAttribute<Sale, LocalDate> dateSale;
    public static volatile SingularAttribute<Sale, Buyer> buyer;

}