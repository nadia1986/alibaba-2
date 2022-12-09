/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.view.resources;


import com.pooitec1.alibaba2.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nadia
 */
public class TableModelProduct extends AbstractTableModel {
     private static final String[] COLUMNAS = {"Cod_prod", "Descrption", "ProductType", "StockProduct", "Precio"};
    private List<Product> products;
    

    public TableModelProduct() {
        products = new ArrayList<>();
    }

    @Override
    public int getRowCount() {        
        return products == null ? 0 : products.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object retorno = null;
        Product product = products.get(rowIndex);

        switch (columnIndex) {
            case 0:
                retorno = product.getCodProd();
                break;
            case 1:
                retorno = product.getDescription();
                break;
            case 2:
                retorno = product.getProductType().getDescription();
                break;
            
            //case 3:
               // retorno= product.getStockProduct().getQuantity();
                //break;
           // case 4:
                //retorno=product.getStockProduct().getSalePrice();
               // break;
        }
        return retorno;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNAS[column];
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getProductIn (int row) {
        return products.get(row);
    }

    public int findRowProduct(Product productSearch){
        int row = 0;
        int accountant = 0;
        for (Product productR : products) {
            accountant = accountant +1;
            if (productSearch.getCodProd()==productR.getCodProd()) {
                row = accountant;
            }
        }
        return row;
    }
}
