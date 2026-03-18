/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author axelm
 * @author EdgarUris
 */
@Entity
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    
    private String carrera;
    
    private String descripcion;
    
    private String correo;
    
    private String contrasena;
    
    @ManyToMany
    @JoinTable(name = "estudiante_aficion", joinColumns = @JoinColumn(name = "estudiante_id"), inverseJoinColumns = @JoinColumn(name = "aficion_id"))
    private Set<Aficion> aficiones;
    
    @OneToMany(mappedBy = "estudiante1", cascade = CascadeType.ALL)
    private Set<Match> matchesIniciados = new HashSet<>();
    
    @OneToMany(mappedBy = "estudiante2", cascade = CascadeType.ALL)
    private Set<Match> matchesRecibidos = new HashSet<>();
    
    @OneToMany(mappedBy = "estudianteOrigen", cascade = CascadeType.ALL)
    private Set<Interaccion> interaccionesHechas = new HashSet<>();
    
    @OneToMany(mappedBy = "estudianteDestino", cascade = CascadeType.ALL)
    private Set<Interaccion> interaccionesRecibidas = new HashSet<>();

    public Estudiante() {
    }

    public Estudiante(Long id, String nombre, String carrera, String descripcion, String correo, String contrasena, Set<Aficion> aficiones, Set<Match> matchesIniciados, Set<Match> matchesRecibidos) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.descripcion = descripcion;
        this.correo = correo;
        this.contrasena = contrasena;
        this.aficiones = aficiones;
        this.matchesIniciados = matchesIniciados;
        this.matchesRecibidos = matchesRecibidos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setAficiones(Set<Aficion> aficiones) {
        this.aficiones = aficiones;
    }

    public void setMatchesIniciados(Set<Match> matchesIniciados) {
        this.matchesIniciados = matchesIniciados;
    }

    public void setMatchesRecibidos(Set<Match> matchesRecibidos) {
        this.matchesRecibidos = matchesRecibidos;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Set<Aficion> getAficiones() {
        return aficiones;
    }

    public Set<Match> getMatchesIniciados() {
        return matchesIniciados;
    }

    public Set<Match> getMatchesRecibidos() {
        return matchesRecibidos;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Estudiante[ id=" + id + " ]";
    }
    
}