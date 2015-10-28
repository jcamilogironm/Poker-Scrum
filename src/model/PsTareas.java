/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sistemas
 */
@Entity
@Table(name = " ps_tareas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PsTareas.findAll", query = "SELECT p FROM PsTareas p"),
    @NamedQuery(name = "PsTareas.findByNumero", query = "SELECT p FROM PsTareas p WHERE p.numero = :numero"),
    @NamedQuery(name = "PsTareas.findByDescripcion", query = "SELECT p FROM PsTareas p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PsTareas.findByTempPuntuacion", query = "SELECT p FROM PsTareas p WHERE p.tempPuntuacion = :tempPuntuacion"),
    @NamedQuery(name = "PsTareas.findByTempDebate", query = "SELECT p FROM PsTareas p WHERE p.tempDebate = :tempDebate"),
    @NamedQuery(name = "PsTareas.findByFechaApertura", query = "SELECT p FROM PsTareas p WHERE p.fechaApertura = :fechaApertura"),
    @NamedQuery(name = "PsTareas.findByFechaModificacion", query = "SELECT p FROM PsTareas p WHERE p.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "PsTareas.findByCreador", query = "SELECT p FROM PsTareas p WHERE p.creador = :creador"),
    @NamedQuery(name = "PsTareas.findByEstado", query = "SELECT p FROM PsTareas p WHERE p.estado = :estado")})
public class PsTareas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NUMERO")
    private Integer numero;
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = " TEMP_PUNTUACION")
    private String tempPuntuacion;
    @Basic(optional = false)
    @Column(name = "TEMP_DEBATE")
    private String tempDebate;
    @Basic(optional = false)
    @Column(name = "FECHA_APERTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaApertura;
    @Basic(optional = false)
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Basic(optional = false)
    @Column(name = "CREADOR")
    private String creador;
    @Basic(optional = false)
    @Column(name = "ESTADO")
    private int estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbTareasNumero")
    private List<PsTareasCalificadas> psTareasCalificadasList;
    @JoinColumn(name = "TB_USUARIOS_CODIGO", referencedColumnName = "NUMERO")
    @ManyToOne(optional = false)
    private PsUsuarios tbUsuariosCodigo;

    public PsTareas() {
    }

    public PsTareas(Integer numero) {
        this.numero = numero;
    }

    public PsTareas(Integer numero, String descripcion, String tempPuntuacion, String tempDebate, Date fechaApertura, Date fechaModificacion, String creador, int estado) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.tempPuntuacion = tempPuntuacion;
        this.tempDebate = tempDebate;
        this.fechaApertura = fechaApertura;
        this.fechaModificacion = fechaModificacion;
        this.creador = creador;
        this.estado = estado;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTempPuntuacion() {
        return tempPuntuacion;
    }

    public void setTempPuntuacion(String tempPuntuacion) {
        this.tempPuntuacion = tempPuntuacion;
    }

    public String getTempDebate() {
        return tempDebate;
    }

    public void setTempDebate(String tempDebate) {
        this.tempDebate = tempDebate;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<PsTareasCalificadas> getPsTareasCalificadasList() {
        return psTareasCalificadasList;
    }

    public void setPsTareasCalificadasList(List<PsTareasCalificadas> psTareasCalificadasList) {
        this.psTareasCalificadasList = psTareasCalificadasList;
    }

    public PsUsuarios getTbUsuariosCodigo() {
        return tbUsuariosCodigo;
    }

    public void setTbUsuariosCodigo(PsUsuarios tbUsuariosCodigo) {
        this.tbUsuariosCodigo = tbUsuariosCodigo;
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
        if (!(object instanceof PsTareas)) {
            return false;
        }
        PsTareas other = (PsTareas) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PsTareas[ numero=" + numero + " ]";
    }
    
}
