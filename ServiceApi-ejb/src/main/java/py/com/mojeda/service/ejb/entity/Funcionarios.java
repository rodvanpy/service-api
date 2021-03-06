/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "FUNCIONARIOS", schema = "PUBLIC", uniqueConstraints = @UniqueConstraint(name = "funcionario_alias_uq", columnNames = {"alias"}))
public class Funcionarios extends Base {

    private static final long serialVersionUID = 8538760347986185608L;
    private static final String SECUENCIA = "seq_funcionario_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    //@NotNull(message = "Ingrese Alias")
    @NotEmpty(message = "Ingrese Alias")
    @Column(name = "ALIAS")
    private String alias;

    //@NotNull(message = " Ingrese Clave Acceso")
    @NotEmpty(message = "Ingrese Clave Acceso")
    @Column(name = "CLAVE_ACCESO")
    private String claveAcceso;

    @Column(name = "SUPER_USUARIO")
    private Boolean superUsuario;

    @NotNull(message = "Ingrese Tiempo de Expiracion del Tokens")
    @Column(name = "EXPIRATION_TIME_TOKENS")
    private Long expirationTimeTokens;
    
    @NotEmpty(message = "Ingrese Nro. Legajo")
    @Column(name = "NRO_LEGAJO")
    private String nroLegajo;
    
    @NotNull(message = "Ingrese Fecha Ingreso")
    @Column(name = "FECHA_INGRESO")
    private Timestamp fechaIngreso;
    
    @Column(name = "FECHA_EGRESO")
    private Timestamp fechaEgreso;
    
    @NotNull(message = "Ingrese Cargo")
    @ManyToOne
    @JoinColumn(name = "ID_CARGO", referencedColumnName = "id")
    @Valid
    private TipoCargos cargo;
    
    @NotNull(message = "Ingrese Tipo Funcionario")
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_FUNCIONARIO", referencedColumnName = "id")
    @Valid
    private TipoFuncionarios tipoFuncionario;
    
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_MOTI_RETIRO", referencedColumnName = "id")
    private TipoMotivosRetiro tipoMotivoRetiro;
    
    @Column(name = "OBSERVACION_RETIRO")
    private String observacionRetiro;

    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    @Valid
    private Personas persona;

    @ManyToOne
    @JoinColumn(name = "ID_SUCURSAL", referencedColumnName = "id")
    private Sucursales sucursal;
    
    @NotNull(message = "Ingrese Rol")
    @ManyToOne
    @JoinColumn(name = "ID_ROL", referencedColumnName = "id")
    private Rol rol;
    
    @Column(name = "RETIRADO")
    private Boolean retirado;
    
    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "id")
    private Empresas empresa;
    
    @Column(name = "ENTIDAD")
    private String entidad = "FUNCIONARIOS";

    @Transient
    private List<DepartamentosSucursal> departamentos;    

    public Funcionarios() {

    }

    /**
     * @param id el id de Usuario
     */
    public Funcionarios(Long id) {
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
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @return the claveAcceso
     */
    public String getClaveAcceso() {
        return claveAcceso;
    }

    /**
     * @param claveAcceso the claveAcceso to set
     */
    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    /**
     * @return the superUsuario
     */
    public Boolean getSuperUsuario() {
        return superUsuario;
    }

    /**
     * @param superUsuario the superUsuario to set
     */
    public void setSuperUsuario(Boolean superUsuario) {
        this.superUsuario = superUsuario;
    }

    /**
     * @return the rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
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

    public Long getExpirationTimeTokens() {
        return expirationTimeTokens;
    }

    public void setExpirationTimeTokens(Long expirationTimeTokens) {
        this.expirationTimeTokens = expirationTimeTokens;
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    public List<DepartamentosSucursal> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<DepartamentosSucursal> departamentos) {
        this.departamentos = departamentos;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * @return the nroLegajo
     */
    public String getNroLegajo() {
        return nroLegajo;
    }

    /**
     * @param nroLegajo the nroLegajo to set
     */
    public void setNroLegajo(String nroLegajo) {
        this.nroLegajo = nroLegajo;
    }

    /**
     * @return the fechaIngreso
     */
    public Timestamp getFechaIngreso() {
        return fechaIngreso;
    }

    /**
     * @param fechaIngreso the fechaIngreso to set
     */
    public void setFechaIngreso(Timestamp fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    /**
     * @return the fechaEgreso
     */
    public Timestamp getFechaEgreso() {
        return fechaEgreso;
    }

    /**
     * @param fechaEgreso the fechaEgreso to set
     */
    public void setFechaEgreso(Timestamp fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    /**
     * @return the cargo
     */
    public TipoCargos getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(TipoCargos cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the tipoFuncionario
     */
    public TipoFuncionarios getTipoFuncionario() {
        return tipoFuncionario;
    }

    /**
     * @param tipoFuncionario the tipoFuncionario to set
     */
    public void setTipoFuncionario(TipoFuncionarios tipoFuncionario) {
        this.tipoFuncionario = tipoFuncionario;
    }

    /**
     * @return the tipoMotivoRetiro
     */
    public TipoMotivosRetiro getTipoMotivoRetiro() {
        return tipoMotivoRetiro;
    }

    /**
     * @param tipoMotivoRetiro the tipoMotivoRetiro to set
     */
    public void setTipoMotivoRetiro(TipoMotivosRetiro tipoMotivoRetiro) {
        this.tipoMotivoRetiro = tipoMotivoRetiro;
    }
    
    /**
     * @return the entidad
     */
    public String getEntidad() {
        return entidad;
    }

    /**
     * @param entidad the entidad to set
     */
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public Boolean getRetirado() {
        return retirado;
    }

    public void setRetirado(Boolean retirado) {
        this.retirado = retirado;
    } 

    public String getObservacionRetiro() {
        return observacionRetiro;
    }

    public void setObservacionRetiro(String observacionRetiro) {
        this.observacionRetiro = observacionRetiro;
    } 

    public Empresas getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }    

}
