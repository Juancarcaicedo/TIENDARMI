package s_gestion_tienda.dto;

import java.io.Serializable;

/**
 *
 * @author Juan R Jesus V
 */
public class ProductosDTO implements Serializable{
    private Integer Id;
    private String nombre;
    private int Precio;
    private int puntos;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public ProductosDTO(Integer Id, String nombre, int Precio, int puntos) {
        this.Id = Id;
        this.nombre = nombre;
        this.Precio = Precio;
        this.puntos = puntos;
    }
    
    


}