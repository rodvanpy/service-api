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
public interface FuncionariosManager extends GenericDao<Funcionarios, Long>{  
    
    public Funcionarios guardar(Funcionarios usuario) throws Exception;
    
    public Funcionarios editar(Funcionarios usuario) throws Exception;
    
    public Funcionarios getUsuario(Funcionarios usuario, String included) throws Exception;
    
}
