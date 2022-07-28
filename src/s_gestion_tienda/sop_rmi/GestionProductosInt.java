package s_gestion_tienda.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import s_gestion_tienda.dto.ProductosDTO;

/**
 *
 * @author Juan R Jesus V
 */
public interface GestionProductosInt extends Remote{
    public ArrayList<ProductosDTO> GenerarProductos() throws RemoteException;
    public boolean actualizarProducto(int posicion, ProductosDTO objProducto) throws RemoteException;
}
