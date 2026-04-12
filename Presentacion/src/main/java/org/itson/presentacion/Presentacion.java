/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.presentacion;

import com.mycompany.negocios.AficionService;
import java.io.IOException;
import models.Estudiante;

/**
 *
 * @author EdgarUris
 */
public class Presentacion {

    public static void main(String[] args) {
        AficionService aficionServi = new AficionService();
        aficionServi.inicializarAficiones();
        
//        frmInicio inicio = new frmInicio();
//        inicio.setVisible(true);

    frmVentanaPrincipal principal = new frmVentanaPrincipal(new Estudiante());
        try {
            principal.ver();
        } catch (IOException ex) {
            System.getLogger(Presentacion.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
}
