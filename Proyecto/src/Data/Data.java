/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import Concesionario.*;
import Entidades.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Group2
 */
public class Data {
    public static ArrayList<Usuario> usuariosLoad(ArrayList<Vehiculo> vehs) throws FileNotFoundException, IOException{
        File file = new File("loginData.csv");
        ArrayList<Usuario> usuarios = new ArrayList<>();        
        if(file.exists() == false ){
            file.createNewFile();
        }else if(file.exists()){
            BufferedReader nr = new BufferedReader(new FileReader(file)); 
            String ad;
            int index = 0;
            while ((ad = nr.readLine()) != null){
                String[] da = ad.split("-");
                System.out.println(ad);
                List<ArrayList<String>> soli = new ArrayList<>();
                if (!"[]".equals(da[5])){
                    da[5] = da[5].replace("[", "");
                    da[5] = da[5].substring(0,da[5].length()-2);//ignoring last two ]]
                    String[] fd = da[5].trim().split("], ");
                    int i = 0;
                    for (String s : fd) {
                        soli.add(new ArrayList<>());
                        soli.get(i).add(s);
                        i++;
                    }
                }
                if("c".equals(da[0])){
                    ArrayList<Vehiculo> vehiculos = new ArrayList<>();
                    if ("[]".equals(da[9])){
                        vehiculos = new ArrayList<>();
                    } else {
                        da[9] = da[9].replace("[", "");
                        da[9] = da[9].replace("{", "");
                        da[9] = da[9].replace("]", "");
                        da[9] = da[9].replace("}", "");
                        
                        String[] data = da[9].trim().split(", ");
                        for (String j : data){
                            String[] d = j.split("-");
                            Vehiculo vuv = Data.getVehiculo(vehs, d[0]);
                            if(vuv != null && vuv.getClass().getName().contains("Tractor")){
                                d[0] = d[0].replace("Tractor", "");
                                vehiculos.add(new Tractor(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), Boolean.parseBoolean(d[9]),d[10]));
                            } else if(vuv != null && vuv.getClass().getName().contains("Camion")){
                                d[0] = d[0].replace("Camion", "");
                                vehiculos.add(new Camion(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), Integer.parseInt(d[9]), Integer.parseInt(d[10])));
                            }else if(vuv != null && vuv.getClass().getName().contains("Motocicleta")){
                                d[0] = d[0].replace("Motocicleta", "");
                                vehiculos.add(new Motocicleta(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), d[9]));
                            }else if(vuv != null && vuv.getClass().getName().contains("Auto")){
                                d[0] = d[0].replace("Auto", "");
                                vehiculos.add(new Auto(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), Integer.parseInt(d[9]),Boolean.parseBoolean(d[10]),Boolean.parseBoolean(d[11])));
                            }                            
                        }
                    }
                    usuarios.add(new Cliente(da[1], da[2], da[3], da[4],soli,da[6], da[7], Double.parseDouble(da[8]), vehiculos));
                }else if("v".equals(da[0])){
                    ArrayList<Vehiculo> vehiculos = new ArrayList<>();
                    if ("[]".equals(da[7])){
                        vehiculos = new ArrayList<>();
                    } else {
                        da[7] = da[7].replace("[", "");
                        da[7] = da[7].replace("{", "");
                        da[7] = da[7].replace("]", "");
                        da[7] = da[7].replace("}", "");
                        
                        String[] data = da[7].trim().split(", ");
                        for (String j : data){
                            String[] d = j.split("-");
                            Vehiculo vuv = Data.getVehiculo(vehs, d[0]);
                            if(vuv != null && vuv.getClass().getName().contains("Tractor")){
                                d[0] = d[0].replace("Tractor", "");
                                vehiculos.add(new Tractor(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), Boolean.parseBoolean(d[9]),d[10]));
                            } else if(vuv != null && vuv.getClass().getName().contains("Camion")){
                                d[0] = d[0].replace("Camion", "");
                                vehiculos.add(new Camion(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), Integer.parseInt(d[9]), Integer.parseInt(d[10])));
                            }else if(vuv != null && vuv.getClass().getName().contains("Motocicleta")){
                                d[0] = d[0].replace("Motocicleta", "");
                                vehiculos.add(new Motocicleta(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), d[9]));
                            }else if(vuv != null && vuv.getClass().getName().contains("Auto")){
                                d[0] = d[0].replace("Auto", "");
                                vehiculos.add(new Auto(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), Integer.parseInt(d[9]),Boolean.parseBoolean(d[10]),Boolean.parseBoolean(d[11])));
                            }
                        }
                    }
                    usuarios.add(new Vendedor(da[1], da[2], da[3], da[4],soli,da[6], vehiculos));
                }else if("s".equals(da[0])){
                    ArrayList<String> cert = new ArrayList<>();
                    if ("[]".equals(da[5])){
                        cert = new ArrayList<>();
                    }else{
                        da[6] = da[6].replace("[", "");
                        da[6] = da[6].replace("[", "");
                        cert = new ArrayList<>(Arrays.asList(",".join(da[6])));                    }
                    usuarios.add(new Supervisor(da[1], da[2], da[3], da[4],soli,cert));
                }else if("t".equals(da[0])){
                    ArrayList<String> cert = new ArrayList<>();
                    if ("[]".equals(da[6])){
                        cert = new ArrayList<>();
                    }else{
                        da[6] = da[6].replace("[", "");
                        da[6] = da[6].replace("[", "");
                        cert = new ArrayList<>(Arrays.asList(",".join(da[6])));                    }
                    ArrayList<Vehiculo> manten = new ArrayList<>();
                    if ("[]".equals(da[7])){
                        manten = new ArrayList<>();
                    } else {
                        da[7] = da[7].replace("[", "");
                        da[7] = da[7].replace("{", "");
                        da[7] = da[7].replace("]", "");
                        da[7] = da[7].replace("}", "");
                        
                        String[] data = da[7].trim().split(", ");
                        for (String j : data){
                            String[] d = j.split("-");
                            Vehiculo vuv = Data.getVehiculo(vehs, d[0]);
                            if(vuv != null && vuv.getClass().getName().contains("Tractor")){
                                d[0] = d[0].replace("Tractor", "");
                                manten.add(new Tractor(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), Boolean.parseBoolean(d[9]),d[10]));
                            } else if(vuv != null && vuv.getClass().getName().contains("Camion")){
                                d[0] = d[0].replace("Camion", "");
                                manten.add(new Camion(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), Integer.parseInt(d[9]), Integer.parseInt(d[10])));
                            }else if(vuv != null && vuv.getClass().getName().contains("Motocicleta")){
                                d[0] = d[0].replace("Motocicleta", "");
                                manten.add(new Motocicleta(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), d[9]));
                            }else if(vuv != null && vuv.getClass().getName().contains("Auto")){
                                d[0] = d[0].replace("Auto", "");
                                manten.add(new Auto(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), Integer.parseInt(d[9]),Boolean.parseBoolean(d[10]),Boolean.parseBoolean(d[11])));
                            }                            
                        }
                    }
                    ArrayList<Vehiculo> vehiculos = new ArrayList<>();
                    if ("[]".equals(da[8])){
                        vehiculos = new ArrayList<>();
                    } else {
                        da[8] = da[8].replace("[", "");
                        da[8] = da[8].replace("{", "");
                        da[8] = da[8].replace("]", "");
                        da[8] = da[8].replace("}", "");
                        String[] data = da[8].trim().split(", ");
                        for (String j : data){
                            String[] d = j.split("-");
                            Vehiculo vuv = Data.getVehiculo(vehs, d[0]);
                            if(vuv != null && vuv.getClass().getName().contains("Tractor")){
                                d[0] = d[0].replace("Tractor", "");
                                vehiculos.add(new Tractor(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), Boolean.parseBoolean(d[9]),d[10]));
                            } else if(vuv != null && vuv.getClass().getName().contains("Camion")){
                                d[0] = d[0].replace("Camion", "");
                                vehiculos.add(new Camion(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), Integer.parseInt(d[9]), Integer.parseInt(d[10])));
                            }else if(vuv != null && vuv.getClass().getName().contains("Motocicleta")){
                                d[0] = d[0].replace("Motocicleta", "");
                                vehiculos.add(new Motocicleta(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), d[9]));
                            }else if(vuv != null && vuv.getClass().getName().contains("Auto")){
                                d[0] = d[0].replace("Auto", "");
                                vehiculos.add(new Auto(d[0], d[1], d[2], d[3], d[4],Double.parseDouble(d[5]), Integer.parseInt(d[6]), Boolean.parseBoolean(d[7]), Boolean.parseBoolean(d[8]), Integer.parseInt(d[9]),Boolean.parseBoolean(d[10]),Boolean.parseBoolean(d[11])));
                            }                            
                        }
                    }
                    usuarios.add(new JefeTaller(da[1], da[2], da[3], da[4],soli,cert, manten, vehiculos, Double.parseDouble(da[9])));
                }
            }
        }
        return usuarios;
    }
    public static ArrayList<Vehiculo> stockLoad() throws FileNotFoundException, IOException{
        File file = new File("Stock.csv");
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        if(!file.exists()){
            file.createNewFile();
        }else{                              
            BufferedReader dr = new BufferedReader(new FileReader(file)); 
            String ft;
            while ((ft = dr.readLine()) != null){ 
                String[] d = ft.split("-");

                if("t".equals(d[0])){
                    vehiculos.add(new Tractor(d[1], d[2], d[3], d[4], d[5],Double.parseDouble(d[6]), Integer.parseInt(d[7]), Boolean.parseBoolean(d[8]), Boolean.parseBoolean(d[9]), Boolean.parseBoolean(d[10]),d[11]));
                }else if("m".equals(d[0])){
                    vehiculos.add(new Motocicleta(d[1], d[2], d[3], d[4], d[5],Double.parseDouble(d[6]), Integer.parseInt(d[7]), Boolean.parseBoolean(d[8]), Boolean.parseBoolean(d[9]), d[10]));
                }else if("c".equals(d[0])){
                    vehiculos.add(new Camion(d[1], d[2], d[3], d[4], d[5],Double.parseDouble(d[6]), Integer.parseInt(d[7]), Boolean.parseBoolean(d[8]), Boolean.parseBoolean(d[9]), Integer.parseInt(d[10]),Integer.parseInt(d[11])));
                }else if("a".equals(d[0])){
                    vehiculos.add(new Auto(d[1], d[2], d[3], d[4], d[5],Double.parseDouble(d[6]), Integer.parseInt(d[7]), Boolean.parseBoolean(d[8]), Boolean.parseBoolean(d[9]), Integer.parseInt(d[10]),Boolean.parseBoolean(d[11]),Boolean.parseBoolean(d[12])));
                }
            }
        }
        return vehiculos;
    }
    public static void updateUsuarios(ArrayList<Usuario> dbL) throws IOException{
        File file = new File("loginData.csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (Usuario u: dbL){
            if(u.getClass().getName().contains("Cliente")){
                Cliente c1 = (Cliente) u;
                String i = Double.toString(c1.getIngresos());
                String soli = Arrays.toString(c1.getSolicitud().toArray());
                String vehiculos = Arrays.toString(c1.getVehiculos().toArray()); // ["!", "o", ]
                String data = "c" + "-" + c1.getName() + "-" + c1.getLstName() + "-" + c1.getUser() + "-" + c1.getPassword() + "-" + soli + "-" + c1.getCed() + "-" + c1.getOcupacion() + "-" + i + "-" + vehiculos; 
                bw.write(data);
            }else if(u.getClass().getName().contains("Vendedor")){
                Vendedor v1 = (Vendedor) u;
                String soli = Arrays.toString(v1.getSolicitud().toArray());
                String ventas = Arrays.toString(v1.getVentas().toArray()); // ["!", "o", ]
                String data = "v" + "-" + v1.getName() + "-" + v1.getLstName() + "-" + v1.getUser() + "-" + v1.getPassword() + "-" + soli + "-" + v1.getId() + "-" + ventas; 
                bw.write(data);
            }else if(u.getClass().getName().contains("Supervisor")){
                Supervisor s1 = (Supervisor) u;
                String soli = Arrays.toString(s1.getSolicitud().toArray());
                String certif = Arrays.toString(s1.getCertAcad().toArray());
                String data = "s" + "-" + s1.getName() + "-" + s1.getLstName() + "-" + s1.getUser() + "-" + s1.getPassword() + "-" + soli + "-" + certif; 
                bw.write(data);
            }else if(u.getClass().getName().contains("JefeTaller")){
                JefeTaller t1 = (JefeTaller) u;
                String soli = Arrays.toString(t1.getSolicitud().toArray());
                String certif = Arrays.toString(t1.getCertTecnica().toArray());
                String manten = Arrays.toString(t1.getMantenimiento().toArray()); // ["!", "o", ]
                String porEntre = Arrays.toString(t1.getPorEntregar().toArray()); // ["!", "o", ]
                String pago = Double.toString(t1.getPagos());
                String data = "t" + "-" + t1.getName() + "-" + t1.getLstName() + "-" + t1.getUser() + "-" + t1.getPassword() + "-" + soli + "-" + certif + "-" + manten + "-" + porEntre + "-" + pago; 
                bw.write(data);
            }
            bw.newLine();
        }
        bw.close();
    }
    public static void crearUsuario(String q) throws IOException{
        File file = new File("loginData.csv");
        boolean exists = false;
        String gt;
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        String st;
        Scanner sc = new Scanner(System.in);
        String data = "";
        Cliente c1 = null;
        Vendedor v1 = null;
        if ("c".equals(q)){
            System.out.println("CLIENTE\nIngrese los siguientes datos.");
            System.out.print("Nombre: "); String n = sc.nextLine();    
            System.out.print("Apellido: "); String a = sc.nextLine();
            System.out.print("Usuario: "); String us = sc.nextLine();    
            System.out.print("Contraseña: "); String p = sc.nextLine();    
            List<ArrayList<String>> sol = new ArrayList<>();
            ArrayList<Vehiculo> vehiculos = new ArrayList<>();
            System.out.print("Cedula: "); String ced = sc.nextLine();    
            System.out.print("Ocupación: "); String ocup = sc.nextLine();
            System.out.print("Ingresos Mensuales: "); Double ing = Double.parseDouble(sc.nextLine());
            c1 = new Cliente(n, a, us, p, sol, ced, ocup, ing, vehiculos);
            String i = Double.toString(c1.getIngresos());
            String soli = Arrays.toString(c1.getSolicitud().toArray());
            String vehi = Arrays.toString(c1.getVehiculos().toArray()); 

            data = q + "-" + c1.getName() + "-" + c1.getLstName() + "-" + c1.getUser() + "-" + c1.getPassword() + "-" + soli + "-" + c1.getCed() + "-" + c1.getOcupacion() + "-" + i + "-" + vehi; 
            while ((gt = br.readLine())!=null){
                String[] d = gt.split("-");
                if((c1.getUser().equals(d[3]))){
                    exists = true;
                } else if ("c".equals(q)){
                    if (c1.getCed().equals(d[6])){
                        exists = true;
                    }
                }
            }
        } else if ("v".equals(q)){
            System.out.println("VENDEDOR\nIngrese los siguientes datos.");
            System.out.print("Nombre: "); String n = sc.nextLine();    
            System.out.print("Apellido: "); String a = sc.nextLine();
            System.out.print("Usuario: "); String us = sc.nextLine();    
            System.out.print("Contraseña: "); String p = sc.nextLine();    
            List<ArrayList<String>> sol = new ArrayList<>();
            System.out.print("Id: "); String id = sc.nextLine();    
            ArrayList<Vehiculo> ventas = new ArrayList<>();
            v1 = new Vendedor(n, a, us, p, sol, id, ventas);
            String vent = Arrays.toString(v1.getVentas().toArray()); 
            String soli = Arrays.toString(v1.getSolicitud().toArray());
            data = q + "-" + v1.getName() + "-" + v1.getLstName() + "-" + v1.getUser() + "-" + v1.getPassword() + "-" + soli + "-" + v1.getId() + "-" + vent; 
            while ((gt = br.readLine())!=null){
                String[] d = gt.split("-");
                if((v1.getUser().equals(d[3]))){
                    exists = true;
                } else if("v".equals(q)){
                    if (v1.getId().equals(d[6])){
                        exists = true;
                    }
                }
            }    
        } else if("s".equals(q)){
            System.out.println("SUPERVISOR\nIngrese los siguientes datos.");
            System.out.print("Nombre: "); String n = sc.nextLine();    
            System.out.print("Apellido: "); String a = sc.nextLine();
            System.out.print("Usuario: "); String us = sc.nextLine();    
            System.out.print("Contraseña: "); String p = sc.nextLine();    
            List<ArrayList<String>> sol = new ArrayList<>();
            boolean listo = false;
            ArrayList<String> cert = new ArrayList<>();
            while (!listo){
                System.out.print("Ingrese certificación academica: ");
                cert.add(sc.nextLine());
                System.out.print("Desea terminar? s/n: "); String s = sc.nextLine();
                if ("s".equals(s.toLowerCase())){listo = true;}
            }
            Supervisor s1 = new Supervisor(n, a, us, p, sol, cert);
            String certif = Arrays.toString(s1.getCertAcad().toArray());
            String soli = Arrays.toString(s1.getSolicitud().toArray());
            data = q + "-" + s1.getName() + "-" + s1.getLstName() + "-" + s1.getUser() + "-" + s1.getPassword() + "-" + soli + "-" + certif; 
            while ((gt = br.readLine())!=null){
                String[] d = gt.split("-");
                if((s1.getUser().equals(d[3]))){
                    exists = true;
                }
            }
        }else if("t".equals(q)){
            System.out.println("JEFE DE TALLER\nIngrese los siguientes datos.");
            System.out.print("Nombre: "); String n = sc.nextLine();    
            System.out.print("Apellido: "); String a = sc.nextLine();
            System.out.print("Usuario: "); String us = sc.nextLine();    
            System.out.print("Contraseña: "); String p = sc.nextLine();    
            List<ArrayList<String>> sol = new ArrayList<>();
            boolean listo = false;
            ArrayList<String> cert = new ArrayList<>();
            ArrayList<Vehiculo> manten = new ArrayList<>();
            ArrayList<Vehiculo> porEntregar = new ArrayList<>();
            while (!listo){
                System.out.print("Ingrese certificación tecnicas: ");
                cert.add(sc.nextLine());
                System.out.print("Desea terminar? s/n: "); String s = sc.nextLine();
                if ("s".equals(s.toLowerCase())){listo = true;}
            }
            JefeTaller t1 = new JefeTaller(n, a, us, p, sol, cert, manten, porEntregar,0);
            String certif = Arrays.toString(t1.getCertTecnica().toArray()); 
            String soli = Arrays.toString(t1.getSolicitud().toArray());
            String manteni = Arrays.toString(t1.getMantenimiento().toArray());
            String porE = Arrays.toString(t1.getPorEntregar().toArray());
            
            data = q + "-" + t1.getName() + "-" + t1.getLstName() + "-" + t1.getUser() + "-" + t1.getPassword() + "-" + soli + "-" + certif + "-" + manteni + "-" + porE + "-" + "0"; 
            while ((gt = br.readLine())!=null){
                String[] d = gt.split("-");
                if((t1.getUser().equals(d[3]))){
                    exists = true;
                }
            }    
        }
        if (exists == false){
            br = new BufferedReader(new FileReader(file));            
            if(br.readLine() != null){
                bw.newLine();
                bw.write(data);
            }else{
                bw.write(data);
            }
            bw.close();
            System.out.println("Usuario creado con exito...");
        }else{
            System.out.println("Usuario ya existe...");
        }
        
    }
    public static Vehiculo getVehiculo(ArrayList<Vehiculo> vehiculos, String cod){
        int i = 0;
        for (Vehiculo v : vehiculos){
            if (cod.equals(v.getCodigo())){
                return vehiculos.get(i);
            }
            i++;
        }
        return null;
    }
    public static int getIndexUser(ArrayList<Usuario> usuarios, String user){
        int i = 0;
        for (Usuario u: usuarios){
            if(user.equals(u.getUser())){
                return i;
            }
            i++;
        }
        return -1;
    }
    public static int getIndexVehiculo(ArrayList<Vehiculo> vehs, String cod){
        int i = 0;
        for (Vehiculo u: vehs){
            if(cod.equals(u.getCodigo())){
                return i;
            }
            i++;
        }
        return -1;
    }
    public static void ingresarVehiculos(ArrayList<Vehiculo> stock) throws IOException{
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        String data = "";
        System.out.println("INGRESO DE VEHICULOS.");
        File file = new File("Stock.csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        while(!salir){
            boolean valido = false;
            System.out.print("Tipo de vehiculo(T: tractor, C: Camion, A: Auto, M: Motocicleta)-->  ");
            String tipo = sc.nextLine();
            System.out.print("Codigo: "); String codigo = sc.nextLine();
            for (Vehiculo v : stock){
                if(codigo.equals(v.getCodigo())){
                    valido = true;
                }
            }
            if (valido == true) { continue; }
            System.out.print("Marca: ");String mar = sc.nextLine();
            System.out.print("Modelo: ");String mod = sc.nextLine();
            System.out.print("Año de Fabricacion: ");String fab = sc.nextLine();
            System.out.print("Precio: (Ingrese numeros) "); String precio = sc.nextLine();
            System.out.print("Tipo de Gas: "); String tipoGas = sc.nextLine();
            System.out.print("Es Usado?: s/n");String uso = sc.nextLine();
            System.out.print("Numero de llantas: (Ingrese numeros) "); String numLlantas = sc.nextLine();
            if ("s".equals(uso.toLowerCase())){
                uso = "true";
            }else{
                uso = "false";
            }
            boolean solicitado = false;
            if ("t".equals(tipo.toLowerCase())){
                System.out.print("Es Agricola?: s/n");String ag = sc.nextLine();
                if ("s".equals(uso.toLowerCase())){
                    ag = "true";
                }else{
                    ag = "false";
                }
                System.out.print("Tipo de transmision: (A: automatico, M: manual");String trans = sc.nextLine();
                data = "t" + "-" + codigo + "-" + mar + "-"+ mod + "-"+ "Diesel" + "-" + fab + "-" + precio + "-" + "4" + "-" + uso + "-" +"false" + "-" + ag + "-" + trans; 
            }else if("m".equals(tipo.toLowerCase())){
                System.out.print("Categoria: (Deportiva, scotter, todoTerreno)");String cate = sc.nextLine();
                data = "m" + "-" + codigo + "-" + mar + "-"+ mod + "-"+ "tipoGas" + "-" + fab + "-" + precio + "-" + "2" + "-" + uso + "-" +"false" + "-" + cate; 
            }else if("a".equals(tipo.toLowerCase())){
                System.out.print("Numero de asientos: (ingrese un numero) "); String numAsientos = sc.nextLine();
                System.out.print("Es Convertible?: s/n");String con = sc.nextLine();
                if ("s".equals(uso.toLowerCase())){
                    con = "true";
                }else{
                    con = "false";
                }
                System.out.print("Tiene Camara de Retro?: s/n");String cam = sc.nextLine();
                if ("s".equals(uso.toLowerCase())){
                    cam = "true";
                }else{
                    cam = "false";
                }
                data = "a" + "-" + codigo + "-" + mar + "-"+ mod + "-"+ tipoGas + "-" + fab + "-" + precio + "-" + "4" + "-" + uso + "-" +"false" + "-" + numAsientos + "-" + con + "-" + cam; 
            }else if("c".equals(tipo.toLowerCase())){
                System.out.print("Capacidad de carga: (Ingrese numeros)"); String cap = sc.nextLine();
                String numEjes = Integer.toString(Integer.parseInt(numLlantas) / 2);
                data = "c" + "-" + codigo + "-" + mar + "-"+ mod + "-"+ tipoGas + "-" + fab + "-" + precio + "-" + numLlantas + "-" + uso + "-" + "false" + "-" + cap + "-" + numEjes; 
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            if(br.readLine() != null){
                bw.newLine();
                bw.write(data);
            }else{
                bw.write(data);
            }
            salir = true;
        }
        System.out.println("Vehiculos han sido ingresados.");
        bw.close();
        
    }

    public static void updateStock(ArrayList<Vehiculo> stock) throws IOException {
        File file = new File("Stock.csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        
        for (Vehiculo u: stock){
            if(u.getClass().getName().contains("Tractor")){
                Tractor t = (Tractor) u;
                String data = "t" + "-" + t.getCodigo() + "-" + t.getMarca() + "-"+ t.getModelo() + "-"+ "Diesel" + "-" + t.getAñoFab() + "-" + Double.toString(t.getPrecio()) + "-" + "4" + "-" + Boolean.toString(t.isEsUsado()) + "-" + Boolean.toString(t.isSolicitado()) + "-" + Boolean.toString(t.isEsAgricola()) + "-" + t.getTipoTransmision(); 
                bw.write(data);
            }else if(u.getClass().getName().contains("Camion")){
                Camion c = (Camion) u;
                String data = "c" + "-" + c.getCodigo() + "-" + c.getMarca() + "-"+ c.getModelo() + "-"+ c.getTipoGas() + "-" + c.getAñoFab() + "-" + Double.toString(c.getPrecio()) + "-" + Integer.toString(c.getNumLlantas()) + "-" + Boolean.toString(c.isEsUsado()) + "-" + Boolean.toString(c.isSolicitado()) + "-" + Integer.toString(c.getCapacidadCarga()) + "-" + Integer.toString(c.getNumEjes()); 
                bw.write(data);
            }else if(u.getClass().getName().contains("Motocicleta")){
                Motocicleta m = (Motocicleta) u;
                String data = "m" + "-" + m.getCodigo() + "-" + m.getMarca() + "-"+ m.getModelo() + "-"+ m.getTipoGas() + "-" + m.getAñoFab() + "-" + Double.toString(m.getPrecio()) + "-" + "2" + "-" + Boolean.toString(m.isEsUsado()) + "-" + Boolean.toString(m.isSolicitado()) + "-" + m.getCategoria(); 
                bw.write(data);
            }else if(u.getClass().getName().contains("Auto")){
                Auto a = (Auto) u;
                String data = "a" + "-" + a.getCodigo() + "-" + a.getMarca() + "-"+ a.getModelo() + "-"+ a.getTipoGas() + "-" + a.getAñoFab() + "-" + Double.toString(a.getPrecio()) + "-" + "4" + "-" + Boolean.toString(a.isEsUsado()) + "-" + Boolean.toString(a.isSolicitado()) + "-" + Integer.toString(a.getNumAsientos()) + "-" + Boolean.toString(a.isEsConvertible()) + "-" + Boolean.toString(a.isCamRetro()); 
                bw.write(data);
            }
            bw.newLine();
        }
        bw.close();
    }
}