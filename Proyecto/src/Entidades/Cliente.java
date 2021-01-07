/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Concesionario.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Group2
 */
public class Cliente extends Usuario{
    String ced;
    String ocupacion;
    double ingresos;
    ArrayList<Vehiculo> vehiculos;
    public Cliente(String n, String a, String u, String p, List<ArrayList<String>> soli, String ced, String ocup, double ing, ArrayList<Vehiculo> vehiculo) {
        super(n,a,u,p,soli);
        this.ced = ced;
        this.ocupacion = ocup;
        this.ingresos = ing;
        this.vehiculos = vehiculo;
    }

    public String getCed() {
        return ced;
    }

    public void setCed(String ced) {
        this.ced = ced;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }
    public ArrayList<Vehiculo> getVehiculos() {
        return this.vehiculos;
    }

    public void setVehiculos(Vehiculo v) {
        this.vehiculos.add(v);
        
    }
    public String mostrarInfo(){
        return "Nombre: " + this.name + "\nApellido: " + this.lstName + "\nUsuario: " + this.user + "\nCedula:" + this.ced + "\nOcupacion: " + this.ocupacion + "\nIngresos: " + this.ingresos;
    }
    public boolean enviarCotizacion(ArrayList<Usuario> dbL, Vehiculo v){
        if(dbL != null){
            ArrayList<Vendedor> vendedor = new ArrayList<>();
            Random r = new Random();
            for(Usuario u : dbL){
                if (u.getClass().getName().contains("Vendedor")){
                    vendedor.add((Vendedor) u);
                }
            }
            int i = r.nextInt(vendedor.size());
            Vendedor u1 = vendedor.get(i);
            int j = 0;
            for(Usuario u: dbL){
                if(u.getUser().equals(u1.getUser())){
                    ArrayList<String> info = new ArrayList<>();
                    info.add(this.getUser());
                    info.add(v.getCodigo());
                    info.add("Solicitado");
                    u.setSolicitud(info);
                    dbL.set(j, u);
                    return true;
                }
                j++;
            }
        }
        return false;
    }

    public boolean enviarCompra(ArrayList<Usuario> dbL, Vehiculo v) {
        if(dbL != null){
            ArrayList<Supervisor> supervisor = new ArrayList<>();
            Random r = new Random();
            Supervisor sup = null;
            for(Usuario u : dbL){
                if (u.getClass().getName().contains("Supervisor")){
                    supervisor.add((Supervisor) u);
                }
            }
            int i = r.nextInt(supervisor.size());
            Supervisor u1 = supervisor.get(i);
            int j = 0;
            for(Usuario u: dbL){
                if(u.getUser().equals(u1.getUser())){
                    sup = (Supervisor) u;
                    ArrayList<String> info = new ArrayList<>();
                    info.add(this.getUser());
                    info.add(v.getCodigo());
                    info.add("Solicitado");
                    sup.setSolicitud(info);
                    dbL.set(j, sup);
                    return true;
                }
                j++;
            }
        }
        return false;
    }
    public boolean mantenimiento(ArrayList<Usuario> dbL, Vehiculo v, String p, String km){
        if(dbL != null){
            ArrayList<JefeTaller> jefetaller = new ArrayList<>();
            Random r = new Random();
            JefeTaller jta = null;
            for(Usuario u : dbL){
                if (u.getClass().getName().contains("JefeTaller")){
                    jefetaller.add((JefeTaller) u);
                }
            }
            int i = r.nextInt(jefetaller.size());
            JefeTaller u1 = jefetaller.get(i);
            int j = 0;
            for(Usuario u: dbL){
                if(u.getUser().equals(u1.getUser())){
                    jta = (JefeTaller) u;
                    ArrayList<String> info = new ArrayList<>();
                    info.add(this.getUser());
                    info.add(v.getCodigo());
                    info.add("Mantenimiento " + p + ";" + km);
                    jta.setSolicitud(info);
                    dbL.set(j, jta);
                    return true;
                }
                j++;
            }
        }
        return false;
    }
    public boolean comprar(ArrayList<Usuario> dbL, Vehiculo v) {
        if(dbL != null){
            ArrayList<JefeTaller> jefetaller = new ArrayList<>();
            Random r = new Random();
            JefeTaller jta = null;
            for(Usuario u : dbL){
                if (u.getClass().getName().contains("JefeTaller")){
                    jefetaller.add((JefeTaller) u);
                }
            }
            int i = r.nextInt(jefetaller.size());
            JefeTaller u1 = jefetaller.get(i);
            int j = 0;
            for(Usuario u: dbL){
                if(u.getUser().equals(u1.getUser())){
                    jta = (JefeTaller) u;
                    ArrayList<String> info = new ArrayList<>();
                    info.add(this.getUser());
                    info.add(v.getCodigo());
                    info.add("Solicitado");
                    jta.setSolicitud(info);
                    dbL.set(j, jta);
                    return true;
                }
                j++;
            }
        }
        return false;
    }
    @Override
    public void mostrarSolicitud(){
        List<ArrayList<String>> solicitudesU = this.getSolicitud();
        int i = 0;
        if (this.getClass().getName().contains("Cliente")){
            for (ArrayList<String> str : solicitudesU) {
                String[] s = str.get(0).split(", ");
                if ("Aprobado".equals(s[2]) || "Rechazado".equals(s[2]) || "Comprado".equals(s[2]) || "Listo".equals(s[2]) || "Arreglado".equals(s[2])){
                    System.out.println(i + "-" + s[0] + "-" + s[1] + "-" + s[2]);
                }
                i++;
            }
        } 
    }
    @Override
    public void mostrarStock(ArrayList<Vehiculo> stock){
        System.out.println("- Vehiculos Disponibles -");
        System.out.println("Tipo - Codigo - Marca - Modelo - Año de fabricación -");
        if (stock != null){
            stock.forEach((v) -> {
                if(!v.isSolicitado()){
                    String tipo = "";
                    if(v.getClass().getName().contains("Tractor")){
                        tipo = "Tractor";
                    }else if(v.getClass().getName().contains("Auto")){
                        tipo = "Auto";
                    }else if(v.getClass().getName().contains("Motocicleta")){
                        tipo = "Motocicleta";
                    }else if(v.getClass().getName().contains("Camion")){
                        tipo = "Camion";
                    }
                    System.out.println(tipo + "  -  " + v.getCodigo() + "  -  " + v.getMarca() + "  -  " + v.getModelo() + "  -  " +v.getAñoFab());
                }
            });
        }else{
            System.out.println("No hay vehiculos disponibles");
        }
        
    }
}