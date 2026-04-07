/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.negocios;

import java.util.List;
import models.Match;

/**
 *
 * @author axelm
 */
public interface IMatchService extends IGenericoService<Match, Long>{
    List<Match> listarMatchesPorEstudiante(Long idEstudiante);
}
