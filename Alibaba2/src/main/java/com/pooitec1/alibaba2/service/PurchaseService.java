/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.service;

import com.pooitec1.alibaba2.entity.Employee;
import com.pooitec1.alibaba2.entity.Product;
import com.pooitec1.alibaba2.entity.Purchase;
import com.pooitec1.alibaba2.entity.PurchaseLine;
import com.pooitec1.alibaba2.entity.Seller;
import com.pooitec1.alibaba2.entity.Wharehouse;
import com.pooitec1.alibaba2.entity.repository.Conexion;
import com.pooitec1.alibaba2.entity.repository.PurchaseRepository;
import java.util.List;

/**
 *
 * @author nadia
 */
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final SellerService sellerService;
    private final WharehouseService wharehouseService;
    private final ProductService productService;

    public PurchaseService() {

        this.purchaseRepository = new PurchaseRepository(Conexion.getEmf());
        this.productService = new ProductService();
        this.sellerService = new SellerService();
        this.wharehouseService = new WharehouseService();

    }

    public List<Seller> findSellerByName(String name) {
        return sellerService.findBySellerName(name);
    }

    public List<Wharehouse> findWharehouse(long id) {
        return wharehouseService.findByWharehouseId(id);
    }

    public List<Product> findProductByDescription(String description) {
        return productService.findByDescriptionProduct(description);
    }

    public List<Product> findProductByCode(String code) {
        return productService.findByCodeProduct(code);
    }

    public List<Product> findAllProduct() {
        return productService.getProduct();
    }

    public void savePurchase(Purchase purchase) {
        this.purchaseRepository.create(purchase);

    }
}
