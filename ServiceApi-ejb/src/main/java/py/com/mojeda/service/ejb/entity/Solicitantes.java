/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "SOLICITANTES")
public class Solicitantes implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SECUENCIA = "SEQ_SOLICITANTES";   
    
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "TIPO_RELACION")
    private String tipoRelacion;
    
    @Column(name = "CONOCIDOS")
    private String conocidos;
    
    @Column(name = "OTROS_BIENES")
    private String otrosBienes;
    
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    
    @Column(name = "PERSONAS_MAYORES")
    private Short personasMayores;
    
    @Column(name = "PERSONAS_MENORES")
    private Integer personasMenores;
    
    @Basic(optional = false)
    @Column(name = "DIRECCION_PARTICULAR")
    private String direccionParticular;
    
    @Column(name = "ESTADO_CIVIL")
    private String estadoCivil;
    
    @Column(name = "TELEFONO_PARTICULAR")
    private String telefonoParticular;
    
    @Column(name = "GARANTE_SOCIO")
    private Integer garanteSocio;
    
    @Column(name = "CANT_HIJOS_CONY")
    private Integer cantHijosCony;
    
    @Column(name = "FECHA_DIRECCION_CONY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDireccionCony;
    
    @Column(name = "CANT_PERSONAS_A_CARGO_CONY")
    private Integer cantPersonasACargoCony;
    
    @Column(name = "CANT_HIJOS")
    private Integer cantHijos;
    
    @Column(name = "FECHA_DIRECCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDireccion;
    
    @Column(name = "CANT_PERSONAS_A_CARGO")
    private Integer cantPersonasACargo;
    
    @Column(name = "DETALLE_CARGADO")
    private String detalleCargado;
    
    @Column(name = "DEJAR_MENSAJE")
    private String dejarMensaje;
    
    @JoinColumn(name = "ID_PERSONA_CONYUGE", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Personas idPersonaConyuge;
    
    @Column(name = "CONSIDERAR_CONYUGE")
    private Boolean considerarConyuge;
    
    @Column(name = "AHORRO_BLOQUEADO")
    private Boolean ahorroBloqueado;
    
    @JoinColumn(name = "ID_PROFESION_CONY", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Profesiones profesionesCony;
    
    @ManyToOne
    @JoinColumn(name = "ID_PAIS", referencedColumnName = "id")
    private Paises pais;
    
    @ManyToOne
    @JoinColumn(name = "ID_DEPARTAMENTO_PAIS", referencedColumnName = "id")
    private DepartamentosPais departamento;
    
    @ManyToOne
    @JoinColumn(name = "ID_CIUDAD", referencedColumnName = "id")
    private Ciudades ciudad;
    
    @ManyToOne
    @JoinColumn(name = "ID_BARRIO", referencedColumnName = "id")
    private Barrios barrio;
    
    @Column(name = "LATITUD")
    private Double latitud;

    @Column(name = "LONGITUD")
    private Double longitud;
    
    @JoinColumn(name = "ID_SOLICITUD_PROPUESTA", referencedColumnName = "id")
    @ManyToOne
    private PropuestaSolicitud propuestaSolicitud;
    
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Personas idPersona;
    
    @JoinColumn(name = "ID_TIPO_GARANTIA", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TipoGarantias tipoGarantias;
    
    @JoinColumn(name = "ID_PROFESION", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Profesiones profesiones;
    
    @JsonIgnore
    @Column(name = "FECHA_CREACION")
    private Timestamp fechaCreacion;
    
    @JsonIgnore
    @Column(name = "FECHA_MODIFICACION")
    private Timestamp fechaModificacion;

    public Solicitantes() {
    }

    public Solicitantes(Long id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the tipoRelacion
     */
    public String getTipoRelacion() {
        return tipoRelacion;
    }

    /**
     * @param tipoRelacion the tipoRelacion to set
     */
    public void setTipoRelacion(String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    /**
     * @return the conocidos
     */
    public String getConocidos() {
        return conocidos;
    }

    /**
     * @param conocidos the conocidos to set
     */
    public void setConocidos(String conocidos) {
        this.conocidos = conocidos;
    }

    /**
     * @return the otrosBienes
     */
    public String getOtrosBienes() {
        return otrosBienes;
    }

    /**
     * @param otrosBienes the otrosBienes to set
     */
    public void setOtrosBienes(String otrosBienes) {
        this.otrosBienes = otrosBienes;
    }

    /**
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the personasMayores
     */
    public Short getPersonasMayores() {
        return personasMayores;
    }

    /**
     * @param personasMayores the personasMayores to set
     */
    public void setPersonasMayores(Short personasMayores) {
        this.personasMayores = personasMayores;
    }

    /**
     * @return the personasMenores
     */
    public Integer getPersonasMenores() {
        return personasMenores;
    }

    /**
     * @param personasMenores the personasMenores to set
     */
    public void setPersonasMenores(Integer personasMenores) {
        this.personasMenores = personasMenores;
    }

    /**
     * @return the direccionParticular
     */
    public String getDireccionParticular() {
        return direccionParticular;
    }

    /**
     * @param direccionParticular the direccionParticular to set
     */
    public void setDireccionParticular(String direccionParticular) {
        this.direccionParticular = direccionParticular;
    }

    /**
     * @return the estadoCivil
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * @param estadoCivil the estadoCivil to set
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    /**
     * @return the telefonoParticular
     */
    public String getTelefonoParticular() {
        return telefonoParticular;
    }

    /**
     * @param telefonoParticular the telefonoParticular to set
     */
    public void setTelefonoParticular(String telefonoParticular) {
        this.telefonoParticular = telefonoParticular;
    }

    /**
     * @return the garanteSocio
     */
    public Integer getGaranteSocio() {
        return garanteSocio;
    }

    /**
     * @param garanteSocio the garanteSocio to set
     */
    public void setGaranteSocio(Integer garanteSocio) {
        this.garanteSocio = garanteSocio;
    }

    /**
     * @return the cantHijosCony
     */
    public Integer getCantHijosCony() {
        return cantHijosCony;
    }

    /**
     * @param cantHijosCony the cantHijosCony to set
     */
    public void setCantHijosCony(Integer cantHijosCony) {
        this.cantHijosCony = cantHijosCony;
    }

    /**
     * @return the fechaDireccionCony
     */
    public Date getFechaDireccionCony() {
        return fechaDireccionCony;
    }

    /**
     * @param fechaDireccionCony the fechaDireccionCony to set
     */
    public void setFechaDireccionCony(Date fechaDireccionCony) {
        this.fechaDireccionCony = fechaDireccionCony;
    }

    /**
     * @return the cantPersonasACargoCony
     */
    public Integer getCantPersonasACargoCony() {
        return cantPersonasACargoCony;
    }

    /**
     * @param cantPersonasACargoCony the cantPersonasACargoCony to set
     */
    public void setCantPersonasACargoCony(Integer cantPersonasACargoCony) {
        this.cantPersonasACargoCony = cantPersonasACargoCony;
    }

    /**
     * @return the cantHijos
     */
    public Integer getCantHijos() {
        return cantHijos;
    }

    /**
     * @param cantHijos the cantHijos to set
     */
    public void setCantHijos(Integer cantHijos) {
        this.cantHijos = cantHijos;
    }

    /**
     * @return the fechaDireccion
     */
    public Date getFechaDireccion() {
        return fechaDireccion;
    }

    /**
     * @param fechaDireccion the fechaDireccion to set
     */
    public void setFechaDireccion(Date fechaDireccion) {
        this.fechaDireccion = fechaDireccion;
    }

    /**
     * @return the cantPersonasACargo
     */
    public Integer getCantPersonasACargo() {
        return cantPersonasACargo;
    }

    /**
     * @param cantPersonasACargo the cantPersonasACargo to set
     */
    public void setCantPersonasACargo(Integer cantPersonasACargo) {
        this.cantPersonasACargo = cantPersonasACargo;
    }

    /**
     * @return the detalleCargado
     */
    public String getDetalleCargado() {
        return detalleCargado;
    }

    /**
     * @param detalleCargado the detalleCargado to set
     */
    public void setDetalleCargado(String detalleCargado) {
        this.detalleCargado = detalleCargado;
    }

    /**
     * @return the dejarMensaje
     */
    public String getDejarMensaje() {
        return dejarMensaje;
    }

    /**
     * @param dejarMensaje the dejarMensaje to set
     */
    public void setDejarMensaje(String dejarMensaje) {
        this.dejarMensaje = dejarMensaje;
    }

    /**
     * @return the idPersonaConyuge
     */
    public Personas getIdPersonaConyuge() {
        return idPersonaConyuge;
    }

    /**
     * @param idPersonaConyuge the idPersonaConyuge to set
     */
    public void setIdPersonaConyuge(Personas idPersonaConyuge) {
        this.idPersonaConyuge = idPersonaConyuge;
    }

    /**
     * @return the considerarConyuge
     */
    public Boolean getConsiderarConyuge() {
        return considerarConyuge;
    }

    /**
     * @param considerarConyuge the considerarConyuge to set
     */
    public void setConsiderarConyuge(Boolean considerarConyuge) {
        this.considerarConyuge = considerarConyuge;
    }

    /**
     * @return the ahorroBloqueado
     */
    public Boolean getAhorroBloqueado() {
        return ahorroBloqueado;
    }

    /**
     * @param ahorroBloqueado the ahorroBloqueado to set
     */
    public void setAhorroBloqueado(Boolean ahorroBloqueado) {
        this.ahorroBloqueado = ahorroBloqueado;
    }

    /**
     * @return the profesionesCony
     */
    public Profesiones getProfesionesCony() {
        return profesionesCony;
    }

    /**
     * @param profesionesCony the profesionesCony to set
     */
    public void setProfesionesCony(Profesiones profesionesCony) {
        this.profesionesCony = profesionesCony;
    }

    /**
     * @return the pais
     */
    public Paises getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(Paises pais) {
        this.pais = pais;
    }

    /**
     * @return the departamento
     */
    public DepartamentosPais getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(DepartamentosPais departamento) {
        this.departamento = departamento;
    }

    /**
     * @return the ciudad
     */
    public Ciudades getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the barrio
     */
    public Barrios getBarrio() {
        return barrio;
    }

    /**
     * @param barrio the barrio to set
     */
    public void setBarrio(Barrios barrio) {
        this.barrio = barrio;
    }

    /**
     * @return the latitud
     */
    public Double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public Double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the propuestaSolicitud
     */
    public PropuestaSolicitud getPropuestaSolicitud() {
        return propuestaSolicitud;
    }

    /**
     * @param propuestaSolicitud the propuestaSolicitud to set
     */
    public void setPropuestaSolicitud(PropuestaSolicitud propuestaSolicitud) {
        this.propuestaSolicitud = propuestaSolicitud;
    }

    /**
     * @return the idPersona
     */
    public Personas getIdPersona() {
        return idPersona;
    }

    /**
     * @param idPersona the idPersona to set
     */
    public void setIdPersona(Personas idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * @return the tipoGarantias
     */
    public TipoGarantias getTipoGarantias() {
        return tipoGarantias;
    }

    /**
     * @param tipoGarantias the tipoGarantias to set
     */
    public void setTipoGarantias(TipoGarantias tipoGarantias) {
        this.tipoGarantias = tipoGarantias;
    }

    /**
     * @return the profesiones
     */
    public Profesiones getProfesiones() {
        return profesiones;
    }

    /**
     * @param profesiones the profesiones to set
     */
    public void setProfesiones(Profesiones profesiones) {
        this.profesiones = profesiones;
    }

    /**
     * @return the fechaCreacion
     */
    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * @return the fechaModificacion
     */
    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    /**
     * @param fechaModificacion the fechaModificacion to set
     */
    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    
    
}
