/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocios;

import jakarta.persistence.EntityManager;
import java.util.List;
import models.Interaccion;
import models.Match;
import models.TipoInteraccion;
import org.itson.persistencia.InteraccionDAO;
import org.itson.utilidades.JPAUtil;

/**
 *
 * @author axelm
 */
public class InteraccionService implements iInteraccionService {

    private InteraccionDAO interaccionDAO;

    public InteraccionService() {
        this.interaccionDAO = new InteraccionDAO();
    }

    @Override
    public void guardar(Interaccion interaccion) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            interaccionDAO.agregar(interaccion, em);
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
    public void actualizar(Interaccion interaccion) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            interaccionDAO.actualizar(interaccion, em);
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
    public Interaccion buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return interaccionDAO.buscarPorId(id, em);
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            interaccionDAO.eliminar(id, em);
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
    public List<Interaccion> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return interaccionDAO.listar(em);
        } finally {
            em.close();
        }
    }

    
    public void guardarConMatch(Interaccion interaccion) {
        EntityManager em = JPAUtil.getEntityManager();
        MatchService matchService = new MatchService();

        try {
            em.getTransaction().begin();

            
            interaccionDAO.agregar(interaccion, em);

            Long idOrigen = interaccion.getEstudianteOrigen().getId();
            Long idDestino = interaccion.getEstudianteDestino().getId();

            
            Interaccion inversa = interaccionDAO.buscarInteraccion(
                idDestino, idOrigen, em
            );

           
            if (interaccion.getTipoInteraccion() == TipoInteraccion.LIKE && inversa != null) {

                boolean existeMatch = matchService.existeMatch(idOrigen, idDestino);

                if (!existeMatch) {
                    Match match = new Match();
                    match.setEstudiante1(interaccion.getEstudianteOrigen());
                    match.setEstudiante2(interaccion.getEstudianteDestino());

                    em.persist(match);
                }
            }

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
}