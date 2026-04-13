/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import models.Aficion;
import models.Estudiante;

/**
 *
 * @author axelm
 */
public class AficionDAO implements IAficionDAO{

    @Override
    public List<Estudiante> estAficionesSim(Long idAficion, EntityManager em) {
        Aficion aficion = em.find(Aficion.class, idAficion);
        if(aficion != null){
            return aficion.getEstudiantes().stream().toList();
        }
        return null;
    }

    @Override
    public void agregar(Aficion aficion, EntityManager em) {
        em.persist(aficion);
    }

    @Override
    public void actualizar(Aficion aficion, EntityManager em) {
        em.merge(aficion);
    }

    @Override
    public Aficion buscarPorId(Long id, EntityManager em) {
        return em.find(Aficion.class, id);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        Aficion aficion = em.find(Aficion.class, id);
        if(aficion != null){
            em.remove(aficion);
        }
    }

    @Override
    public List<Aficion> listar(EntityManager em) {
        TypedQuery<Aficion> query = em.createQuery("SELECT a FROM Aficion a ORDER BY a.id", Aficion.class);
        query.setFirstResult(0);
        query.setMaxResults(100);
        return query.getResultList();
    }
    
    @Override
    public Aficion buscarPorNombre(String nombre, EntityManager em){
        TypedQuery<Aficion> query = em.createQuery("SELECT a FROM Aficion a WHERE a.nombre LIKE :nombre", Aficion.class);
        query.setParameter("nombre", "%" + nombre + "%");
        query.setFirstResult(0);
        query.setMaxResults(100);
        return query.getSingleResult();
    }
}