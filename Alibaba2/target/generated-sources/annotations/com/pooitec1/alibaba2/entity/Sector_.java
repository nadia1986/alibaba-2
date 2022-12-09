package com.pooitec1.alibaba2.entity;

import com.pooitec1.alibaba2.entity.LoteProduct;
import com.pooitec1.alibaba2.entity.ProductType;
import com.pooitec1.alibaba2.entity.Wharehouse;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-12-08T22:16:22", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Sector.class)
public class Sector_ { 

    public static volatile ListAttribute<Sector, LoteProduct> stocksProcucts;
    public static volatile SingularAttribute<Sector, String> sectorCode;
    public static volatile SingularAttribute<Sector, String> description;
    public static volatile SingularAttribute<Sector, ProductType> productTypes;
    public static volatile SingularAttribute<Sector, Long> id;
    public static volatile SingularAttribute<Sector, Wharehouse> wharehouse;

}