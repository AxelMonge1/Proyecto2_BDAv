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
        //Para que no genere error al usar drop and create
        try {
            AficionService aficionServi = new AficionService();
            aficionServi.inicializarAficiones();
            java.awt.EventQueue.invokeLater(() -> {
                new frmInicio().setVisible(true);
            });
        } catch (Exception ex) {
            System.err.println("Error al inicializar la base de datos: " + ex.getMessage());
            ex.printStackTrace();
        }

    }
}
