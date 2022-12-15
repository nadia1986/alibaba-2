package com.pooitec1.alibaba2.entity;

import com.pooitec1.alibaba2.entity.ProductType;
import com.pooitec1.alibaba2.entity.Seller;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-12-14T20:47:07", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Seller> seller;
    public static volatile SingularAttribute<Product, String> codProd;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Long> id;
    public static volatile SingularAttribute<Product, ProductType> productType;

}