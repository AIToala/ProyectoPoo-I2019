/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concesionario;
/**
 *
 * @author Group2
 */
/*Comentario: Siguiendo el esquema/diagrama UML a continuacion se creara la clase padre conocida como: Vehiculo, 
  la cual inicializara las variables que indiquen: marca, modelo, tipo de gasolina, año de fabricacion, precio,
  numero de llantas y si este es usado.   */ 
public abstract class Vehiculo {
    String codigo;
    String marca;
    String modelo;
    String tipoGas;
    String añoFab;
    double precio;
    int numLlantas;
    boolean esUsado;
    boolean solicitado;
    
    public Vehiculo(String codigo, String marca, String modelo, String tipoGas, String añoFab, double precio, int numLlantas, boolean esUsado, boolean solicitado) {
        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoGas = tipoGas;
        this.añoFab = añoFab;
        this.precio = precio;
        this.numLlantas = numLlantas;
        this.esUsado = esUsado;
        this.solicitado = solicitado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isSolicitado() {
        return solicitado;
    }

    public void setSolicitado(boolean soli) {
        this.solicitado = soli;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoGas() {
        return tipoGas;
    }

    public void setTipoGas(String tipoGas) {
        this.tipoGas = tipoGas;
    }

    public String getAñoFab() {
        return añoFab;
    }

    public void setAñoFab(String añoFab) {
        this.añoFab = añoFab;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getNumLlantas() {
        return numLlantas;
    }

    public void setNumLlantas(int numLlantas) {
        this.numLlantas = numLlantas;
    }

    public boolean isEsUsado() {
        return esUsado;
    }

    public void setEsUsado(boolean esUsado) {
        this.esUsado = esUsado;
    }
    @Override
    public abstract String toString();

}