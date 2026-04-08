/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocios;

import jakarta.persistence.EntityManager;
import java.util.List;
import models.Estudiante;
import org.itson.persistencia.EstudianteDAO;
import org.itson.persistencia.IEstudianteDAO;
import org.itson.utilidades.JPAUtil;

/**
 *
 * @author axelm
 */
public class EstudianteService implements IEstudianteService{
    
    private IEstudianteDAO estudianteDAO;

    public EstudianteService() {
        this.estudianteDAO = new EstudianteDAO();
    }
    
    private void validar(Estudiante estudiante){
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }
        if (estudiante.getNombre() == null || estudiante.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante es obligatorio.");
        }
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
}