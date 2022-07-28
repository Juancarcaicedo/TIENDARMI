package s_gestion_tienda.sop_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import s_gestion_tienda.dto.ProductosDTO;

/**
 *
 * @author Juan R jesus V
 */
public class GestionProductosImpl extends UnicastRemoteObject implements GestionProductosInt{
  private ArrayList<ProductosDTO> Productos;
    public GestionProductosImpl() throws RemoteException {
        
         super();
        Productos = new ArrayList();
        ProductosDTO producto = new ProductosDTO(1,"TELEVISOR",50000,100);
        ProductosDTO producto2 = new ProductosDTO(2,"PLANCHA",100,10);
        ProductosDTO producto3 = new ProductosDTO(3,"CELULAR",1000,50);
        ProductosDTO producto4 = new ProductosDTO(4,"LAPTOT",600000,120);
        Productos.add(producto);
        Productos.add(producto2);
        Productos.add(producto3);
        Productos.add(producto4);
         
    }
    

    @Override
    public ArrayList<ProductosDTO> GenerarProductos() throws RemoteException {
        return Productos ;
    }

    @Override
    public boolean actualizarProducto(int posicion, ProductosDTO objProducto) throws RemoteException {
        Productos.get(posicion).setPuntos(objProducto.getPuntos());
        return true;
    }
    
}
