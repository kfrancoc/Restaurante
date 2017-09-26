
package RestauranteSoft.c1_Presentacion;

import RestauranteSoft.c1_Presentacion.util.ConfiguradorDeTabla;
import RestauranteSoft.c2_aplicacion.RegistrarPedidoServicio;
import RestauranteSoft.c3_dominio.entidades.Pedido;
import RestauranteSoft.c3_dominio.servicio.ManejadorDePedidos;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Fila;
import mastersoft.tabladatos.Tabla;

/**
 *
 * @author Patricia
 */
public class FormPedidosPorCobrar extends java.awt.Dialog {
    
    
    Pedido pedido = new Pedido();
    /**
     * Creates new form FormGestionarComprobante
     */
    public FormPedidosPorCobrar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana =this.getSize();
        this.setLocation((pantalla.width - ventana.width)/2, (pantalla.height - ventana.height)/2);
        inicializarTabla();
        setIconImage(new ImageIcon(getClass().getResource("/iconos/Icono Restaurante.jpg")).getImage()); 
    }

    private void inicializarTabla(){
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("Numero", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("Estado", "java.lang.String"));
        tabla.agregarColumna(new Columna("Fecha", "java.lang.String"));
        tabla.agregarColumna(new Columna("Monto", "java.lang.Double"));                       
        tabla.agregarColumna(new Columna("Mesa", "java.lang.Integer"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaPedidosPorCobrar.setModel(modeloTabla);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaPedidosPorCobrar, 1, 150, 160, 50);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaPedidosPorCobrar, 2, 150, 100, 50);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaPedidosPorCobrar, 3, 150, 100, 50);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaPedidosPorCobrar, 4, 150, 100, 50);
        ConfiguradorDeTabla.ocultarColumna(tablaPedidosPorCobrar, 0);
        txtMesa.requestFocusInWindow();
    }
    
    private void buscar(){
        Fila filaTabla;
        try {
            RegistrarPedidoServicio registrarPedidoServicio = new RegistrarPedidoServicio();
            ManejadorDePedidos manejadorDePedidos = registrarPedidoServicio.ListaPedidos();
            ModeloTabla modeloTabla = (ModeloTabla)tablaPedidosPorCobrar.getModel();
            modeloTabla.eliminarTotalFilas();
            for(Pedido pedido : manejadorDePedidos.getPedidos()){
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(pedido.getPedidoid());                
                filaTabla.agregarValorCelda(pedido.getEstado());
                filaTabla.agregarValorCelda(pedido.getFecha());
                filaTabla.agregarValorCelda(pedido.getMonto());                
                filaTabla.agregarValorCelda(pedido.getMesa().getNumero());
                modeloTabla.agregarFila(filaTabla);
            }
            modeloTabla.refrescarDatos();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error"+e.getMessage()+JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        txtMesa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnBuscarMesa = new javax.swing.JButton();
        btnGenerarComprobanteDePago = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPedidosPorCobrar = new javax.swing.JTable();

        setBackground(new java.awt.Color(240, 240, 240));
        setSize(new java.awt.Dimension(1000, 1000));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("PEDIDOS POR COBRAR");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 11, -1, -1));

        txtMesa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(txtMesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 128, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Mesa");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Lista de pedidos por cobrar");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        btnBuscarMesa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBuscarMesa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Buscar.png"))); // NOI18N
        btnBuscarMesa.setText("Buscar");
        btnBuscarMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarMesaActionPerformed(evt);
            }
        });
        add(btnBuscarMesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        btnGenerarComprobanteDePago.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnGenerarComprobanteDePago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Generar.png"))); // NOI18N
        btnGenerarComprobanteDePago.setText("Generar Comprobante");
        btnGenerarComprobanteDePago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarComprobanteDePagoActionPerformed(evt);
            }
        });
        add(btnGenerarComprobanteDePago, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 179, -1));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 410, -1, -1));

        tablaPedidosPorCobrar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaPedidosPorCobrar);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, 220));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_formWindowOpened

    private void btnGenerarComprobanteDePagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarComprobanteDePagoActionPerformed
        // TODO add your handling code here:
        int pedidoid=obtenerId();
        if(pedidoid == 0)
            return;
        RegistrarPedidoServicio registrarPedidoServicio= new RegistrarPedidoServicio();
        try {
            Pedido pedido= registrarPedidoServicio.buscarPedido(pedidoid);
            if(pedido != null){
                FormGestionarComprobante formGenerarComprobante= new FormGestionarComprobante(null, true, pedido);
                formGenerarComprobante.setVisible(true);
                buscar();
            }else{
                JOptionPane.showMessageDialog(this, "Error", "La fila no existe", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error"+e.getMessage()+JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerarComprobanteDePagoActionPerformed

    private void btnBuscarMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarMesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarMesaActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private int obtenerId() {
        int numFila = tablaPedidosPorCobrar.getSelectedRow();
        if(numFila == -1){
            JOptionPane.showMessageDialog(this, "Error", "La fila no fue seleccionada", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        ModeloTabla modeloTabla =(ModeloTabla)tablaPedidosPorCobrar.getModel();
        Fila fila = modeloTabla.obtenerFila(numFila);
        return (Integer)fila.obtenerCelda(0).getValor(); // se retorna el id de la fila seleccionada
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarMesa;
    private javax.swing.JButton btnGenerarComprobanteDePago;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaPedidosPorCobrar;
    private javax.swing.JTextField txtMesa;
    // End of variables declaration//GEN-END:variables
}
