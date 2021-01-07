/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Concesionario.Vehiculo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Group2
 */
public abstract class Usuario {
    String name;
    String lstName;
    String user;
    String password;
    List<ArrayList<String>> solicitud; 
    public Usuario(String n, String a, String u, String p, List<ArrayList<String>> soli) {
        this.name = n;
        this.lstName = a;
        this.user = u;
        this.password = p;
        this.solicitud = soli;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLstName() {
        return lstName;
    }

    public void setLstName(String lstName) {
        this.lstName = lstName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ArrayList<String>> getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(ArrayList<String> sol) {
        this.solicitud.add(sol);
    }
    public void removeSolicitud(int index){
        this.solicitud.remove(index);
    }
    @Override
    public String toString(){
        return "User: " + this.user + "Password:" + this.password;
    }
    public abstract void mostrarSolicitud();
    public abstract void mostrarStock(ArrayList<Vehiculo> stock);
}
