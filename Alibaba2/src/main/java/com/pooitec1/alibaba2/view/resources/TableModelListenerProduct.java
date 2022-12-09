/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.view.resources;

import com.pooitec1.alibaba2.view.JPanel_VentaPaso3;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author nadia
 */
public class TableModelListenerProduct implements ListSelectionListener{
    
     private final JPanel_VentaPaso3 jPanelProcesarVentaPaso3;

    public TableModelListenerProduct(JPanel_VentaPaso3 pantallaContenido) {
        this.jPanelProcesarVentaPaso3 = pantallaContenido;
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        this.jPanelProcesarVentaPaso3.seleccionarProducto();
    }
    
  

  
    
}
