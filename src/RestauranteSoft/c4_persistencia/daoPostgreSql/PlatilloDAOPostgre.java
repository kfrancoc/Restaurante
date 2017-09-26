/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c4_persistencia.daoPostgreSql;

import RestauranteSoft.c3_dominio.contrato.IPlatilloDAO;
import RestauranteSoft.c3_dominio.entidades.Platillo;
import RestauranteSoft.c4_persistencia.GestorJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class PlatilloDAOPostgre implements IPlatilloDAO{
    
    GestorJDBC gestorJDBC;

    public PlatilloDAOPostgre(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }
    
    @Override
    public List<Platillo> buscar(String descripcion) throws SQLException{
        ArrayList<Platillo> platillos = new ArrayList();
        Platillo platillo;
        ResultSet resultado;
        String sentenciaSQL;

        sentenciaSQL = "select platilloid, descripcion, tipo, precio, stock, estado"
                + " from platillo where descripcion like '%" + descripcion + "%' order by descripcion";

        resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
        try{
            while(resultado.next()){            
                platillo = new Platillo();
                platillo.setPlatilloid(resultado.getInt("platilloid"));
                platillo.setDescripcion(resultado.getString("descripcion"));
                platillo.setTipo(resultado.getString("tipo"));
                platillo.setPrecio(resultado.getDouble("precio"));
                platillo.setStock(resultado.getInt("stock"));
                platillo.setEstado(resultado.getString("estado"));
                platillos.add(platillo);
            }
            resultado.close();
            return platillos;    
        }catch(Exception e){
            throw new SQLException("No se pudo encontrar el Platillo.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
    }

    @Override
    public Platillo buscar(int platilloid) throws SQLException {
        Platillo platillo = null;
        ResultSet resultado;
        String sentenciaSQL;

        sentenciaSQL = "select platilloid, descripcion, tipo, precio, stock, estado"
                + " from platillo where platilloid = " + platilloid;

        resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
        try{
            if(resultado.next()){            
                platillo = new Platillo();
                platillo.setPlatilloid(resultado.getInt("platilloid"));
                platillo.setDescripcion(resultado.getString("descripcion"));
                platillo.setTipo(resultado.getString("tipo"));
                platillo.setPrecio(resultado.getDouble("precio"));
                platillo.setStock(resultado.getInt("stock"));
                platillo.setEstado(resultado.getString("estado"));
            }
            resultado.close();
            return platillo;  
        }catch(Exception e){
            throw new SQLException("No se pudo encontrar el Platillo.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
    }
    
    @Override
    public int ingresar(Platillo platillo) throws SQLException {
        String sentenciaSQL = "insert into platillo(descripcion,tipo,precio,stock,estado) "
                + "values(?,?,?,?,?)";
        try{
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            sentencia.setString(1, platillo.getDescripcion());
            sentencia.setString(2, platillo.getTipo());
            sentencia.setDouble(3, platillo.getPrecio());
            sentencia.setInt(4, platillo.getStock());
            sentencia.setString(5, platillo.getEstado());
            return sentencia.executeUpdate();
        }catch(Exception e){
            throw new SQLException("No se pudo registrar el Platillo.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
    }
    
    @Override
    public int modificar(Platillo platillo) throws SQLException {
        String sentenciaSQL = "update platillo set descripcion = ?, tipo = ?, precio = ?, stock = ?, estado = ? "
                + "where platilloid = ?";
        try{
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            sentencia.setString(1, platillo.getDescripcion());
            sentencia.setString(2, platillo.getTipo());
            sentencia.setDouble(3, platillo.getPrecio());
            sentencia.setInt(4, platillo.getStock());
            sentencia.setString(5, platillo.getEstado());
            sentencia.setInt(6, platillo.getPlatilloid());
            return sentencia.executeUpdate();
        }catch(Exception e){
            throw new SQLException("No se pudo modificar el Platillo.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
    }

    @Override
    public int eliminar(Platillo platillo) throws SQLException {
        String sentenciaSQL = "delete from platillo where platilloid = ?";
        try{
            PreparedStatement sentencia = gestorJDBC.prepararSentencia(sentenciaSQL);
            sentencia.setInt(1, platillo.getPlatilloid());
            return sentencia.executeUpdate();
        }catch(Exception e){
            throw new SQLException("No se pudo eliminar el Platillo.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
    }

    @Override
    public int actualizarStock(Platillo platillo) throws Exception {
        String sentenciaSQL = "UPDATE platillo SET stock=? WHERE platilloid = ?";
        PreparedStatement sentencia= gestorJDBC.prepararSentencia(sentenciaSQL);
        sentencia.setDouble(1, platillo.getStock());
        sentencia.setInt(2, platillo.getPlatilloid());
        return sentencia.executeUpdate();
    }

    @Override
    public List<Platillo> listaPlatillos() throws SQLException {
        ArrayList<Platillo> platillos = new ArrayList();
        Platillo platillo;
        ResultSet resultado;
        String sentenciaSQL;

        sentenciaSQL = "select platilloid, descripcion, tipo, precio, stock, estado"
                + " from platillo";

        resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
        while(resultado.next()){            
            platillo = new Platillo();
            platillo.setPlatilloid(resultado.getInt("platilloid"));
            platillo.setDescripcion(resultado.getString("descripcion"));
            platillo.setTipo(resultado.getString("tipo"));
            platillo.setPrecio(resultado.getDouble("precio"));
            platillo.setStock(resultado.getInt("stock"));
            platillo.setEstado(resultado.getString("estado"));
            platillos.add(platillo);
        }
        resultado.close();
        return platillos;    
    }

    @Override
    public List<Platillo> buscarPlatillos() throws SQLException {
        ArrayList<Platillo> platillos = new ArrayList();
        Platillo platillo;
        ResultSet resultado;
        String sentenciaSQL;

        sentenciaSQL = "select platilloid, descripcion, tipo, precio, stock, estado from platillo";

        resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
        try{
            while(resultado.next()){            
                platillo = new Platillo();
                platillo.setPlatilloid(resultado.getInt("platilloid"));
                platillo.setDescripcion(resultado.getString("descripcion"));
                platillo.setTipo(resultado.getString("tipo"));
                platillo.setPrecio(resultado.getDouble("precio"));
                platillo.setStock(resultado.getInt("stock"));
                platillo.setEstado(resultado.getString("estado"));
                platillos.add(platillo);
            }
            resultado.close();
            return platillos;    
        }catch(Exception e){
            throw new SQLException("No se pudo encontrar el Platillo.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
    }
    
}
