/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.presentacion;

import com.mycompany.negocios.EstudianteService;
import com.mycompany.negocios.IEstudianteService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import models.Aficion;
import models.Estudiante;
import org.itson.persistencia.AficionDAO;
import org.itson.persistencia.IAficionDAO;
import org.itson.utilidades.JPAUtil;

/**
 *
 * @author EdgarUris
 */
public class pnlIntereses extends JPanel {
    
    private List<JCheckBox> interesesLista;
    private List<JCheckBox> hobbiesLista;
    private Estudiante estEnCreacion;
    private IAficionDAO aficionDAO; 
    private IEstudianteService estudianteService;
    

    public pnlIntereses(Estudiante est) {
        this.estEnCreacion = est;
        aficionDAO = new AficionDAO();
        estudianteService = new EstudianteService();
        interesesLista = new ArrayList<JCheckBox>();
        hobbiesLista = new ArrayList<JCheckBox>();
        
        setSize(700, 500);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        //logo
        JLabel logoLabel = new JLabel(new ImageIcon("resources/unilink_logo.png"));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(logoLabel, BorderLayout.NORTH);

        //panel con las columnas
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        centerPanel.setBackground(mainPanel.getBackground());

        //columna izquierda
        JPanel hobbiesPanel = new JPanel();
        hobbiesPanel.setLayout(new BoxLayout(hobbiesPanel, BoxLayout.Y_AXIS));
        hobbiesPanel.setBackground(mainPanel.getBackground());
        hobbiesPanel.setBorder(BorderFactory.createTitledBorder("Selecciona tus hobbies (Mínimo 2)"));

        String[] hobbies = {"Arte", "Fotografía", "Música", "Videojuegos", "Baile", "Coleccionismo", "Jardinería", "Lectura"};
        for (String hobby : hobbies) {
            JCheckBox checkBox = new JCheckBox(hobby);
            checkBox.setFont(new Font("Segoe UI", Font.BOLD, 14));
            hobbiesPanel.add(checkBox);
            hobbiesLista.add(checkBox);
        }

        //columna derecha
        JPanel interesesPanel = new JPanel();
        interesesPanel.setLayout(new BoxLayout(interesesPanel, BoxLayout.Y_AXIS));
        interesesPanel.setBackground(mainPanel.getBackground());
        interesesPanel.setBorder(BorderFactory.createTitledBorder("Selecciona temas de tu interés (Mínimo 2)"));

        String[] intereses = {"Deportes", "Programación", "Robótica", "Idiomas", "CineYSeries", "Moda", "Turismo", "Gastronomía"};
        for (String interes : intereses) {
            JCheckBox checkBox = new JCheckBox(interes);
            checkBox.setFont(new Font("Segoe UI", Font.BOLD, 14));
            interesesPanel.add(checkBox);
            interesesLista.add(checkBox);
        }

        centerPanel.add(hobbiesPanel);
        centerPanel.add(interesesPanel);

        //boton para crear cuenta
        JButton btnCrearCuenta = new JButton("Crear cuenta");
        btnCrearCuenta.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCrearCuenta.setBackground(Color.WHITE);
        btnCrearCuenta.setForeground(new Color(128,0,128));
        btnCrearCuenta.setFocusPainted(false);
        btnCrearCuenta.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        btnCrearCuenta.addActionListener(e -> {
            if(validarIntereses() || validarHobbies()){
                JOptionPane.showConfirmDialog(this, "Cuenta creada con exito","Cuenta creada",JOptionPane.INFORMATION_MESSAGE);
                estudianteService.guardar(est);
        
                frmCrearCuenta padre = (frmCrearCuenta) SwingUtilities.getWindowAncestor(this);
                padre.volverAInicioSesion();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(mainPanel.getBackground());
        buttonPanel.add(btnCrearCuenta);

        //panel principal
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
    
    private boolean validarIntereses(){
        int min = 0;
        for (JCheckBox jCheckBox : interesesLista) {
            if(jCheckBox.isSelected()){
                min++;
            }
        }
        if(min < 2){
            JOptionPane.showMessageDialog(this, "Selecciona al menos 2 intereses","Faltan intereses",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        for (JCheckBox jCheckBox : interesesLista) {
            if(jCheckBox.isSelected()){
                String nombreInteres = jCheckBox.getText();
                Aficion in = aficionDAO.buscarPorNombre(nombreInteres, JPAUtil.getEntityManager());
                in.getEstudiantes().add(estEnCreacion);
                estEnCreacion.getAficiones().add(in);
            }
        }
        return true;
    }
    
    private boolean validarHobbies(){
        int min = 0;
        for (JCheckBox jCheckBox : hobbiesLista) {
            if(jCheckBox.isSelected()){
                min++;
            }
        }
        if(min < 2){
            JOptionPane.showMessageDialog(this, "Selecciona al menos 2 hobbies","Faltan hobbies",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        for (JCheckBox jCheckBox : hobbiesLista) {
            if(jCheckBox.isSelected()){
                String nombreInteres = jCheckBox.getText();
                Aficion hob = aficionDAO.buscarPorNombre(nombreInteres, JPAUtil.getEntityManager());
                hob.getEstudiantes().add(estEnCreacion);
                estEnCreacion.getAficiones().add(hob);
            }
        }
        return true;
    }

    
}  

