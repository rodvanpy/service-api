/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.informconf;


/**
 *
 * @author miguel.ojeda
 */
public class IfcEControladosDb{

    private Long id;

    private String ruc;

    private String documento;

    private String fechaInicioControl;

    private String fechaFinalControl;

    private String lote;

    public IfcEControladosDb() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }      

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getFechaInicioControl() {
        return fechaInicioControl;
    }

    public void setFechaInicioControl(String fechaInicioControl) {
        this.fechaInicioControl = fechaInicioControl;
    }

    public String getFechaFinalControl() {
        return fechaFinalControl;
    }

    public void setFechaFinalControl(String fechaFinalControl) {
        this.fechaFinalControl = fechaFinalControl;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }
    
}
