/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author nadia
 */
@Entity
public class Sector implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sectorCode;
    private String description;

    @OneToMany
    private List<LoteProduct> stocksProcucts;

    @ManyToOne
    private ProductType productTypes;

    @ManyToOne
    private Wharehouse wharehouse;

    public Sector() {
        this.stocksProcucts = new ArrayList<>();

    }

    public Sector(String sectorCode, ProductType productTypes, Wharehouse wharehouse) {
        this.sectorCode = sectorCode;
        this.productTypes = productTypes;
        this.wharehouse = wharehouse;
        this.stocksProcucts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sector)) {
            return false;
        }
        Sector other = (Sector) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pooitec1.alibaba2.entity.Sector[ id=" + id + " ]";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LoteProduct> getStocksProcucts() {
        return stocksProcucts;
    }

    public void setStocksProcucts(List<LoteProduct> stocksProcucts) {
        this.stocksProcucts = stocksProcucts;
    }

   

    public String getSectorCode() {
        return sectorCode;
    }

    public void setSectorCode(String sectorCode) {
        this.sectorCode = sectorCode;
    }

    public ProductType getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(ProductType productTypes) {
        this.productTypes = productTypes;
    }

    public Wharehouse getWharehouse() {
        return wharehouse;
    }

    public void setWharehouse(Wharehouse wharehouse) {
        this.wharehouse = wharehouse;
    }
    
    

}
