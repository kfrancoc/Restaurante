/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c4_persistencia.daoPostgreSql;

import RestauranteSoft.c3_dominio.contrato.IUsuarioDAO;
import RestauranteSoft.c3_dominio.entidades.Usuario;
import RestauranteSoft.c4_persistencia.GestorJDBC;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vanessa
 */
public class UsuarioDAOpostgre implements IUsuarioDAO{
    
    GestorJDBC gestorJDBC;

    public UsuarioDAOpostgre(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }
    
    @Override
    public Usuario buscarPorDNI(String dni) throws SQLException {
        Usuario usuario=null;
        ResultSet resultadoUsuario;
        String sentenciaSQL="select usuarioid,dni,nombre from usuario where dni= '" + dni + "'";
        resultadoUsuario = gestorJDBC.ejecutarConsulta(sentenciaSQL);
            if(resultadoUsuario.next()){
                usuario= new Usuario();
                usuario.setUsuarioid(resultadoUsuario.getInt("usuarioid"));
                usuario.setDni(resultadoUsuario.getString("dni"));
                usuario.setNombre(resultadoUsuario.getString("nombre"));
            }
            resultadoUsuario.close();
            return usuario;
    }

    @Override
    public List<Usuario> listaUsuarios() throws SQLException {
        ArrayList<Usuario> usuarios = new ArrayList();
        Usuario usuario;
        ResultSet resultado;
        String sentenciaSQL;
        sentenciaSQL = "SELECT usuarioid, dni, nombre, direccion, tipo FROM usuario";
        resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
        while(resultado.next()){            
            usuario = new Usuario();
            usuario.setUsuarioid(resultado.getInt("usuarioid"));
            usuario.setDni(resultado.getString("dni"));
            usuario.setNombre(resultado.getString("nombre"));
            usuario.setDireccion(resultado.getString("direccion"));
            usuarios.add(usuario);
        }
        resultado.close();
        return usuarios;
    }

    @Override
    public boolean accederAlSistema(String nombre, String password, String tipo) throws SQLException {
         boolean flag = false;
         ResultSet resultado;
            String sentenciaSQL; 
            sentenciaSQL = "select usuarioid,dni,nombre from usuario u where  u.nombre = '"+nombre+"' and u.password = '"+password+"' and u.tipo = '"+tipo+"'"; 
            resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL); 
            while(resultado.next()){            
              flag = true;
            }
            resultado.close(); 
         return flag;
    }


    
}
