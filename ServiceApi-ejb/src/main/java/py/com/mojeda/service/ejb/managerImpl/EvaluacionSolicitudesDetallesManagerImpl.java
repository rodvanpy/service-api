/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.EvaluacionSolicitudesDetalles;
import py.com.mojeda.service.ejb.manager.EvaluacionSolicitudesDetallesManager;



/**
 *
 * @author mojeda
 */
@Stateless
public class EvaluacionSolicitudesDetallesManagerImpl extends GenericDaoImpl<EvaluacionSolicitudesDetalles, Long>
        implements EvaluacionSolicitudesDetallesManager {

    @Override
    protected Class<EvaluacionSolicitudesDetalles> getEntityBeanType() {
        return EvaluacionSolicitudesDetalles.class;
    }
}
