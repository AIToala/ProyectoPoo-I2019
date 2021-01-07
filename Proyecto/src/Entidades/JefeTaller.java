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
public class JefeTaller extends Usuario {
    ArrayList<String> certTecnica; //Array de strings con Certificaciones Tecnicas
    ArrayList<Vehiculo> mantenimiento;
    ArrayList<Vehiculo> porEntregar;
    double pagos;
    
    
    public JefeTaller(String n, String a, String u, String p, List<ArrayList<String>> soli, ArrayList<String> cert, ArrayList<Vehiculo> mantenimiento, ArrayList<Vehiculo> porEntregar, double pa) {
        super(n, a, u, p, soli);
        this.certTecnica = cert;
        this.mantenimiento = mantenimiento;
        this.porEntregar = porEntregar;
        this.pagos = pa;
    }

    public double getPagos() {
        return pagos;
    }

    public void setPagos(double pagos) {
        this.pagos += pagos;
    }

    public ArrayList<Vehiculo> getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(Vehiculo mantenimiento) {
        this.mantenimiento.add(mantenimiento);
    }

    public ArrayList<Vehiculo> getPorEntregar() {
        return porEntregar;
    }

    public void setPorEntregar(Vehiculo porEntregar) {
        this.porEntregar.add(porEntregar);
    }

    @Override
    public String toString() {
        return "JefeTaller{" + "certTecnica=" + certTecnica + ", mantenimiento=" + mantenimiento + ", porEntregar=" + porEntregar + '}';
    }

    public ArrayList<String> getCertTecnica() {
        return certTecnica;
    }

    public void setCertTecnica(ArrayList<String> certTecnica) {
        this.certTecnica = certTecnica;
    }
    public boolean arreglarVehiculo(ArrayList<Usuario> dbL, ArrayList<String> soli){
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
                        System.out.println("Vehiculo ha sido arreglado?"); String sd = sc.nextLine();
                        if ("s".equals(sd.toLowerCase())){
                            ArrayList<String> info = new ArrayList<>();
                            info.add(this.getUser());
                            info.add(codigoVehiculo);
                            info.add("Arreglado");
                            client.setSolicitud(info);
                            dbL.set(j, client);
                            return true;
                        }
                    }
                }
                j++;
            }
        }
        return false;
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
                        
                        ArrayList<String> info = new ArrayList<>();
                        info.add(this.getUser());
                        info.add(codigoVehiculo);
                        info.add("Listo");
                        client.setSolicitud(info);
                        dbL.set(j, client);
                        System.out.println("VEHICULO LISTO. SE ENVIO MENSAJE A CLIENTE...");
                        return true;
                    }
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
        if (this.getClass().getName().contains("JefeTaller")){
            for (ArrayList<String> str : solicitudesU) {
                System.out.println(str);
                String[] s = str.get(0).split(", ");
                if ("Solicitado".equals(s[2]) || s[2].contains("Mantenimiento")){
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