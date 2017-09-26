
package RestauranteSoft.c3_dominio.contrato;

import RestauranteSoft.c3_dominio.entidades.Usuario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Vanessa
 */
public interface IUsuarioDAO {
    public Usuario buscarPorDNI(String dni)throws SQLException;
    
    public List<Usuario> listaUsuarios()throws SQLException;
    
    public boolean accederAlSistema(String nombre, String password, String tipo)throws SQLException;
    
}
