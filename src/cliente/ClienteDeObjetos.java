package cliente;

import cliente.utilidades.UtilidadesConsola;
import cliente.utilidades.UtilidadesRegistroC;
import static java.rmi.Naming.list;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import s_gestion_clientes.dto.ClienteDTO;
import s_gestion_clientes.dto.CredencialDTO;
import s_gestion_clientes.sop_rmi.GestionClientesInt;
import s_gestion_tienda.dto.ProductosDTO;
import s_gestion_tienda.sop_rmi.GestionProductosImpl;
import s_gestion_tienda.sop_rmi.GestionProductosInt;

/**
 *
 * @author Juan R Jesus V
 */
public class ClienteDeObjetos {
    /**
     * @param args the command line arguments
     */
    private static GestionClientesInt objRemoto;
    private static GestionProductosInt objRemoto2;
    private static ClienteDTO cliente;
    
    private static List<ProductosDTO> producto;
    private static ProductosDTO[] productocompras;

    public static void main(String[] args) throws RemoteException {
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";

        System.out.println("Cual es la direccion ip donde se encuentra el rmiregistry: ");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el numero de puerto por el cual escucha el rmiregistry: ");
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero();

        objRemoto = (GestionClientesInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoClientes");
        objRemoto2 = (GestionProductosInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoProductos");
        MenuPrincipal();
    }

    private static void MenuPrincipal() throws RemoteException {
        int opcion = 0;
        do {
            System.out.println("==Menu==");
            System.out.println("1. Iniciar Sesion");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1:
                    IniciarSesion();
                    break;
                case 2:
                    RegistrarUsuario();
                    break;
                case 3:
                    System.out.println("Salir...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 3);
    }

    public static void IniciarSesion() throws RemoteException {
        CredencialDTO usuario = null;
        String usuarioSesion, claveSesion;
        System.out.println("==Iniciar Sesión==");
        System.out.println("Ingrese usuario: ");
        usuarioSesion = UtilidadesConsola.leerCadena();
        System.out.println("Ingrese clave: ");
        claveSesion = UtilidadesConsola.leerCadena();
        usuario = new CredencialDTO(usuarioSesion, claveSesion);
        cliente = objRemoto.abrirSesion(usuario);
        if (cliente != null) {
            System.out.println("===Sesion Iniciada===");
            System.out.println("su rol es " + cliente.getRol());

            if (cliente.getRol().equals("admin")) {
                MenuAdmin();
            } else {
                MenuUsuario();
            }

        } else {
            System.out.println("no existe");
        }
    }

    public static void RegistrarUsuario() throws RemoteException {
        ClienteDTO usuario = null;
        String usuarioSesion, claveSesion, rol;
        System.out.println("==REGISTRARSE==");
        System.out.println("Ingrese usuario: ");
        usuarioSesion = UtilidadesConsola.leerCadena();
        System.out.println("Ingrese clave: ");
        claveSesion = UtilidadesConsola.leerCadena();
        System.out.println("Ingrese ROL: ");
        rol = UtilidadesConsola.leerCadena();

        usuario = new ClienteDTO(usuarioSesion, claveSesion, rol);
        objRemoto.registrarUsuario(usuario);
        boolean valor = objRemoto.registrarUsuario(usuario);
        if (valor) {
            System.out.println("Registro realizado satisfactoriamente...");
        } else {
            System.out.println("no se pudo realizar el registro...");
        }

    }

    public static void MenuAdmin() throws RemoteException {

        int opcion = 0;
        do {
            System.out.println("==Menu ADMIN ==");
            System.out.println("1. redimir puntos");
            System.out.println("2. MODIFICAR PUNTOS ");
            System.out.println("3. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1:
                    int puntosredimidos;
                    for (ClienteDTO usuario: objRemoto.listarUsuariosUser()) {
                        System.out.println("puntos de usuario"+usuario.getPuntos());
                        puntosredimidos=usuario.getPuntos();
                        puntosredimidos=puntosredimidos/10*5000;
                        System.out.println("total saldo"+puntosredimidos);
                    }
                       
                    break;
                case 2:
                    int pos;
                    int salir;
                    int puntosmod;
                        producto = objRemoto2.GenerarProductos(); 
                        for (ProductosDTO product : producto) {
                            System.out.println("***Producto con id***" + product.getId() + "***Producto Nombre***" + product.getNombre() + "***Precio***" + product.getPrecio() + "  Puntos  " + product.getPuntos());
                        }
                        System.out.println("****ELIJA LA ID DEL PRODUCTO A Modificar**** ");
                        pos = UtilidadesConsola.leerEntero();
                        System.out.println("****ELIJA la cantidad de puntos a modficar**** ");
                        puntosmod = UtilidadesConsola.leerEntero();
                        producto.get(pos-1).setPuntos(puntosmod);
                        objRemoto2.actualizarProducto(pos-1, producto.get(pos-1));
                        
                        for (ProductosDTO producto : producto) {
                            System.out.println("producto modificados"+producto.getPuntos());
                    }

                    break;
                case 3:
                    System.out.println("Salir...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 3);

    }

    public static void MenuUsuario() throws RemoteException {
        int opcion = 0;
        int valorId = 0;
        int idCompra;
        int total = 0;
        int totalD;
        int descuento;
        int puntosprecio;
        productocompras = new ProductosDTO[6];
        do {
            System.out.println("==BIENVENIDO A TU TIENDA  ==");
            System.out.println("1. COMPRAR ");
            System.out.println("2. Generar Factura ");
             System.out.println("3. Consultar valor puntos ");
            System.out.println("4. Salir");

            opcion = UtilidadesConsola.leerEntero();

            switch (opcion) {
                case 1:
                    do {
                        for (ProductosDTO producto : objRemoto2.GenerarProductos()) {
                            System.out.println("***Producto con id***" + producto.getId() + "***Producto Nombre***" + producto.getNombre() + "***Precio***" + producto.getPrecio() + "  Puntos  " + producto.getPuntos());
                        }
                        System.out.println("****ELIJA LA ID DEL PRODUCTO A COMPRAR  PRODUCTO**** ");
                        valorId = UtilidadesConsola.leerEntero();
                        obtenerlistaCompras(valorId);
                        System.out.println("****Compra EXITOSA SE AGREGO EL PRODUCTO A SU FACTURA*** ");
                        System.out.println("DESEA COMPRAR OTRO PRODUCTO 1 SI 2 PARA NO");
                        idCompra = UtilidadesConsola.leerEntero();
                    } while (idCompra != 2);
                    break;
                case 2:
                    
                    for (int i = 0; i < productocompras.length; i++) {
                        if (productocompras[i] != null) {
                            System.out.println("producto" + productocompras[i].getNombre());
                            total=productocompras[i].getPrecio()+total;
                            cliente.setPuntos(cliente.getPuntos() + productocompras[i].getPuntos());
                            
                        }
                    }
                    
                    objRemoto.actualizarUsuario(cliente);
                    if(productocompras.length>3){
                        descuento= (int)(Math.random()*20);
                        totalD=total - total* descuento/100;
                        System.out.println(" Valor Total de los productos "+ total+"**se genero un descuento de**"+descuento+"%");
                        System.out.println("***** TOTAL A PAGAR****"+totalD);
                        System.out.println("TOTAL PUNTOS ADQUIRIDOS" + cliente.getPuntos());
                    }else{
                    System.out.println("TOTAL A PAGAR $ " + total);
                    System.out.println("Total Puntos Adquiridos"+ cliente.getPuntos());
                    }
                    break;
                case 3:
                    System.out.println("***estos son sus puntos****"+cliente.getPuntos());
                    puntosprecio=cliente.getPuntos()/10*5000;
                    System.out.println("sus puntos equivalen a $ "+ puntosprecio+"pesos");
                case 4:
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }

        } while (opcion != 4);

    }

    private static void obtenerlistaCompras(int valorId) throws RemoteException {
        int cont = 0;
        for (ProductosDTO producto : objRemoto2.GenerarProductos()) {
            if (producto.getId() == valorId) {
                if (productocompras[cont] == null) {
                    productocompras[cont] = producto;
                    System.out.println("producto escogido" + productocompras[cont].getNombre());
                    break;
                }
            }
            cont++;
        }
    }
    
     
    
}

     
