/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import models.Estudiante;
import models.Interaccion;
import models.TipoInteraccion;

/**
 *
 * @author axelm
 */
public class EstudianteDAO implements IEstudianteDAO {
    @Override
    public List<Estudiante> buscarPorNombre(String nombre, EntityManager em) {
        TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e WHERE nombre LIKE :nombre", Estudiante.class);
        query.setParameter("nombre", "%" + nombre + "%");
        query.setFirstResult(1);
        query.setMaxResults(100);
        return query.getResultList();
    }

    @Override
    public void agregar(Estudiante estudiante, EntityManager em) {
        em.persist(estudiante);
    }

    @Override
    public void actualizar(Estudiante estudiante, EntityManager em) {
        em.merge(estudiante);
    }

    @Override
    public Estudiante buscarPorId(Long id, EntityManager em) {
        return em.find(Estudiante.class, id);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        Estudiante estudiante = em.find(Estudiante.class,id);
        if(estudiante != null){
            em.remove(estudiante);
        }
    }

    @Override
    public List<Estudiante> listar(EntityManager em) {
        TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e ORDER BY e.id", Estudiante.class);
        query.setFirstResult(0);
        query.setMaxResults(100);
        return query.getResultList();
    }

    @Override
    public List<Estudiante> likesDados(Long idEstudiante, EntityManager em) {
        Estudiante estudiante = em.find(Estudiante.class, idEstudiante);
        if(estudiante != null){
            List<Interaccion> interaccionesHechas = estudiante.getInteraccionesHechas().stream().toList();
            List<Estudiante> estudiantes = new ArrayList<>();
            interaccionesHechas.forEach(i -> { 
                    if(i.getTipoInteraccion() == TipoInteraccion.LIKE){
                        estudiantes.add(i.getEstudianteDestino());
                    }
            });
            return estudiantes;
        }
        return null;
    }

    @Override
    public List<Estudiante> likesRecibidos(Long idEstudiante, EntityManager em) {
        Estudiante estudiante = em.find(Estudiante.class, idEstudiante);
        if(estudiante != null){
            List<Interaccion> interaccionesRecibidas = estudiante.getInteraccionesRecibidas().stream().toList();
            List<Estudiante> estudiantes = new ArrayList<>();
            interaccionesRecibidas.forEach(i -> { 
                    if(i.getTipoInteraccion() == TipoInteraccion.LIKE){
                        estudiantes.add(i.getEstudianteOrigen());
                    }
            });
            return estudiantes;
        }
        return null;
    }
}