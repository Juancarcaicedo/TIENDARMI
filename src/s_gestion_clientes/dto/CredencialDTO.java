package s_gestion_clientes.dto;

import java.io.Serializable;

/**
 *
 * @author juan R jesus V
 */
public class CredencialDTO implements Serializable{
    private String usuario;
    private String clave;
    private String rol;

    public CredencialDTO(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public CredencialDTO(String usuarioSesion, String claveSesion, String rol) {
        this.usuario= usuarioSesion;
        this.clave= claveSesion;
        this.rol=rol;
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
