
package RestauranteSoft.c3_dominio.contrato;

import RestauranteSoft.c5_infraestructura.parametros.LectorDeParametros;
import RestauranteSoft.c4_persistencia.GestorJDBC;

/**
 *
 * @author Marlon
 */
public abstract class FabricaAbstractaDAO {
    public static FabricaAbstractaDAO getInstancia(){
        String nombreClaseFabricaDAO;
        FabricaAbstractaDAO FabricaDAO;        
        try {
            LectorDeParametros lectorDeParametros = new LectorDeParametros();
            nombreClaseFabricaDAO = lectorDeParametros.getValorParametro("claseFabricaDAO");
            FabricaDAO = (FabricaAbstractaDAO)Class.forName(nombreClaseFabricaDAO).newInstance();
            return FabricaDAO;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
    public abstract  GestorJDBC crearGestorJDBC();
    
    public abstract IPlatilloDAO crearPlatilloDAO(GestorJDBC gestorJDBC);
    
    public abstract IPedidoDAO crearPedidoDAO (GestorJDBC gestorJDBC);
    
    public abstract IClienteDAO crearClienteDAO (GestorJDBC gestorJDBC);
    
    public abstract IUsuarioDAO crearUsuarioDAO (GestorJDBC gestorJDBC);
    
    public abstract IMesaDAO crearMesaDAO (GestorJDBC gestorJDBC);
    
    public abstract IComprobanteDePagoDAO crearComprobanteDePago (GestorJDBC gestorJDBC);
    
}
