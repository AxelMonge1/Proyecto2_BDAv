/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.itson.proyecto_bdav;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author axelm
 */
public class Proyecto_BDAv {

    public static void main(String[] args){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TinderPU");
        EntityManager em = emf.createEntityManager();
        
    }
}
