package Taller;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Sistema sistema = new SistemaImpl();
        sistema.cargarDatos("usuarios.txt", "proyectos.txt", "tareas.txt");

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Sistema TaskForge ===");
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        Usuario u = sistema.login(usuario, pass);

        if (u == null) {
            System.out.println("Credenciales inválidas.");
            return;
        }

        if (u.getRol().equals("Administrador")) {
            sistema.menuAdministrador(u, sc);
        } else {
            sistema.menuColaborador(u, sc);
        }

        sistema.guardarDatos("proyectos.txt", "tareas.txt");

        System.out.println("Datos guardados. Adiós.");
    }
}
