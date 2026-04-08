/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.persistencia;

import jakarta.persistence.EntityManager;
import models.Aficion;
import models.Estudiante;
import models.Interaccion;
import models.Match;
import models.TipoInteraccion;
import org.itson.utilidades.JPAUtil;

/**
 *
 * @author axelm
 */
public class Persistencia {

    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        
        EstudianteDAO estudianteDAO = new EstudianteDAO();
        AficionDAO aficionDAO = new AficionDAO();
        InteraccionDAO interaccionDAO = new InteraccionDAO();
        MatchDAO matchDAO = new MatchDAO();
        
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
        
        em.getTransaction().begin();
        
        estudianteDAO.agregar(e, em);
        estudianteDAO.agregar(e2, em);
        aficionDAO.agregar(a, em);
        interaccionDAO.agregar(i1, em);
        interaccionDAO.agregar(i2, em);
        matchDAO.agregar(m, em);
        
        em.getTransaction().commit();
        
        em.close();
    }
}