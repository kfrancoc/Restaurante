/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c5_infraestructura.parametros;

//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Marlon
 */
public class LectorDeParametros {
    
    public String getValorParametro(String parametro){
        String valorParametro;
        InputStream canalDeEntrada;
        Properties propiedades = new Properties();
        try {
            canalDeEntrada = Thread.currentThread().getContextClassLoader().getResourceAsStream("RestauranteSoft/c5_Infraestructura/parametros/Parametros.properties");
            propiedades.load(canalDeEntrada);            
            valorParametro = propiedades.getProperty(parametro);
            return valorParametro;
        } catch (IOException e) {
            return null;
        }
    }
    
}
