/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestauranteSoft.c3_dominio.entidades;

/**
 *
 * @author Karito
 */
public class Mesa {
    private int mesaid;
    private String estado;
    private int numero;

    public int getMesaid() {
        return mesaid;
    }

    public void setMesaid(int mesaid) {
        this.mesaid = mesaid;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}