/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c3_dominio.contrato;

import RestauranteSoft.c3_dominio.entidades.ComprobanteDePago;
import java.util.List;

/**
 *
 * @author Marlon
 */
public interface IComprobanteDePagoDAO {
    
    public int ingresar(ComprobanteDePago comprobanteDePago) throws Exception;
    
    public List<ComprobanteDePago> reporteVentas()throws Exception;
}
