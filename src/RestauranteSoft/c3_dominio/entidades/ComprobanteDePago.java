/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c3_dominio.entidades;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Marlon
 */
public class ComprobanteDePago {
    private int comprobantedepagoid;
    private Date fecha;
    private double igv;
    private double montoAPagar;
    private Usuario usuario;
    private Cliente cliente;
    private Pedido pedido;

    public ComprobanteDePago() {
        this.fecha= Date.valueOf(LocalDate.now());
    }

    public int getComprobantedepagoid() {
        return comprobantedepagoid;
    }

    public void setComprobantedepagoid(int comprobantedepagoid) {
        this.comprobantedepagoid = comprobantedepagoid;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getMontoAPagar() {
        return montoAPagar;
    }

    public void setMontoAPagar(double montoAPagar) {
        this.montoAPagar = montoAPagar;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
