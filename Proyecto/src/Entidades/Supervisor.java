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
public class Supervisor extends Usuario {
    ArrayList<String> certAcad; //Array de strings con Certificaciones Academicas

    public Supervisor(String n, String a, String u, String p, List<ArrayList<String>> soli, ArrayList<String> cert) {
        super(n, a, u, p, soli);
        this.certAcad = cert;
    }

    public ArrayList<String> getCertAcad() {
        return certAcad;
    }

    public void setCertAcad(ArrayList<String> certAcad) {
        this.certAcad = certAcad;
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
                        
                        System.out.print("Desea permitirle la compra? s/n --> "); String s = sc.nextLine();
                        if ("s".equals(s.toLowerCase())){
                            ArrayList<String> info = new ArrayList<>();
                            info.add(this.getUser());
                            info.add(codigoVehiculo);
                            info.add("Comprado");
                            client.setSolicitud(info);
                            dbL.set(j, client);
                            System.out.println("Aprobacion a solicitud del cliente ha sido enviado...");
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
                            System.out.println("Rechazo a solicitud del cliente ha sido enviado...");
                            return false;
                        }
                    }
                }
                j++;
            }
        }
        return false;
    }

    public Vendedor getVendedor(ArrayList<Usuario> dbL, Vehiculo veh) {
        Vendedor v1 = null;
        for (Usuario u: dbL){
            if(u.getClass().getName().contains("Vendedor")){
                v1 = (Vendedor) u;
                for(Vehiculo v : v1.getVentas()){
                    if(v.getCodigo().equals(veh.getCodigo())){
                        return v1;
                    }
                }
            }
        }
        return null;
    }
    @Override
    public void mostrarSolicitud(){
        List<ArrayList<String>> solicitudesU = this.getSolicitud();
        int i = 0;
        if (this.getClass().getName().contains("Supervisor")){
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
    public void verInfoEmpleados(ArrayList<Usuario> usuarios){
        for (Usuario u: usuarios){
            if(u.getClass().getName().contains("Vendedor")){
                System.out.println(u.getUser() + "-" + u);
            }else if(u.getClass().getName().contains("Supervisor")){
                System.out.println(u.getUser() + "-" + u);
            }else if(u.getClass().getName().contains("JefeTaller")){
                System.out.println(u.getUser() + "-" + u);
            }
        }
    }

    @Override
    public String toString() {
        return "Supervisor{" + "certAcad=" + certAcad + '}';
    }
}
