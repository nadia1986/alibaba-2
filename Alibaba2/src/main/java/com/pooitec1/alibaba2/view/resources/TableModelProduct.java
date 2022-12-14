/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.view.resources;


import com.pooitec1.alibaba2.entity.LoteProduct;
import com.pooitec1.alibaba2.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nadia
 */
public class TableModelProduct extends AbstractTableModel {
    private static final String[] COLUMNAS = {"Cod_prod", "Producto", "Descripcion", "StockProduct ", "Precio"};
    private List<LoteProduct> loteProducts;
    

    public TableModelProduct() {
        loteProducts = new ArrayList<>();
    }

    @Override
    public int getRowCount() {        
        return loteProducts == null ? 0 : loteProducts.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object retorno = null;
        LoteProduct loteProduct = loteProducts.get(rowIndex);

        switch (columnIndex) {
            case 0:
                retorno = loteProduct.getProduct().getCodProd();
                break;
            case 1:
                retorno = loteProduct.getProduct().getDescription();
                break;
            case 2:
                retorno = loteProduct.getProduct().getProductType().getDescription();
                break;
            
            case 3:
               retorno= loteProduct.getCantidadActual();
                break;
           case 4:
                retorno=loteProduct.getSalePrice();
                break;
        }
        return retorno;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNAS[column];
    }

    public void setProducts(List<LoteProduct> loteProducts) {
        this.loteProducts = loteProducts;
    }

    public LoteProduct getProductIn (int row) {
        return loteProducts.get(row);
    }

    public int findRowProduct(LoteProduct productSearch){
        int row = 0;
        int accountant = 0;
        for (LoteProduct loteR : loteProducts) {
            accountant = accountant +1;
            if (productSearch.getProduct().getDescription()==loteR.getProduct().getDescription()) {
                row = accountant;
            }
        }
        return row;
    }
}
