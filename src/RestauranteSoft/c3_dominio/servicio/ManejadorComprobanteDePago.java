/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c3_dominio.servicio;

import RestauranteSoft.c3_dominio.entidades.ComprobanteDePago;
import java.util.List;

/**
 *
 * @author Marlon
 */
public class ManejadorComprobanteDePago {
    private List<ComprobanteDePago> comprobanteDePagos;

    public ManejadorComprobanteDePago(List<ComprobanteDePago> comprobanteDePagos) {
        this.comprobanteDePagos = comprobanteDePagos;
    }

    public List<ComprobanteDePago> getComprobanteDePago() {
        return comprobanteDePagos;
    }
    
    public int evaluarTotalComprobantesDePago(){
        return comprobanteDePagos.size();
    }
}
