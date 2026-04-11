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
    /**
     *
     * @param nombre
     * @param em
     * @return Lista de estudiantes que su nombre coincida con la busqueda
     */
    List<Estudiante> buscarPorNombre(String nombre, EntityManager em);
    
    
    /**
     * 
     * @param correo
     * @param em
     * @return Estudiante con el correo dado
     */
    Estudiante buscarPorCorreo(String correo, EntityManager em);
    

    /**
     *
     * @param idEstudiante
     * @param em
     * @return Lista de los estudiantes a los que ha dado like
     */
    List<Estudiante> likesDados(Long idEstudiante, EntityManager em);

    /**
     *
     * @param idEstudiante
     * @param em
     * @return Lista de estudiantes que le han dado like
     */
    List<Estudiante> likesRecibidos(Long idEstudiante, EntityManager em);
}