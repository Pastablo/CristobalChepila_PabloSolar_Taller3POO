package Taller;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Archivo {

    public ArrayList<Usuario> cargarUsuarios(String nombre) {

        ArrayList<Usuario> lista = new ArrayList<Usuario>();

        try {
            Scanner sc = new Scanner(new File(nombre));

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();

                String[] p = linea.split("\\|");

                if (p.length >= 3) {
                    Usuario u = new Usuario(p[0], p[1], p[2]);
                    lista.add(u);
                }
            }

            sc.close();

        } catch (Exception e) {
            System.out.println("Error leyendo usuarios.");
        }

        return lista;
    }

    public ArrayList<Proyecto> cargarProyectos(String nombre) {

        ArrayList<Proyecto> lista = new ArrayList<Proyecto>();

        try {
            Scanner sc = new Scanner(new File(nombre));

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] p = linea.split("\\|");

                if (p.length >= 3) {
                    Proyecto pro = new Proyecto(p[0], p[1], p[2]);
                    lista.add(pro);
                }
            }
            sc.close();

        } catch (Exception e) {
            System.out.println("Error leyendo proyectos.");
        }

        return lista;
    }

    public ArrayList<Tarea> cargarTareas(String nombre) {

        ArrayList<Tarea> lista = new ArrayList<Tarea>();

        try {
            Scanner sc = new Scanner(new File(nombre));

            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] p = linea.split("\\|");

                if (p.length >= 8) {
                    Tarea t = new Tarea(
                        p[0], p[1], p[2], p[3],
                        p[4], p[5], p[6], p[7]
                    );
                    lista.add(t);
                }
            }

            sc.close();

        } catch (Exception e) {
            System.out.println("Error leyendo tareas.");
        }

        return lista;
    }

    public void guardarProyectos(String nombre, ArrayList<Proyecto> lista) {

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(nombre));

            for (int i = 0; i < lista.size(); i++) {
                Proyecto p = lista.get(i);
                pw.println(p.getId() + "|" + p.getNombre() + "|" + p.getResponsable());
            }

            pw.close();

        } catch (Exception e) {
            System.out.println("No se pudo guardar proyectos.");
        }
    }

    public void guardarTareas(String nombre, ArrayList<Tarea> lista) {

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(nombre));

            for (int i = 0; i < lista.size(); i++) {
                Tarea t = lista.get(i);
                pw.println(
                    t.getIdProyecto() + "|" + t.getId() + "|" +
                    t.getTipo() + "|" + t.getDescripcion() + "|" +
                    t.getEstado() + "|" + t.getResponsable() + "|" +
                    t.getComplejidad() + "|" + t.getFecha()
                );
            }

            pw.close();

        } catch (Exception e) {
            System.out.println("No se pudo guardar tareas.");
        }
    }

    public void guardarReporte(String nombre, ArrayList<Proyecto> proyectos, ArrayList<Tarea> tareas) {

        try {
            PrintWriter pw = new PrintWriter(new FileWriter(nombre));

            pw.println("==== REPORTE DE PROYECTOS ====");

            for (int i = 0; i < proyectos.size(); i++) {

                Proyecto p = proyectos.get(i);
                pw.println("\n" + p);

                for (int j = 0; j < tareas.size(); j++) {
                    Tarea t = tareas.get(j);

                    if (t.getIdProyecto().equals(p.getId())) {
                        pw.println("   * " + t);
                    }
                }
            }

            pw.close();

        } catch (Exception e) {
            System.out.println("No se pudo generar reporte.");
        }
    }
}
