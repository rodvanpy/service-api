/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Empresas;


/**
 *
 * @author Miguel
 */
@Local
public interface EmpresaManager extends GenericDao<Empresas, Long>{ 
    
    public Map<String, Object> guardar(Empresas empresa) throws Exception;
    
    public Map<String, Object> getEmpresa(Empresas empresa) throws Exception;
    
    public Map<String, Object> editar(Empresas empresa) throws Exception;
    
}
