/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Camilo Giron
 */
@Entity
@Table(name = "ps_tareas_calificadas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PsTareasCalificadas.findAll", query = "SELECT p FROM PsTareasCalificadas p"),
    @NamedQuery(name = "PsTareasCalificadas.findByNumero", query = "SELECT p FROM PsTareasCalificadas p WHERE p.numero = :numero"),
    @NamedQuery(name = "PsTareasCalificadas.findByUsrioNombre", query = "SELECT p FROM PsTareasCalificadas p WHERE p.usrioNombre = :usrioNombre"),
    @NamedQuery(name = "PsTareasCalificadas.findByCalificacion", query = "SELECT p FROM PsTareasCalificadas p WHERE p.calificacion = :calificacion"),
    @NamedQuery(name = "PsTareasCalificadas.findByTareaAsunto", query = "SELECT p FROM PsTareasCalificadas p WHERE p.tareaAsunto = :tareaAsunto"),
    @NamedQuery(name = "PsTareasCalificadas.findByFechaCalificacion", query = "SELECT p FROM PsTareasCalificadas p WHERE p.fechaCalificacion = :fechaCalificacion")})
public class PsTareasCalificadas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NUMERO")
    private Integer numero;
    @Basic(optional = false)
    @Column(name = "USRIO_NOMBRE")
    private String usrioNombre;
    @Basic(optional = false)
    @Column(name = "CALIFICACION")
    private int calificacion;
    @Basic(optional = false)
    @Column(name = "TAREA_ASUNTO")
    private String tareaAsunto;
    @Basic(optional = false)
    @Column(name = "FECHA_CALIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCalificacion;
    @JoinColumn(name = " TB_TAREAS_NUMERO", referencedColumnName = "NUMERO")
    @ManyToOne(optional = false)
    private PsTareas tbTareasNumero;
    @JoinColumn(name = "TB_USUARIOS_NUMERO", referencedColumnName = "NUMERO")
    @ManyToOne(optional = false)
    private PsUsuarios tbUsuariosNumero;

    public PsTareasCalificadas() {
    }

    public PsTareasCalificadas(Integer numero) {
        this.numero = numero;
    }

    public PsTareasCalificadas(Integer numero, String usrioNombre, int calificacion, String tareaAsunto, Date fechaCalificacion) {
        this.numero = numero;
        this.usrioNombre = usrioNombre;
        this.calificacion = calificacion;
        this.tareaAsunto = tareaAsunto;
        this.fechaCalificacion = fechaCalificacion;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getUsrioNombre() {
        return usrioNombre;
    }

    public void setUsrioNombre(String usrioNombre) {
        this.usrioNombre = usrioNombre;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getTareaAsunto() {
        return tareaAsunto;
    }

    public void setTareaAsunto(String tareaAsunto) {
        this.tareaAsunto = tareaAsunto;
    }

    public Date getFechaCalificacion() {
        return fechaCalificacion;
    }

    public void setFechaCalificacion(Date fechaCalificacion) {
        this.fechaCalificacion = fechaCalificacion;
    }

    public PsTareas getTbTareasNumero() {
        return tbTareasNumero;
    }

    public void setTbTareasNumero(PsTareas tbTareasNumero) {
        this.tbTareasNumero = tbTareasNumero;
    }

    public PsUsuarios getTbUsuariosNumero() {
        return tbUsuariosNumero;
    }

    public void setTbUsuariosNumero(PsUsuarios tbUsuariosNumero) {
        this.tbUsuariosNumero = tbUsuariosNumero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PsTareasCalificadas)) {
            return false;
        }
        PsTareasCalificadas other = (PsTareasCalificadas) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PsTareasCalificadas[ numero=" + numero + " ]";
    }
    
}
