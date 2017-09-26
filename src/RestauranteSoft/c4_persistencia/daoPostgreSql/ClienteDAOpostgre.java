/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c4_persistencia.daoPostgreSql;

import RestauranteSoft.c3_dominio.contrato.IClienteDAO;
import RestauranteSoft.c3_dominio.entidades.Cliente;
import RestauranteSoft.c4_persistencia.GestorJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vanessa
 */
public class ClienteDAOpostgre implements IClienteDAO{
    GestorJDBC gestorJDBC;

    public ClienteDAOpostgre(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }
    
   
    @Override
    public List<Cliente> buscar(String nombre) throws SQLException{
        ArrayList<Cliente> clientes = new ArrayList();
        Cliente cliente;
        ResultSet resultado;
        String sentenciaSQL;

        sentenciaSQL = "select clienteid, dni, nombre, apellidos "
                + "from cliente where nombre like '%" + nombre + "%' order by nombre";

        resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
        try{
            while(resultado.next()){            
                cliente = new Cliente();
                cliente.setClienteid(resultado.getInt("clienteid"));
                cliente.setDni(resultado.getString("dni"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellidos(resultado.getString("apellidos"));
                clientes.add(cliente);
            }
            resultado.close();
            return clientes;    
        }catch(Exception e){
            throw new SQLException("No se pudo encontrar el Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
    }

    @Override
    public Cliente buscar(int clienteid) throws SQLException {
        Cliente cliente = null;
        ResultSet resultado;
        String sentenciaSQL;

        sentenciaSQL = "select clienteid, dni, nombre, apellidos "
                + "from cliente where clienteid = " + clienteid;
        resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
        try{
            if(resultado.next()){            
                cliente = new Cliente();
                cliente.setClienteid(resultado.getInt("clienteid"));
                cliente.setDni(resultado.getString("dni"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellidos(resultado.getString("apellidos"));            
            }
            resultado.close();
            return cliente;  
        }catch(Exception e){
            throw new SQLException("No se pudo encontrar el Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
    }

    @Override
    public Cliente buscarPorDNI(String dni) throws SQLException {
        Cliente cliente = null;
        ResultSet resultadoCliente;
        String sentenciaSQL;

        sentenciaSQL = "select clienteid, dni, nombre, apellidos "
                + "from cliente where dni = '" + dni + "'";
        
        resultadoCliente = gestorJDBC.ejecutarConsulta(sentenciaSQL);
        
            if(resultadoCliente.next()){            
                cliente = new Cliente();
                cliente.setClienteid(resultadoCliente.getInt("clienteid"));
                cliente.setDni(resultadoCliente.getString("dni"));
                cliente.setNombre(resultadoCliente.getString("nombre"));
                cliente.setApellidos(resultadoCliente.getString("apellidos"));
            }        
        resultadoCliente.close();
        return cliente; 
        
    }
    
    @Override
    public int ingresar(Cliente cliente) throws SQLException {
        String sentenciaSQL = "insert into cliente(dni, nombre, apellidos) "
                + "values(?,?,?)";
        try{
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            sentencia.setString(1, cliente.getDni());
            sentencia.setString(2, cliente.getNombre());
            sentencia.setString(3, cliente.getApellidos());
            return sentencia.executeUpdate();
        }catch(Exception e){
            throw new SQLException("No se pudo registrar el Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
    }
   
    @Override
    public int modificar(Cliente cliente) throws SQLException {
        String sentenciaSQL = "update cliente set dni = ?, nombre = ?, apellidos = ? "
                + "where clienteid = ?";
        try{
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            sentencia.setString(1, cliente.getDni());
            sentencia.setString(2, cliente.getNombre());
            sentencia.setString(3, cliente.getApellidos());
            sentencia.setInt(4, cliente.getClienteid());
            return sentencia.executeUpdate();
        }catch(Exception e){
            throw new SQLException("No se pudo modificar el Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
    }

   
    @Override
    public int eliminar(Cliente cliente) throws SQLException {
        String sentenciaSQL = "delete from cliente where clienteid = ?";
        try{
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            sentencia.setInt(1, cliente.getClienteid());
            return sentencia.executeUpdate();
        }catch(Exception e){
            throw new SQLException("No se pudo elimnar el Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
    }

    @Override
    public List<Cliente> buscarClientes() throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList();
        Cliente cliente;
        ResultSet resultado;
        String sentenciaSQL;

        sentenciaSQL = "select * from cliente";

        resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
        try{
            while(resultado.next()){            
                cliente = new Cliente();
                cliente.setClienteid(resultado.getInt("clienteid"));
                cliente.setDni(resultado.getString("dni"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellidos(resultado.getString("apellidos"));
                clientes.add(cliente);
            }
            resultado.close();
            return clientes;    
        }catch(Exception e){
            throw new SQLException("No se pudo encontrar el Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
    }

    @Override
    public Cliente buscarCliente(Cliente cliente) throws SQLException {
        ResultSet resultado;
        String sentenciaSQL;

        sentenciaSQL = "select clienteid, dni, nombre, apellidos from cliente";
        resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
        try{
            if(resultado.next()){            
                cliente = new Cliente();
                cliente.setClienteid(resultado.getInt("clienteid"));
                cliente.setDni(resultado.getString("dni"));
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellidos(resultado.getString("apellidos"));            
            }
            resultado.close();
            return cliente;  
        }catch(Exception e){
            throw new SQLException("No se pudo encontrar el Cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
    }
}
