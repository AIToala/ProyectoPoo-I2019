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
/*Comentario: Siguiendo el esquema/diagrama UML a continuacion se creara una clase conocida como: Tractor, 
  la cual Extiende de Vehiculo y en el que se añadiran datos como el tipo de transmision, la posibilidad 
  de ser un tractor agricola, ademas algunos datos se volveran mas especificos como; numero de llantas es 4
  y la gasolina que va a utilizar que es Diesel.   */ 
public class Tractor extends Vehiculo {
    boolean esAgricola;
    String tipoTransmision;

    public Tractor(String codigo, String marca, String modelo, String tipoGas, String añoFab, double precio, int numLlantas, boolean esUsado, boolean solicitado, boolean esAgricola, String tipoTransmision) {
        super(codigo, marca, modelo, "Diesel", añoFab, precio, 4, esUsado, solicitado);
        this.esAgricola = esAgricola;
        this.tipoTransmision = tipoTransmision;
    }

    public boolean isEsAgricola() {
        return esAgricola;
    }

    public void setEsAgricola(boolean esAgricola) {
        this.esAgricola = esAgricola;
    }

    public String getTipoTransmision() {
        return tipoTransmision;
    }

    public void setTipoTransmision(String tipoTransmision) {
        this.tipoTransmision = tipoTransmision;
    }

    @Override
    public String toString() {
        return "Tractor{"+ this.codigo + "-" + this.marca + "-" + this.modelo + "-" + this.tipoGas + "-" + this.añoFab + "-" + this.precio + "-" + this.numLlantas + "-" + this.esUsado + "-" + this.solicitado + "-" + esAgricola + "-" + tipoTransmision + '}';
    }
    
    
    
}
