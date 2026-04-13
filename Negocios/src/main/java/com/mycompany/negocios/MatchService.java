/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocios;

import jakarta.persistence.EntityManager;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Estudiante;
import models.Match;
import org.itson.persistencia.IMatchDAO;
import org.itson.persistencia.MatchDAO;
import org.itson.utilidades.JPAUtil;

/**
 *
 * @author axelm
 */
public class MatchService implements IMatchService{
    private IMatchDAO matchDAO;

    public MatchService() {
        this.matchDAO = new MatchDAO();
    }

    @Override
    public List<Match> listarMatchesPorEstudiante(Long idEstudiante) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return matchDAO.listarMatchesPorEstudiante(idEstudiante, em);
        } finally {
            em.close();
        }
    }

    @Override
    public void guardar(Match match) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            matchDAO.agregar(match, em);
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
    public void actualizar(Match match) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            matchDAO.actualizar(match, em);
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
    public Match buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return matchDAO.buscarPorId(id, em);
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            matchDAO.eliminar(id, em);
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
    public List<Match> listar() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return matchDAO.listar(em);
        } finally {
            em.close();
        }
    }
    
     public DefaultTableModel obtenerTablaConLista(List<Match> matches, Long idActual) {
        String[] columnas = {"Nombre", "Carrera", "Semestre"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        matches.forEach(m -> {
            Estudiante otro;

            if (m.getEstudiante1().getId().equals(idActual)) {
                otro = m.getEstudiante2();
            } else {
                otro = m.getEstudiante1();
            }

            modelo.addRow(new Object[]{
                otro.getNombre(),
                otro.getCarrera(),
                otro.getSemestre()
            });
        });

        return modelo;
    }
    
    public boolean existeMatch(Long id1, Long id2) {
    EntityManager em = JPAUtil.getEntityManager();
    try {
        Match match = em.createQuery(
            "SELECT m FROM Match m WHERE " +
            "(m.estudiante1.id = :id1 AND m.estudiante2.id = :id2) OR " +
            "(m.estudiante1.id = :id2 AND m.estudiante2.id = :id1)",
            Match.class
        )
        .setParameter("id1", id1)
        .setParameter("id2", id2)
        .getResultStream()
        .findFirst()
        .orElse(null);

        return match != null;

    } finally {
        em.close();
    }
}
    
    
}