/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c2_aplicacion;

import RestauranteSoft.c3_dominio.contrato.FabricaAbstractaDAO;
import RestauranteSoft.c3_dominio.contrato.IPlatilloDAO;
import RestauranteSoft.c3_dominio.entidades.Platillo;
import RestauranteSoft.c3_dominio.servicio.ManejadorDePlatillos;
import RestauranteSoft.c4_persistencia.GestorJDBC;
import RestauranteSoft.c4_persistencia.daoPostgreSql.PlatilloDAOPostgre;
import java.util.List;

/**
 *
 * @author Karito
 */
public class GestionarPlatilloServicio {
    private GestorJDBC gestorJDBC;
    private IPlatilloDAO platilloDAO;

    public GestionarPlatilloServicio() {
        FabricaAbstractaDAO fabricaAbstractaDAO = FabricaAbstractaDAO.getInstancia();
        gestorJDBC = fabricaAbstractaDAO.crearGestorJDBC();
        platilloDAO = fabricaAbstractaDAO.crearPlatilloDAO(gestorJDBC);
    }
    
    public ManejadorDePlatillos buscarPlatillosDescripcion(String descripcion)throws Exception{
        gestorJDBC.abrirConexion();
        PlatilloDAOPostgre platilloDAO = new PlatilloDAOPostgre(gestorJDBC);
        List<Platillo> platillos = platilloDAO.buscar(descripcion);
        ManejadorDePlatillos manejadorDePlatillos = new ManejadorDePlatillos(platillos);
        gestorJDBC.cerrarConexion();
        return manejadorDePlatillos;
    }

    public ManejadorDePlatillos buscarPlatillos()throws Exception{
        gestorJDBC.abrirConexion();
        PlatilloDAOPostgre platilloDAO = new PlatilloDAOPostgre(gestorJDBC);
        List<Platillo> platillos = platilloDAO.buscarPlatillos();
        ManejadorDePlatillos manejadorDePlatillos = new ManejadorDePlatillos(platillos);
        gestorJDBC.cerrarConexion();
        return manejadorDePlatillos;
    }

    public Platillo buscarPlatillo(int platilloid)throws Exception{
        gestorJDBC.abrirConexion();
        Platillo platillo= platilloDAO.buscar(platilloid);
        gestorJDBC.cerrarConexion();
        return platillo;
    }
    
    public int crearPlatillo(Platillo platillo)throws Exception{
        gestorJDBC.abrirConexion();
        int registros_afectados= platilloDAO.ingresar(platillo);
        gestorJDBC.cerrarConexion();
        return registros_afectados;
    }
    
    public int modificarPlatillo(Platillo platillo)throws Exception{
        gestorJDBC.abrirConexion();
        int registros_afectados= platilloDAO.modificar(platillo);
        gestorJDBC.cerrarConexion();
        return registros_afectados;
    }
    
    public int eliminarPlatillo(Platillo platillo)throws Exception{
        gestorJDBC.abrirConexion();
        int registros_afectados= platilloDAO.eliminar(platillo);
        gestorJDBC.cerrarConexion();
        return registros_afectados;
    }
}
