/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.entity;

/**
 *
 * @author nadia
 */
public class ProductDTO {
    
    private Product product;
    private LoteProduct loteProduct;
    
    private SaleLine saleLine;

    public ProductDTO() {
    }

    public ProductDTO(Product product, LoteProduct loteProduct, SaleLine saleLine) {
        this.product = product;
        this.loteProduct = loteProduct;
        this.saleLine = saleLine;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LoteProduct getLoteProduct() {
        return loteProduct;
    }

    public void setLoteProduct(LoteProduct loteProduct) {
        this.loteProduct = loteProduct;
    }

    public SaleLine getSaleLine() {
        return saleLine;
    }

    public void setSaleLine(SaleLine saleLine) {
        this.saleLine = saleLine;
    }
    
    
}
