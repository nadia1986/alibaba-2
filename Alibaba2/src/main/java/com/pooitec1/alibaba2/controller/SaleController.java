/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.controller;

import com.pooitec1.alibaba2.entity.Buyer;
import com.pooitec1.alibaba2.entity.Employee;
import com.pooitec1.alibaba2.entity.LoteProduct;
import com.pooitec1.alibaba2.entity.Product;
import com.pooitec1.alibaba2.entity.Sale;
import com.pooitec1.alibaba2.entity.SaleLine;
//import com.pooitec1.alibaba2.service.BuyerService;
//import com.pooitec1.alibaba2.service.ProductService;
import com.pooitec1.alibaba2.service.SaleService;
import com.pooitec1.alibaba2.service.LoteProductService;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author nadia
 */
public class SaleController {

    private final Sale newSale;
   // private final BuyerService buyerService;
   // private final ProductService productService;
  //  private final LoteProductService stockService;
    private final SaleService saleService;

    public SaleController(Employee employee) {

        this.newSale = new Sale();
        newSale.setDateSale(LocalDate.now());
        newSale.setEmployee(employee);
        //this.productService = new ProductService();
        //this.buyerService = new BuyerService();
        //this.stockService = new LoteProductService();
        this.saleService = new SaleService();

    }

    public Sale getNewSale() {
        return newSale;
    }

    
    //FUNCION QUE LLAMO EN VENTA PASO 1 PARA BUSCAR CLIENTE
    public List<Buyer> findBuyerByDni(String dni) {
        return saleService.findBuyerByDni(dni);
    }
    
    //FUNCION QUE LLAMO EN VENTA PASO 3 PARA BUSCAR PRODUCTOS EN LoteProduct(porque el lote es quien tiene el producto)
     public List<LoteProduct> findProductByDescription(String description) {
        return saleService.findProductByDescription(description);
    }
    
     public List<LoteProduct> findByProduct(Product product) {
        return saleService.findByProduct(product);
    }
     
    // public Product findOneProduct(String codeProduct){
         //return saleService.findProductByDescription(codeProduct);
    //}

    public List<Buyer> findAllBuyer() {
        return saleService.findAllBuyer();
    }

   

    public List<Product> findProductByCode(String code) {
        return saleService.findProductByCode(code);
    }

    public List<Product> findAllProduct() {
        return saleService.findAllProduct();
    }
    
    
    
     public void addBuyer(Buyer buyer) {

        this.newSale.setBuyer(buyer);

    }

    public void addProduct(SaleLine saleLine) {
      

       // if(this.stockService.verStockTotalProducto(product)){
        //llamar al servicio de stockService para ver si hay o no ptos 
       this.newSale.getSaleLines().add(saleLine);
       // }

    }

    public void saveSale(Sale sale) {
       this.saleService.saveSale(sale);
       
    }

}
