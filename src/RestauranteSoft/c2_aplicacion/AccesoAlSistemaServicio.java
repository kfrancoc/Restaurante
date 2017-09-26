/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c2_aplicacion;

import RestauranteSoft.c3_dominio.contrato.FabricaAbstractaDAO;
import RestauranteSoft.c3_dominio.contrato.IUsuarioDAO;
import RestauranteSoft.c4_persistencia.GestorJDBC;

/**
 *
 * @author Marlon
 */
public class AccesoAlSistemaServicio {
    private GestorJDBC gestorJDBC;
    private IUsuarioDAO usuarioDAO;

    public AccesoAlSistemaServicio() {
        FabricaAbstractaDAO fabricaAbstractaDAO = FabricaAbstractaDAO.getInstancia();
        gestorJDBC = fabricaAbstractaDAO.crearGestorJDBC();
        usuarioDAO = fabricaAbstractaDAO.crearUsuarioDAO(gestorJDBC);
    }
    
    public boolean accesoPermitido(String nombre ,String password, String tipo)throws Exception{
        gestorJDBC.abrirConexion();
        boolean flag = usuarioDAO.accederAlSistema(nombre, password, tipo);
        gestorJDBC.cerrarConexion();
        return flag;
    }
    
}
