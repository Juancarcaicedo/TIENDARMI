package s_gestion_clientes.sop_rmi;

import cliente.utilidades.UtilidadesRegistroC;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import s_gestion_clientes.dto.CredencialDTO;
import s_gestion_clientes.dto.ClienteDTO;
import s_gestion_tienda.sop_rmi.GestionProductosInt;

/**
 *
 * @author Juan Ruales Jesus V
 */
public class GestionClientesImpl extends UnicastRemoteObject implements GestionClientesInt{
    
    private ArrayList<ClienteDTO> clientes;
    private int contador = 0;
    private GestionProductosInt objReferenciaRemotaNotificacion;
    private int siAdmin = 0;
    
    public GestionClientesImpl() throws RemoteException
    {
        super();
        clientes = new ArrayList();
        ClienteDTO Juan = new ClienteDTO("juan", "1234", "user");
          ClienteDTO gustavo = new ClienteDTO("gustavo", "1234", "admin");
        clientes.add(Juan);
         clientes.add(gustavo);
    }
    
    @Override
    public boolean registrarUsuario(ClienteDTO objUsuario) throws RemoteException {
        System.out.println("Entrando a registrar usuario");
        boolean bandera=false;
        
        if(clientes.size() < 5)
        {
            
            bandera=clientes.add(objUsuario);
           // System.out.println("Usuario registrado: identificación: " + identificacion + ", nombres: " + nombres + ", apellidos. " +apellidos );
            System.out.println("Usuario Registrado"+objUsuario.getUsuario() + "Rol" + objUsuario.getRol());
        }
        return bandera;
    }
    
    @Override
    public ClienteDTO consultarUsuario(int id) throws RemoteException {
        return null;
    }

    @Override
    public ClienteDTO abrirSesion(CredencialDTO objCredencial) throws RemoteException {
        for (ClienteDTO cliente : clientes) {
            if (cliente.getUsuario().equals(objCredencial.getUsuario()) && cliente.getClave().equals(objCredencial.getClave())) return cliente;
        }
        return null;
    }
    
    public void consultarReferenciaRemota(String direccionIpRMIRegistry, int numPuertoRMIRegistry){
        System.out.println(" ");
        System.out.println("Desde consultarReferenciaRemota()...");
        this.objReferenciaRemotaNotificacion = (GestionProductosInt) UtilidadesRegistroC.obtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry, "ObletoRemotoNotificacion");
    }

    @Override
    public List<ClienteDTO> listarUsuariosUser() throws RemoteException {
        List<ClienteDTO> users = new ArrayList<ClienteDTO>();
        for (ClienteDTO cliente : clientes) {
            if(cliente.getRol() == "user") users.add(cliente);
        }
        return users;
    }

    @Override
    public boolean actualizarUsuario(ClienteDTO objUsuario) throws RemoteException {
        int cont = 0;
        for (ClienteDTO cliente : clientes) {
            if (cliente.getUsuario().equals(objUsuario.getUsuario())) {
                cont++;
                break;
            }
        }
        clientes.get(cont).setPuntos(objUsuario.getPuntos());
        return true;
    }
}
