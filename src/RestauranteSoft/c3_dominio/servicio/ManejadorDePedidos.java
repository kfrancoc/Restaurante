/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c3_dominio.servicio;

import RestauranteSoft.c3_dominio.entidades.Pedido;
import java.util.List;

/**
 *
 * @author Patricia
 */
public class ManejadorDePedidos {
    private List<Pedido> pedidos;

    public ManejadorDePedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    public int evaluarTotalPedidos(){
        return pedidos.size();
    }
}
