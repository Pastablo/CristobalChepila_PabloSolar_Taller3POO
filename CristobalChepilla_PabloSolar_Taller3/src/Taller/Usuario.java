package Taller;
public class Usuario {

    private String usuario;
    private String password;
    private String rol;

    public Usuario(String usuario, String password, String rol) {
        this.usuario = usuario;
        this.password = password;
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }

    public String toString() {
        return usuario + " (" + rol + ")";
    }
}
