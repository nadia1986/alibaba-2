/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.service;

import com.pooitec1.alibaba2.entity.Buyer;
import com.pooitec1.alibaba2.entity.repository.BuyerRepository;
import com.pooitec1.alibaba2.entity.repository.Conexion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nadia
 */
public class BuyerService {

    private final BuyerRepository repository;

    public BuyerService() {

        this.repository = new BuyerRepository(Conexion.getEmf());
    }

    public List<Buyer> findByDni(String dni) {
        List<Buyer> buyersfound = new ArrayList<>();

        for (Buyer buyerSearch : repository.findBuyerEntities()) {
            if (buyerSearch.getDni().contains(dni)) {
                buyersfound.add(buyerSearch);
            }
        }
        return buyersfound;
    }

    public List<Buyer> getBuyer() {
        List<Buyer> listBuyers = repository.findBuyerEntities();
        return listBuyers;

    }

}
