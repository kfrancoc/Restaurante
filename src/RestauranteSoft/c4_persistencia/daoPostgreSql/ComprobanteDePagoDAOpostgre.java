
package RestauranteSoft.c4_persistencia.daoPostgreSql;

import RestauranteSoft.c3_dominio.contrato.IComprobanteDePagoDAO;
import RestauranteSoft.c3_dominio.entidades.Cliente;
import RestauranteSoft.c3_dominio.entidades.ComprobanteDePago;
import RestauranteSoft.c3_dominio.entidades.Pedido;
import RestauranteSoft.c4_persistencia.GestorJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marlon
 */
public class ComprobanteDePagoDAOpostgre implements IComprobanteDePagoDAO{
    
    GestorJDBC gestorJDBC;

    public ComprobanteDePagoDAOpostgre(GestorJDBC gestorJDBC) {
        this.gestorJDBC=gestorJDBC;
    }
    

    @Override
    public int ingresar(ComprobanteDePago comprobanteDePago) throws Exception {
        int registros_afectados;
        String sentenciaSQL= "insert into comprobantedepago (fecha, igv, montoapagar,usuarioid, "
                + "clienteid, pedidoid ) values(?,?,?,?,?,?) ";
        try {
            PreparedStatement sentenciacomprobante= gestorJDBC.prepararSentencia(sentenciaSQL);
            sentenciacomprobante.setDate(1, comprobanteDePago.getFecha());
            sentenciacomprobante.setDouble(2, comprobanteDePago.getIgv());
            sentenciacomprobante.setDouble(3, comprobanteDePago.getMontoAPagar());
            sentenciacomprobante.setInt(4, comprobanteDePago.getUsuario().getUsuarioid());
            sentenciacomprobante.setInt(5, comprobanteDePago.getCliente().getClienteid());
            sentenciacomprobante.setInt(6, comprobanteDePago.getPedido().getPedidoid());
            registros_afectados=sentenciacomprobante.executeUpdate();
            if(registros_afectados==1){
                //se realizo el cambio de estado del pedido a pagado
                PedidoDAOPostgre pedidoDAO= new PedidoDAOPostgre(gestorJDBC);
                pedidoDAO.modificarEstado(comprobanteDePago.getPedido());
                //se tiene que cambiar el estado de la mesa a : disponible
                MesaDAOPostgre mesaDAO= new MesaDAOPostgre(gestorJDBC);
                mesaDAO.modificarEstado(comprobanteDePago.getPedido().getMesa());
                
            }
            return registros_afectados;
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar el Comprobante de pago.\n"
                    + "Intente de nuevo o consulte con el Administrador.");
            //JOptionPane.showMessageDialog(null, e.getMessage());
        }
        //return 0;
 
    }

    @Override
    public List<ComprobanteDePago> reporteVentas() throws Exception {
        ArrayList<ComprobanteDePago> ventas = new ArrayList();
        ComprobanteDePago venta;
        String sentenciaSQL_ventas;
        ResultSet resultado_ventas;
        
        sentenciaSQL_ventas="SELECT cp.comprobantedepagoid, cp.fecha, cp.igv, cp.montoapagar, " +
                "c.clienteid, c.dni, c.nombre, c.apellidos," +
                "p.pedidoid, p.monto " +
                " FROM comprobantedepago cp " +
                "inner join cliente c on cp.clienteid = c.clienteid " +
                "inner join pedido p on cp.pedidoid = p.pedidoid ";  
        
        resultado_ventas= gestorJDBC.ejecutarConsulta(sentenciaSQL_ventas);
        while(resultado_ventas.next()){
            venta= new ComprobanteDePago();
            venta.setComprobantedepagoid(resultado_ventas.getInt("comprobantedepagoid"));
            venta.setFecha(resultado_ventas.getDate("fecha"));
            venta.setIgv(resultado_ventas.getDouble("igv"));
            venta.setMontoAPagar(resultado_ventas.getDouble("montoapagar"));
            
            Cliente cliente = new Cliente();
            cliente.setClienteid(resultado_ventas.getInt("clienteid"));
            cliente.setDni(resultado_ventas.getString("dni"));
            cliente.setNombre(resultado_ventas.getString("nombre"));
            cliente.setApellidos(resultado_ventas.getString("apellidos"));
            venta.setCliente(cliente);
            Pedido pedido = new Pedido();
            pedido.setPedidoid(resultado_ventas.getInt("pedidoid"));
            pedido.setMonto(resultado_ventas.getDouble("monto"));
            venta.setPedido(pedido);
            
            
            ventas.add(venta);
        }
        resultado_ventas.close();
        return ventas;
    }
    
    
}
