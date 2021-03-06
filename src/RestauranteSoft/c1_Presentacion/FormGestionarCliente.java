/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c1_Presentacion;

import RestauranteSoft.c1_Presentacion.util.ConfiguradorDeTabla;
import RestauranteSoft.c2_aplicacion.GestionarClienteServicio;
import RestauranteSoft.c3_dominio.entidades.Cliente;
import RestauranteSoft.c3_dominio.servicio.ManejadorDeClientes;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Fila;
import mastersoft.tabladatos.Tabla;

/**
 *
 * @author Vanessa
 */ 
public class FormGestionarCliente extends javax.swing.JDialog {

    /**
     * Creates new form FormGestionarCliente
     * @param parent
     * @param modal
     */
    public FormGestionarCliente(java.awt.Frame parent, boolean modal){
        super(parent,modal);
        initComponents();
        setSize(1080,600);
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("ID", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("Nombre", "java.lang.String"));
        tabla.agregarColumna(new Columna("Apellidos", "java.lang.String"));
        tabla.agregarColumna(new Columna("DNI", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaCliente.setModel(modeloTabla);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaCliente, 1, 100, 150, 80);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaCliente, 2, 120, 200, 100);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaCliente, 3, 120, 200, 100);
        ConfiguradorDeTabla.ocultarColumna(tablaCliente, 0);
        txtNombre.requestFocusInWindow();
    }
    public FormGestionarCliente(JDialog owner, Cliente cliente) {
        super(owner, true);
        initComponents();
        buscar();
    }
    private void buscar(){        
        Fila filaTabla;
        try {
            GestionarClienteServicio gestionarClienteServicio = new GestionarClienteServicio();
            ManejadorDeClientes manejadorDeClientes = gestionarClienteServicio.buscarClientes();
            ModeloTabla modeloTabla = (ModeloTabla)tablaCliente.getModel();
            modeloTabla.eliminarTotalFilas();
            for(Cliente cliente : manejadorDeClientes.getClientes()){
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(cliente.getClienteid());                
                filaTabla.agregarValorCelda(cliente.getNombre());
                filaTabla.agregarValorCelda(cliente.getApellidos());
                filaTabla.agregarValorCelda(cliente.getDni());
                modeloTabla.agregarFila(filaTabla);
                txtTotalClitentes.setText(String.valueOf(manejadorDeClientes.evaluarTotalClientes()));
            }
            modeloTabla.refrescarDatos();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    private void buscarNombre(){
        Fila filaTabla;
        String nombre = txtNombre.getText().trim();
        try {
            GestionarClienteServicio gestionarClienteServicio = new GestionarClienteServicio();
            ManejadorDeClientes manejadorDeClientes = gestionarClienteServicio.buscarClienteNombre(nombre);
            ModeloTabla modeloTabla = (ModeloTabla)tablaCliente.getModel();
            modeloTabla.eliminarTotalFilas();
            for(Cliente cliente : manejadorDeClientes.getClientes()){
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(cliente.getClienteid());                
                filaTabla.agregarValorCelda(cliente.getNombre());
                filaTabla.agregarValorCelda(cliente.getApellidos());
                filaTabla.agregarValorCelda(cliente.getDni());
                modeloTabla.agregarFila(filaTabla);
                txtTotalClitentes.setText(String.valueOf(manejadorDeClientes.evaluarTotalClientes()));
            }
            modeloTabla.refrescarDatos();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombre = new javax.swing.JTextField();
        btnCrear = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        botBuscar = new javax.swing.JButton();
        botSalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCliente = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTotalClitentes = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, 160, -1));

        btnCrear.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Crear.png"))); // NOI18N
        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        getContentPane().add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 140, 50));

        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Modificar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 140, 50));

        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 140, 50));

        botBuscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Buscar.png"))); // NOI18N
        botBuscar.setText("Buscar");
        botBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(botBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 140, 50));

        botSalir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        botSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Salir.png"))); // NOI18N
        botSalir.setText("Salir");
        botSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botSalirActionPerformed(evt);
            }
        });
        getContentPane().add(botSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, 130, 50));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setText("Gestionar Clientes");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 63, 1100, 10));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Botones"));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 180, 270));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setForeground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Brush Script MT", 3, 48)); // NOI18N
        jLabel3.setText("Lista de Clientes");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 340, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 500, 70));

        jLabel4.setText("Nombre del Cliente:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, -1));

        tablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaCliente);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 500, 170));

        jLabel1.setText("Total de Clientes");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 480, -1, -1));

        txtTotalClitentes.setEditable(false);
        getContentPane().add(txtTotalClitentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, 80, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

 
    private void botBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botBuscarActionPerformed
        // TODO add your handling code here:
        buscarNombre();
    }//GEN-LAST:event_botBuscarActionPerformed

    private void botSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_botSalirActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_formWindowOpened

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        // TODO add your handling code here:
        FormDatosCliente formDatosCliente = new FormDatosCliente(this);
        formDatosCliente.setVisible(true);
        buscar();
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int clienteid = obtenerId();
        if(clienteid == 0)
            return;
        GestionarClienteServicio gestionarClienteServicio = new GestionarClienteServicio();    
        try {
            Cliente cliente = gestionarClienteServicio.buscarClientesID(clienteid);
            if(cliente != null){
                FormDatosCliente formDatosCliente = new FormDatosCliente(this, cliente);
                formDatosCliente.setVisible(true);
            }
            else
                JOptionPane.showMessageDialog(this, "Error", "La fila no existe", JOptionPane.ERROR_MESSAGE);
                buscar();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnModificarActionPerformed
 
    private int obtenerId() {
        int numFila = tablaCliente.getSelectedRow();
        if(numFila == -1){
            JOptionPane.showMessageDialog(this, "Error", "La fila no fue seleccionada", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        ModeloTabla modeloTabla =(ModeloTabla)tablaCliente.getModel();
        Fila fila = modeloTabla.obtenerFila(numFila);
        return (Integer)fila.obtenerCelda(0).getValor(); // se retorna el id de la fila seleccionada
    }
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        int clienteid = obtenerId();
        int opcionSalida = JOptionPane.showConfirmDialog(null, "¿Desa eliminar esta fila?","Advertencia",JOptionPane.YES_NO_OPTION);
        if(opcionSalida == JOptionPane.YES_OPTION)
            JOptionPane.showMessageDialog(this, "Acontinuacion se eliminara el cliente seleccionado");
        else
            return;
        GestionarClienteServicio gestionarClienteServicio = new GestionarClienteServicio();
        Cliente cliente = new Cliente();
        cliente.setClienteid(clienteid);
        try {
            int registros_afectados = gestionarClienteServicio.eliminarCliente(cliente);
            if(registros_afectados == 1)
                JOptionPane.showMessageDialog(this, "Se eliminaron los datos correctamente");
            else
                JOptionPane.showMessageDialog(this, "Verifique o consulte con su administrador");
                buscar();
        } catch(Exception e){
                JOptionPane.showMessageDialog(this, "Error"+e.getMessage()+JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormGestionarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormGestionarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormGestionarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormGestionarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormGestionarCliente dialog = new FormGestionarCliente(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botBuscar;
    private javax.swing.JButton botSalir;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tablaCliente;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTotalClitentes;
    // End of variables declaration//GEN-END:variables
}
