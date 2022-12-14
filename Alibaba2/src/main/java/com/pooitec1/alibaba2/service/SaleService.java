/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.service;

import com.pooitec1.alibaba2.entity.Buyer;
import com.pooitec1.alibaba2.entity.LoteProduct;
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
    private final LoteProductService stockService;
    private final BuyerService buyerService;
    private final ProductService serviceProduct;

    
    
    public SaleService() {

        this.saleRepository = new SaleRepository(Conexion.getEmf());
        this.stockService = new LoteProductService();
        this.buyerService = new BuyerService();
        this.serviceProduct = new ProductService();

    }

    public List<Buyer> findBuyerByDni(String dni) {
        return buyerService.findByDni(dni);
    }

    public List<Buyer> findAllBuyer() {
        return buyerService.getBuyer();
    }
    
    
    
    //llamo al servicio de LoteService para buscar un producto por descripcion
    
    public List<LoteProduct> findProductByDescription(String descripcion) {
        return stockService.findByProductDescription(descripcion);
    }
    
    // Retorna una lista de todos los lotes
    public List<LoteProduct> findAllLoteProduct(){
        return stockService.getLoteProduct();
    }
    
    
    
    

    public List<LoteProduct> findByProduct(Product product) {
        return stockService.findByProduct(product);
    }

    public List<Product> findProductByCode(String code) {
        return serviceProduct.findByCodeProduct(code);
    }
    
     public Product findOneProductByDescription(String description) {
        return serviceProduct.getOneProduct(description);
    }

    public List<Product> findAllProduct() {
        return serviceProduct.getProduct();
    }

    public  void saveSale(Sale sale) {
        this.saleRepository.create(sale);

    }

}
