/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.io.File;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


/**
 *
 * @author Miguel
 */
@Entity
public class Documentos extends Base implements Serializable{ 
    /**
    * 
    */
   private static final long serialVersionUID = 79861856088L;
    private static final String SECUENCIA = "seq_documentos_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

   @Lob
   @Column(name = "DOCUMENTO")
   private byte[] documento;
   
   @Column(name = "TIPO_ARCHIVO")
   private String tipoArchivo;

   @Column(name = "NOMBRE_DOCUMENTO")
   private String nombreDocumento;  

   @Column(name = "ENTIDAD")
   private String entidad;
   
   @Column(name = "PATH")
   private String path;

   @Column(name = "ID_ENTIDAD")
   private Long idEntidad;
   
   @ManyToOne
   @JoinColumn(name = "ID_TIPO_DOCUMENTO")
   private TipoDocumentos tipoDocumento;

   @JsonIgnore
   @ManyToOne
   @JoinColumn(name = "EMPRESA")
   private Empresas empresa;
   
//   @JsonDeserialize(as = MultipartFile.class)
//   @JsonIgnoreProperties(ignoreUnknown = true)


   /**
    * Constructor vacío
    */
   public Documentos() {

   }

   /**
    * @param id
    *            el id de Imagen
    */
   public Documentos(Long id) {
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
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the idEntidad
     */
    public Long getIdEntidad() {
        return idEntidad;
    }

    /**
     * @param idEntidad the idEntidad to set
     */
    public void setIdEntidad(Long idEntidad) {
        this.idEntidad = idEntidad;
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
     * @return the documento
     */
    public byte[] getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(byte[] documento) {
        this.documento = documento;
    }

    /**
     * @return the nombreDocumento
     */
    public String getNombreDocumento() {
        return nombreDocumento;
    }

    /**
     * @param nombreDocumento the nombreDocumento to set
     */
    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    /**
     * @return the tipoDocumento
     */
    public TipoDocumentos getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(TipoDocumentos tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
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

    
   
}
