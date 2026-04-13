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
    
    public DefaultTableModel obtenerTablaConLista(List<Match> matches){
        String[] columnas = {"Nombre", "Carrera", "Semestre"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        matches.forEach(e -> modelo.addRow(new Object[]{e.getEstudiante1().getNombre(), e.getEstudiante1().getCarrera(), e.getEstudiante1().getSemestre()}));
        return modelo;
    }
}