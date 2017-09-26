/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RestauranteSoft.c4_persistencia.daoPostgreSql;

import RestauranteSoft.c4_persistencia.GestorJDBC;
import java.sql.DriverManager;

/**
 *
 * @author Marlon
 */
public class GestorJDBCPostgre extends GestorJDBC {    

    @Override
    public void abrirConexion() throws Exception{        
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/dbVentas";
        conexion = DriverManager.getConnection(url, "postgres", "root");   
    }
    
}
