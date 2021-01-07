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
/*Comentario: Siguiendo el esquema/diagrama UML a continuacion se creara una clase conocida 
como: Camión, la cual Extiende de Vehiculo y en el que se añadiran datos como la categoría, 
ademas en este caso ningun dato se volveran mas especificos pues dependera de la crecion.   */ 

public class Motocicleta extends Vehiculo {
    String categoria;

    public Motocicleta(String codigo, String marca, String modelo, String tipoGas, String añoFab, double precio, int numLlantas, boolean esUsado, boolean solicitado, String categoria) {
        super(codigo, marca, modelo, "Diesel", añoFab, precio, 4, esUsado, solicitado);
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Motocicleta{" + this.codigo + "-" + this.marca + "-" + this.modelo + "-" + this.tipoGas + "-" + this.añoFab + "-" + this.precio + "-" + this.numLlantas + "-" + this.esUsado + "-" + this.solicitado + "-" + categoria + '}';
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    
}
