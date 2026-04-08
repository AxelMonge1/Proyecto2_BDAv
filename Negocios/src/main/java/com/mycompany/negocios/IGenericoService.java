/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.negocios;

import java.util.List;

/**
 *
 * @author axelm
 */
public interface IGenericoService<T, ID> {
    void guardar(T entidad);
    void actualizar(T entidad);
    T buscarPorId(ID id);
    void eliminar(ID id);
    List<T> listar();
}
