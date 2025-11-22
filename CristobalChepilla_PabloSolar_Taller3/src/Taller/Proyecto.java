package Taller;
public class Proyecto {

    private String id;
    private String nombre;
    private String responsable;

    public Proyecto(String id, String nombre, String responsable) {
        this.id = id;
        this.nombre = nombre;
        this.responsable = responsable;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getResponsable() {
        return responsable;
    }

    public String toString() {
        return id + " | " + nombre + " | Resp: " + responsable;
    }
}
