/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "PERSONAS")
public class Personas extends Base {

    private static final long serialVersionUID = -9149680520407250259L;
    private static final String SECUENCIA = "seq_persona_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @NotEmpty(message = "Ingrese Primer Nombre")
    @Column(name = "PRIMER_NOMBRE", nullable = false, length = 128)
    private String primerNombre;

    @Column(name = "SEGUNDO_NOMBRE")
    private String segundoNombre;

    @NotEmpty(message = "Ingrese Primer Apellido")
    @Column(name = "PRIMER_APELLIDO", nullable = false, length = 128)
    private String primerApellido;

    @Column(name = "SEGUNDO_APELLIDO")
    private String segundoApellido;
    
    @NotEmpty(message = "Ingrese Documento")
    @Column(name = "DOCUMENTO", nullable = false)
    private String documento;

    @Column(name = "RUC")
    private String ruc;

    @NotNull(message = "Ingrese Fecha Nacimiento")
    @Column(name = "FECHA_NACIMIENTO")
    private Timestamp fechaNacimiento;

    @NotEmpty(message = "Ingrese Tipo Persona")
    @Column(name = "TIPO_PERSONA")
    private String tipoPersona;    

    @Size(max = 10)
    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "NUMERO_HIJOS")
    private Integer numeroHijos;

    @Column(name = "NUMERO_DEPENDIENTES")
    private Integer numeroDependientes;

    @NotEmpty(message = "Ingrese Estado Civil")
    @Column(name = "ESTADO_CIVIL", nullable = false)
    private String estadoCivil;
    
    @Column(name = "SEPARACION_BIENES")
    private Boolean separacionBienes;
    
    @NotEmpty(message = "Ingrese Email")
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEFONO_PARTICULAR")
    private String telefonoParticular;

    @Column(name = "TELEFONO_SECUNDARIO")
    private String telefonoSecundario;
    
    @NotEmpty(message = "Ingrese Direccion Particular")
    @Column(name = "DIRECCION_PARTICULAR", nullable = false)
    private String direccionParticular;

    @Column(name = "DIRECCION_DETALLADA")
    private String direccionDetallada;

    @Column(name = "OBSERVACION")
    private String observacion;

    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "id")
    private Empresas empresa;

    public Personas() {

    }

    public Personas(Long id) {
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
     * @return the primerNombre
     */
    public String getPrimerNombre() {
        return primerNombre;
    }

    /**
     * @param primerNombre the primerNombre to set
     */
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    /**
     * @return the segundoNombre
     */
    public String getSegundoNombre() {
        return segundoNombre;
    }

    /**
     * @param segundoNombre the segundoNombre to set
     */
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    /**
     * @return the primerApellido
     */
    public String getPrimerApellido() {
        return primerApellido;
    }

    /**
     * @param primerApellido the primerApellido to set
     */
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * @return the segundoApellido
     */
    public String getSegundoApellido() {
        return segundoApellido;
    }

    /**
     * @param segundoApellido the segundoApellido to set
     */
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    /**
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * @return the ruc
     */
    public String getRuc() {
        return ruc;
    }

    /**
     * @param ruc the ruc to set
     */
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    /**
     * @return the fechaNacimiento
     */
    public Timestamp getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(Timestamp fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the numeroHijos
     */
    public Integer getNumeroHijos() {
        return numeroHijos;
    }

    /**
     * @param numeroHijos the numeroHijos to set
     */
    public void setNumeroHijos(Integer numeroHijos) {
        this.numeroHijos = numeroHijos;
    }

    /**
     * @return the numeroDependientes
     */
    public Integer getNumeroDependientes() {
        return numeroDependientes;
    }

    /**
     * @param numeroDependientes the numeroDependientes to set
     */
    public void setNumeroDependientes(Integer numeroDependientes) {
        this.numeroDependientes = numeroDependientes;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the direccionDetallada
     */
    public String getDireccionDetallada() {
        return direccionDetallada;
    }

    /**
     * @param direccionDetallada the direccionDetallada to set
     */
    public void setDireccionDetallada(String direccionDetallada) {
        this.direccionDetallada = direccionDetallada;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the empresa
     */
    public Empresas getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the tipoPersona
     */
    public String getTipoPersona() {
        return tipoPersona;
    }

    /**
     * @param tipoPersona the tipoPersona to set
     */
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    /**
     * @return the separacionBienes
     */
    public Boolean getSeparacionBienes() {
        return separacionBienes;
    }

    /**
     * @param separacionBienes the separacionBienes to set
     */
    public void setSeparacionBienes(Boolean separacionBienes) {
        this.separacionBienes = separacionBienes;
    }
    
    

}
