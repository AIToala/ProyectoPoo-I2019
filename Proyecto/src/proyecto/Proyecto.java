package proyecto;
import Concesionario.*;
import Data.*;
import Entidades.*;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author Group2
 */
public class Proyecto {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    
    public static void main(String[] args) throws IOException{
        boolean end = false;
        while (!end){
            Scanner sc = new Scanner(System.in);
            ArrayList<Vehiculo> stock = Data.stockLoad();
            ArrayList<Usuario> dbL = Data.usuariosLoad(stock);

            System.out.println("--- CONCESIONARIA ---\n" +
            "BIENVENIDO...\n" +
            "1. Iniciar Sesión\n" +
            "2. Crear cuenta\n" +
            "3. Salir");
            System.out.print("->");String opc = sc.nextLine();
            if ("1".equals(opc)){
                System.out.println("---INICIO DE SESIÓN---");
                System.out.print("Usuario: ");String user = sc.nextLine();
                System.out.print("Contraseña: ");String passw = sc.nextLine();
                if (login(user, passw, dbL) != null){
                    Usuario u = login(user, passw, dbL);
                    System.out.println("Inicio de sesion exitoso.");
                    if (u.getClass().getName().contains("Cliente")){
                        Cliente c1 = (Cliente) u;
                        int index = Data.getIndexUser(dbL, c1.getUser());

                        boolean salir = false;
                        while (!salir){
                            stock = Data.stockLoad();
                            dbL = Data.usuariosLoad(stock);
                            System.out.println("--- MENU Cliente ---\n" +
                                "Opciones:\n" +
                                "1. Consultar vehículos\n" +
                                "2. Revisar solicitudes de cotización\n" +
                                "3. Mantenimiento a mi vehiculo\n" +
                                "4. Regresar\n" +
                                "5. Salir");
                            System.out.print("->");String opc1 = sc.nextLine();
                            if("1".equals(opc1)){
                                c1.mostrarStock(stock);
                                if (!stock.isEmpty()){
                                    System.out.print("Desea realizar alguna cotización? (s/n) -->"); String re = sc.nextLine();
                                    if ("s".equals(re)){
                                        if(seleccionVehiculo(stock)){
                                            System.out.print("Seleccione cual Vehiculo cotizara? (Elija el codigo del vehiculo que desea)->"); String sel = sc.nextLine();
                                            Boolean vs = c1.enviarCotizacion(dbL, Data.getVehiculo(stock, sel));
                                            if(vs == true){
                                                Vehiculo veh = Data.getVehiculo(stock, sel);
                                                veh.setSolicitado(true);
                                                int i = stock.indexOf(veh);
                                                stock.set(i, veh);
                                                Data.updateStock(stock);
                                                Data.updateUsuarios(dbL);
                                                System.out.println("Su solicitud ha sido enviada al vendedor asignado," + "espere respuesta en subMenu Solicitudes.");
                                            }else{
                                                System.out.println("ERROR. NO HAY VENDEDOR DISPONIBLE.");
                                            }

                                        }
                                        System.out.println("Regresando...");

                                    }
                                }else{
                                    System.out.println("EL STOCK ESTA VACIO.");
                                }
                            } else if("2".equals(opc1)){
                                System.out.println("SOLICITUDES...");
                                boolean vehArreglado = false;
                                if (!c1.getSolicitud().isEmpty()){
                                    c1.mostrarSolicitud();
                                    List<ArrayList<String>> soli = c1.getSolicitud();
                                    System.out.print("Seleccione la solicitud que desea enviar: (Debe ser numero indicado al costado) -->"); int sel = sc.nextInt();
                                    if(soli.get(sel).get(0).contains("Aprobado")){
                                        String codVehiculo = soli.get(sel).get(0).split(", ")[1];
                                        Vehiculo veh = Data.getVehiculo(stock, codVehiculo);
                                        System.out.print("INFORMACION DEL VEHICULO COTIZADO\n");
                                        System.out.print(veh + "\n");
                                        Scanner sc1 = new Scanner(System.in);
                                        System.out.print("Desea enviar la solicitud de compra: s/n--> "); String resp = sc1.nextLine();
                                        if("s".equals(resp.toLowerCase())){
                                            boolean su = c1.enviarCompra(dbL, veh);
                                            if(su == true){
                                                Data.updateUsuarios(dbL);
                                                Data.updateStock(stock);
                                                System.out.println("Su solicitud ha sido enviada al supervisor asignado " + ", espere respuesta en subMenu Solicitudes.");
                                            }else{
                                                System.out.println("ERROR EN ENVIO. NO EXISTEN SUPERVISORES.");
                                            }
                                        }else{
                                            System.out.println("Compra cancelada.");
                                        }
                                    } else if (soli.get(sel).get(0).contains("Arreglado")){
                                        String codVehiculo = soli.get(sel).get(0).split(", ")[1];
                                        Vehiculo veh = Data.getVehiculo(stock, codVehiculo);
                                        boolean ts = c1.comprar(dbL, veh);
                                        if(ts == true){
                                            c1.setVehiculos(veh);
                                            if(index != -1){
                                                dbL.set(index, c1);
                                            }
                                            Data.updateStock(stock);
                                            Data.updateUsuarios(dbL);
                                            System.out.println("Su vehiculo ha sido arreglado, retirelo.");
                                            vehArreglado = true;
                                        }else{
                                            System.out.println("Su vehiculo aun no ha sido arreglado.");
                                        }
                                    } else if (soli.get(sel).get(0).contains("Comprado")){
                                        String codVehiculo = soli.get(sel).get(0).split(", ")[1];
                                        Vehiculo veh = Data.getVehiculo(stock, codVehiculo);
                                        boolean ts = c1.comprar(dbL, veh);
                                        if(ts == true){
                                            if(index != -1){
                                                dbL.set(index, c1);
                                            }
                                            Data.updateStock(stock);
                                            Data.updateUsuarios(dbL);
                                            System.out.println("Su solicitud ha sido enviada al Jefe de Taller asignado " + ", espere respuesta en subMenu Solicitudes.");
                                        }else{
                                            System.out.println("ERROR EN ENVIO. NO EXISTEN JEFES DE TALLER.");
                                        }
                                    } else if (soli.get(sel).get(0).contains("Listo")){
                                        String codVehiculo = soli.get(sel).get(0).split(", ")[1];
                                        Vehiculo veh = Data.getVehiculo(stock, codVehiculo);
                                        c1.setVehiculos(veh);
                                        if (index != -1){
                                            dbL.set(index, c1);
                                        }
                                        Data.updateStock(stock);
                                        Data.updateUsuarios(dbL);
                                    } else if(soli.get(sel).get(0).contains("Rechazado")){
                                        System.out.println("MOTIVO DE RECHAZO \n" + soli.get(sel).get(0).split(", ")[1]);
                                        if (index != -1){
                                            dbL.set(index, c1);
                                        }
                                        String cod = soli.get(sel).get(0).split(", ")[1].split("--")[1];
                                        Vehiculo veh = Data.getVehiculo(stock, cod);
                                        veh.setSolicitado(false);
                                        int indd = Data.getIndexVehiculo(stock, veh.getCodigo());
                                        
                                        if (indd != -1){
                                            stock.set(indd, veh);
                                        }
                                        Data.updateUsuarios(dbL);
                                        Data.updateStock(stock);
                                    }
                                    if(sel >= soli.size() || sel < 0){
                                        System.out.println("Ingrese correctamente el indice de la solicitud que desea...");
                                    }else if (!soli.get(sel).get(0).contains("Arreglado") || vehArreglado == true){
                                        c1.removeSolicitud(sel);
                                        if (index != -1){
                                            dbL.set(index, c1);
                                        }
                                        Data.updateUsuarios(dbL);
                                    }
                                    
                                } else {
                                    System.out.println("NECESITA HABER ENVIADO SU COTIZACION DE UN AUTO y ESPERAR A QUE SEA ATENDIDO");
                                }
                            } else if("3".equals(opc1)){
                                if (!c1.getVehiculos().isEmpty()){
                                    System.out.println("ENVIO DE SOLICITUD DE MANTENIMIENTO");
                                    int i = 0;
                                    for(Vehiculo v : c1.getVehiculos()){
                                        System.out.println(i + "-" + v);
                                        i++;
                                    }
                                    ArrayList<Vehiculo> vehs = c1.getVehiculos();
                                    System.out.print("Seleccione un vehiculo: ( escoga el indice ) --> "); String ind = sc.nextLine();
                                    System.out.print("TIPO DE MANTENIMIENTO que desea (P: Preventivo, E: emergencia) --> "); String le = sc.nextLine().toLowerCase();
                                    Vehiculo car = vehs.get(Integer.parseInt(ind));
                                    System.out.println("CUANTOS KILOMETROS HA RECORRIDO SU VEHICULO?: (ingrese numeros) --> "); String km = sc.nextLine();
                                    boolean t1 = c1.mantenimiento(dbL, car, le, km);
                                    if(t1 == true){
                                        Data.updateStock(stock);
                                        Data.updateUsuarios(dbL);
                                    }else{
                                        System.out.println("Vehiculo seleccionado no esta en el rango. seleccione correctamente...");
                                    }
                                }else{
                                    System.out.println("Se necesita que el Cliente haya Comprado un vehiculo en esta concesionaria");
                                }
                            } else if("4".equals(opc1)){
                                System.out.println("Regresando...");
                                salir = true;
                            } else if("5".equals(opc1)){
                                end = true;
                            }
                        }
                    }else if (u.getClass().getName().contains("Vendedor")){
                        boolean salir = false;
                        u = (Vendedor) u;
                        Vendedor v1 = (Vendedor) u;
                        int index = Data.getIndexUser(dbL, v1.getUser());
                        while (!salir){
                            sc = new Scanner(System.in);
                            stock = Data.stockLoad();
                            dbL = Data.usuariosLoad(stock);                            
                            System.out.println("--- MENU VENDEDOR ---\n" +
                                "Opciones:\n" +
                                "1. Consultar vehículos\n" +
                                "2. Revisar solicitudes de cotización\n" +
                                "3. Ingresar nuevos vehiculos\n" +    
                                "4. Regresar\n" +
                                "5. Salir");
                            System.out.print("-> ");String opc1 = sc.nextLine();
                            if("1".equals(opc1)){
                                System.out.println("LISTA DE VEHICULOS DISPONIBLES A LA VENTA.");
                                v1.mostrarStock(stock);
                            } else if("2".equals(opc1)){
                                if (!v1.getSolicitud().isEmpty()){
                                    System.out.println("SOLICITUDES...");
                                    v1.mostrarSolicitud();
                                    List<ArrayList<String>> soli = v1.getSolicitud();
                                    System.out.print("Seleccione la solicitud que desea enviar cotizacion: (Debe ser numero) --> "); int sel = sc.nextInt();
                                    if (soli.get(sel).get(0).contains("Solicitado")){
                                        String[] data = soli.get(sel).get(0).split(", ");
                                        System.out.println("Solicitud de Cotizacion. Procesando...");
                                        String codVehiculo = data[1];
                                        Vehiculo veh = Data.getVehiculo(stock, codVehiculo);
                                        if(v1.enviarCompra(dbL, soli.get(sel)) == false){
                                            veh.setSolicitado(false);
                                            int indd = Data.getIndexVehiculo(stock, veh.getCodigo());
                                            if (indd != -1){
                                                stock.set(indd, veh);
                                            }
                                            Data.updateStock(stock);
                                        }
                                        v1.removeSolicitud(sel);
                                        v1.setVentas(veh);
                                        if (index != -1){
                                            dbL.set(index, v1);
                                        }
                                        Data.updateUsuarios(dbL);
                                    }else{
                                        System.out.println("Seleccione correctamente la solicitud.");
                                    }
                                }else{
                                    System.out.println("No tiene solicitudes.");
                                }
                            } else if("3".equals(opc1)){
                                System.out.println("INGRESAR NUEVOS VEHICULOS AL STOCK DE LA CONCESIONARIA...");
                                Data.ingresarVehiculos(stock);
                            } else if("4".equals(opc1)){
                                System.out.println("Regresando...");
                                salir = true;
                            } else if("5".equals(opc1)){
                                end = true;
                            }
                                    
                        }
                    }else if (u.getClass().getName().contains("Supervisor")){
                        boolean salir = false;
                        Supervisor s1 = (Supervisor) u;
                        int index = Data.getIndexUser(dbL, s1.getUser());

                        while (!salir){
                            stock = Data.stockLoad();
                            dbL = Data.usuariosLoad(stock); 
                            System.out.println("--- MENU SUPERVISOR ---\n" +
                                "Opciones:\n" +
                                "1. Consultar vehículos del Stock.\n" +
                                "2. Revisar solicitudes de compra\n" +
                                "3. Ingresar nuevos vehiculos\n" +    
                                "4. Consultar y modificar detalles de empleados de concesionaria\n" +
                                "5. Regresar\n" +
                                "6. Salir");
                            System.out.print("->");String opc1 = sc.nextLine();
                            if("1".equals(opc1)){
                                System.out.println("LISTA DE VEHICULOS DISPONIBLES A LA VENTA.");
                                s1.mostrarStock(stock);
                            } else if("2".equals(opc1)){
                                System.out.println("SOLICITUDES...");
                                if (!s1.getSolicitud().isEmpty()){
                                    s1.mostrarSolicitud();
                                    List<ArrayList<String>> soli = s1.getSolicitud();
                                    System.out.print("Seleccione la solicitud que desea enviar cotizacion: (Debe ser numero) --> "); int sel = sc.nextInt();
                                    if (soli.get(sel).get(0).contains("Solicitado")){
                                        String codVehiculo = soli.get(sel).get(0).split(", ")[1];
                                        Vehiculo veh = Data.getVehiculo(stock, codVehiculo);
                                        Vendedor vt = s1.getVendedor(dbL, veh);
                                        if(s1.enviarCompra(dbL, soli.get(sel)) == false){
                                            veh.setSolicitado(false);
                                            int indd = Data.getIndexVehiculo(stock, veh.getCodigo());
                                            if (indd != -1){
                                                stock.set(indd, veh);
                                            }
                                            if (index != -1){
                                                dbL.set(index, vt);
                                            }
                                            Data.updateStock(stock);
                                        }
                                        s1.removeSolicitud(sel);
                                        if (index != -1){
                                            dbL.set(index, s1);
                                        }
                                        
                                        Data.updateUsuarios(dbL);

                                    }else{
                                        System.out.println("Seleccione correctamente la solicitud...");
                                    }
                                }else{
                                    System.out.println("No hay solicitudes para este supervisor");
                                }
                            } else if("3".equals(opc1)){
                                System.out.println("INGRESAR NUEVOS VEHICULOS AL STOCK DE LA CONCESIONARIA...");
                                Data.ingresarVehiculos(stock);
                            } else if("4".equals(opc1)){
                                System.out.println("EN MANTENIMIENTO...");
                                
                            } else if("5".equals(opc1)){
                                System.out.println("Regresando...");
                                salir = true;
                            } else if("6".equals(opc1)){
                                end = true;
                            }
                                    
                        }
                    }else if (u.getClass().getName().contains("JefeTaller")){
                        boolean salir = false;
                        JefeTaller t1 = (JefeTaller) u;
                        int index = Data.getIndexUser(dbL, t1.getUser());

                        while (!salir){
                            stock = Data.stockLoad();
                            dbL = Data.usuariosLoad(stock);
                            System.out.println("--- MENU JEFE DE TALLER ---\n" +
                                "Opciones:\n" +
                                "1. Consultar vehículos\n" +
                                "2. Revisar solicitudes de reparacion\n" +
                                "3. Ingresar nuevos vehiculos\n" +    
                                "4. Regresar\n" +
                                "5. Salir");
                            System.out.print("-> ");String opc1 = sc.nextLine();
                            if("1".equals(opc1)){
                                System.out.println("LISTA DE VEHICULOS DISPONIBLES A LA VENTA.");
                                t1.mostrarStock(stock);
                            } else if("2".equals(opc1)){
                                System.out.println("SOLICITUDES...");
                                if(!t1.getSolicitud().isEmpty()){
                                    List<ArrayList<String>> soli = t1.getSolicitud();
                                    t1.mostrarSolicitud();
                                    System.out.print("Seleccione la solicitud que desea atender: (Debe ser numero) --> "); int sel = sc.nextInt();
                                    if (soli.get(sel).get(0).contains("Solicitado")){
                                        String codVehiculo = soli.get(sel).get(0).split(", ")[1];
                                        Vehiculo veh = Data.getVehiculo(stock, codVehiculo);
                                        if(!t1.enviarCompra(dbL, soli.get(sel))){
                                            veh.setSolicitado(false);
                                            int indd = Data.getIndexVehiculo(stock, veh.getCodigo());
                                            if (indd != -1){
                                                stock.set(indd, veh);
                                            }
                                            Data.updateStock(stock);
                                        }
                                        t1.getPorEntregar().remove(veh);
                                        t1.removeSolicitud(sel);
                                        if (index != -1){
                                            dbL.set(index, t1);
                                        }

                                        Data.updateUsuarios(dbL);
                                    } else if(soli.get(sel).get(0).contains("Mantenimiento p")){
                                        String km = soli.get(sel).get(0).split(", ")[2].split(";")[1];
                                        int k = Integer.parseInt(km);
                                        String codVehiculo = soli.get(sel).get(0).split(", ")[1];
                                        Vehiculo veh = Data.getVehiculo(stock, codVehiculo);
                                        if(t1.arreglarVehiculo(dbL, soli.get(sel))){
                                            t1.getMantenimiento().remove(veh);
                                            t1.setPagos(k * 0.1);
                                            t1.removeSolicitud(sel);
                                            if (index != -1){
                                                dbL.set(index, t1);
                                            }
                                            
                                            Data.updateUsuarios(dbL);
                                        }else{
                                            System.out.println("No ha sido arreglado aun.");
                                        }
                                    } else if(soli.get(sel).get(0).contains("Mantenimiento e")){
                                        String km = soli.get(sel).get(0).split(", ")[2].split(";")[1];
                                        int k = Integer.parseInt(km);
                                        String codVehiculo = soli.get(sel).get(0).split(", ")[1];
                                        Vehiculo veh = Data.getVehiculo(stock, codVehiculo);
                                        if(t1.arreglarVehiculo(dbL, soli.get(sel))){
                                            t1.getMantenimiento().remove(veh);
                                            System.out.println("PRECIO POR MANTENIMIENTO: ");double precio = sc.nextDouble();
                                            t1.setPagos(k * precio);
                                            t1.removeSolicitud(sel);
                                            if (index != -1){
                                                dbL.set(index, t1);
                                            }
                                            Data.updateUsuarios(dbL);
                                        }else{
                                            System.out.println("No ha sido arreglado aun.");
                                        }
                                    }
                                    t1.removeSolicitud(sel);
                                    if (index != -1){
                                        dbL.set(index, t1);
                                    }
                                    Data.updateUsuarios(dbL);
                                }else{
                                    System.out.println("No hay solicitudes para este Jefe de taller.");
                                }
                            } else if("3".equals(opc1)){
                                System.out.println("INGRESAR NUEVOS VEHICULOS AL STOCK DE LA CONCESIONARIA...");
                                Data.ingresarVehiculos(stock);
                            } else if("4".equals(opc1)){
                                System.out.println("Regresando...");
                                salir = true;
                            } else if("5".equals(opc1)){
                                end = true;
                            }
                                    
                        }
                    }
                } else {
                    System.out.println("Usuario o contraseña incorrectos.");
                }
            } else if("2".equals(opc)){
                System.out.println("CREACIÓN DE CUENTA");
                System.out.println("Usuarios: " +
                "\n1. Cliente" +
                "\n2. Vendedor" +
                "\n3. Supervisor" +
                "\n4. Jefe de Taller");
                System.out.print("-> "); String opc1 = sc.nextLine();
                if ("1".equals(opc1)){
                    Data.crearUsuario("c");
                }else if("2".equals(opc1)){
                    Data.crearUsuario("v");
                }else if("3".equals(opc1)){
                    Data.crearUsuario("s");
                }else if("4".equals(opc1)){
                    Data.crearUsuario("t");
                }
                
            } else{
                System.out.println("Saliendo...");
                end = true;
            }
        }
    }
    public static Usuario login(String user, String passw, ArrayList<Usuario> usuarios){
        for (Usuario u:usuarios) {
            if (user.equals(u.getUser()) && passw.equals(u.getPassword())){
                return u;
            }
        }
        return null;

    }
    
    public static boolean seleccionVehiculo(ArrayList<Vehiculo> stock){
        Scanner sc = new Scanner(System.in);
        System.out.println("COTIZACION | Ingrese marca, modelo y año de fabricacion del vehiculo que le interese...");
        System.out.print("Marca: "); String ma = sc.nextLine();
        System.out.print("Modelo: "); String mo = sc.nextLine();
        System.out.print("Año de Fabricacion: "); String fab = sc.nextLine();
        System.out.println("\n\n- Vehiculos Disponibles -");
        System.out.println("Codigo  -  Marca   -   Modelo   -   Año de fabricación");
        ArrayList<Vehiculo> deseado = new ArrayList<>();
        if (stock != null){
            int i = 0;
            for(Vehiculo v: stock){
                if(!v.isSolicitado() && ma.equals(v.getMarca()) && mo.equals(v.getModelo()) && fab.equals(v.getAñoFab())){
                    System.out.println(v.getCodigo() + "  -  " +v.getMarca() + "  -  " + v.getModelo() + "  -  " + v.getAñoFab());
                    deseado.add(v);
                }
            }
        }else{
            System.out.println("No hay vehiculos disponibles");
            return false;
        }
        if (!deseado.isEmpty()){
            return true;
        }
        System.out.println("El vehiculo con marca, modelo y año de fabricacion especificados no esta disponible.");
        return false;
    }
}
