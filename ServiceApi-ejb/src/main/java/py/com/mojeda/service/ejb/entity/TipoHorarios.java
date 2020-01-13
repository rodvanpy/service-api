/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Timestamp;
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
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "TIPO_HORARIOS", schema = "PUBLIC")
public class TipoHorarios extends Base{

    private static final long serialVersionUID = 1574657L;
    private static final String SECUENCIA = "seq_tipo_horarios_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @NotEmpty(message = "Ingrese Nombre")
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @NotEmpty(message = "Ingrese Codigo")
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private String codigo;
    
    @NotNull(message = "Ingrese Fecha Inicio")
    @Column(name = "FECHA_INICIO")
    private Timestamp fechaInicio;
    
    @Column(name = "FECHA_FIN")
    private Timestamp fechaFin;
    
    @NotEmpty(message = "Ingrese Hora Entrada")
    @Basic(optional = false)
    @Column(name = "HORA_ENTRADA")
    private String horaEntrada;
    
    @NotEmpty(message = "Ingrese Hora Salida")
    @Basic(optional = false)
    @Column(name = "HORA_SALIDA")
    private String horaSalida;
    
    @NotEmpty(message = "Ingrese Inicio Hora Almuerzo ")
    @Basic(optional = false)
    @Column(name = "HORA_ALMUERZO_INICIO")
    private String horaAlmuerzoInicio;
    
    @NotEmpty(message = "Ingrese Inicio Hora Fin ")
    @Basic(optional = false)
    @Column(name = "HORA_ALMUERZO_FIN")
    private String horaAlmuerzoFin;    
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "id")
    private Empresas empresa;
    
    public TipoHorarios() {
    }

    public TipoHorarios(Long id) {
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

    /**
     * @return the horaEntrada
     */
    public String getHoraEntrada() {
        return horaEntrada;
    }

    /**
     * @param horaEntrada the horaEntrada to set
     */
    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    /**
     * @return the horaSalida
     */
    public String getHoraSalida() {
        return horaSalida;
    }

    /**
     * @param horaSalida the horaSalida to set
     */
    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * @return the horaAlmuerzoInicio
     */
    public String getHoraAlmuerzoInicio() {
        return horaAlmuerzoInicio;
    }

    /**
     * @param horaAlmuerzoInicio the horaAlmuerzoInicio to set
     */
    public void setHoraAlmuerzoInicio(String horaAlmuerzoInicio) {
        this.horaAlmuerzoInicio = horaAlmuerzoInicio;
    }

    /**
     * @return the horaAlmuerzoFin
     */
    public String getHoraAlmuerzoFin() {
        return horaAlmuerzoFin;
    }

    /**
     * @param horaAlmuerzoFin the horaAlmuerzoFin to set
     */
    public void setHoraAlmuerzoFin(String horaAlmuerzoFin) {
        this.horaAlmuerzoFin = horaAlmuerzoFin;
    }
  
    
}
