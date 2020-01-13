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
 * @author miguel.ojeda
 */
@Entity
@Table(name = "PARAMETROS", schema = "PUBLIC")
public class Parametros{
    
    private static long serialVersionUID = 857603479861808L;
    private static final String SECUENCIA = "seq_parametros_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "ABREVIATURA")
    private String abreviatura;
    
    @Column(name = "COD_AFILIADO")
    private String codAfiliado;
    
    @Column(name = "COD_EMPRESA_INF")
    private String codEmpresaInf;
    
    @Column(name = "PUBLIC_KEY")
    private String publicKey;
    
    @Column(name = "PRIVATE_KEY")
    private String privateKey;
    
    @Column(name = "USUARIO")
    private String usuario;
    
    @Column(name = "CLAVE_ACCESO")
    private String claveAcceso;
    
    @Column(name = "NOMBRE")
    private String nombre;
    
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
    
    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "id")
    private Empresas empresa;
    
    
    public Parametros() {
        
    }

    public Parametros(Long id) {
        this.id = id;
    }

    public Parametros(Empresas empresa) {
        this.empresa = empresa;
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
     * @return the abreviatura
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * @param abreviatura the abreviatura to set
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
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

    public String getServiName() {
        return serviName;
    }

    public void setServiName(String serviName) {
        this.serviName = serviName;
    }

    public String getServicePort() {
        return servicePort;
    }

    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public String getCodAfiliado() {
        return codAfiliado;
    }

    public void setCodAfiliado(String codAfiliado) {
        this.codAfiliado = codAfiliado;
    }

    public Empresas getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    } 

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getCodEmpresaInf() {
        return codEmpresaInf;
    }

    public void setCodEmpresaInf(String codEmpresaInf) {
        this.codEmpresaInf = codEmpresaInf;
    }   
           
}
