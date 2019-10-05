/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Cuotas;
import py.com.mojeda.service.ejb.entity.Modalidades;
import py.com.mojeda.service.ejb.entity.TipoCalculos;
import py.com.mojeda.service.ejb.entity.TipoDesembolsos;


/**
 *
 * @author Miguel
 */
@Local
public interface CuotasManager extends GenericDao<Cuotas, Long>{   
    
    public List<Cuotas> cuotasCredito(BigDecimal montoCapital, BigDecimal montoInteres,Date fechaVencimiento,Integer diaVencimiento,
            Short plazo, Short periodoCapital, Short periodoInteres, Short periodoGracia, TipoCalculos tipoCalculo,
            Modalidades modalidad, TipoDesembolsos tipoDesembolso, Date fechaGeneracion) throws Exception;
}
