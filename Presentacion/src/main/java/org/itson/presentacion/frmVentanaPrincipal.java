/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import models.Estudiante;

/**
 *
 * @author EdgarUris
 * @author jeniferfl
 */
public class frmVentanaPrincipal extends JFrame{
    
    Estudiante est;
    
    public frmVentanaPrincipal(Estudiante estEnSesion){
        this.est = estEnSesion;
        setTitle("UniLink");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        //logo central
        System.out.println(getClass().getResource("/logoUK.png"));
        ImageIcon icono = new ImageIcon(getClass().getResource("/logoUK.png"));
        Image imagenOriginal = icono.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        JLabel logoLabel = new JLabel(iconoEscalado);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(logoLabel, BorderLayout.NORTH);
        
        //panel para botones
        JPanel panelBotones = new JPanel(new GridLayout(0, 1, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panelBotones.setBackground(new Color(245, 240, 255));
        Font fuenteBoton = new Font("Segoe UI", Font.BOLD, 16);
        
        //Botones al estilo de mi logo
        JButton btnExplorar = crearBoton("Explorar", fuenteBoton);
        JButton btnMiPerfil = crearBoton("Mi perfil", fuenteBoton);
        JButton btnMisMatches = crearBoton("Mis Matches", fuenteBoton);
        JButton btnSalir = crearBoton("Salir", fuenteBoton);

        
        //Acciones
        btnExplorar.addActionListener(e -> {
            try {
                abrirMenuExploracion();
            } catch (IOException ex) {
                Logger.getLogger(frmVentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnMiPerfil.addActionListener(e -> {
            try {
                abrirMiPerfil();
            } catch (IOException ex) {
                Logger.getLogger(frmVentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnMisMatches.addActionListener(e -> {
            try {
                abrirMatches();
            } catch (IOException ex) {
                Logger.getLogger(frmVentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnSalir.addActionListener(e -> salirDelSistema());
        
        //Agregar botones al panel
        panelBotones.add(btnMiPerfil);
        panelBotones.add(btnExplorar);
        panelBotones.add(btnMisMatches);
        panelBotones.add(btnSalir);
        add(panelBotones, BorderLayout.CENTER);
        setVisible(true);
        
        
    }
    private void salirDelSistema(){
        int confir = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea realizar esta acción?", "Confirmar salida",
            JOptionPane.YES_OPTION);
        if (confir == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
    //Metodo para crear crear los botones con estilo
    private JButton crearBoton(String texto, Font fuente){
        JButton boton = new JButton(texto);
        boton.setFont(fuente);
        boton.setFocusPainted(false);
        boton.setBackground(Color.WHITE);
        boton.setForeground(new Color(0,0,0)); //negro
        boton.setBorder(BorderFactory.createLineBorder(new Color(128, 0, 128), 2)); //morado
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        boton.setIconTextGap(15);
        return boton;
    }
    
    //Metodos de navegacion
    private void abrirMenuExploracion() throws IOException{
//        PacientesV ventanaPacientes = new PacientesV();
//        ventanaPacientes.setVisible(true);
    }
    private void abrirMiPerfil() throws IOException{
//        MedicosV ventanaMedicos = new MedicosV();
//        ventanaMedicos.setVisible(true);
    }
    private void abrirMatches() throws IOException{
//        EspecialidadV ventanaEsp = new EspecialidadV();
//        ventanaEsp.setVisible(true);
    }
   
    public void ver() throws IOException{
        new frmVentanaPrincipal(est).setVisible(true);
    }
}