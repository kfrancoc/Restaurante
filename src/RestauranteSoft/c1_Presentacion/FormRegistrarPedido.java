
package RestauranteSoft.c1_Presentacion;

import RestauranteSoft.c1_Presentacion.util.ConfiguradorDeTabla;
import RestauranteSoft.c2_aplicacion.RegistrarPedidoServicio;
import RestauranteSoft.c3_dominio.entidades.LineaDePedido;
import RestauranteSoft.c3_dominio.entidades.Mesa;
import RestauranteSoft.c3_dominio.entidades.Pedido;
import RestauranteSoft.c3_dominio.entidades.Platillo;
import RestauranteSoft.c3_dominio.entidades.Usuario;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Date;
import java.util.Calendar;
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
public class FormRegistrarPedido extends java.awt.Dialog {
    
    private Pedido pedido;
    private Usuario usuario;
    private Mesa mesa;
    private Platillo platillo;

    /**
     * Creates new form FormRegistrarPedido
     */
    public FormRegistrarPedido(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Registrar Pedido");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana =this.getSize();
        this.setLocation((pantalla.width - ventana.width)/2, (pantalla.height - ventana.height)/2);
        pedido= new Pedido();
        prepararTabla();
        iniciaComboMesa();
        iniciarComboMesero();
        txtFecha.setText(String.valueOf(pedido.getFecha()));
        txtFecha.setEditable(false);
        setIconImage(new ImageIcon(getClass().getResource("/iconos/Icono Restaurante.jpg")).getImage());
    }
    private void iniciaComboMesa(){
        try {
            RegistrarPedidoServicio registrarPedidoServicio = new RegistrarPedidoServicio();
            List<Mesa> listaMesas =registrarPedidoServicio.listaMesas();
            for(Mesa mesa : listaMesas){
                cboNumeroMesa.addItem(mesa.getNumero());
            }
        } catch (Exception e) {
        }
    }
    
    private void iniciarComboMesero(){
        try {
            RegistrarPedidoServicio registrarPedidoServicio= new RegistrarPedidoServicio();
            List<Usuario> listaUsuarios= registrarPedidoServicio.listaUsuarios();
            for(Usuario usuario : listaUsuarios){
                cboMesero.addItem(usuario.getDni());
            }
        } catch (Exception e) {
        }
    }

    private void prepararTabla(){
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("ID", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("Descripcion", "java.lang.String"));
        tabla.agregarColumna(new Columna("Tipo", "java.lang.String"));
        tabla.agregarColumna(new Columna("Condicion", "java.lang.String"));   
        tabla.agregarColumna(new Columna("Cantidad", "java.lang.Integer")); 
        tabla.agregarColumna(new Columna("Precio", "java.lang.Double")); 
        tabla.agregarColumna(new Columna("Importe", "java.lang.Double"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaListaPlatillos.setModel(modeloTabla);        
        ConfiguradorDeTabla.configurarAnchoColumna(tablaListaPlatillos, 1, 250, 400, 50);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaListaPlatillos, 2, 80, 100, 50);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaListaPlatillos, 2, 80, 100, 50);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaListaPlatillos, 2, 80, 100, 50);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaListaPlatillos, 3, 300, 400, 100);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaListaPlatillos, 4, 250, 300, 100);
        ConfiguradorDeTabla.ocultarColumna(tablaListaPlatillos, 0);
    }
    
    private void inicializarPedido(){
        cboNumeroMesa.setSelectedIndex(0);
        cboMesero.setSelectedIndex(0);
        mesa=null;
        usuario=null;
        btnAgregarALista.setEnabled(false);
        btnRegistrarPedido.setEnabled(false);
        ModeloTabla modeloTabla = (ModeloTabla) tablaListaPlatillos.getModel();
        modeloTabla.eliminarTotalFilas();
    }
    
    private void inicializarPlatillo(){
        cboCondicion.setSelectedIndex(0);
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtTipo.setText("");
        platillo=null;
    }
    
    private void activarBotonRegistrarPedido(){
        //validad si la tabla tiene registros
        ModeloTabla modeloTabla = (ModeloTabla) tablaListaPlatillos.getModel();
        if(usuario!=null && mesa!=null && modeloTabla.getRowCount()>0){
            btnRegistrarPedido.setEnabled(true);
        }else{
            btnRegistrarPedido.setEnabled(false);
            //JOptionPane.showMessageDialog(null, "Se debe seleccionar o llenar todos los campos");
        }
    }
    
    private void activarBotonAgregarALista(){
        if(platillo!=null && txtCantidad.getText().compareTo("")!=0 && cboCondicion.getSelectedIndex()!=0){
            btnAgregarALista.setEnabled(true);
        }else{
            btnAgregarALista.setEnabled(false);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSeleccionarProducto = new javax.swing.JButton();
        btnAgregarALista = new javax.swing.JButton();
        txtDescripcion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListaPlatillos = new javax.swing.JTable();
        btnRegistrarPedido = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        cboCondicion = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cboMesero = new javax.swing.JComboBox();
        cboNumeroMesa = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        txtTipo = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();

        setBackground(new java.awt.Color(240, 240, 240));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSeleccionarProducto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSeleccionarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Buscar.png"))); // NOI18N
        btnSeleccionarProducto.setText("Seleccionar Platillo");
        btnSeleccionarProducto.setActionCommand("");
        btnSeleccionarProducto.setName("btnSeleccionarProducto"); // NOI18N
        btnSeleccionarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarProductoActionPerformed(evt);
            }
        });
        add(btnSeleccionarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(426, 171, -1, -1));

        btnAgregarALista.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAgregarALista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Agregar.png"))); // NOI18N
        btnAgregarALista.setText("Agregar a Lista");
        btnAgregarALista.setEnabled(false);
        btnAgregarALista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarAListaActionPerformed(evt);
            }
        });
        add(btnAgregarALista, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 240, 190, 60));

        txtDescripcion.setEditable(false);
        txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 217, 227, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Descripcion");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 209, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Precio");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 235, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Cantidad");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 242, -1, -1));

        txtPrecio.setEditable(false);
        txtPrecio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 242, 75, -1));

        txtCantidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 242, 62, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Condicion");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 182, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Numero Mesa");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 79, -1, -1));

        jLabel10.setBackground(new java.awt.Color(51, 153, 255));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("REGISTRO DE PEDIDO");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 657, 43));

        jLabel1.setText("Fecha");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(503, 81, 118, -1));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 165, 633, 10));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Lista de Platillos");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 345, -1, -1));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 329, 645, 10));

        tablaListaPlatillos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablaListaPlatillos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaListaPlatillos);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 380, 595, 136));

        btnRegistrarPedido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRegistrarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Registrar.png"))); // NOI18N
        btnRegistrarPedido.setText("Registrar Pedido");
        btnRegistrarPedido.setEnabled(false);
        btnRegistrarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarPedidoActionPerformed(evt);
            }
        });
        add(btnRegistrarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, -1, 60));

        btnCerrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Cerrar.png"))); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 530, 130, 60));

        cboCondicion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboCondicion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "PARA LLEVAR", "PARA CONSUMIR" }));
        cboCondicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCondicionActionPerformed(evt);
            }
        });
        add(cboCondicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 181, 227, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Mesero");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 115, -1, -1));

        cboMesero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        cboMesero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMeseroActionPerformed(evt);
            }
        });
        add(cboMesero, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 112, 313, -1));

        cboNumeroMesa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        cboNumeroMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNumeroMesaActionPerformed(evt);
            }
        });
        add(cboNumeroMesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 76, 96, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Tipo");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 278, -1, -1));

        txtTipo.setEditable(false);
        txtTipo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 278, 227, -1));

        txtTotal.setEditable(false);
        add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 546, 110, -1));

        txtFecha.setEditable(false);
        add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(503, 112, 118, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void btnSeleccionarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarProductoActionPerformed
        // TODO add your handling code here:
        try {
            FormGestionarPlatillo formGestionarPlatillo= new FormGestionarPlatillo(null, true);
            formGestionarPlatillo.setVisible(true);
            platillo = formGestionarPlatillo.platilloSeleccionado;
            txtDescripcion.setText(platillo.getDescripcion());
            txtTipo.setText(platillo.getTipo());
            txtPrecio.setText(String.valueOf(platillo.getPrecio()));
            activarBotonAgregarALista();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnSeleccionarProductoActionPerformed

    private void btnAgregarAListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarAListaActionPerformed
        //preguntar primero si se ingreso la cantidad
        ModeloTabla modeloTabla = (ModeloTabla) tablaListaPlatillos.getModel();
        Fila filaTabla;
        try {
            int cantidad= Integer.parseInt(txtCantidad.getText());
            String condicion=cboCondicion.getSelectedItem().toString();
            
            if(pedido.agregarLineaDePedido(cantidad, condicion, platillo)){
                filaTabla= new Fila();
                filaTabla.agregarValorCelda(platillo.getPlatilloid());
                filaTabla.agregarValorCelda(platillo.getDescripcion());
                filaTabla.agregarValorCelda(platillo.getTipo());
                filaTabla.agregarValorCelda(cboCondicion.getSelectedItem());
                filaTabla.agregarValorCelda(cantidad);
                filaTabla.agregarValorCelda(platillo.getPrecio());
                filaTabla.agregarValorCelda(cantidad*platillo.getPrecio());
                modeloTabla.agregarFila(filaTabla);
            }else{
                JOptionPane.showMessageDialog(this, "No hay stock disponible en estos momentos");
            }
            
            //totalPedido += lineaDePedido.getCantidad()*lineaDePedido.getPrecio();
            txtTotal.setText(String.valueOf(pedido.calcularTotal()));
            modeloTabla.refrescarDatos();
            inicializarPlatillo();
            activarBotonRegistrarPedido();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error"+e.getMessage()+JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnAgregarAListaActionPerformed

    private void btnRegistrarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarPedidoActionPerformed
        int registros_afectados;
        // se debe llenar todos los datos a un pedido creado :la fecha no hay necesidad ya que se toma del sistema
        //pedido.setFecha(pedido.getFecha()); ya no es necesario : cuando se crear ya tiene fecha actual de sistema
        pedido.setEstado(pedido.getEstado());
        pedido.setMonto(pedido.calcularTotal());
        pedido.setUsuario(usuario);
        pedido.setMesa(mesa);
        pedido.setEstado("SIN COBRAR");
        
        try {
            RegistrarPedidoServicio registrarPedidoServicio= new RegistrarPedidoServicio();
            registros_afectados = registrarPedidoServicio.crearPedido(pedido);
            if(registros_afectados == 1){
                JOptionPane.showMessageDialog(this, "Se registró correctamente el pedido");
                inicializarPedido();
            }else{
                JOptionPane.showMessageDialog(this, "Verifique sus datos por favor");
            }    
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error"+e.getMessage()+JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnRegistrarPedidoActionPerformed

    private void cboNumeroMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNumeroMesaActionPerformed
        //CODIGO PARA PODER LLENAR UN OBJETO MESA
        int numero=0;
        if(cboNumeroMesa.getSelectedIndex()!=0){
            numero= Integer.parseInt(cboNumeroMesa.getSelectedItem().toString());
            try {
                RegistrarPedidoServicio registrarPedidoServicio= new RegistrarPedidoServicio();
                mesa= registrarPedidoServicio.buscarMesa(numero);
                activarBotonRegistrarPedido();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error"+e.getMessage()+JOptionPane.ERROR_MESSAGE);
            }
        }else{
            //JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un numero de mesa");
            mesa=null;
            activarBotonRegistrarPedido();
        }
        
    }//GEN-LAST:event_cboNumeroMesaActionPerformed

    private void cboMeseroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMeseroActionPerformed
        //CODIGO PARA PODER LLENAR UN OBJETO MESERO
        String dni;
        if(cboMesero.getSelectedIndex()!=0){
            dni = cboMesero.getSelectedItem().toString();
            //buscaremos el mesero con este nombre, pero con el login se tomaria este como usuario que registra
            try {
                RegistrarPedidoServicio registrarPedidoServicio= new RegistrarPedidoServicio();
                usuario= registrarPedidoServicio.buscarUsuarioPorDNI(dni);
                activarBotonRegistrarPedido();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error"+e.getMessage()+JOptionPane.ERROR_MESSAGE);
            }
        }else{
            //JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un dni de mesero");
            usuario=null;
            activarBotonRegistrarPedido();
        }
    }//GEN-LAST:event_cboMeseroActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void cboCondicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCondicionActionPerformed
        // TODO add your handling code here:
        activarBotonAgregarALista();
    }//GEN-LAST:event_cboCondicionActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        // TODO add your handling code here:
        activarBotonAgregarALista();
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        // TODO add your handling code here:
        activarBotonAgregarALista();
    }//GEN-LAST:event_txtCantidadKeyReleased

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarALista;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnRegistrarPedido;
    private javax.swing.JButton btnSeleccionarProducto;
    private javax.swing.JComboBox cboCondicion;
    private javax.swing.JComboBox cboMesero;
    private javax.swing.JComboBox cboNumeroMesa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tablaListaPlatillos;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
