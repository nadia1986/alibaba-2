/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.service;

import com.pooitec1.alibaba2.entity.Buyer;
import com.pooitec1.alibaba2.entity.Product;
import com.pooitec1.alibaba2.entity.Sale;
import com.pooitec1.alibaba2.entity.repository.Conexion;
import com.pooitec1.alibaba2.entity.repository.SaleRepository;
//import com.pooitec1.alibaba2.entity.repository.LoteProductRepository;
import java.util.List;

/**
 *
 * @author nadia
 */
public class SaleService {

    private final SaleRepository saleRepository;
   // private final LoteProductService stockService;
    private final BuyerService buyerService;
    private final ProductService service;

    
    
    public SaleService() {

        this.saleRepository = new SaleRepository(Conexion.getEmf());
        //this.stockService = new LoteProductService();
        this.buyerService = new BuyerService();
        this.service = new ProductService();

    }

    public List<Buyer> findBuyerByDni(String dni) {
        return buyerService.findByDni(dni);
    }

    public List<Buyer> findAllBuyer() {
        return buyerService.getBuyer();
    }

    public List<Product> findProductByDescription(String description) {
        return service.findByDescriptionProduct(description);
    }

    public List<Product> findProductByCode(String code) {
        return service.findByCodeProduct(code);
    }

    public List<Product> findAllProduct() {
        return service.getProduct();
    }

    public  void saveSale(Sale sale) {
        this.saleRepository.create(sale);

    }

}
