/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.presentacion;

import com.mycompany.negocios.AficionService;

/**
 *
 * @author EdgarUris
 */
public class Presentacion {

    public static void main(String[] args) {
        AficionService aficionServi = new AficionService();
        aficionServi.inicializarAficiones();
        
        frmInicio inicio = new frmInicio();
        inicio.setVisible(true);
        
    }
}
