/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.service;

import com.pooitec1.alibaba2.entity.Product;

import com.pooitec1.alibaba2.entity.repository.Conexion;
import com.pooitec1.alibaba2.entity.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nadia
 */
public class ProductService {

    private final ProductRepository repository;

    public ProductService() {

        this.repository = new ProductRepository(Conexion.getEmf());
    }
    
    

    public List<Product> findByCodeProduct(String code) {
        List<Product> productsfound = new ArrayList<>();

        for (Product productSearch : repository.findProductEntities()) {
            if (productSearch.getCodProd().contains(code)) {
                productsfound.add(productSearch);
            }
        }
        return productsfound;
    }
    
       public List<Product> findByDescriptionProduct(String description) {
        List<Product> productsfound = new ArrayList<>();

        for (Product productSearch : repository.findProductEntities()) {
            if (productSearch.getCodProd().contains(description)) {
                productsfound.add(productSearch);
            }
        }
        return productsfound;
    }

    public List<Product> getProduct() {
        List<Product> listProducts = repository.findProductEntities();
        return listProducts;

    }
    
    public Product getOneProduct(String codProduct){
         Product oneProduct =null;
        for (Product productSearch : repository.findProductEntities()) {
            if (productSearch.getCodProd().contains(codProduct)) {
               oneProduct = productSearch;
            }
        } 
        
        return oneProduct;
        
    }

}
