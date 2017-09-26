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
public class GenerarReporteDePlatillosServicio {
    private GestorJDBC gestorJDBC;
    private IPlatilloDAO platilloDAO;

    public GenerarReporteDePlatillosServicio() {
        FabricaAbstractaDAO fabricaAbstractaDAO = FabricaAbstractaDAO.getInstancia();
        gestorJDBC = fabricaAbstractaDAO.crearGestorJDBC();
        platilloDAO = fabricaAbstractaDAO.crearPlatilloDAO(gestorJDBC);
    }
    
    public ManejadorDePlatillos reportePlatillos()throws Exception{
        gestorJDBC.abrirConexion();
        PlatilloDAOPostgre platilloDAOPostgre = new PlatilloDAOPostgre(gestorJDBC);
        List<Platillo> platillos= platilloDAOPostgre.listaPlatillos();
        ManejadorDePlatillos manejadorDePlatillos = new ManejadorDePlatillos(platillos);
        gestorJDBC.cerrarConexion();
        return manejadorDePlatillos;
    }
}
