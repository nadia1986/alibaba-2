/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.service;

import com.pooitec1.alibaba2.entity.Product;
import com.pooitec1.alibaba2.entity.Sector;
import com.pooitec1.alibaba2.entity.Wharehouse;
import com.pooitec1.alibaba2.entity.repository.Conexion;
import com.pooitec1.alibaba2.entity.repository.WharehouseRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nadia
 */
public class WharehouseService {
    
     private final WharehouseRepository repository;
     
     
     
     

    public WharehouseService() {
        this.repository = new WharehouseRepository(Conexion.getEmf());
    }

    public List<Wharehouse> findByWharehouseId(long id) {
        List<Wharehouse> wharehousefound = new ArrayList<>();

        for (Wharehouse wharehouseSearch : repository.findWharehouseEntities()) {
            if (wharehouseSearch.getId()==id) {
                wharehousefound.add(wharehouseSearch);
            }
        }
        return wharehousefound;
    }

    public List<Wharehouse> getWharehose() {
        List<Wharehouse> listWharehouses = repository.findWharehouseEntities();
        return listWharehouses;

    }
    
    public Sector verificarSectorProducto(Product product, Wharehouse wharehouse){
        Sector sectorVerify= null;
           for (Sector sectorR : wharehouse.getSectors()) {
           
               if (sectorR.getProductTypes().equals(product.getProductType())) {
                   sectorVerify=sectorR;
               }
            

        }
 
        return sectorVerify;
        
        
    }
    
}
