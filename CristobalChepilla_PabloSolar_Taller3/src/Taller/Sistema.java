package Taller;
import java.util.Scanner;

public interface Sistema {

    void cargarDatos(String archivoUsuarios, String archivoProyectos, String archivoTareas);

    void guardarDatos(String archivoProyectos, String archivoTareas);

    Usuario login(String user, String pass);

    void menuAdministrador(Usuario u, Scanner sc);

    void menuColaborador(Usuario u, Scanner sc);
}
