/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import models.Interaccion;

/**
 *
 * @author axelm
 */
public class InteraccionDAO implements iInteraccionDAO {
    @Override
    public void agregar(Interaccion interaccion, EntityManager em) {
        em.persist(interaccion);
    }

    @Override
    public void actualizar(Interaccion interaccion, EntityManager em) {
        em.merge(interaccion);
    }

    @Override
    public Interaccion buscarPorId(Long id, EntityManager em) {
        return em.find(Interaccion.class, id);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        Interaccion interaccion = em.find(Interaccion.class, id);
        if(interaccion != null){
            em.remove(interaccion);
        }
    }

    @Override
    public List<Interaccion> listar(EntityManager em) {
        TypedQuery<Interaccion> query = em.createQuery("SELECT i FROM Interaccion i ORDER BY i.id", Interaccion.class);
        query.setFirstResult(0);
        query.setMaxResults(100);
        return query.getResultList();
    }
    
    
}