/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pooitec1.alibaba2.view.resources;



import com.pooitec1.alibaba2.view.JPanel_VentaPaso1;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author nadia
 */
public class TableModelListenerBuyer implements ListSelectionListener{
    
     private final JPanel_VentaPaso1 jPanelProcesarCompraPaso1;

    public TableModelListenerBuyer(JPanel_VentaPaso1 pantallaContenido) {
        this.jPanelProcesarCompraPaso1 = pantallaContenido;
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        this.jPanelProcesarCompraPaso1.seleccionarSocio();
    }
    
  

  
    
}
