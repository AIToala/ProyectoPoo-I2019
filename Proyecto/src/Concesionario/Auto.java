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
/*Comentario: Siguiendo el esquema/diagrama UML a continuacion se creara una clase conocida como: Auto, 
  la cual Extiende de Vehiculo y en el que se añadiran datos como numero de asientos, la posibilidad de ser convertible
  y de poseer una camara retrovisora, ademas algunos datos se volveran mas especificos como; numero de llantas es 4.   */ 

public class Auto extends Vehiculo{
    int numAsientos;
    boolean esConvertible;
    boolean camRetro;

    public Auto(String codigo, String marca, String modelo, String tipoGas, String añoFab, double precio, int numLlantas, boolean esUsado, boolean solicitado, int numAsientos, boolean esConvertible, boolean camRetro) {
        super(codigo, marca, modelo, tipoGas, añoFab, precio, 4, esUsado, solicitado);
        this.numAsientos = numAsientos;
        this.esConvertible = esConvertible;
        this.camRetro = camRetro;
        
    }

    @Override
    public String toString() {
        return "Auto{" + this.codigo + "-" + this.marca + "-" + this.modelo + "-" + this.tipoGas + "-" + this.añoFab + "-" + this.precio + "-" + this.numLlantas + "-" + this.esUsado + "-" + this.solicitado + "-" + numAsientos + "-" + esConvertible + "-" + camRetro + '}';
    }

    public int getNumAsientos() {
        return numAsientos;
    }

    public void setNumAsientos(int numAsientos) {
        this.numAsientos = numAsientos;
    }

    public boolean isEsConvertible() {
        return esConvertible;
    }

    public void setEsConvertible(boolean esConvertible) {
        this.esConvertible = esConvertible;
    }

    public boolean isCamRetro() {
        return camRetro;
    }

    public void setCamRetro(boolean camRetro) {
        this.camRetro = camRetro;
    }

}
