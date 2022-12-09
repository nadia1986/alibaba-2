package com.pooitec1.alibaba2.entity;

import com.pooitec1.alibaba2.entity.Product;
import com.pooitec1.alibaba2.entity.Purchase;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-12-08T22:16:22", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(PurchaseLine.class)
public class PurchaseLine_ { 

    public static volatile SingularAttribute<PurchaseLine, Product> product;
    public static volatile SingularAttribute<PurchaseLine, Integer> quantity;
    public static volatile SingularAttribute<PurchaseLine, Purchase> purchase;
    public static volatile SingularAttribute<PurchaseLine, Long> id;

}