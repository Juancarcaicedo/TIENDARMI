package s_gestion_clientes.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import s_gestion_clientes.dto.CredencialDTO;
import s_gestion_clientes.dto.ClienteDTO;

/**
 *
 * @author Juan R Jesus V
 */
public interface GestionClientesInt extends Remote{
    public boolean registrarUsuario(ClienteDTO objUsuario) throws RemoteException;
    public boolean actualizarUsuario(ClienteDTO objUsuario) throws RemoteException;
    public ClienteDTO consultarUsuario(int id) throws RemoteException;
    public ClienteDTO abrirSesion(CredencialDTO objCredencial) throws RemoteException;
    public List<ClienteDTO> listarUsuariosUser() throws RemoteException;
}
