/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c3_dominio.contrato;

import RestauranteSoft.c3_dominio.entidades.Cliente;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Vanessa
 */
public interface IClienteDAO {
    public List<Cliente> buscar(String nombre)throws SQLException;
    
    public List<Cliente> buscarClientes()throws SQLException;
    
    public Cliente buscar(int clienteid) throws SQLException;
    
    public Cliente buscarCliente(Cliente cliente) throws SQLException;
    
    public Cliente buscarPorDNI(String dni) throws SQLException;
    
    public int ingresar(Cliente cliente) throws SQLException;
    
    public int modificar(Cliente cliente) throws SQLException;
    
    public int eliminar(Cliente cliente) throws SQLException;
    
}
