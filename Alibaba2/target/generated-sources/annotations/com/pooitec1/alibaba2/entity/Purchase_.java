package com.pooitec1.alibaba2.entity;

import com.pooitec1.alibaba2.entity.Employee;
import com.pooitec1.alibaba2.entity.PurchaseLine;
import com.pooitec1.alibaba2.entity.Seller;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-12-08T22:16:22", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Purchase.class)
public class Purchase_ { 

    public static volatile SingularAttribute<Purchase, Seller> seller;
    public static volatile SingularAttribute<Purchase, LocalDate> purchaseDate;
    public static volatile SingularAttribute<Purchase, Long> purchaseId;
    public static volatile ListAttribute<Purchase, PurchaseLine> purchaseLines;
    public static volatile SingularAttribute<Purchase, Employee> employee;

}