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
/*Comentario: Siguiendo el esquema/diagrama UML a continuacion se creara una clase conocida como: Motocicleta, 
  la cual Extiende de Vehiculo y en el que se añadiran datos como capacidad de carga y  el numero de ejes que 
  tendrá, ademas algunos datos se volveran mas especificos como; numero de llantas donde en este caso será 2.   */ 

public class Camion extends Vehiculo {
    int capacidadCarga;
    int numEjes;

    public Camion(String codigo, String marca, String modelo, String tipoGas, String añoFab, double precio, int numLlantas, boolean esUsado, boolean solicitado, int capacidadCarga, int numEjes) {
        super(codigo, marca, modelo, tipoGas, añoFab, precio, numLlantas, esUsado, solicitado);
        this.capacidadCarga = capacidadCarga;
        this.numEjes = numLlantas / 2;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(int capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public int getNumEjes() {
        return numEjes;
    }

    public void setNumEjes(int numEjes) {
        this.numEjes = numEjes;
    }

    @Override
    public String toString() {
        return "Camion{" + this.codigo + "-" + this.marca + "-" + this.modelo + "-" + this.tipoGas + "-" + this.añoFab + "-" + this.precio + "-" + this.numLlantas + "-" + this.esUsado + "-" + this.solicitado + "-" + capacidadCarga + "-" + numEjes + '}';
    }

    
    
}
