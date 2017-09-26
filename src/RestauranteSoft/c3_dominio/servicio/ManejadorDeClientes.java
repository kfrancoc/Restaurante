/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c3_dominio.servicio;

import RestauranteSoft.c3_dominio.entidades.Cliente;
import java.util.List;

/**
 *
 * @author Vanessa
 */
public class ManejadorDeClientes {
    private List<Cliente> clientes;

    public ManejadorDeClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
    
    public int evaluarTotalClientes(){
        return clientes.size();
    }
}
