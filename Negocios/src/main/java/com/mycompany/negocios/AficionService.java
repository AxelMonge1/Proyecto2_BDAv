/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocios;

import jakarta.persistence.EntityManager;
import java.util.List;
import models.Aficion;
import models.Estudiante;
import models.TipoAficion;
import org.itson.persistencia.AficionDAO;
import org.itson.persistencia.IAficionDAO;
import org.itson.utilidades.JPAUtil;

/**
 *
 * @author axelm
 */
public class AficionService implements IAficionService{
    private IAficionDAO aficionDAO;

    public AficionService() {
        this.aficionDAO = new AficionDAO();
    }
    
    public void inicializarAficiones(){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            if (aficionDAO.listar(em).isEmpty()) {
                em.getTransaction().begin(); 
                String[] aficionesHobbies = {"Arte","Fotografia","Musica","Videojuegos","Baile","Coleccionismo","Jardineria","Lectura"};
                for (String nombre : aficionesHobbies) {
                    Aficion nueva = new Aficion();
                    nueva.setNombre(nombre);
                    nueva.setTipoAficion(TipoAficion.HOBBY);
                    aficionDAO.agregar(nueva, em);
                }
                String[] aficionesIntereses = {"Deportes","Programacion","Robotica","Idiomas","CineYSeries","Moda","Turismo","Gastronomia"};
                for (String nombre : aficionesIntereses) {
                    Aficion nueva = new Aficion();
                    nueva.setNombre(nombre);
                    nueva.setTipoAficion(TipoAficion.INTERES);
                    aficionDAO.agregar(nueva, em);
                }
                em.getTransaction().commit(); 
                System.out.println("Aficiones inicializadas correctamente.");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Estudiante> estAficionesSim(Long idAficion) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return aficionDAO.estAficionesSim(idAficion, em);
        } finally {
            em.close();
        }
    }

    @Override
    public void guardar(Aficion aficion) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            aficionDAO.agregar(aficion, em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Aficion aficion) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            aficionDAO.actualizar(aficion, em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public Aficion buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return aficionDAO.buscarPorId(id, em);
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            aficionDAO.eliminar(id, em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Aficion> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return aficionDAO.listar(em);
        } finally {
            em.close();
        }
    }    
}