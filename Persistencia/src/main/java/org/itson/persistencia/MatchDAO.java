/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import models.Estudiante;
import models.Match;

/**
 *
 * @author axelm
 */
public class MatchDAO implements IMatchDAO{

    @Override
    public List<Match> listarMatchesPorEstudiante(Long idEstudiante, EntityManager em) {
        Estudiante estudiante = em.find(Estudiante.class, idEstudiante);
        TypedQuery<Match> query = em.createQuery("SELECT m FROM Match m WHERE m.estudiante1 = :estudiante", Match.class);
        query.setParameter("estudiante", estudiante);
        query.setFirstResult(0);
        query.setMaxResults(100);
        return query.getResultList();
    }

    @Override
    public void agregar(Match match, EntityManager em) {
        em.persist(match);
    }

    @Override
    public void actualizar(Match match, EntityManager em) {
        em.merge(match);
    }

    @Override
    public Match buscarPorId(Long id, EntityManager em) {
        return em.find(Match.class, id);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        Match match = em.find(Match.class, id);
        if(match != null){
            em.remove(match);
        }
    }

    @Override
    public List<Match> listar(EntityManager em) {
        TypedQuery<Match> query = em.createQuery("SELECT m FROM Match m ORDER BY m.id", Match.class);
        query.setFirstResult(0);
        query.setMaxResults(100);
        return query.getResultList();
    }   
}