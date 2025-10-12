package Taller2;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class RepositorioDatos {
    public static ArrayList<Usuario> cargarUsuarios(String ruta) {
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(ruta));
            while (sc.hasNextLine()) {
                String l = sc.nextLine().trim();
                if (l.isEmpty()) continue;
                String[] p = l.split(";");
                if (p.length >= 3) lista.add(new Usuario(p[0], p[1], p[2]));
            }
            sc.close();
        } catch (Exception e) {}
        return lista;
    }

    public static ArrayList<PC> cargarPCs(String ruta) {
        ArrayList<PC> pcs = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(ruta));
            while (sc.hasNextLine()) {
                String l = sc.nextLine().trim();
                if (l.isEmpty()) continue;
                String[] p = l.split("\\|");
                if (p.length >= 3) pcs.add(new PC(p[0], p[1], p[2]));
            }
            sc.close();
        } catch (Exception e) {}
        return pcs;
    }

    public static void cargarPuertos(String ruta, ArrayList<PC> pcs) {
        try {
            Scanner sc = new Scanner(new File(ruta));
            while (sc.hasNextLine()) {
                String l = sc.nextLine().trim();
                if (l.isEmpty()) continue;
                String[] p = l.split("\\|");
                if (p.length >= 3) {
                    String id = p[0];
                    int numero = Integer.parseInt(p[1]);
                    String estado = p[2];
                    PC pc = buscarPC(pcs, id);
                    if (pc != null) pc.agregarPuerto(new Puerto(numero, estado));
                }
            }
            sc.close();
        } catch (Exception e) {}
    }

    public static void cargarVulnerabilidades(String ruta, ArrayList<PC> pcs) {
        try {
            Scanner sc = new Scanner(new File(ruta));
            while (sc.hasNextLine()) {
                String l = sc.nextLine().trim();
                if (l.isEmpty()) continue;
                String[] p = l.split("\\|");
                if (p.length >= 3) {
                    int puerto = Integer.parseInt(p[0]);
                    Vulnerabilidad v = new Vulnerabilidad(puerto, p[1], p[2]);
                    for (PC pc : pcs) for (Puerto pu : pc.getPuertos()) if (pu.getNumero() == puerto) pu.agregarVulnerabilidad(v);
                }
            }
            sc.close();
        } catch (Exception e) {}
    }

    public static PC buscarPC(ArrayList<PC> pcs, String id) {
        for (PC pc : pcs) if (pc.getId().equals(id)) return pc;
        return null;
    }
}

