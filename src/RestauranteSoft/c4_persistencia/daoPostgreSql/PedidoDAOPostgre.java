/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c4_persistencia.daoPostgreSql;

import RestauranteSoft.c3_dominio.contrato.IPedidoDAO;
import RestauranteSoft.c3_dominio.entidades.Pedido;
import RestauranteSoft.c3_dominio.entidades.LineaDePedido;
import RestauranteSoft.c3_dominio.entidades.Mesa;
import RestauranteSoft.c3_dominio.entidades.Platillo;
import RestauranteSoft.c3_dominio.entidades.Usuario;
import RestauranteSoft.c4_persistencia.GestorJDBC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Patricia
 */
public class PedidoDAOPostgre implements IPedidoDAO{
    
    GestorJDBC gestorJDBC;

    public PedidoDAOPostgre(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;
    }
    
    @Override
    public int ingresar(Pedido pedido) throws Exception {
        int registros_afectados_pedido, registros_afectados_lineadepedido;
        String setenciaSQLpedido= "insert into pedido (fecha, estado, monto, mesaid, usuarioid) values(?,?,?,?,?)";
        String sentenciaSQLlineaDePedido="INSERT INTO lineadepedido(cantidad, condicion, precio, platilloid, pedidoid) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement sentenciapedido= gestorJDBC.prepararSentencia(setenciaSQLpedido);
            sentenciapedido.setDate(1, pedido.getFecha());
            sentenciapedido.setString(2, pedido.getEstado());
            sentenciapedido.setDouble(3, pedido.getMonto());
            sentenciapedido.setInt(4, pedido.getMesa().getMesaid());
            sentenciapedido.setInt(5, pedido.getUsuario().getUsuarioid());
            registros_afectados_pedido = sentenciapedido.executeUpdate();
            if(registros_afectados_pedido==1){
                //hacer las lineas para agregar las lineas de pedido
                //lo que hace la sigueinte linea es : recorrer cada linea de pedido agregado, para registrar
                // en su tabla guardando la cantidad, ... y el id del pedido
                int ultimo=ultimoRegistro();
                //realizamos una sola consulta para ver el ultimo registro afectado
                for(LineaDePedido lineaDePedido: pedido.getLineasDePedido()){
                    registros_afectados_lineadepedido=0;
                    PreparedStatement sentencialineadepedido= gestorJDBC.prepararSentencia(sentenciaSQLlineaDePedido);
                    sentencialineadepedido.setInt(1, lineaDePedido.getCantidad());                    
                    sentencialineadepedido.setString(2, lineaDePedido.getCondicion());
                    sentencialineadepedido.setDouble(3, lineaDePedido.getPrecio());
                    sentencialineadepedido.setInt(4, lineaDePedido.getPlatillo().getPlatilloid());                    
                    sentencialineadepedido.setInt(5, ultimo);
                    registros_afectados_lineadepedido = sentencialineadepedido.executeUpdate();
                    //---------------  Actualizacion de stock ------------------------------
                    if(registros_afectados_lineadepedido==1){
                        //actualizar stock
                        PlatilloDAOPostgre platilloDAO= new PlatilloDAOPostgre(gestorJDBC);
                        platilloDAO.actualizarStock(lineaDePedido.getPlatillo());
                    }
                    //----------------------------------------------------------------------
                }
                //Cambiar el estado de la mesa a no disponible, ya que se ha hecho n pedido
                MesaDAOPostgre mesaDAO= new MesaDAOPostgre(gestorJDBC);
                mesaDAO.modificarEstado(pedido.getMesa());
                //---------------------------------------------------------------------------
            }         
            return registros_afectados_pedido;
        } catch (Exception e) {
            throw new SQLException("No se pudo registrar el Pedido.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
        
    }

    @Override
    public int modificar(Pedido pedido) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pedido buscar(int pedidoid) throws Exception {
        Pedido pedido = null;
        String sentenciaSQL_pedido, sentenciaSQL_lineasdepedido;
        ResultSet resultado_pedido, resultado_lineasdepedido;

        sentenciaSQL_pedido="SELECT pe.pedidoid, pe.estado, pe.fecha, pe.monto, "
                + "m.mesaid, m.estado, m.numero, "
                + "u.usuarioid, u.dni, u.nombre, u.direccion, u.tipo "
                + " FROM pedido pe "
                + "inner join mesa m on pe.mesaid = m.mesaid "
                + "inner join usuario u on pe.usuarioid = u.usuarioid "
                + " where pe.pedidoid = '" + pedidoid + "'";
        
        sentenciaSQL_lineasdepedido="SELECT ldp.cantidad, ldp.condicion, ldp.precio, "
                + "p.platilloid, p.descripcion, p.estado, p.precio, p.stock, p.tipo "
                + " FROM pedido pe "
                + "inner join lineadepedido ldp on pe.pedidoid = ldp.pedidoid "
                + "inner join platillo p on ldp.platilloid = p.platilloid "
                + " where pe.pedidoid = ";
        
        resultado_pedido= gestorJDBC.ejecutarConsulta(sentenciaSQL_pedido);
        try{
            if(resultado_pedido.next()){            
                pedido= new Pedido();
                pedido.setPedidoid(resultado_pedido.getInt("pedidoid"));
                pedido.setEstado(resultado_pedido.getString("estado"));
                pedido.setFecha(resultado_pedido.getDate("fecha"));
                pedido.setMonto(resultado_pedido.getDouble("monto"));
                Mesa mesa = new Mesa();
                mesa.setMesaid(resultado_pedido.getInt("mesaid"));
                mesa.setEstado(resultado_pedido.getString("estado"));
                mesa.setNumero(resultado_pedido.getInt("numero"));
                Usuario usuario = new Usuario();
                usuario.setUsuarioid(resultado_pedido.getInt("usuarioid"));
                usuario.setDni(resultado_pedido.getString("dni"));
                usuario.setNombre(resultado_pedido.getString("nombre"));
                usuario.setDireccion(resultado_pedido.getString("direccion"));
                usuario.setTipo(resultado_pedido.getString("tipo"));        
                pedido.setMesa(mesa);
                pedido.setUsuario(usuario);
                sentenciaSQL_lineasdepedido =  sentenciaSQL_lineasdepedido + pedido.getPedidoid(); //hasta qui el pedido ya debe tener un id
                resultado_lineasdepedido = gestorJDBC.ejecutarConsulta(sentenciaSQL_lineasdepedido);
                while (resultado_lineasdepedido.next()) {                
                    Platillo platillo= new Platillo();
                    platillo.setPlatilloid(resultado_lineasdepedido.getInt("platilloid"));
                    platillo.setDescripcion(resultado_lineasdepedido.getString("descripcion"));
                    platillo.setEstado(resultado_lineasdepedido.getString("estado"));
                    platillo.setPrecio(resultado_lineasdepedido.getDouble("precio"));
                    platillo.setStock(resultado_lineasdepedido.getInt("stock"));
                    platillo.setTipo(resultado_lineasdepedido.getString("tipo"));
                    pedido.agregarLineaDePedido(resultado_lineasdepedido.getInt("cantidad"), resultado_lineasdepedido.getString("condicion"), platillo);
                }
            }
            resultado_pedido.close();
            return pedido;
        }catch(Exception e){
            throw new SQLException("No se pudo encontrar el Pedido.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
    }

    @Override
    public int ultimoRegistro() throws Exception {        
        int ultimo=0;
        ResultSet resultado;
        String sentenciaSQL;
        sentenciaSQL="select max(pedidoid) as ultimo from pedido";
        resultado = gestorJDBC.ejecutarConsulta(sentenciaSQL);
        if(resultado.next()){
         ultimo= resultado.getInt(1);   
        }               
        resultado.close();
        return ultimo;
        
    }

    @Override
    public List<Pedido> ListaPedidos() throws SQLException {
        ArrayList<Pedido> pedidos = new ArrayList();
        Pedido pedido;
        String sentenciaSQL_pedido, sentenciaSQL_lineasdepedido;
        ResultSet resultado_pedido, resultado_lineasdepedido;
        
        sentenciaSQL_pedido="SELECT pe.pedidoid, pe.estado, pe.fecha, pe.monto, "
                + "m.mesaid, m.estado, m.numero, "
                + "u.usuarioid, u.dni, u.nombre, u.direccion, u.tipo "
                + " FROM pedido pe "
                + "inner join mesa m on pe.mesaid = m.mesaid "
                + "inner join usuario u on pe.usuarioid = u.usuarioid";
        
        sentenciaSQL_lineasdepedido="SELECT ldp.cantidad, ldp.condicion, ldp.precio, "
                + "p.platilloid, p.descripcion, p.estado, p.precio, p.stock, p.tipo "
                + " FROM pedido pe "
                + "inner join lineadepedido ldp on pe.pedidoid = ldp.pedidoid "
                + "inner join platillo p on ldp.platilloid = p.platilloid "
                + " where pe.pedidoid = ";
        
        resultado_pedido= gestorJDBC.ejecutarConsulta(sentenciaSQL_pedido);
        while(resultado_pedido.next()){
            pedido= new Pedido();
            pedido.setPedidoid(resultado_pedido.getInt("pedidoid"));
            pedido.setEstado(resultado_pedido.getString("estado"));
            pedido.setFecha(resultado_pedido.getDate("fecha"));
            pedido.setMonto(resultado_pedido.getDouble("monto"));
            Mesa mesa = new Mesa();
            mesa.setMesaid(resultado_pedido.getInt("mesaid"));
            mesa.setEstado(resultado_pedido.getString("estado"));
            mesa.setNumero(resultado_pedido.getInt("numero"));
            Usuario usuario = new Usuario();
            usuario.setUsuarioid(resultado_pedido.getInt("usuarioid"));
            usuario.setDni(resultado_pedido.getString("dni"));
            usuario.setNombre(resultado_pedido.getString("nombre"));
            usuario.setDireccion(resultado_pedido.getString("direccion"));
            usuario.setTipo(resultado_pedido.getString("tipo"));        
            pedido.setMesa(mesa);
            pedido.setUsuario(usuario);
            
            sentenciaSQL_lineasdepedido =  sentenciaSQL_lineasdepedido + pedido.getPedidoid(); //hasta qui el pedido ya debe tener un id
            resultado_lineasdepedido = gestorJDBC.ejecutarConsulta(sentenciaSQL_lineasdepedido);
            while (resultado_lineasdepedido.next()) {
                Platillo platillo= new Platillo();
                platillo.setPlatilloid(resultado_lineasdepedido.getInt("platilloid"));
                platillo.setDescripcion(resultado_lineasdepedido.getString("descripcion"));
                platillo.setEstado(resultado_lineasdepedido.getString("estado"));
                platillo.setPrecio(resultado_lineasdepedido.getDouble("precio"));
                platillo.setStock(resultado_lineasdepedido.getInt("stock"));
                platillo.setTipo(resultado_lineasdepedido.getString("tipo"));
                pedido.agregarLineaDePedido(resultado_lineasdepedido.getInt("cantidad"), resultado_lineasdepedido.getString("condicion"), platillo);
            }            
            pedidos.add(pedido);
        }
        resultado_pedido.close();
        return pedidos;  
    }

    @Override
    public int modificarEstado(Pedido pedido) throws Exception {
        String sentenciaSQL = "UPDATE pedido SET estado=? WHERE pedidoid = ?";
        try{
            PreparedStatement sentencia= gestorJDBC.prepararSentencia(sentenciaSQL);
            sentencia.setString(1, pedido.getEstado());
            sentencia.setInt(2, pedido.getPedidoid());
            return sentencia.executeUpdate();
        }catch(Exception e){
            throw new SQLException("No se pudo modificar el Pedido.\n"
                    + "Intente de nuevo o consulte con el Administrador." +e.getMessage());
        }
    }    
}
