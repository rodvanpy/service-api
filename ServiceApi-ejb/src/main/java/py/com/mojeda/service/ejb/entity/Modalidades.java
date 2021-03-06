/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "MODALIDADES", schema = "PUBLIC")
public class Modalidades extends Base {

    private static final long serialVersionUID = 1574657L;
    private static final String SECUENCIA = "seq_modalidad_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @NotEmpty(message = "Ingrese Nombre")
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "CODIGO")
    private String codigo;
    
    @Column(name = "MONTO_MAXIMO")
    private BigDecimal montoMaximo;
    
    @Column(name = "MONTO_MINIMO")
    private BigDecimal montoMinimo;
    
    @Column(name = "INTERES")
    private Double interes;
    
    @Column(name = "PERIODO_CAPITAL")
    private Short periodoCapital;
    
    @Column(name = "VENCIMIENTO_INTERES")
    private Long vencimientoInteres;
    
    @Column(name = "PERIODO_GRACIA")
    private Short periodoGracia;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_CALCULO", referencedColumnName = "id")
    private TipoCalculos tipoCalculos;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "id")
    private Empresas empresa;
    
    @Column(name = "ENTIDAD")
    private String entidad = "MODALIDADES";
    
    public Modalidades() {
    }

    public Modalidades(Long id) {
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
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
        
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoCalculos getTipoCalculos() {
        return tipoCalculos;
    }

    public void setTipoCalculos(TipoCalculos tipoCalculos) {
        this.tipoCalculos = tipoCalculos;
    }

    /**
     * @return the montoMaximo
     */
    public BigDecimal getMontoMaximo() {
        return montoMaximo;
    }

    /**
     * @param montoMaximo the montoMaximo to set
     */
    public void setMontoMaximo(BigDecimal montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    /**
     * @return the montoMinimo
     */
    public BigDecimal getMontoMinimo() {
        return montoMinimo;
    }

    /**
     * @param montoMinimo the montoMinimo to set
     */
    public void setMontoMinimo(BigDecimal montoMinimo) {
        this.montoMinimo = montoMinimo;
    }

    /**
     * @return the interes
     */
    public Double getInteres() {
        return interes;
    }

    /**
     * @param interes the interes to set
     */
    public void setInteres(Double interes) {
        this.interes = interes;
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

    public Short getPeriodoCapital() {
        return periodoCapital;
    }

    public void setPeriodoCapital(Short periodoCapital) {
        this.periodoCapital = periodoCapital;
    }

    public Long getVencimientoInteres() {
        return vencimientoInteres;
    }

    public void setVencimientoInteres(Long vencimientoInteres) {
        this.vencimientoInteres = vencimientoInteres;
    }

    public Short getPeriodoGracia() {
        return periodoGracia;
    }

    public void setPeriodoGracia(Short periodoGracia) {
        this.periodoGracia = periodoGracia;
    }
    
    
    
}
