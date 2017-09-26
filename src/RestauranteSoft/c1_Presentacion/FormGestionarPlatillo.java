/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RestauranteSoft.c1_Presentacion;

import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Celda;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Fila;
import mastersoft.tabladatos.Tabla;
import RestauranteSoft.c1_Presentacion.util.ConfiguradorDeTabla;
import RestauranteSoft.c2_aplicacion.GestionarPlatilloServicio;
import RestauranteSoft.c3_dominio.entidades.Platillo;
import RestauranteSoft.c3_dominio.servicio.ManejadorDeClientes;
import RestauranteSoft.c3_dominio.servicio.ManejadorDePlatillos;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Karito
 */
public class FormGestionarPlatillo extends javax.swing.JDialog {
    
    public Platillo platilloSeleccionado;

    public FormGestionarPlatillo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        setSize(1080,600);
        initComponents();
        setTitle("Gestionar Platillo");
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("ID", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("Descripcion", "java.lang.String"));
        tabla.agregarColumna(new Columna("Tipo", "java.lang.String"));
        tabla.agregarColumna(new Columna("Precio", "java.lang.Double"));                       
        tabla.agregarColumna(new Columna("Stock", "java.lang.Integer"));
        tabla.agregarColumna(new Columna("Estado", "java.lang.String"));
        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tablaPlatillos.setModel(modeloTabla);        
        ConfiguradorDeTabla.configurarAnchoColumna(tablaPlatillos, 1, 350, 400, 50);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaPlatillos, 2, 150, 160, 50);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaPlatillos, 3, 150, 100, 50);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaPlatillos, 4, 150, 100, 50);
        ConfiguradorDeTabla.configurarAnchoColumna(tablaPlatillos, 5, 350, 100, 50);
        ConfiguradorDeTabla.ocultarColumna(tablaPlatillos, 0);
        txtDescripcion.requestFocusInWindow();
        setIconImage(new ImageIcon(getClass().getResource("/iconos/Icono Restaurante.jpg")).getImage());
    }
    
    private void buscar(){        
        Fila filaTabla;
        try {
            GestionarPlatilloServicio gestionarPlatilloServicio = new GestionarPlatilloServicio();
            ManejadorDePlatillos manejadorDePlatillos = gestionarPlatilloServicio.buscarPlatillos();
            ModeloTabla modeloTabla = (ModeloTabla)tablaPlatillos.getModel();
            modeloTabla.eliminarTotalFilas();
            for(Platillo platillo : manejadorDePlatillos.getPlatillos()){
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(platillo.getPlatilloid());                
                filaTabla.agregarValorCelda(platillo.getDescripcion());
                filaTabla.agregarValorCelda(platillo.getTipo());
                filaTabla.agregarValorCelda(platillo.getPrecio());                
                filaTabla.agregarValorCelda(platillo.getStock());
                filaTabla.agregarValorCelda(platillo.getEstado());
                modeloTabla.agregarFila(filaTabla);
            }
            modeloTabla.refrescarDatos();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error"+e.getMessage()+JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarDescripcion(){
        Fila filaTabla;
        String descripcion = txtDescripcion.getText().trim();
        try {
            GestionarPlatilloServicio gestionarPlatilloServicio = new GestionarPlatilloServicio();
            ManejadorDePlatillos manejadorDePlatillos = gestionarPlatilloServicio.buscarPlatillosDescripcion(descripcion);
            ModeloTabla modeloTabla = (ModeloTabla)tablaPlatillos.getModel();
            modeloTabla.eliminarTotalFilas();
            for(Platillo platillo : manejadorDePlatillos.getPlatillos()){
                filaTabla = new Fila();
                filaTabla.agregarValorCelda(platillo.getPlatilloid());                
                filaTabla.agregarValorCelda(platillo.getDescripcion());
                filaTabla.agregarValorCelda(platillo.getTipo());
                filaTabla.agregarValorCelda(platillo.getPrecio());                
                filaTabla.agregarValorCelda(platillo.getStock());
                filaTabla.agregarValorCelda(platillo.getEstado());
                modeloTabla.agregarFila(filaTabla);
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

        panelConsulta = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        scrLectores = new javax.swing.JScrollPane();
        tablaPlatillos = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        barOpciones = new javax.swing.JToolBar();
        btnCrear = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestionar Lector");
        setName("formLector"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panelConsulta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Descripcion del Platillo");
        panelConsulta.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 31, -1, -1));

        txtDescripcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyPressed(evt);
            }
        });
        panelConsulta.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 29, 267, -1));

        btnAceptar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Aceptar.png"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        panelConsulta.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(672, 21, 130, 60));

        tablaPlatillos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaPlatillos.setName("tablaPlatillos"); // NOI18N
        tablaPlatillos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrLectores.setViewportView(tablaPlatillos);

        panelConsulta.add(scrLectores, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 129, 790, 285));

        btnBuscar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setToolTipText("");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        panelConsulta.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 130, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel1.setText("Lista de Platillos");
        panelConsulta.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 87, 793, 30));

        getContentPane().add(panelConsulta, java.awt.BorderLayout.CENTER);

        barOpciones.setFloatable(false);
        barOpciones.setRollover(true);

        btnCrear.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Crear.png"))); // NOI18N
        btnCrear.setText("Crear");
        btnCrear.setFocusable(false);
        btnCrear.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        barOpciones.add(btnCrear);

        btnModificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Modificar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setFocusable(false);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        barOpciones.add(btnModificar);

        btnEliminar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setFocusable(false);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        barOpciones.add(btnEliminar);

        btnSalir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/Salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setFocusable(false);
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        barOpciones.add(btnSalir);

        getContentPane().add(barOpciones, java.awt.BorderLayout.PAGE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        FormDatosPlatillo formDatosPlatillo = new FormDatosPlatillo(this);
        formDatosPlatillo.setVisible(true);
        buscar();
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int platilloid = obtenerId();
        if(platilloid == 0)
            JOptionPane.showMessageDialog(this, "El codigo del platillo no existe");
        GestionarPlatilloServicio gestionarPlatilloServicio = new GestionarPlatilloServicio();    
        try {
            Platillo platillo = gestionarPlatilloServicio.buscarPlatillo(platilloid);    
            gestionarPlatilloServicio.modificarPlatillo(platillo);
            if(platillo != null){
                FormDatosPlatillo formDatosPlatillo = new FormDatosPlatillo(this, platillo);
                formDatosPlatillo.setVisible(true);
            }
            else
                JOptionPane.showMessageDialog(this, "Error", "La fila no existe", JOptionPane.ERROR_MESSAGE);
                buscar();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error"+e.getMessage()+JOptionPane.ERROR_MESSAGE);
        }                                
    }//GEN-LAST:event_btnModificarActionPerformed

    private int obtenerId() {
        int numFila = tablaPlatillos.getSelectedRow();
        if(numFila == -1){
            JOptionPane.showMessageDialog(this, "Error", "La fila no fue seleccionada", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        ModeloTabla modeloTabla =(ModeloTabla)tablaPlatillos.getModel();
        Fila fila = modeloTabla.obtenerFila(numFila);
        return (Integer)fila.obtenerCelda(0).getValor(); // se retorna el id de la fila seleccionada
    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        buscar();
    }//GEN-LAST:event_formWindowOpened

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        int platilloid = obtenerId();
        int opcionSalida = JOptionPane.showConfirmDialog(this, "¿Desa eliminar esta fila?","Advertencia",JOptionPane.YES_NO_OPTION);
        if(opcionSalida == JOptionPane.YES_OPTION)
            JOptionPane.showMessageDialog(this, "Acontinuacion se eliminara el cliente seleccionado");
        else
            return;
        GestionarPlatilloServicio gestionarPlatilloServicio = new GestionarPlatilloServicio();            
        Platillo platillo = new Platillo();
        platillo.setPlatilloid(platilloid);
        try {
            int registros_afectados = gestionarPlatilloServicio.eliminarPlatillo(platillo); 
            if(registros_afectados == 1)
                JOptionPane.showMessageDialog(this, "Se eliminaron los datos correctamente");
            else
                JOptionPane.showMessageDialog(this, "Verifique o consulte con su administrador");
            buscar();    
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error"+e.getMessage()+JOptionPane.ERROR_MESSAGE);
        }                        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscarDescripcion();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        int numFila = tablaPlatillos.getSelectedRow();
        //tomando como referencia al modificar hay que solo recuperar el id del platillo y luego buscarlo
        //no tomar los datos de la tabla
        int idPlatillo= obtenerId();
        if(idPlatillo == 0)
            return ;
        GestionarPlatilloServicio gestionarPlatilloServicio= new GestionarPlatilloServicio();
        try {
//            Platillo platillo= gestionarPlatilloServicio.buscarPlatillo(idPlatillo);
            //usando el platilloseleccionado : GLOBAL
            platilloSeleccionado = gestionarPlatilloServicio.buscarPlatillo(idPlatillo);
            
            this.dispose();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error"+e.getMessage()+JOptionPane.ERROR_MESSAGE);
        }
               
        
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void txtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyPressed
        if( evt.getKeyCode() == 10 ) // es verdad cuando se presiona enter (codigo: 10)
        buscar();
    }//GEN-LAST:event_txtDescripcionKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barOpciones;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelConsulta;
    private javax.swing.JScrollPane scrLectores;
    private javax.swing.JTable tablaPlatillos;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
