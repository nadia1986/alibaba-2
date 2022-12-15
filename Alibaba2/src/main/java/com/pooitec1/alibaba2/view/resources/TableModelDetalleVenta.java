/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.view.resources;

import com.pooitec1.alibaba2.entity.Buyer;
import com.pooitec1.alibaba2.entity.ProductDTO;
import com.pooitec1.alibaba2.entity.SaleLine;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author nadia
 */
public class TableModelDetalleVenta extends AbstractTableModel {
     private static final String[] COLUMNAS = {"cod_producto", "Descripcion", "Precio", "Subtotal"};
    private List<ProductDTO> saleLines;
    

    public TableModelDetalleVenta() {
        saleLines = new ArrayList<>();
    }

    @Override
    public int getRowCount() {        
        return saleLines == null ? 0 : saleLines.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object retorno = null;
        ProductDTO saleLine = saleLines.get(rowIndex);

        switch (columnIndex) {
            case 0:
                retorno = saleLine.getProduct().getCodProd();
                break;
            case 1:
                retorno = saleLine.getProduct().getDescription();
                break;
            case 2:
                retorno = saleLine.getLoteProduct().getSalePrice();
                break;
            case 3:
               retorno = saleLine.getLoteProduct().getSalePrice() * saleLine.getSaleLine().getQuantity();
                break;
           
        }
        return retorno;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNAS[column];
    }

    public void setSaleLines(List<ProductDTO> saleLines) {
        this.saleLines = saleLines;
    }

    public ProductDTO getProductDTO (int row) {
        return saleLines.get(row);
    }

    public int findRowBuyer(ProductDTO productSearch){
        int row = 0;
        int accountant = 0;
        for (ProductDTO productDTOR : saleLines) {
            accountant = accountant +1;
            if (productSearch.getProduct().getCodProd()==productDTOR.getProduct().getCodProd()) {
                row = accountant;
            }
        }
        return row;
    }
}
