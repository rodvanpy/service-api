/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.com.mojeda.service.ejb.manager;


import java.util.Map;
import javax.ejb.Local;
import py.com.mojeda.service.ejb.entity.Clientes;


/**
 *
 * @author Miguel
 */
@Local
public interface ClientesManager extends GenericDao<Clientes, Long>{  
    
    public Clientes guardar(Clientes cliente) throws Exception;
    
    public Clientes editar(Clientes cliente) throws Exception;
    
    public Clientes getCliente(Clientes cliente, String included) throws Exception;
    
}
