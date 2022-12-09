/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.view.resources;

import com.pooitec1.alibaba2.entity.Buyer;
import com.pooitec1.alibaba2.entity.Seller;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nadia
 */
public class TableModelSeller extends AbstractTableModel {
     private static final String[] COLUMNAS = {"Name", "Direccion", "Phone", "Email"};
    private List<Seller> sellers;
    

    public TableModelSeller() {
        sellers = new ArrayList<>();
    }

    @Override
    public int getRowCount() {        
        return sellers == null ? 0 : sellers.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object retorno = null;
        Seller seller = sellers.get(rowIndex);

        switch (columnIndex) {
            case 0:
                retorno = seller.getName();
                break;
            case 1:
                retorno = seller.getDirection();
                break;
            case 2:
                retorno = seller.getPhone();
                break;
            case 3:
                retorno = seller.getEmail();
                break;
           
        }
        return retorno;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNAS[column];
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }

    public Seller getSellerIn (int row) {
        return sellers.get(row);
    }

    public int findRowSeller(Seller sellerSearch){
        int row = 0;
        int accountant = 0;
        for (Seller sellerR : sellers) {
            accountant = accountant +1;
            if (sellerSearch.getName()==sellerR.getName()) {
                row = accountant;
            }
        }
        return row;
    }
}
