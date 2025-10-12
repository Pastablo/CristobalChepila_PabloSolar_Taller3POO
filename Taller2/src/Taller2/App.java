package Taller2;

//Cristobal Chepilla 21.873.055-8 I.T.I
//Pablo Solar 21.590.002-9 I.T.I

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String rutaUsuarios = "usuarios.txt";
        String rutaPCs = "pcs.txt";
        String rutaPuertos = "puertos.txt";
        String rutaVulnerabilidades = "vulnerabilidades.txt";

        ArrayList<Usuario> usuarios = RepositorioDatos.cargarUsuarios(rutaUsuarios);
        ArrayList<PC> pcs = RepositorioDatos.cargarPCs(rutaPCs);
        RepositorioDatos.cargarPuertos(rutaPuertos, pcs);
        RepositorioDatos.cargarVulnerabilidades(rutaVulnerabilidades, pcs);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Usuario: ");
            String nombre = sc.nextLine();
            System.out.print("Clave: ");
            String clave = sc.nextLine();
            Usuario u = Autenticacion.iniciarSesion(usuarios, nombre, clave);
            if (u == null) {
                System.out.println("Credenciales invalidas");
                continue;
            }
            if ("ADMIN".equalsIgnoreCase(u.getRol())) {
                ConsolaMenus.menuAdmin(sc, pcs, usuarios, rutaUsuarios);
            } else {
                ConsolaMenus.menuUsuario(sc, pcs, u);
            }
        }
    }
}

