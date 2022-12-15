package com.pooitec1.alibaba2.entity;

import com.pooitec1.alibaba2.entity.Product;
import com.pooitec1.alibaba2.entity.Sector;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2022-12-14T20:47:07", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(LoteProduct.class)
public class LoteProduct_ { 

    public static volatile SingularAttribute<LoteProduct, Integer> cantidadActual;
    public static volatile SingularAttribute<LoteProduct, Double> priceUnit;
    public static volatile SingularAttribute<LoteProduct, Product> product;
    public static volatile SingularAttribute<LoteProduct, Integer> quantity;
    public static volatile SingularAttribute<LoteProduct, Integer> stockMin;
    public static volatile SingularAttribute<LoteProduct, Double> salePrice;
    public static volatile SingularAttribute<LoteProduct, Integer> stockMax;
    public static volatile SingularAttribute<LoteProduct, Double> price;
    public static volatile SingularAttribute<LoteProduct, LocalDate> expiration;
    public static volatile SingularAttribute<LoteProduct, Long> idLote;
    public static volatile SingularAttribute<LoteProduct, Sector> sector;

}