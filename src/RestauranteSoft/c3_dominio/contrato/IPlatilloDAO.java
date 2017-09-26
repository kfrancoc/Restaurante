/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c3_dominio.contrato;

import RestauranteSoft.c3_dominio.entidades.Platillo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Karito
 */
public interface IPlatilloDAO {
    
    public List<Platillo> buscar(String descripcion)throws SQLException;

    public List<Platillo> buscarPlatillos()throws SQLException;
    
    public Platillo buscar(int platilloid)throws SQLException;
    
    public int ingresar(Platillo platillo)throws SQLException;
    
    public int modificar(Platillo platillo)throws SQLException;
    
    public int eliminar(Platillo platillo)throws SQLException;
    
    public int actualizarStock(Platillo platillo)throws Exception;
    
    public List<Platillo> listaPlatillos()throws SQLException;
    
}
