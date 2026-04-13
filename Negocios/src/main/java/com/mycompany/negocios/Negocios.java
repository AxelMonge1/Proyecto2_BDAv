/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.negocios;

import models.Aficion;
import models.Estudiante;
import models.Interaccion;
import models.Match;
import models.TipoInteraccion;

/**
 *
 * @author EdgarUris
 */
public class Negocios {

    public static void main(String[] args) {
        AficionService as = new AficionService();
        EstudianteService es = new EstudianteService();
        MatchService ms = new MatchService();
        InteraccionService is = new InteraccionService();
        
        Estudiante e = new Estudiante();
        e.setNombre("Manolito");
        Aficion a = new Aficion();
        a.setNombre("Webonear");
        Estudiante e2 = new Estudiante();
        e2.setNombre("Manolita");
        Interaccion i1 = new Interaccion();
        i1.setEstudianteDestino(e2);
        i1.setEstudianteOrigen(e);
        i1.setTipoInteraccion(TipoInteraccion.LIKE);
        Interaccion i2 = new Interaccion();
        i2.setEstudianteDestino(e);
        i2.setEstudianteOrigen(e2);
        i2.setTipoInteraccion(TipoInteraccion.LIKE);
        Match m = new Match();
        m.setEstudiante1(e);
        m.setEstudiante2(e2);
        
        Long id = 1L;
        as.guardar(a);
        System.out.println("Aficion guardada");
        Aficion af = as.buscarPorId(id);
        System.out.println("Aficion buscada: Id: " + af.getId() + " Nombre: " + af.getNombre());
        a.setNombre("Flojear");
        as.actualizar(a);
        System.out.println("Aficion actualizada");
        System.out.println("Aficiones:\n");
        as.listar().forEach(a1 -> System.out.println("Id: " + a1.getId() + " Nombre: " + a1.getNombre()));
        as.eliminar(id);
        System.out.println("Aficion eliminada");
    }
}
