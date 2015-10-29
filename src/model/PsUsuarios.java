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
 * @author Juan Camilo Giron
 */
@Entity
@Table(name = "ps_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PsUsuarios.findAll", query = "SELECT p FROM PsUsuarios p"),
    @NamedQuery(name = "PsUsuarios.findByNumero", query = "SELECT p FROM PsUsuarios p WHERE p.numero = :numero"),
    @NamedQuery(name = "PsUsuarios.findByNombres", query = "SELECT p FROM PsUsuarios p WHERE p.nombres = :nombres"),
    @NamedQuery(name = "PsUsuarios.findByApellidos", query = "SELECT p FROM PsUsuarios p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "PsUsuarios.findByUsuario", query = "SELECT p FROM PsUsuarios p WHERE p.usuario = :usuario"),
    @NamedQuery(name = "PsUsuarios.findByPassword", query = "SELECT p FROM PsUsuarios p WHERE p.password = :password"),
    @NamedQuery(name = "PsUsuarios.findByEmpresa", query = "SELECT p FROM PsUsuarios p WHERE p.empresa = :empresa"),
    @NamedQuery(name = "PsUsuarios.findByRol", query = "SELECT p FROM PsUsuarios p WHERE p.rol = :rol"),
    @NamedQuery(name = "PsUsuarios.findByFechaCreacion", query = "SELECT p FROM PsUsuarios p WHERE p.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "PsUsuarios.findByFechaModificacion", query = "SELECT p FROM PsUsuarios p WHERE p.fechaModificacion = :fechaModificacion")})
public class PsUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NUMERO")
    private Integer numero;
    @Basic(optional = false)
    @Column(name = "NOMBRES")
    private String nombres;
    @Basic(optional = false)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "EMPRESA")
    private String empresa;
    @Basic(optional = false)
    @Column(name = "ROL")
    private String rol;
    @Basic(optional = false)
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "FECHA_MODIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbUsuariosNumero")
    private List<PsTareasCalificadas> psTareasCalificadasList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbUsuariosCodigo")
    private List<PsTareas> psTareasList;

    public PsUsuarios() {
    }

    public PsUsuarios(Integer numero) {
        this.numero = numero;
    }

    public PsUsuarios(Integer numero, String nombres, String apellidos, String usuario, String password, String empresa, String rol, Date fechaCreacion, Date fechaModificacion) {
        this.numero = numero;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.password = password;
        this.empresa = empresa;
        this.rol = rol;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @XmlTransient
    public List<PsTareasCalificadas> getPsTareasCalificadasList() {
        return psTareasCalificadasList;
    }

    public void setPsTareasCalificadasList(List<PsTareasCalificadas> psTareasCalificadasList) {
        this.psTareasCalificadasList = psTareasCalificadasList;
    }

    @XmlTransient
    public List<PsTareas> getPsTareasList() {
        return psTareasList;
    }

    public void setPsTareasList(List<PsTareas> psTareasList) {
        this.psTareasList = psTareasList;
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
        if (!(object instanceof PsUsuarios)) {
            return false;
        }
        PsUsuarios other = (PsUsuarios) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PsUsuarios[ numero=" + numero + " ]";
    }
    
}
