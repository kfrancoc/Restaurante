/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c3_dominio.servicio;

import RestauranteSoft.c3_dominio.entidades.Platillo;
import java.util.List;

/**
 *
 * @author Karito
 */
public class ManejadorDePlatillos {
    private List<Platillo> platillos;

    public ManejadorDePlatillos(List<Platillo> platillos) {
        this.platillos = platillos;
    }

    public List<Platillo> getPlatillos() {
        return platillos;
    }
    
    public int evaluarTotalPlatillos(){
        return platillos.size();
    }
}
