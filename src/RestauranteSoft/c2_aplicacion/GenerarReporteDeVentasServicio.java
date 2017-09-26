/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c2_aplicacion;

import RestauranteSoft.c3_dominio.contrato.FabricaAbstractaDAO;
import RestauranteSoft.c3_dominio.contrato.IComprobanteDePagoDAO;
import RestauranteSoft.c3_dominio.entidades.ComprobanteDePago;
import RestauranteSoft.c3_dominio.servicio.ManejadorComprobanteDePago;
import RestauranteSoft.c4_persistencia.daoPostgreSql.ComprobanteDePagoDAOpostgre;
import RestauranteSoft.c4_persistencia.GestorJDBC;
import java.util.List;

/**
 *
 * @author Sandy
 */
public class GenerarReporteDeVentasServicio {
    private GestorJDBC gestorJDBC;
    private IComprobanteDePagoDAO comprobanteDePagoDAO;

    public GenerarReporteDeVentasServicio() {
        FabricaAbstractaDAO fabricaAbstractaDAO= FabricaAbstractaDAO.getInstancia();
        gestorJDBC= fabricaAbstractaDAO.crearGestorJDBC();
        comprobanteDePagoDAO= fabricaAbstractaDAO.crearComprobanteDePago(gestorJDBC);
    }
    
    public ManejadorComprobanteDePago reporteVentas()throws Exception{
        gestorJDBC.abrirConexion();
        ComprobanteDePagoDAOpostgre comprobanteDePagoDAOpostgre = new ComprobanteDePagoDAOpostgre(gestorJDBC);
        List<ComprobanteDePago> reporteVentas= comprobanteDePagoDAOpostgre.reporteVentas();
        ManejadorComprobanteDePago manejadorComprobanteDePago = new ManejadorComprobanteDePago(reporteVentas);
         gestorJDBC.cerrarConexion();
         return manejadorComprobanteDePago;
    }
    
    
}
