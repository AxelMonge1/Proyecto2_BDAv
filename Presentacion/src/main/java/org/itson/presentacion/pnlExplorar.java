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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import models.Estudiante;

/**
 *
 * 
 */
public class pnlExplorar extends JPanel {
    Estudiante est;
    private JLabel lblFoto;
    private JLabel lblNombre;
    private JLabel lblEdad;
    private JLabel lblDescripcion;
    private JButton btnLike, btnDislike, btnSalir;
    
    public pnlExplorar(Estudiante estEnSesion){
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 240, 255));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        //Foto perfil
        ImageIcon icono = new ImageIcon(getClass().getResource("/logoUK.png"));
        Image imagenOriginal = icono.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        lblFoto.setHorizontalAlignment(JLabel.CENTER);
        lblFoto.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(lblFoto, BorderLayout.NORTH);
        
        
        JPanel panelInfo = new JPanel(new GridLayout(0, 1, 5, 5));
        panelInfo.setBackground(new Color(245, 240, 255));
        lblNombre = new JLabel("Nombre: " );
        lblEdad = new JLabel("Edad: " );
        lblDescripcion = new JLabel("Descripcion: " );
        panelInfo.add(lblNombre);
        panelInfo.add(lblEdad);
        panelInfo.add(lblDescripcion);
        add(panelInfo, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel(new GridLayout(0, 1, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panelBotones.setBackground(new Color(245, 240, 255));
        Font fuenteBoton = new Font("Segoe UI", Font.BOLD, 16);
        
        //Botones al estilo de mi logo
        JButton btnLike = crearBoton("Explorar", fuenteBoton);
        JButton btnDislike = crearBoton("Mi perfil", fuenteBoton);
        JButton btnSalir = crearBoton("Salir", fuenteBoton);
                
       }
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
}
