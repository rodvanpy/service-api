/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "EVALUACION_SOLICITUDES")
public class EvaluacionSolicitudes extends Base{

    private static final long serialVersionUID = 1L;
    private static final String SECUENCIA = "seq_evaluacion_solicitud_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "FECHA_ANALISIS")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAnalisis;
    
    @Basic(optional = false)
    @Column(name = "HORA_INICIO_ANALISIS")
    private String horaInicioAnalisis;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_TIPO_DESTINO", referencedColumnName = "id")
    private TipoDestinos tipoDestino;
    
    @Basic(optional = false)
    @Column(name = "MONTO_SOLICITADO")
    private BigDecimal montoSolicitado;
    
    @Basic(optional = false)
    @Column(name = "TIPO_RELACION")
    private String tipoRelacion;
    
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID")
    @ManyToOne(optional = true)
    private Personas persona;
    
    @JoinColumn(name = "ID_FUNCIONARIO_ANALISIS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Funcionarios funcionario;   
    
       
    
    public EvaluacionSolicitudes() {

    }

    public EvaluacionSolicitudes(Long id) {
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

    
}
