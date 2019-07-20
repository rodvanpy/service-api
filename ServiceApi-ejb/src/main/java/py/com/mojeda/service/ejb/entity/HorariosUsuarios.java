/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 *
 * @author Miguel
 */
@Entity
@Table(name = "HORARIOS_FUNCIONARIOS")
public class HorariosUsuarios extends Base{
    
    private static final long serialVersionUID = 8538760347986185608L;
    private static final String SECUENCIA = "seq_horario_funcionario_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @NotNull(message = "Ingrese Fecha Inicio")
    @Column(name = "FECHA_INICIO")
    private Timestamp fechaInicio;
    
    @Column(name = "FECHA_FIN")
    private Timestamp fechaFin;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_HORARIO", referencedColumnName = "id")
    @Valid
    private TipoHorarios tipoHorario;
    
    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "id")
    @Valid
    private Funcionarios usuario;        
    
    public HorariosUsuarios() {

    }

    /**
     * @param id
     *            el id de Usuario
     */
    public HorariosUsuarios(Long id) {
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
     * @return the fechaInicio
     */
    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Timestamp getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the tipoHorario
     */
    public TipoHorarios getTipoHorario() {
        return tipoHorario;
    }

    /**
     * @param tipoHorario the tipoHorario to set
     */
    public void setTipoHorario(TipoHorarios tipoHorario) {
        this.tipoHorario = tipoHorario;
    }

    /**
     * @return the usuario
     */
    public Funcionarios getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Funcionarios usuario) {
        this.usuario = usuario;
    }

    

    
           
}
