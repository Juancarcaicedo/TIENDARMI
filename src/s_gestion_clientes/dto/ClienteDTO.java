package s_gestion_clientes.dto;

import java.io.Serializable;

/**
 *
 * @author Juan r JESUS V
 */
public class ClienteDTO implements Serializable{
    private String nombreCompleto;
    private int puntos;
    private String tipo;
    private String usuario;
    private String clave;
    private String rol;

    public ClienteDTO() {
        super();
    }

    public ClienteDTO(String nombreCompleto, int puntos, String tipo, String usuario, String clave) {
        this.nombreCompleto = nombreCompleto;
        this.puntos = puntos;
        this.tipo = tipo;
        this.usuario = usuario;
        this.clave = clave;
    }

    public ClienteDTO(String username,String clave, String rol){
        this.usuario= username;
        this.clave=clave;
        this.rol=rol;
    }
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public int getPuntos() {
        return puntos;
    }
    
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
