/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "OCUPACION_PERSONA")
public class OcupacionPersona extends Base {

    private static final long serialVersionUID = -9168880520407250259L;
    private static final String SECUENCIA = "seq_ocupacion_cliente_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Ingrese Cargo")
    @Column(name = "CARGO")
    private String cargo;
    
    @NotEmpty(message = "Ingrese Empresa")
    @Column(name = "EMPRESA")
    private String empresa;
    
    @NotEmpty(message = "Ingrese Tipo Trabajo")
    @Column(name = "TIPO_TRABAJO")
    private String tipoTrabajo;
    
    @NotEmpty(message = "Ingrese Direccion")
    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "TELEFONO_PRINCIPAL")
    private String telefonoPrincipal;

    @Column(name = "TELEFONO_SECUNDARIO")
    private String telefonoSecundario;

    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;

    @Column(name = "FECHA_SALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSalida;

    @Column(name = "INTERNO")
    private String interno;
    
    @NotNull(message = "Ingrese Ingreso Mensual")
    @Column(name = "INGRESOS_MENSUALES")
    private BigDecimal ingresosMensuales;

    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Personas persona;

    @ManyToOne
    @NotNull(message = "Ingrese Ocupacion")
    @JoinColumn(name = "ID_TIPO_OCUPACION", referencedColumnName = "id")
    private TipoOcupaciones tipoOcupacion;


    public OcupacionPersona() {

    }

    public OcupacionPersona(Long id) {
        this.setId(id);
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
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefonoPrincipal
     */
    public String getTelefonoPrincipal() {
        return telefonoPrincipal;
    }

    /**
     * @param telefonoPrincipal the telefonoPrincipal to set
     */
    public void setTelefonoPrincipal(String telefonoPrincipal) {
        this.telefonoPrincipal = telefonoPrincipal;
    }

    /**
     * @return the telefonoSecundario
     */
    public String getTelefonoSecundario() {
        return telefonoSecundario;
    }

    /**
     * @param telefonoSecundario the telefonoSecundario to set
     */
    public void setTelefonoSecundario(String telefonoSecundario) {
        this.telefonoSecundario = telefonoSecundario;
    }

    /**
     * @return the fechaIngreso
     */
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return the fechaSalida
     */
    public Date getFechaSalida() {
        return fechaSalida;
    }

    /**
     * @param fechaSalida the fechaSalida to set
     */
    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the interno
     */
    public String getInterno() {
        return interno;
    }

    /**
     * @param interno the interno to set
     */
    public void setInterno(String interno) {
        this.interno = interno;
    }

    /**
     * @return the ingresosMensuales
     */
    public BigDecimal getIngresosMensuales() {
        return ingresosMensuales;
    }

    /**
     * @param ingresosMensuales the ingresosMensuales to set
     */
    public void setIngresosMensuales(BigDecimal ingresosMensuales) {
        this.ingresosMensuales = ingresosMensuales;
    }

    /**
     * @return the persona
     */
    public Personas getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    /**
     * @return the tipoTrabajo
     */
    public String getTipoTrabajo() {
        return tipoTrabajo;
    }

    /**
     * @param tipoTrabajo the tipoTrabajo to set
     */
    public void setTipoTrabajo(String tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    /**
     * @return the tipoOcupacion
     */
    public TipoOcupaciones getTipoOcupacion() {
        return tipoOcupacion;
    }

    /**
     * @param tipoOcupacion the tipoOcupacion to set
     */
    public void setTipoOcupacion(TipoOcupaciones tipoOcupacion) {
        this.tipoOcupacion = tipoOcupacion;
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    

}
