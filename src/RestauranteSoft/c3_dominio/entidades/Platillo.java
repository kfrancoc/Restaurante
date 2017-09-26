/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c3_dominio.entidades;

import java.util.ArrayList;

/**
 *
 * @author Karito
 */
public class Platillo {
    private int platilloid;
    private String descripcion;
    private String tipo;
    private int stock;
    private double precio;
    private String estado;

    public Platillo() {
    }

    public Platillo(String descripcion, String tipo, int stock, double precio, String estado) {
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.stock = stock;
        this.precio = precio;
        this.estado = estado;
    }

    public int getPlatilloid() {
        return platilloid;
    }

    public void setPlatilloid(int platilloid) {
        this.platilloid = platilloid;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public boolean estaDisponibleParaPedido(){
        if(estado.equals("DISPONIBLE"))
            return true;
        else
            return false;            
    }
    public boolean stockDisponible(int cantidad){
        return (stock>=cantidad);
    }
    
    public void restarStock(int cantidad){
        stock=stock-cantidad;
    }
    
    public double valorStock(){
        return precio*stock;
    }
}