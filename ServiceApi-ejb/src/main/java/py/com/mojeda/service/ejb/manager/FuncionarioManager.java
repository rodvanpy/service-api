/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Funcionarios;


/**
 *
 * @author Miguel
 */
@Local
public interface FuncionarioManager extends GenericDao<Funcionarios, Long>{  
    
    public Map<String, Object> guardar(Funcionarios funcionario) throws Exception;
    
    public Map<String, Object> editar(Funcionarios funcionario) throws Exception;
    
    public Map<String, Object> getUsuario(Funcionarios funcionario) throws Exception;
    
}
