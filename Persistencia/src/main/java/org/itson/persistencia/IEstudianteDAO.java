/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.persistencia;

import jakarta.persistence.EntityManager;
import java.util.List;
import models.Estudiante;

/**
 *
 * @author axelm
 */
public interface IEstudianteDAO extends IGenericoDAO<Estudiante, Long> {
    List<Estudiante> buscarPorNombre(String nombre, EntityManager em);
    List<Estudiante> likesDados(Long idEstudiante, EntityManager em);
    List<Estudiante> likesRecibidos(Long idEstudiante, EntityManager em);
}