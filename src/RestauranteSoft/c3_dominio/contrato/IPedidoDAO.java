/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c3_dominio.contrato;

import RestauranteSoft.c3_dominio.entidades.Pedido;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Patricia
 */
public interface IPedidoDAO {
    
    public int ingresar(Pedido pedido) throws Exception;
    
    public int modificar(Pedido pedido) throws Exception;
    
    public Pedido buscar(int pedidoid) throws Exception;
    
    public int ultimoRegistro()throws Exception;
    
    public List<Pedido> ListaPedidos()throws SQLException;
    
    public int modificarEstado(Pedido pedido)throws Exception;
    
}
