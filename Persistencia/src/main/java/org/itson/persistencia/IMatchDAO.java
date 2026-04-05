/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.persistencia;

import jakarta.persistence.EntityManager;
import java.util.List;
import models.Match;

/**
 *
 * @author axelm
 */
public interface IMatchDAO extends IGenericoDAO<Match, Long> {
    /**
     *
     * @param idEstudiante
     * @param em
     * @return
     * Lista los matches del estudiante que tiene la sesion iniciada
     */
    List<Match> listarMatchesPorEstudiante(Long idEstudiante, EntityManager em);
}
