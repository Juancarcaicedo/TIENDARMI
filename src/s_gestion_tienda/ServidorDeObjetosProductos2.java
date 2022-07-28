package s_gestion_tienda;


import cliente.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import s_gestion_clientes.sop_rmi.GestionClientesImpl;
import s_gestion_clientes.utilidades.UtilidadesRegistroS;
import s_gestion_tienda.sop_rmi.GestionProductosImpl;

public class ServidorDeObjetosProductos2
{
    public static void main(String args[]) throws RemoteException
    {
        
         
        int numPuertoRMIRegistry = 0;
        String direccionIpRMIRegistry = "";
        
               
        System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
        direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
        System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
        numPuertoRMIRegistry = UtilidadesConsola.leerEntero(); 
     
        GestionProductosImpl objRemoto = new GestionProductosImpl();
        
        try
        {
           UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
           UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoProductos");            
           
        } catch (Exception e)
        {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" +  e.getMessage());
        }
        
        
    }
}
