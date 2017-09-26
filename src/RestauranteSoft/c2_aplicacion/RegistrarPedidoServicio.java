
package RestauranteSoft.c2_aplicacion;

import RestauranteSoft.c3_dominio.contrato.FabricaAbstractaDAO;
import RestauranteSoft.c3_dominio.contrato.IMesaDAO;
import RestauranteSoft.c3_dominio.contrato.IPedidoDAO;
import RestauranteSoft.c3_dominio.contrato.IPlatilloDAO;
import RestauranteSoft.c3_dominio.contrato.IUsuarioDAO;
import RestauranteSoft.c3_dominio.entidades.LineaDePedido;
import RestauranteSoft.c3_dominio.entidades.Mesa;
import RestauranteSoft.c3_dominio.entidades.Pedido;
import RestauranteSoft.c3_dominio.entidades.Platillo;
import RestauranteSoft.c3_dominio.entidades.Usuario;
import RestauranteSoft.c3_dominio.servicio.ManejadorDePedidos;
import RestauranteSoft.c4_persistencia.GestorJDBC;
import RestauranteSoft.c4_persistencia.daoPostgreSql.GestorJDBCPostgre;
import RestauranteSoft.c4_persistencia.daoPostgreSql.PedidoDAOPostgre;
import RestauranteSoft.c4_persistencia.daoPostgreSql.PlatilloDAOPostgre;
import java.util.List;

/**
 *
 * @author Patricia
 */
public class RegistrarPedidoServicio {
    private GestorJDBC gestorJDBC;
    private IPedidoDAO pedidoDAO;
    private IMesaDAO mesaDAO;
    private IUsuarioDAO usuarioDAO;
    private IPlatilloDAO platilloDAO;

    public RegistrarPedidoServicio() {
        FabricaAbstractaDAO fabricaAbstractaDAO = FabricaAbstractaDAO.getInstancia();
        gestorJDBC = fabricaAbstractaDAO.crearGestorJDBC();
        pedidoDAO = fabricaAbstractaDAO.crearPedidoDAO(gestorJDBC);
        mesaDAO= fabricaAbstractaDAO.crearMesaDAO(gestorJDBC);
        usuarioDAO= fabricaAbstractaDAO.crearUsuarioDAO(gestorJDBC);
        platilloDAO= fabricaAbstractaDAO.crearPlatilloDAO(gestorJDBC);
    }
    
    //------------------Recopilacion de objetos que se necesitan para el pedido----
    public Mesa buscarMesa(int numero) throws Exception{
        gestorJDBC.abrirConexion();
        Mesa mesa= mesaDAO.buscar(numero);
        gestorJDBC.cerrarConexion();
        return mesa;
    }
    //--------------------------------------------------------------------------
    //Metodo para inicar el llenado de datos al combobox
     public List<Mesa> listaMesas()throws Exception{
         try {
             gestorJDBC.abrirConexion();
             List<Mesa>listaMesas= mesaDAO.listaMesas();             
             gestorJDBC.cerrarConexion();
             return listaMesas;
         } catch (Exception e) {
             gestorJDBC.cerrarConexion();
             throw e;
         }
     }
     //---------------------------------------------------------------------------
    //buscamos usuario - que realizara l pedido
     public Usuario buscarUsuarioPorDNI(String dni)throws Exception{
        gestorJDBC.abrirConexion();
        Usuario usuario= usuarioDAO.buscarPorDNI(dni);
        gestorJDBC.cerrarConexion();
        return usuario;
    }
    public List<Usuario> listaUsuarios()throws Exception{
        gestorJDBC.abrirConexion();
        List<Usuario>listaUsuarios= usuarioDAO.listaUsuarios();             
        gestorJDBC.cerrarConexion();
        return listaUsuarios;
    }
    //----------------------------------------------------------------------------
              
    //creamos el pedido
    public int crearPedido(Pedido pedido)throws Exception{
        //reglas de negocio con el platillo -  lineas de platillo
        gestorJDBC.abrirConexion();
        List<LineaDePedido> lineasDePedido = pedido.getLineasDePedido();
        
        try {
            gestorJDBC.iniciarTransaccion();
            pedido.getMesa().setEstado("NO DISPONIBLE");
            //actualizar stock de la lista de platillos de un pedido
            //--------------------------------------------------------------------
            for(LineaDePedido lineaDePedido1: lineasDePedido){
                Platillo platillo= lineaDePedido1.getPlatillo();
                platillo.restarStock(lineaDePedido1.getCantidad()); //obtenemos la cantidad vendida de un platillo
            }
            //--------------------------------------------------------------------
            int registros_afectados= pedidoDAO.ingresar(pedido);
            gestorJDBC.terminarTransaccion();
            return registros_afectados;
        } catch (Exception e) {
            gestorJDBC.cancelarTransaccion();
            throw e;
        }
    }
    
       
    public Pedido buscarPedido(int pedidoid)throws Exception{
        gestorJDBC.abrirConexion();
        Pedido pedido= pedidoDAO.buscar(pedidoid);
        gestorJDBC.cerrarConexion();
        return pedido;
    }
    
    public ManejadorDePedidos ListaPedidos()throws Exception{
        gestorJDBC.abrirConexion();
        PedidoDAOPostgre pedidoDAOPostgre = new PedidoDAOPostgre(gestorJDBC);
        List<Pedido> lineaDePedidos= pedidoDAOPostgre.ListaPedidos();
        ManejadorDePedidos manejadorDePedidos = new ManejadorDePedidos(lineaDePedidos);
        gestorJDBC.cerrarConexion();
        return manejadorDePedidos;
    }
    
}
