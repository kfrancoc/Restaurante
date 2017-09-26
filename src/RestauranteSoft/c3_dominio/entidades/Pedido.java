/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c3_dominio.entidades;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patricia
 */
public class Pedido {
    private int pedidoid;
    private Date fecha;
    private String estado;
    private double monto;
    private Mesa mesa;
    private Usuario usuario;
    private List<LineaDePedido> lineasDePedido; 
    public static final double IGV=0.18;

    public Pedido() {
        this.fecha= Date.valueOf(LocalDate.now());
        this.lineasDePedido= new ArrayList();
    }

    public int getPedidoid() {
        return pedidoid;
    }

    public void setPedidoid(int pedidoid) {
        this.pedidoid = pedidoid;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<LineaDePedido> getLineasDePedido() {
        return lineasDePedido;
    }

     //Reglas de Nogocio
    public boolean agregarLineaDePedido(int cantidad, String condicion, Platillo producto){ //lineadepedido: cantidad, condicion, producto
        if(producto.stockDisponible(cantidad)){
            LineaDePedido lineaDePedido= new LineaDePedido();
            lineaDePedido.setCantidad(cantidad);
            lineaDePedido.setCondicion(condicion);
            lineaDePedido.setPrecio(producto.getPrecio());
            lineaDePedido.setPlatillo(producto);
            lineasDePedido.add(lineaDePedido);
            return true;
        }else{
            return false;
        }
    }
    
    public double calcularTotal(){
       double total=0;
       for(LineaDePedido lineaDePedido : lineasDePedido){
           total+=lineaDePedido.calcularSubTotal();
       }
        return total;
    }
    public double calcularIGV(){
        return calcularTotal()*IGV;
    }
     public double calcularMontoApagar(){
         return calcularTotal()+calcularIGV();
    }
}
