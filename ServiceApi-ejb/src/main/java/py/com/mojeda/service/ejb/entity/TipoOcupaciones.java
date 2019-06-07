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
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Miguel Ojeda
 *
 */
@Entity
@Table(name = "TIPO_OCUPACIONES")
public class TipoOcupaciones extends Base {

    private static final long serialVersionUID = 1546546546L;
    private static final String SECUENCIA = "seq_tipo_ocupacion_id";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = SECUENCIA)
    @SequenceGenerator(name=SECUENCIA, sequenceName=SECUENCIA, allocationSize = 1)
    @Column(name = "id")
    private Long id; 
    
    @NotEmpty(message = "Ingrese Nombre")
    @Column(name = "NOMBRE", nullable = false, length = 128)
    private String nombre;
    
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public TipoOcupaciones() {
    }

    public TipoOcupaciones(Long id) {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    
}
