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
    JPanel panelBotones = new JPanel(new GridLayout(0, 1, 15, 15));
    JLabel logoLabel = new JLabel();
    
    public frmVentanaPrincipal(Estudiante estEnSesion){
        this.est = estEnSesion;
        setTitle("UniLink");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        //logo central
        ImageIcon icono = new ImageIcon(getClass().getResource("/logoUK.png"));
        Image imagenOriginal = icono.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        logoLabel.setIcon(iconoEscalado);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(logoLabel, BorderLayout.NORTH);
        
        //panel para botones

        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panelBotones.setBackground(new Color(245, 240, 255));
        Font fuenteBoton = new Font("Segoe UI", Font.BOLD, 16);
        
        //botones
        JButton btnExplorar = crearBoton("Explorar", fuenteBoton);
        JButton btnMiPerfil = crearBoton("Mi perfil", fuenteBoton);
        JButton btnMisMatches = crearBoton("Mis Matches", fuenteBoton);
        JButton btnSalir = crearBoton("Salir", fuenteBoton);

        
        //eventos de botones
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
        
        //poner botones en panel
        panelBotones.add(btnMiPerfil);
        panelBotones.add(btnExplorar);
        panelBotones.add(btnMisMatches);
        panelBotones.add(btnSalir);
        add(panelBotones, BorderLayout.CENTER);
        setVisible(true);   
    }
    private void salirDelSistema(){
        int confir = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea salir? Su sesión se cerrará", "Confirmar salida",
            JOptionPane.YES_OPTION);
        if (confir == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
    //crear botones con bordes morados
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
    
    //cosas para movernos entre paneles
    private void abrirMenuExploracion() throws IOException{
        pnlExplorar explorar = new pnlExplorar(est);
        minimizarLogo();
        panelBotones.removeAll();
        panelBotones.add(explorar);
        panelBotones.revalidate();
        panelBotones.repaint();
    }
    private void abrirMiPerfil() throws IOException{
        pnlPerfilEstudiante perfil = new pnlPerfilEstudiante(est);
        minimizarLogo();
        panelBotones.removeAll();
        panelBotones.add(perfil);
        panelBotones.revalidate();
        panelBotones.repaint();
    }
    private void abrirMatches() throws IOException{
        pnlMatches matches = new pnlMatches();
        minimizarLogo();
        panelBotones.removeAll();
        panelBotones.add(matches);
        panelBotones.revalidate();
        panelBotones.repaint();
    }
    
    public void minimizarLogo(){
        ImageIcon icono = new ImageIcon(getClass().getResource("/logoUK.png"));
        Image imagenOriginal = icono.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        logoLabel.setIcon(iconoEscalado);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(logoLabel, BorderLayout.NORTH);
        revalidate();
        repaint();
    }
   
    //hacerlo visible
    public void ver() throws IOException{
        new frmVentanaPrincipal(est).setVisible(true);
    }
    
    //obtener el estudiante en sesion
    public Estudiante getEstudianteEnSesion(){
        return est;
    }
}