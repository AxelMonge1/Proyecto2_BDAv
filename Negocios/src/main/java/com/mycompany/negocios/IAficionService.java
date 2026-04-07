/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.negocios;

import java.util.List;
import models.Aficion;
import models.Estudiante;

/**
 *
 * @author axelm
 */
public interface IAficionService extends IGenericoService<Aficion, Long> {
    List<Estudiante> estAficionesSim(Long idAficion);
}
