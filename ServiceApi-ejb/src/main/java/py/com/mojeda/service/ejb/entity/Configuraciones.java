/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
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
@Table(name = "CONFIGURACIONES", schema = "PUBLIC")
public class Configuraciones extends Base {

    private static final long serialVersionUID = 1L;
    private static final String SECUENCIA = "seq_configuraciones_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = SECUENCIA)
    @SequenceGenerator(name = SECUENCIA, sequenceName = SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "CODIGO")
    private String codigo;
    
    @Column(name = "SERVICIO")
    private String servicio;
    
    @Column(name = "USUARIO")
    private String usuario;
    
    @Column(name = "CLAVE_ACCESO")
    private String claveAcceso;

    @Column(name = "URL_1")
    private String url1;
    
    @Column(name = "URL_2")
    private String url2;
    
    @Column(name = "URL_3")
    private String url3;
    
    @Column(name = "SERVICE_NAME")
    private String serviName;
    
    @Column(name = "SERVICE_PORT")
    private String servicePort;
    
    @Column(name = "TIMEOUT")
    private Long timeout;

    @JsonIgnore
    @Column(name = "ID_EMPRESA")
    private Long idEmpresa;

    public Configuraciones() {
    }

    public Configuraciones(Long id) {
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
     * @return the servicio
     */
    public String getServicio() {
        return servicio;
    }

    /**
     * @param servicio the servicio to set
     */
    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
     * @return the url1
     */
    public String getUrl1() {
        return url1;
    }

    /**
     * @param url1 the url1 to set
     */
    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    /**
     * @return the url2
     */
    public String getUrl2() {
        return url2;
    }

    /**
     * @param url2 the url2 to set
     */
    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    /**
     * @return the url3
     */
    public String getUrl3() {
        return url3;
    }

    /**
     * @param url3 the url3 to set
     */
    public void setUrl3(String url3) {
        this.url3 = url3;
    }

    /**
     * @return the serviName
     */
    public String getServiName() {
        return serviName;
    }

    /**
     * @param serviName the serviName to set
     */
    public void setServiName(String serviName) {
        this.serviName = serviName;
    }

    /**
     * @return the servicePort
     */
    public String getServicePort() {
        return servicePort;
    }

    /**
     * @param servicePort the servicePort to set
     */
    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }

    /**
     * @return the timeout
     */
    public Long getTimeout() {
        return timeout;
    }

    /**
     * @param timeout the timeout to set
     */
    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public Long getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Long idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    
}
