/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c2_aplicacion;

import RestauranteSoft.c3_dominio.contrato.FabricaAbstractaDAO;
import RestauranteSoft.c3_dominio.contrato.IClienteDAO;
import RestauranteSoft.c3_dominio.contrato.IComprobanteDePagoDAO;
import RestauranteSoft.c3_dominio.contrato.IUsuarioDAO;
import RestauranteSoft.c3_dominio.entidades.Cliente;
import RestauranteSoft.c3_dominio.entidades.ComprobanteDePago;
import RestauranteSoft.c3_dominio.entidades.Pedido;
import RestauranteSoft.c3_dominio.entidades.Usuario;
import RestauranteSoft.c4_persistencia.GestorJDBC;
import java.util.List;

/**
 *
 * @author Marlon
 */
public class GenerarComprobanteDePagoServicio {
    
    private GestorJDBC gestorJDBC;
    private IComprobanteDePagoDAO comprobanteDePagoDAO;
    private IUsuarioDAO usuarioDAO;
    private IClienteDAO clienteDAO;
    
    public GenerarComprobanteDePagoServicio() {
        FabricaAbstractaDAO fabricaAbstractaDAO= FabricaAbstractaDAO.getInstancia();
        gestorJDBC= fabricaAbstractaDAO.crearGestorJDBC();
        comprobanteDePagoDAO= fabricaAbstractaDAO.crearComprobanteDePago(gestorJDBC);
        usuarioDAO= fabricaAbstractaDAO.crearUsuarioDAO(gestorJDBC);
        clienteDAO= fabricaAbstractaDAO.crearClienteDAO(gestorJDBC);
    }
    
    //--------------------------------------------------------------------------------------------------------
    //buscamos usuario - que realizara l pedido
     public Usuario buscarUsuarioPorDNI(String dni)throws Exception{
        gestorJDBC.abrirConexion();
        Usuario usuario= usuarioDAO.buscarPorDNI(dni);
        gestorJDBC.cerrarConexion();
        return usuario;
    }

     public List<Usuario> listaUsuarios()throws Exception{
        try{
            gestorJDBC.abrirConexion();
            List<Usuario>listaUsuarios= usuarioDAO.listaUsuarios();             
            gestorJDBC.cerrarConexion();
            return listaUsuarios;
        }catch (Exception e) {
            gestorJDBC.cerrarConexion();
            throw e;
        }
    }
    //-----------------------------------------------------------------------------------------------------------
    
    public Cliente buscarClientePorDNI(String dni) throws Exception{     
        gestorJDBC.abrirConexion();        
        Cliente cliente = clienteDAO.buscarPorDNI(dni);
        gestorJDBC.cerrarConexion();
        return cliente;
    }
    //-----------------------------------------------------------------------------------------------------------
    
    public int crearComprobanteDePago(ComprobanteDePago comprobanteDePago)throws Exception{
        gestorJDBC.abrirConexion();
        Pedido pedido= comprobanteDePago.getPedido();
        try {
            gestorJDBC.iniciarTransaccion();
            pedido.setEstado("COBRADO");
            pedido.getMesa().setEstado("DISPONIBLE");
            int registros_afectados=comprobanteDePagoDAO.ingresar(comprobanteDePago);
            gestorJDBC.terminarTransaccion();
            return registros_afectados;
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }
    }
}
