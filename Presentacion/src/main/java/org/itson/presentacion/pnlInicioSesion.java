/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import models.Estudiante;
import org.itson.utilidades.JPAUtil;
import org.itson.utilidades.RegexUtil;
import org.itson.persistencia.IEstudianteDAO;
import org.itson.persistencia.EstudianteDAO;

/**
 *
 * @author HP
 */
public class pnlInicioSesion extends JPanel {

    private final Color COLOR_GRIS_CABECERA = new Color(235, 235, 235); //Gris claro de cabecera
    private final Color COLOR_LILA_FONDO = new Color(240, 240, 250);   //Lila muy pálido de fondo
    private final Color COLOR_MORADO_ACENTO = new Color(111, 63, 140); //Morado para bordes y texto de botones

    private final Font FUENTE_TITULO = new Font("Segoe UI", Font.BOLD, 22);
    private final Font FUENTE_LABELS = new Font("Segoe UI", Font.BOLD, 14);
    private final Font FUENTE_CAMPOS = new Font("Segoe UI", Font.PLAIN, 14);
    private final Font FUENTE_BOTONES = new Font("Segoe UI", Font.BOLD, 16);
    private final Font FUENTE_PREGUNTA = new Font("Segoe UI", Font.BOLD, 13);

    private JTextField txtCorreo;
    private JPasswordField pswContra;
    private JButton btnIniciarSesion;
    private JButton btnCrearCuenta;
    
    private RegexUtil regex = new RegexUtil();
    private IEstudianteDAO estDAO = new EstudianteDAO();

    public pnlInicioSesion() {
        setLayout(new BorderLayout());
        setSize(400,650);

        //cabecera
        JPanel panelCabecera = new JPanel();
        panelCabecera.setLayout(new BoxLayout(panelCabecera, BoxLayout.Y_AXIS));
        panelCabecera.setBackground(COLOR_GRIS_CABECERA);
        panelCabecera.setBorder(new EmptyBorder(30, 0, 10, 0));

        //nuestro logo
        JLabel lblLogo = new JLabel();
        try {
            URL logoUrl = getClass().getResource("/logoUK.png");
            if (logoUrl != null) {
                ImageIcon iconoOriginal = new ImageIcon(logoUrl);
                Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                lblLogo.setIcon(new ImageIcon(imagenEscalada));
            } else {
                System.out.println("Error: No se pudo encontrar el logo. Asegúrate de que la ruta sea correcta.");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar el logo: " + e.getMessage());
        }
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCabecera.add(lblLogo);

        
        panelCabecera.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel lblTituloPrincipal = new JLabel("Inicio de sesion");
        lblTituloPrincipal.setFont(FUENTE_TITULO);
        lblTituloPrincipal.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTituloPrincipal.setForeground(Color.BLACK);
        panelCabecera.add(lblTituloPrincipal);

        add(panelCabecera, BorderLayout.NORTH);

        JPanel panelCuerpo = new JPanel();
        panelCuerpo.setLayout(new GridBagLayout());
        panelCuerpo.setBackground(COLOR_LILA_FONDO);
        panelCuerpo.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(COLOR_LILA_FONDO);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblCorreo = new JLabel("Correo institucional:");
        lblCorreo.setFont(FUENTE_LABELS);
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        panelFormulario.add(lblCorreo, gbc);

        txtCorreo = new JTextField(30);
        txtCorreo.setFont(FUENTE_CAMPOS);
        txtCorreo.setBorder(new LineBorder(Color.GRAY, 1));
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 1.0;
        panelFormulario.add(txtCorreo, gbc);

        gbc.gridy = 2;
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 15)), gbc);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(FUENTE_LABELS);
        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.WEST;
        panelFormulario.add(lblContrasena, gbc);

        JPanel panelPasswordRow = new JPanel(new BorderLayout(5, 0));
        panelPasswordRow.setBackground(COLOR_LILA_FONDO);

        pswContra = new JPasswordField();
        pswContra.setFont(FUENTE_CAMPOS);
        pswContra.setBorder(new LineBorder(Color.GRAY, 1));
        panelPasswordRow.add(pswContra, BorderLayout.CENTER);

        JButton btnOjo = new JButton();
        btnOjo.setPreferredSize(new Dimension(30, 30));
        btnOjo.setBackground(Color.WHITE);
        btnOjo.setBorder(new LineBorder(COLOR_MORADO_ACENTO, 1));

        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 1.0;
        panelFormulario.add(panelPasswordRow, gbc);

        gbc.gridy = 5;
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 25)), gbc);

        btnIniciarSesion = new JButton("Iniciar sesión");
        darEstiloBoton(btnIniciarSesion);
        gbc.gridx = 0; gbc.gridy = 6; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(btnIniciarSesion, gbc);
        btnIniciarSesion.addActionListener(e -> iniciarSesion());

        gbc.gridy = 7;
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 50)), gbc);

        JLabel lblPregunta = new JLabel("¿No tienes una cuenta todavia?");
        lblPregunta.setFont(FUENTE_PREGUNTA);
        lblPregunta.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 8; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(lblPregunta, gbc);

        btnCrearCuenta = new JButton("Crear cuenta");
        darEstiloBoton(btnCrearCuenta);
        btnCrearCuenta.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 9; gbc.fill = GridBagConstraints.NONE; gbc.anchor = GridBagConstraints.CENTER;
        panelFormulario.add(btnCrearCuenta, gbc);
        btnCrearCuenta.addActionListener(e -> crearCuenta());
        
        panelCuerpo.add(panelFormulario);

        add(panelCuerpo, BorderLayout.CENTER);
    }
    
    private void iniciarSesion(){
        String correo = txtCorreo.getText().trim();
        char[] contraChar = pswContra.getPassword();
        String contra = "";        
        for (char c : contraChar) {
            contra += c;
        }
        
        if(correo.trim().isEmpty() || contra.isEmpty()){
            JOptionPane.showMessageDialog(this, "Todos los campos son necesarios", "Campos faltantes", JOptionPane.ERROR_MESSAGE);
        }
        if(!regex.validaCorreoEstudiante(txtCorreo.getText().trim())){
            JOptionPane.showMessageDialog(this, "Usa tu correo institucional (nombre.apellidoID@potros.itson.edu.mx)", "Correo erroneo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<Estudiante> ests = estDAO.buscarPorCorreo(correo, JPAUtil.getEntityManager());
        if(ests.isEmpty()){
            JOptionPane.showMessageDialog(this, "Este correo no esta registrado", "Correo no registrado", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Estudiante e = ests.get(0);
        if(!e.getContrasena().equals(contra)){
            JOptionPane.showMessageDialog(this, "Contraseña incorrecta, intenta de nuevo", 
                    "Contraseña incorrecta", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        frmVentanaPrincipal ventana = new frmVentanaPrincipal(e);
        ventana.setVisible(true);
        frmInicio padre = (frmInicio) SwingUtilities.getWindowAncestor(this);
        padre.dispose();
    }
    
    private void crearCuenta(){
        frmInicio padre = (frmInicio) SwingUtilities.getWindowAncestor(this);
        padre.pasarACrearCuenta();
    }

    private void darEstiloBoton(JButton boton) {
        boton.setFont(FUENTE_BOTONES);
        boton.setForeground(Color.BLACK); // Texto negro como en la imagen decorada
        boton.setBackground(Color.WHITE); // Fondo blanco
        boton.setFocusPainted(false); 

        Border bordeLinea = new LineBorder(COLOR_MORADO_ACENTO, 2);
        Border bordePadding = new EmptyBorder(10, 20, 10, 20);
        boton.setBorder(new CompoundBorder(bordeLinea, bordePadding));
    }
}

