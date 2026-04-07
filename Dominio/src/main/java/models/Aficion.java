/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author axelm
 */
@Entity
public class Aficion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    
    @ManyToMany(mappedBy = "aficiones")
    private Set<Estudiante> estudiantes;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_aficion")
    private TipoAficion tipoAficion;

    public Aficion() {
    }

    public Aficion(Long id, String nombre, Set<Estudiante> estudiantes, TipoAficion tipoAficion) {
        this.id = id;
        this.nombre = nombre;
        this.estudiantes = estudiantes;
        this.tipoAficion = tipoAficion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstudiantes(Set<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public void setTipoAficion(TipoAficion tipoAficion) {
        this.tipoAficion = tipoAficion;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public TipoAficion getTipoAficion() {
        return tipoAficion;
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
        if (!(object instanceof Aficion)) {
            return false;
        }
        Aficion other = (Aficion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Aficion[ id=" + id + " ]";
    }
    
}
