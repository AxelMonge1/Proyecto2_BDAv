/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.presentacion;

import com.mycompany.negocios.EstudianteService;
import com.mycompany.negocios.InteraccionService;
import com.mycompany.negocios.iInteraccionService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import models.Estudiante;
import models.Interaccion;
import models.TipoInteraccion;
import org.itson.persistencia.InteraccionDAO;
import org.itson.persistencia.iInteraccionDAO;
import org.itson.utilidades.JPAUtil;

/**
 * @author EdgarUris
 * @author Jenifer Flores
 */
public class pnlExplorar extends JPanel {

    private JLabel lblFoto, lblNombre, lblCarrera, lblDescripcion;
    private JButton btnLike, btnDislike, btnSalir;
    private List<Estudiante> listaEstudiantes;
    private int indiceActual = 0;
    private iInteraccionService interaccionService;
    private frmVentanaPrincipal padre;

    public pnlExplorar(frmVentanaPrincipal padre, List<Estudiante> estudiantes){
        this.padre = padre;
        Estudiante enSesion = padre.getEstudianteEnSesion();
        this.listaEstudiantes = estudiantes;
        try{
            estudiantes.remove(enSesion);
        }
        catch(Exception e){
            System.out.println("Ni yo se que paso, pero fue malo");
        }
        interaccionService = new InteraccionService();

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 240, 255));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setSize(400,400);

        // Foto
        lblFoto = new JLabel();
        lblFoto.setSize(100,100);
        lblFoto.setHorizontalAlignment(JLabel.CENTER);
        lblFoto.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(lblFoto, BorderLayout.NORTH);
        
        
        // Panel info
        JPanel panelInfo = new JPanel(new GridLayout(0, 1, 5, 5));
        panelInfo.setBackground(new Color(245, 240, 255));
        lblNombre = new JLabel();
        lblCarrera = new JLabel();
        lblDescripcion = new JLabel();
        panelInfo.add(lblNombre);
        panelInfo.add(lblCarrera);
        panelInfo.add(lblDescripcion);
        add(panelInfo, BorderLayout.CENTER);

        // Panel botones
        JPanel panelBotones = new JPanel(new GridLayout(0, 1, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        panelBotones.setBackground(new Color(245, 240, 255));
        Font fuenteBoton = new Font("Segoe UI", Font.BOLD, 16);

        btnLike = crearBoton("Me interesa", fuenteBoton);
        btnDislike = crearBoton("No me interesa", fuenteBoton);
        btnSalir = crearBoton("Salir", fuenteBoton);

        panelBotones.add(btnLike);
        panelBotones.add(btnDislike);
        panelBotones.add(btnSalir);
        add(panelBotones, BorderLayout.SOUTH);

        // Listeners
        btnLike.addActionListener(e -> {
            mandarInteraccionLike(indiceActual);
            mostrarSiguientePerfil();
                    });
        btnDislike.addActionListener(e -> {
            mandarInteraccionDislike(indiceActual);
            mostrarSiguientePerfil();
        });
        btnSalir.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Sesión terminada");
            salir();
        });

        // Mostrar primer perfil
        mostrarPerfil(indiceActual);
    }

    private JButton crearBoton(String texto, Font fuente){
        JButton boton = new JButton(texto);
        boton.setFont(fuente);
        boton.setFocusPainted(false);
        boton.setBackground(Color.WHITE);
        boton.setForeground(new Color(0,0,0));
        boton.setBorder(BorderFactory.createLineBorder(new Color(128, 0, 128), 2));
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        boton.setIconTextGap(15);
        return boton;
    }

    private void mostrarPerfil(int indice){
        if(indice >= 0 && indice < listaEstudiantes.size()){
            Estudiante est = listaEstudiantes.get(indice);
            lblNombre.setText("Nombre: " + est.getNombre());
            lblCarrera.setText("Carrera: " + est.getCarrera());
            lblDescripcion.setText("Descripcion: " + est.getDescripcion());
            
            ImageIcon fotoPerfil = null;
            lblFoto.setText("Error al cargar la foto");

            //foto
        try{
            ByteArrayInputStream bais = new ByteArrayInputStream(est.getFoto());
            BufferedImage bi = ImageIO.read(bais);
            Image imagenEscalada = bi.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
            fotoPerfil = new ImageIcon(imagenEscalada);
        }catch(IOException ex){
            ex.printStackTrace();
        }
        if(fotoPerfil == null){
        }else{
            lblFoto.setIcon(fotoPerfil);
        }
        } else {
            lblFoto.setText("Error al cargar la foto de perfil");
            lblNombre.setText("Nombre: -");
            lblCarrera.setText("Carrera: -");
            lblDescripcion.setText("Descripción: -");
            btnLike.setEnabled(false);
            btnDislike.setEnabled(false);
            JOptionPane.showMessageDialog(this, "No hay otros perfiles");
        }
    }

    private void mostrarSiguientePerfil(){
        indiceActual++;
        if(indiceActual < listaEstudiantes.size()){
            mostrarPerfil(indiceActual);
        } else {
            JOptionPane.showMessageDialog(this, "Has llegado al final de los perfiles");
        }
    }
    
    private void mandarInteraccionLike(int indice){
        Estudiante estInteractuado = new EstudianteService().buscarPorId(Long.valueOf(indice+1));
        frmVentanaPrincipal padre = (frmVentanaPrincipal) SwingUtilities.getWindowAncestor(this);
        Estudiante estInteractua = padre.getEstudianteEnSesion();
        
        Interaccion nueva = new Interaccion();
        nueva.setEstudianteDestino(estInteractuado);
        nueva.setEstudianteOrigen(estInteractua);
        nueva.setTipoInteraccion(TipoInteraccion.LIKE);
        
        interaccionService.guardar(nueva);
    }
    
    private void mandarInteraccionDislike(int indice){
        Estudiante estInteractuado = new EstudianteService().buscarPorId(Long.valueOf(indice+1));
        frmVentanaPrincipal padre = (frmVentanaPrincipal) SwingUtilities.getWindowAncestor(this);
        Estudiante estInteractua = padre.getEstudianteEnSesion();
        
        Interaccion nueva = new Interaccion();
        nueva.setEstudianteDestino(estInteractuado);
        nueva.setEstudianteOrigen(estInteractua);
        nueva.setTipoInteraccion(TipoInteraccion.DISLIKE);
        
        interaccionService.guardar(nueva);
    }
    
    private void salir(){
        frmVentanaPrincipal padre = (frmVentanaPrincipal) SwingUtilities.getWindowAncestor(this);
        padre.volverAInicio();
    }
    //lo admitimos, usamos ia, pero no puede ser que 2 interfaces nos tomaran todo un dia omg
    //a la otra hacemos interfaces antes de que asignen el proyecto.
}