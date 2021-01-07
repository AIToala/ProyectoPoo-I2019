/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import Concesionario.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Group2
 */
public class Vendedor extends Usuario{
    String id;
    ArrayList<Vehiculo> ventas;
    public Vendedor(String n, String a, String u, String p, List<ArrayList<String>> soli, String id, ArrayList<Vehiculo> vent) {
        super(n, a, u, p, soli);
        this.id = id;
        this.ventas = vent;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "id=" + id + ", ventas=" + ventas + '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Vehiculo> getVentas() {
        return ventas;
    }

    public void setVentas(Vehiculo ventas) {
        this.ventas.add(ventas);
        
    }
    public boolean enviarCompra(ArrayList<Usuario> dbL, ArrayList<String> soli){
        Scanner sc = new Scanner(System.in);
        if(dbL != null){
            String user = soli.get(0).split(", ")[0];
            String codigoVehiculo = soli.get(0).split(", ")[1];
            int j = 0;
            for(Usuario u : dbL){
                if (u.getClass().getName().contains("Cliente")){
                    if(u.getUser().equals(user)){
                        Cliente client = (Cliente) u;
                        System.out.println("-INFORMACION DEL CLIENTE-");
                        System.out.println(client.mostrarInfo());
                        System.out.print("Desea enviarle la cotización? s/n -->"); String s = sc.nextLine();
                        if ("s".equals(s.toLowerCase())){
                            ArrayList<String> info = new ArrayList<>();
                            info.add(this.getUser());
                            info.add(codigoVehiculo);
                            info.add("Aprobado");
                            client.setSolicitud(info);
                            dbL.set(j, client);
                            System.out.println("Cotizacion ha sido enviada...");
                            return true;
                        }else{
                            System.out.println("Motivo de rechazo de peticion:");
                            String motivo = sc.nextLine();
                            ArrayList<String> info = new ArrayList<>();
                            info.add(this.getUser());
                            info.add(motivo + "--" + codigoVehiculo);
                            info.add("Rechazado");
                            client.setSolicitud(info);
                            dbL.set(j, client);
                            return false;
                        }
                    }
                }
            }
            j++;
        }
        return false;
    }
    @Override
    public void mostrarSolicitud(){
        List<ArrayList<String>> solicitudesU = this.getSolicitud();
        int i = 0;
        if (this.getClass().getName().contains("Vendedor")){
            for (ArrayList<String> str : solicitudesU) {
                String[] s = str.get(0).split(", ");
                if ("Solicitado".equals(s[2])){
                    System.out.println(i + "-" + s[0] + "-" + s[1] + "-" + s[2]);
                }
                i++;
            }
        } 
    }
    @Override
    public void mostrarStock(ArrayList<Vehiculo> stock){
        System.out.println("- Vehiculos Disponibles -");
        System.out.println("Codigo - Marca - Modelo - TipoGas - Año de fabricación - Precio - Numero de Llantas - es Usado? - solicitado?");
        if (stock != null){
            stock.forEach((v) -> {
                if(!v.isSolicitado()){
                    System.out.println(v);
                }
            });
        }else{
            System.out.println("No hay vehiculos disponibles");
        }
        
    }
}