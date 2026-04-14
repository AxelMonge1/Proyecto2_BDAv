/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.presentacion;

import com.mycompany.negocios.EstudianteService;
import com.mycompany.negocios.IMatchService;
import com.mycompany.negocios.MatchService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import models.Estudiante;
import models.Match;

/**
 *
 * @author Jenifer Flores
 * @author EdgarUris
 */
public class pnlMatches extends JPanel {
    
    private JTable tablaResultados;
    private JTextField txtBusqueda;
    private JComboBox<String> cmbTipoBusqueda;
    private JButton btnRegresar;
    private JButton btnBuscar;
    private MatchService matchService;
    private Estudiante e;
    private frmVentanaPrincipal padre;
    private EstudianteService estService;

    public pnlMatches(frmVentanaPrincipal padre, Estudiante estEnSesion) {
        this.e = estEnSesion;
        this.padre = padre;
        matchService = new MatchService();
        estService = new EstudianteService();
        setSize(800, 500);
        setLayout(new BorderLayout(10, 10));

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelBusqueda.add(new JLabel("Buscar por:"));
        
        cmbTipoBusqueda = new JComboBox<>(new String[]{"Nombre", "Intereses"});
        panelBusqueda.add(cmbTipoBusqueda);

        btnBuscar = crearBoton("Buscar", new Font("Segoe UI", Font.BOLD, 16));
        
        txtBusqueda = new JTextField(20);
        panelBusqueda.add(txtBusqueda);
        panelBusqueda.add(btnBuscar);

        add(panelBusqueda, BorderLayout.NORTH);

        tablaResultados = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaResultados);
        
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        panelTabla.add(scrollPane, BorderLayout.CENTER);

        add(panelTabla, BorderLayout.CENTER);
        
        JPanel panelConUnBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelConUnBoton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnRegresar = crearBoton("Regresar al menú", new Font("Segoe UI", Font.BOLD, 16));
        panelConUnBoton.add(btnRegresar);

        add(panelConUnBoton, BorderLayout.SOUTH);
        
        cargarMatches();
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
    
    private void cargarMatches(){
        List<Match> matches = matchService.listarMatchesPorEstudiante(padre.getEstudianteEnSesion().getId());
        DefaultTableModel modelo = matchService.obtenerTablaConLista(matches, padre.getEstudianteEnSesion().getId());
        tablaResultados.setModel(modelo);
    }   
}