/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

/**
 *
 * @author miguel.ojeda
 */
@Entity
@Table(name = "ESTUDIOS")
public class Estudios extends Base {

    private static final long serialVersionUID = 1574657L;
    private static final String SECUENCIA = "seq_estudios_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "FECHA_INICIO")
    private Timestamp fechaInicio;
    
    @Column(name = "FECHA_FIN")
    private Timestamp fechaFin;
    
    @Column(name = "TITULO")
    private String titulo;
    
    @Column(name = "CONCLUIDO")
    private Boolean concluido;
    
    @Column(name = "NUMERO_REGISTRO")
    private String numeroRegistro;
    
    @Column(name = "SEMESTRE")
    private String semestre;
    
    @Column(name = "NOMBRE_ENTIDAD")
    private String nombre;
            
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_ESTUDIO", referencedColumnName = "id")
    private TipoEstudios tipoEstudio;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "id")
    private Personas persona;  
    
    public Estudios() {
    }

    public Estudios(Long id) {
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
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the tipoEstudio
     */
    public TipoEstudios getTipoEstudio() {
        return tipoEstudio;
    }

    /**
     * @param tipoEstudio the tipoEstudio to set
     */
    public void setTipoEstudio(TipoEstudios tipoEstudio) {
        this.tipoEstudio = tipoEstudio;
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

    public Boolean getConcluido() {
        return concluido;
    }

    public void setConcluido(Boolean concluido) {
        this.concluido = concluido;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
