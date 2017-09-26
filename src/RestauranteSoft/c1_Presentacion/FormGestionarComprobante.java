/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c1_Presentacion;

import RestauranteSoft.c1_Presentacion.util.ConfiguradorDeTabla;
import RestauranteSoft.c2_aplicacion.GenerarComprobanteDePagoServicio;
import RestauranteSoft.c2_aplicacion.RegistrarPedidoServicio;
import RestauranteSoft.c3_dominio.entidades.Cliente;
import RestauranteSoft.c3_dominio.entidades.ComprobanteDePago;
import RestauranteSoft.c3_dominio.entidades.LineaDePedido;
import RestauranteSoft.c3_dominio.entidades.Pedido;
import RestauranteSoft.c3_dominio.entidades.Platillo;
import RestauranteSoft.c3_dominio.entidades.Usuario;
import RestauranteSoft.c3_dominio.contrato.*;
import RestauranteSoft.c4_persistencia.GestorJDBC;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Fila;
import mastersoft.tabladatos.Tabla;

/**
 *
 * @author Marlon
 */
public final class FormGestionarComprobante extends java.awt.Dialog {

    /**
     * Creates new form FormDatosComprobante
     */
    Cliente cliente=null;
    Pedido pedidoACobrar=null;
    Usuario usuario=null;
    ComprobanteDePago comprobanteDePago;
    IClienteDAO iClienteDAO;
    GestorJDBC gestorJDBC;

    
    
    
    public FormGestionarComprobante(java.awt.Frame parent, boolean modal, Pedido pedido) {
        super(parent, modal);
        initComponents();
        inicializarTabla();
        platillosDePedidoACobrar(pedido);
        iniciarComboAdministrador();
        comprobanteDePago=new ComprobanteDePago();
        setIconImage(new ImageIcon(getClass().getResource("/iconos/Icono Restaurante.jpg")).getImage());
    }
    
    private void inicializarTabla(){
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("ID", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("Descripcion", "java.lang.String"));
        tabla.agregarColumna(new Columna("Condicion", "java.lang.String"));
        tabla.agregarColumna(new Columna("Precio", "java.lang.Double"));                       
        tabla.agregarColumna(new Columna("Cantidad", "java.lang.Integer"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaPlatillos.setModel(modeloTabla);        
        ConfiguradorDeTabla.configurarAnchoColumna(tablaPlatillos, 1, 150, 400, 50);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaPlatillos, 2, 350, 160, 50);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaPlatillos, 3, 150, 100, 50);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaPlatillos, 4, 150, 100, 50);
        ConfiguradorDeTabla.ocultarColumna(tablaPlatillos, 0);
        txtNombres.requestFocusInWindow();
    }
    
    public void platillosDePedidoACobrar(Pedido pedido){
        try {
            this.pedidoACobrar=pedido;
            List<LineaDePedido> lineasdepedido= pedidoACobrar.getLineasDePedido();
            ModeloTabla modeloTabla = (ModeloTabla)tablaPlatillos.getModel();
            for(LineaDePedido lineaDePedido : lineasdepedido){
                Fila filaTabla = new Fila();
                filaTabla.agregarValorCelda(lineaDePedido.getPlatillo().getPlatilloid());                
                filaTabla.agregarValorCelda(lineaDePedido.getPlatillo().getDescripcion());
                filaTabla.agregarValorCelda(lineaDePedido.getCondicion());
                filaTabla.agregarValorCelda(lineaDePedido.getPrecio());                
                filaTabla.agregarValorCelda(lineaDePedido.getCantidad());
                modeloTabla.agregarFila(filaTabla);
            }
            modeloTabla.refrescarDatos();
            txtIGV.setText(String.valueOf(pedidoACobrar.calcularIGV()));
            txtTotal.setText(String.valueOf(pedidoACobrar.calcularMontoApagar()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error: "+ e.getMessage());
        }
    }
    
    private void iniciarComboAdministrador(){
        try {
            GenerarComprobanteDePagoServicio generarComprobanteDePagoServicio= new GenerarComprobanteDePagoServicio();
            List<Usuario> listaUsuarios= generarComprobanteDePagoServicio.listaUsuarios();
            for(Usuario usuario : listaUsuarios){
                cboAdministrador.addItem(usuario.getDni());
            }
        } catch (Exception e) {
        }
    }

    private void activarBotonImprimir(){
        ModeloTabla modeloTabla = (ModeloTabla) tablaPlatillos.getModel();
        if(usuario!=null && cliente!=null && modeloTabla.getRowCount()>0){
            btnImprimirComprobante.setEnabled(true);
        }else{
            btnImprimirComprobante.setEnabled(false);
            //JOptionPane.showMessageDialog(null, "Se debe seleccionar o llenar todos los campos");
        }
    }
    
    public void inicializarComprobanteDePago(){
        txtDni.setText("");
        txtNombres.setText("");
        txtIGV.setText("");
        txtTotal.setText("");
        cboAdministrador.setSelectedIndex(0);
        ModeloTabla modeloTabla= (ModeloTabla) tablaPlatillos.getModel();
        modeloTabla.eliminarTotalFilas();
        this.dispose();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        btnBuscarCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPlatillos = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnImprimirComprobante = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        cboAdministrador = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        txtIGV = new javax.swing.JTextField();

        setBackground(new java.awt.Color(240, 240, 240));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("GENERAR COMPROBANTE");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 11, -1, -1));

        txtNombres.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNombres.setEnabled(false);
        add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 107, 134, -1));

        btnBuscarCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Buscar.png"))); // NOI18N
        btnBuscarCliente.setText("Buscar Cliente");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });
        add(btnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 180, 56));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("DNI");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 88, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Nombres");
        jLabel2.setToolTipText("");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 112, -1, -1));

        txtDni.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 80, 134, -1));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 226, 520, 10));

        tablaPlatillos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablaPlatillos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaPlatillos);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 272, 509, 136));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Lista de Platillos");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 249, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Monto Total");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 452, -1, -1));

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 450, 88, -1));

        btnImprimirComprobante.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnImprimirComprobante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Imprimir.png"))); // NOI18N
        btnImprimirComprobante.setText("Imprimir Comprobante");
        btnImprimirComprobante.setEnabled(false);
        btnImprimirComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirComprobanteActionPerformed(evt);
            }
        });
        add(btnImprimirComprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, 60));

        btnCerrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Salir.png"))); // NOI18N
        btnCerrar.setText("Salir");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 490, 130, 60));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Administrador");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 171, -1, -1));

        cboAdministrador.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccionar>" }));
        cboAdministrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAdministradorActionPerformed(evt);
            }
        });
        add(cboAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 162, 313, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("IGV 18%");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 428, -1, -1));

        txtIGV.setEditable(false);
        txtIGV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        add(txtIGV, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 426, 88, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        // TODO add your handling code here:
        txtNombres.setText("");
        try{
            GenerarComprobanteDePagoServicio generarComprobanteDePagoServicio= new GenerarComprobanteDePagoServicio(); 
            cliente = generarComprobanteDePagoServicio.buscarClientePorDNI(txtDni.getText().trim());
            if(cliente != null){
                txtNombres.setText(cliente.getNombre());
                activarBotonImprimir();
            }
            else{
                JOptionPane.showMessageDialog(this, "El cliente no se encuentra registrado");
                cliente=null;
                activarBotonImprimir();
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error con la base de datos "+ e.getMessage());
        }
        
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnImprimirComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirComprobanteActionPerformed
        // TODO add your handling code here:
        //objetos y variables que necesito: pedido, cliente, usuario, fecha, montoapagar
        int registros_afectados;
        comprobanteDePago.setIgv(pedidoACobrar.calcularIGV());
        comprobanteDePago.setMontoAPagar(pedidoACobrar.calcularMontoApagar());
        comprobanteDePago.setUsuario(usuario);
        comprobanteDePago.setCliente(cliente);
        comprobanteDePago.setPedido(pedidoACobrar);    
        try {
            GenerarComprobanteDePagoServicio generarComprobanteDePagoServicio= new GenerarComprobanteDePagoServicio();
            registros_afectados= generarComprobanteDePagoServicio.crearComprobanteDePago(comprobanteDePago);
            if(registros_afectados==1){
                JOptionPane.showMessageDialog(null, "El comprobante de pago se generó correctamente");
                inicializarComprobanteDePago();
            }else{
                JOptionPane.showMessageDialog(null, "Verifique los datos ingresados por favor");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error"+e.getMessage()+JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnImprimirComprobanteActionPerformed

    private void cboAdministradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAdministradorActionPerformed
        //CODIGO PARA PODER LLENAR UN OBJETO MESERO
        String dni;
        if(cboAdministrador.getSelectedIndex()!=0){
            dni = cboAdministrador.getSelectedItem().toString();
            //buscaremos el mesero con este nombre, pero con el login se tomaria este como usuario que registra
            try {
                GenerarComprobanteDePagoServicio generarComprobanteDePagoServicio= new GenerarComprobanteDePagoServicio();
                usuario= generarComprobanteDePagoServicio.buscarUsuarioPorDNI(dni);
                activarBotonImprimir();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error"+e.getMessage()+JOptionPane.ERROR_MESSAGE);
            }
        }else{
            //JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un dni de mesero");
            usuario=null;
            activarBotonImprimir();
        }
    }//GEN-LAST:event_cboAdministradorActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnImprimirComprobante;
    private javax.swing.JComboBox cboAdministrador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tablaPlatillos;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtIGV;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
