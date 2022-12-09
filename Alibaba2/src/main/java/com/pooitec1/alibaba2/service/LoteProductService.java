/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.service;

import com.pooitec1.alibaba2.entity.Product;
import com.pooitec1.alibaba2.entity.Purchase;
import com.pooitec1.alibaba2.entity.Sale;
import com.pooitec1.alibaba2.entity.LoteProduct;

import com.pooitec1.alibaba2.entity.repository.Conexion;
import com.pooitec1.alibaba2.entity.repository.ProductRepository;
import com.pooitec1.alibaba2.entity.repository.LoteProductRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nadia
 */
public class LoteProductService {

    private final LoteProductRepository stockRepository;
    private final ProductRepository productRepository;
    private final ProductService service;

    public LoteProductService() {
        this.productRepository = new ProductRepository(Conexion.getEmf());
        this.stockRepository = new LoteProductRepository(Conexion.getEmf());
        this.service = new ProductService();

    }

    // El purchase suma al stock
    public void addPurchaseStock(Purchase purchase) {

        //  LoteProduct stockProduct= new LoteProduct(0, Integer.MIN_VALUE, 0, LocalDate.MIN, 0, 0, 0, 0, sector, productRepository);
    }

    //Sale resta al stock
    public void addSaleStock(Sale sale) {

    }
    
    
    //creo una funcion o agrego una  nueva clase PrecioProducto con precioVenta y precioCompra???

    public double obtenerPrecioVentaProducto(Product product) {
        
        double precioProducto= 0;
        for (LoteProduct stockPr : this.stockRepository.findStockProductEntities()) {
             if (stockPr.getProduct().equals(product)) {
            precioProducto= stockPr.getSalePrice();
             }

        }
        return precioProducto;
    }
    
    
    
   

    

    // desconat stock producto disponible
    public void deductProductAvailable(int quantity, String code) {
        for (Product pr : service.findByCodeProduct(code)) {
            // if (checkStock(pr, quantity) ) {
            //hacer algo tengo que setear el nuevo valor de stock
            //pr.getStockProduct().getQuantity()

        }
    }

    public Integer verStockTotalProducto(Product product) {

        Integer cantidadTotalProducto = 0;

        for (LoteProduct stockPr : this.stockRepository.findStockProductEntities()) {
            if (stockPr.getProduct().equals(product)) {
                if (verificarVencimiento(stockPr)) {
                    cantidadTotalProducto = cantidadTotalProducto + stockPr.getCantidadActual();

                }
            }

        }

        return cantidadTotalProducto;
    }

    public List<LoteProduct> verCantidadDeProductosPorlote(Product product) {
        List<LoteProduct> stockProductfound = new ArrayList<>();
        for (LoteProduct stockPr : this.stockRepository.findStockProductEntities()) {
            if (stockPr.getProduct().equals(product)) {
                if (verificarVencimiento(stockPr)) {
                    stockProductfound.add(stockPr);
                }

            }

        }

        return stockProductfound;

    }

    private Boolean verificarVencimiento(LoteProduct stockProduct) {
        Boolean state = false;

        if (stockProduct.getExpiration().isEqual(LocalDate.now()) || stockProduct.getExpiration().isAfter(LocalDate.now())) {
            state = true;
        }

        return state;
    }

}
