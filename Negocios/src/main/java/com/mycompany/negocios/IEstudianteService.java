/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.negocios;

import java.util.List;
import models.Estudiante;

/**
 *
 * @author axelm
 */
public interface IEstudianteService extends IGenericoService<Estudiante, Long>{
    List<Estudiante> buscarPorNombre(String nombre);
    List<Estudiante> likesDados(Long idEstudiante);
    List<Estudiante> likesRecibidos(Long idEstudiante);
}
