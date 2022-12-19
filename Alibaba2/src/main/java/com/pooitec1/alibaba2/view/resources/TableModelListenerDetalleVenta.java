package com.pooitec1.alibaba2.view.resources;


import com.pooitec1.alibaba2.view.JPanel_VentaPaso2;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TableModelListenerDetalleVenta implements ListSelectionListener {

    private final JPanel_VentaPaso2 jPanelVentaPaso2;

    public TableModelListenerDetalleVenta(JPanel_VentaPaso2 pantallaContenido) {
        this.jPanelVentaPaso2= pantallaContenido;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        // obtenemos la escuela seleccionada
        //comentamos para ver como organizar las listas
       // this.jPanelVentaPaso2.seleccionarLibro();
        //this.jPanelProcesarPrestamoPaso2.seleccionarSocio();
        //this.unPanelPersona.getValidador().habilitarBoton(true, this.unPanelPersona.getJbtn_eliminar(), new Color(30, 132, 73), Color.WHITE, null, null);
        //this.unPanelPersona.getValidador().habilitarBoton(true, this.unPanelPersona.getJbtn_modificar(), new Color(30, 132, 73), Color.WHITE, null, null);
       //this.unPanelPersona.getValidador().limpiarCampo(this.unPanelPersona.getJtf_buscarPersona());
    }

}