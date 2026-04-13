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
import org.itson.persistencia.EstudianteDAO;
import org.itson.persistencia.IEstudianteDAO;
import org.itson.utilidades.JPAUtil;

public class frmVentanaPrincipal extends JFrame {

    private Estudiante est;
    private JPanel panelBotones = new JPanel(new GridLayout(0, 1, 15, 15));
    private JLabel logoLabel = new JLabel();
    private JButton btnAtras;
    private IEstudianteDAO estDAO;

    public frmVentanaPrincipal(Estudiante estEnSesion) {
        this.est = estEnSesion;
        estDAO = new EstudianteDAO();
        setTitle("UniLink");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        btnAtras = crearBoton("Atras", new Font("Segoe UI", Font.BOLD, 16));
        btnAtras.setVisible(false);
        btnAtras.addActionListener(e -> inicioSistema());
        JPanel navPanel = new JPanel(new BorderLayout());
        navPanel.setBackground(new Color(245, 240, 255));
        navPanel.add(btnAtras, BorderLayout.WEST);
        navPanel.add(logoLabel, BorderLayout.CENTER);
        add(navPanel, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        inicioSistema();
        setVisible(true);
    }

    private void inicioSistema() {
        btnAtras.setVisible(false);
        //Logo central
        ImageIcon icono = new ImageIcon(getClass().getResource("/logoUK.png"));
        Image imagenOriginal = icono.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        logoLabel.setIcon(iconoEscalado);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        //Panel para botones
        panelBotones.removeAll();
        panelBotones.setLayout(new GridLayout(0, 1, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panelBotones.setBackground(new Color(245, 240, 255));
        //Botones
        Font fuenteBoton = new Font("Segoe UI", Font.BOLD, 16);
        JButton btnExplorar = crearBoton("Explorar", fuenteBoton);
        JButton btnMiPerfil = crearBoton("Mi perfil", fuenteBoton);
        JButton btnBuscarGente = crearBoton("Buscar personas", fuenteBoton);
        JButton btnMisMatches = crearBoton("Mis Matches", fuenteBoton);
        JButton btnSalir = crearBoton("Salir", fuenteBoton);
        //Eventos de botones
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
        btnBuscarGente.addActionListener(e -> abrirBuscarPersonas());
        btnMisMatches.addActionListener(e -> {
            try {
                abrirMatches();
            } catch (IOException ex) {
                Logger.getLogger(frmVentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnSalir.addActionListener(e -> cerrarSesion());
        //Poner botones al panel
        panelBotones.add(btnMiPerfil);
        panelBotones.add(btnExplorar);
        panelBotones.add(btnMisMatches);
        panelBotones.add(btnBuscarGente);
        panelBotones.add(btnSalir);
        panelBotones.revalidate();
        panelBotones.repaint();
    }

    private void salirDelSistema() {
        int confir = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea salir? Su sesión se cerrará", "Confirmar salida",
                JOptionPane.YES_OPTION);
        if (confir == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private JButton crearBoton(String texto, Font fuente) {
        JButton boton = new JButton(texto);
        boton.setFont(fuente);
        boton.setFocusPainted(false);
        boton.setBackground(Color.WHITE);
        boton.setForeground(new Color(0, 0, 0));
        boton.setBorder(BorderFactory.createLineBorder(new Color(128, 0, 128), 2));
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        boton.setIconTextGap(15);
        return boton;
    }

    private void abrirMenuExploracion() throws IOException {
        btnAtras.setVisible(true);
        pnlExplorar explorar = new pnlExplorar(this, estDAO.listar(JPAUtil.getEntityManager()));
        minimizarLogo();
        panelBotones.removeAll();
        panelBotones.setLayout(new BorderLayout());
        panelBotones.add(explorar, BorderLayout.CENTER);
        panelBotones.revalidate();
        panelBotones.repaint();
    }

    private void abrirMiPerfil() throws IOException{
        btnAtras.setVisible(true);
        pnlPerfilEstudiante perfil = new pnlPerfilEstudiante(est);
        minimizarLogo();
        panelBotones.removeAll();
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBotones.setLayout(new BorderLayout());
        panelBotones.add(perfil, BorderLayout.CENTER);
        panelBotones.revalidate();
        panelBotones.repaint();
    }
    
    private void abrirBuscarPersonas(){
        btnAtras.setVisible(true);
        pnlBuscarPersonas buscar = new pnlBuscarPersonas();
        minimizarLogo();
        panelBotones.removeAll();
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBotones.setLayout(new BorderLayout());
        panelBotones.add(buscar, BorderLayout.CENTER);
        panelBotones.revalidate();
        panelBotones.repaint();
    }

    private void abrirMatches() throws IOException {
        btnAtras.setVisible(true);
        pnlMatches matches = new pnlMatches(this, est);
        minimizarLogo();
        panelBotones.removeAll();
        panelBotones.setLayout(new BorderLayout());
        panelBotones.add(matches, BorderLayout.CENTER);
        panelBotones.revalidate();
        panelBotones.repaint();
    }

    public void minimizarLogo() {
        ImageIcon icono = new ImageIcon(getClass().getResource("/logoUK.png"));
        Image imagenOriginal = icono.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        logoLabel.setIcon(iconoEscalado);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        revalidate();
        repaint();
    }

    public void ver() throws IOException {
        new frmVentanaPrincipal(est).setVisible(true);
    }

    public Estudiante getEstudianteEnSesion() {
        return est;
    }
    
    public void volverAInicio(){
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        btnAtras = crearBoton("Atras", new Font("Segoe UI", Font.BOLD, 16));
        btnAtras.setVisible(false);
        btnAtras.addActionListener(e -> inicioSistema());
        JPanel navPanel = new JPanel(new BorderLayout());
        navPanel.setBackground(new Color(245, 240, 255));
        navPanel.add(btnAtras, BorderLayout.WEST);
        navPanel.add(logoLabel, BorderLayout.CENTER);
        add(navPanel, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        inicioSistema();
        setVisible(true);
    }
    
    private void cerrarSesion(){
        frmInicio inicio = new frmInicio();
        inicio.setVisible(true);
        this.dispose();
    }
}