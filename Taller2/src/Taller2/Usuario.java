package Taller2;

public class Usuario {
    private String nombre;
    private String hash;
    private String rol;

    public Usuario(String nombre, String hash, String rol) {
        this.nombre = nombre;
        this.hash = hash;
        this.rol = rol;
    }

    public String getNombre() { return nombre; }
    public String getHash() { return hash; }
    public String getRol() { return rol; }
}


