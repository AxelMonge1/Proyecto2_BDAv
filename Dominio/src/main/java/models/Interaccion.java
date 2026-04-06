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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

/**
 *
 * @author EdgarUris
 */
@Entity
public class Interaccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_interaccion")
    private TipoInteraccion tipoInteraccion;
    
    @ManyToOne
    @JoinColumn(name = "id_est_ori", nullable = false)
    private Estudiante estudianteOrigen;
    
    @ManyToOne
    @JoinColumn(name = "id_est_desti", nullable = false)
    private Estudiante estudianteDestino;

    public Interaccion() {
    }

    public Interaccion(Long id, TipoInteraccion tipoInteraccion, Estudiante estudianteOrigen, Estudiante estudianteDestino) {
        this.id = id;
        this.tipoInteraccion = tipoInteraccion;
        this.estudianteOrigen = estudianteOrigen;
        this.estudianteDestino = estudianteDestino;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoInteraccion getTipoInteraccion() {
        return tipoInteraccion;
    }

    public void setTipoInteraccion(TipoInteraccion tipoInteraccion) {
        this.tipoInteraccion = tipoInteraccion;
    }

    public Estudiante getEstudianteOrigen() {
        return estudianteOrigen;
    }

    public void setEstudianteOrigen(Estudiante estudianteOrigen) {
        this.estudianteOrigen = estudianteOrigen;
    }

    public Estudiante getEstudianteDestino() {
        return estudianteDestino;
    }

    public void setEstudianteDestino(Estudiante estudianteDestino) {
        this.estudianteDestino = estudianteDestino;
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
        if (!(object instanceof Interaccion)) {
            return false;
        }
        Interaccion other = (Interaccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.Interacciones[ id=" + id + " ]";
    }
    
}
