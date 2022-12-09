/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.view.resources;

import com.pooitec1.alibaba2.entity.Buyer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nadia
 */
public class TableModelBuyer extends AbstractTableModel {
     private static final String[] COLUMNAS = {"FisrtName", "LastName", "Dni", "Address", "Phone", "Email"};
    private List<Buyer> buyers;
    

    public TableModelBuyer() {
        buyers = new ArrayList<>();
    }

    @Override
    public int getRowCount() {        
        return buyers == null ? 0 : buyers.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object retorno = null;
        Buyer buyer = buyers.get(rowIndex);

        switch (columnIndex) {
            case 0:
                retorno = buyer.getFirstName();
                break;
            case 1:
                retorno = buyer.getLastName();
                break;
            case 2:
                retorno = buyer.getDni();
                break;
            case 3:
                retorno = buyer.getAddress();
                break;
            case 4:
                retorno= buyer.getPhone();
                break;
            case 5:
                retorno=buyer.getEmail();
                break;
        }
        return retorno;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNAS[column];
    }

    public void setBuyers(List<Buyer> buyers) {
        this.buyers = buyers;
    }

    public Buyer getBuyerIn (int row) {
        return buyers.get(row);
    }

    public int findRowBuyer(Buyer buyerSearch){
        int row = 0;
        int accountant = 0;
        for (Buyer buyerR : buyers) {
            accountant = accountant +1;
            if (buyerSearch.getDni()==buyerR.getDni()) {
                row = accountant;
            }
        }
        return row;
    }
}
