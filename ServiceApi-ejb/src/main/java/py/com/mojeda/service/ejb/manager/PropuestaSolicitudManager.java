/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Personas;
import py.com.mojeda.service.ejb.entity.PropuestaSolicitud;
import py.com.mojeda.service.ejb.entity.TipoCalculos;


/**
 *
 * @author Miguel
 */
@Local
public interface PropuestaSolicitudManager extends GenericDao<PropuestaSolicitud, Long>{   
    
    public PropuestaSolicitud guardar(PropuestaSolicitud propuestaSolicitud, Long idSucursal) throws Exception;
    
    public PropuestaSolicitud editar(PropuestaSolicitud propuestaSolicitud, Long idSucursal) throws Exception;
    
    public PropuestaSolicitud getPropuestaSolicitud(PropuestaSolicitud propuestaSolicitud) throws Exception;
    
    public Personas getPersonaSolicitud(Long idSolicitud, Long idPersona, String tipo) throws Exception;   
    
    public Personas editarPersonaSolicitud(Personas persona, Long idSolicitud, Long idEmpresa) throws Exception;
    
    public void tranferirPropuesta(Long idSolicitud, Long idPersona) throws Exception;
       
    public Double periodoInteresCompuesto(Double plazo, Double interes, Long periodoCapital, Long vencimientoInteres);
    
    public Double periodoInteresSimple(Double plazo, Double interes, Long periodoCapital, Long vencimientoInteres);
    
    public Long calcularCuota(Double modalidad, Double plazo, Long periodoCapital, Long vencimientoInteres,
            Double tasaInteres, Double montoSolicitado, TipoCalculos tipoCalculoImporte, Double gastosAdministrativos);
    
    public Long calcularMontoInteres(Double plazo, Long periodoCapital, Long vencimientoInteres,
            Double tasaInteres, Double montoSolicitado, TipoCalculos tipoCalculoImporte, Double gastosAdministrativos);
}
