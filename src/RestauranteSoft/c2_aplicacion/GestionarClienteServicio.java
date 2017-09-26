package RestauranteSoft.c2_aplicacion;

import RestauranteSoft.c3_dominio.contrato.FabricaAbstractaDAO;
import RestauranteSoft.c3_dominio.entidades.Cliente;
import RestauranteSoft.c3_dominio.contrato.IClienteDAO;
import RestauranteSoft.c3_dominio.servicio.ManejadorDeClientes;
import RestauranteSoft.c4_persistencia.GestorJDBC;
import RestauranteSoft.c4_persistencia.daoPostgreSql.ClienteDAOpostgre;
import java.util.List;
/**
 *
 * @author Vanessa
 */
public class GestionarClienteServicio {
        private GestorJDBC gestorJDBC;
        private IClienteDAO clienteDAO;

    public GestionarClienteServicio() {
        FabricaAbstractaDAO fabricaAbstractaDAO= FabricaAbstractaDAO.getInstancia();
        gestorJDBC= fabricaAbstractaDAO.crearGestorJDBC();
        clienteDAO= fabricaAbstractaDAO.crearClienteDAO(gestorJDBC);
    }

        public int guardarCliente(Cliente cliente) throws Exception{
            gestorJDBC.abrirConexion();
            int registros_afectados = clienteDAO.ingresar(cliente);
            gestorJDBC.cerrarConexion();
            return registros_afectados;
        }
        
        public int modificarCliente(Cliente cliente)throws Exception{
            gestorJDBC.abrirConexion();
            int registros_afectados= clienteDAO.modificar(cliente);
            gestorJDBC.cerrarConexion();
            return registros_afectados;
        }
        
        public ManejadorDeClientes buscarClienteNombre(String nombre)throws Exception{
            gestorJDBC.abrirConexion();
            ClienteDAOpostgre clienteDAO = new ClienteDAOpostgre(gestorJDBC);
            List<Cliente> clientes = clienteDAO.buscar(nombre);
            ManejadorDeClientes manejadorDeClientes = new ManejadorDeClientes(clientes);
            gestorJDBC.cerrarConexion();
            return manejadorDeClientes;
        }
        public ManejadorDeClientes buscarClientes()throws Exception{
            gestorJDBC.abrirConexion();
            ClienteDAOpostgre clienteDAO = new ClienteDAOpostgre(gestorJDBC);
            List<Cliente> clientes = clienteDAO.buscarClientes();
            ManejadorDeClientes manejadorDeClientes = new ManejadorDeClientes(clientes);
            gestorJDBC.cerrarConexion();
            return manejadorDeClientes;
        }

        public Cliente buscarClientesID(int id)throws Exception{
            gestorJDBC.abrirConexion();
            Cliente platillos= clienteDAO.buscar(id);
            gestorJDBC.cerrarConexion();
            return platillos;
        }
        
        public int eliminarCliente(Cliente cliente)throws Exception{
        gestorJDBC.abrirConexion();
        int registros_afectados= clienteDAO.eliminar(cliente);
        gestorJDBC.cerrarConexion();
        return registros_afectados;
    }
}
