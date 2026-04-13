/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.persistencia;

import jakarta.persistence.EntityManager;
import java.util.List;
import models.Aficion;
import models.Estudiante;

/**
 *
 * @author axelm
 */
public interface IAficionDAO extends IGenericoDAO<Aficion, Long> {

    /**
     *
     * @param idAficion
     * @param em
     * @return
     * Devuelve estudiantes con las mismas aficiones
     */
    List<Estudiante> estAficionesSim(Long idAficion, EntityManager em);
    
    /**
     * 
     * @param nombre
     * @param em
     * @return aficion con un nombre especifico
     */
    Aficion buscarPorNombre(String nombre, EntityManager em);
}
