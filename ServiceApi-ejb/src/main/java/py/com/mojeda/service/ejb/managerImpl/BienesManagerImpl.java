/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.com.mojeda.service.ejb.managerImpl;

import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import py.com.mojeda.service.ejb.entity.Bienes;
import py.com.mojeda.service.ejb.manager.BienesManager;
import py.com.mojeda.service.ejb.manager.CiudadesManager;
import py.com.mojeda.service.ejb.manager.DepartamentosPaisManager;
import py.com.mojeda.service.ejb.manager.PaisesManager;
import static py.com.mojeda.service.ejb.managerImpl.OcupacionPersonaManagerImpl.logger;
import py.com.mojeda.service.ejb.utils.ApplicationLogger;


/**
 *
 * @author Miguel
 */
@Stateless
public class BienesManagerImpl extends GenericDaoImpl<Bienes, Long>
        implements BienesManager {

    @Override
    protected Class<Bienes> getEntityBeanType() {
        return Bienes.class;
    }
    
    protected static final ApplicationLogger logger = ApplicationLogger.getInstance();
    
    @EJB(mappedName = "java:app/ServiceApi-ejb/PaisesManagerImpl")
    private PaisesManager paisesManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/DepartamentosPaisManagerImpl")
    private DepartamentosPaisManager departamentosPaisManager;

    @EJB(mappedName = "java:app/ServiceApi-ejb/CiudadesManagerImpl")
    private CiudadesManager ciudadesManager;

    @Override
    public Map<String, Object> guardarBienes(Bienes bienes) throws Exception {
        Map<String, Object> object = null;
        try {
            
        } catch (Exception e) {
            logger.error("Error al guardar registro", e);
        }
        return object;
    }

    @Override
    public Map<String, Object> editarBienes(Bienes bienes) throws Exception {
        Map<String, Object> object = null;
        try {
            
        } catch (Exception e) {
            logger.error("Error al guardar editar", e);
        }
        return object;
    }

    @Override
    public Map<String, Object> getBienes(Bienes bienes) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
