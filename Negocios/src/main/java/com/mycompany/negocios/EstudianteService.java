/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocios;

import jakarta.persistence.EntityManager;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Estudiante;
import org.itson.persistencia.EstudianteDAO;
import org.itson.persistencia.IEstudianteDAO;
import org.itson.utilidades.JPAUtil;
import org.itson.utilidades.RegexUtil;

/**
 *
 * @author axelm
 */
public class EstudianteService implements IEstudianteService{
    
    private IEstudianteDAO estudianteDAO;
    private RegexUtil regex;

    public EstudianteService() {
        this.estudianteDAO = new EstudianteDAO();
        this.regex = new RegexUtil();
    }
    
    public boolean validar(Estudiante estudiante){
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }
        if (estudiante.getNombre() == null || estudiante.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante es obligatorio.");
        }
        if(!regex.validaCorreoEstudiante(estudiante.getCorreo())){
            throw new IllegalArgumentException("El correo del estudiante debe ser el institucional");
        }
        return true;
    }

    @Override
    public List<Estudiante> buscarPorNombre(String nombre) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return estudianteDAO.buscarPorNombre(nombre, em);
        }finally{
            em.close();
        }
    }

    @Override
    public List<Estudiante> likesDados(Long idEstudiante) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return estudianteDAO.likesDados(idEstudiante, em);
        }finally{
            em.close();
        }
    }

    @Override
    public List<Estudiante> likesRecibidos(Long idEstudiante) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return estudianteDAO.likesRecibidos(idEstudiante, em);
        }finally{
            em.close();
        }
    }

    @Override
    public void guardar(Estudiante estudiante) {
        validar(estudiante);
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            estudianteDAO.agregar(estudiante, em);
            em.getTransaction().commit();
        }catch(Exception ex){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw ex;
        }finally{
            em.close();
        }
    }

    @Override
    public void actualizar(Estudiante estudiante) {
        validar(estudiante);
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            estudianteDAO.actualizar(estudiante, em);
            em.getTransaction().commit();
        }catch(Exception ex){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw ex;
        }finally{
            em.close();
        }
    }

    @Override
    public Estudiante buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return estudianteDAO.buscarPorId(id, em);
        }finally{
            em.close();
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            estudianteDAO.eliminar(id, em);
            em.getTransaction().commit();
        }catch(Exception ex){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw ex;
        }finally{
            em.close();
        }
    }

    @Override
    public List<Estudiante> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return estudianteDAO.listar(em);
        }finally{
            em.close();
        }
    }

    @Override
    public byte[] getFotoDePerfil(Long idEstudiante) {
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return estudianteDAO.getFotoDePerfil(idEstudiante, em);
        }finally{
            em.close();
        }
    }
    
    public DefaultTableModel obtenerTablaEstudiantes() {
        EntityManager em = JPAUtil.getEntityManager();
        String[] columnas = {"NOMBRE", "CARRERA", "SEMESTRE"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        List<Estudiante> lista = estudianteDAO.listar(em);
        lista.forEach(e -> modelo.addRow(new Object[]{e.getNombre(), e.getCarrera(), e.getSemestre()}));
        return modelo;
    }
    
    public DefaultTableModel obtenerTablaEstudiantesPorFiltro(String filtro) {
        EntityManager em = JPAUtil.getEntityManager();
        String[] columnas = {"NOMBRE", "CARRERA", "SEMESTRE"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        try {
            List<Estudiante> lista = estudianteDAO.buscarPorNombre(filtro, em);
            System.out.println("Tam lista: " + lista.size());
            for (Estudiante e : lista) {
                Object[] fila = {e.getNombre(), e.getCarrera(), e.getSemestre()};
                modelo.addRow(fila);
            }
        } catch (Throwable t) {
            System.err.println("Error al filtrar estudiantes: " + t.getMessage());
        } finally {
            em.close();
        }
        return modelo;
    }
    
    public DefaultTableModel obtenerTablaConLista(List<Estudiante> estudiantes){
        String[] columnas = {"NOMBRE", "CARRERA", "SEMESTRE"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        estudiantes.forEach(e -> modelo.addRow(new Object[]{e.getNombre(), e.getCarrera(), e.getSemestre().toString()}));
        return modelo;
    }
}