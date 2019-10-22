/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.entity;

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
 * @author Miguel
 */
@Entity
@Table(name = "DOCUMENTOS_ENTIDADES")
public class DocumentosEntidades extends Base{
    
    private static final long serialVersionUID = 8538760347986185608L;
    private static final String SECUENCIA = "seq_documento_entidad_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "ID_ENTIDAD")
    private Long idEntidad;
    
    @Column(name = "ENTIDAD")
    private String entidad;
    
    @ManyToOne
    @JoinColumn(name = "ID_DOCUMENTO", referencedColumnName = "id")
    private Documentos documentos;
    
    public DocumentosEntidades() {

    }

    /**
     * @param id
     *            el id de Usuario
     */
    public DocumentosEntidades(Long id) {
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

    public Documentos getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Documentos documentos) {
        this.documentos = documentos;
    }

    public Long getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Long idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }    
           
}
