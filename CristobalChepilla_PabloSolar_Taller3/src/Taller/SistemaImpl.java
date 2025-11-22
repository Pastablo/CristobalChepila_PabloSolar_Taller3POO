package Taller;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaImpl implements Sistema {

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Proyecto> proyectos = new ArrayList<>();
    private ArrayList<Tarea> tareas = new ArrayList<>();

    private Archivo archivo = new Archivo();

    public void cargarDatos(String archivoUsuarios, String archivoProyectos, String archivoTareas) {
        usuarios = archivo.cargarUsuarios(archivoUsuarios);
        proyectos = archivo.cargarProyectos(archivoProyectos);
        tareas = archivo.cargarTareas(archivoTareas);
    }

    public void guardarDatos(String archivoProyectos, String archivoTareas) {
        archivo.guardarProyectos(archivoProyectos, proyectos);
        archivo.guardarTareas(archivoTareas, tareas);
    }

    public Usuario login(String user, String pass) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            if (u.getUsuario().equals(user) && u.getPassword().equals(pass)) {
                return u;
            }
        }
        return null;
    }

    // ================= ADMINISTRADOR ===================
    public void menuAdministrador(Usuario u, Scanner sc) {

        String opcion = "";

        while (!opcion.equals("0")) {
            System.out.println("\n--- Menú Administrador ---");
            System.out.println("1) Ver proyectos y tareas");
            System.out.println("2) Agregar proyecto");
            System.out.println("3) Eliminar proyecto");
            System.out.println("4) Agregar tarea");
            System.out.println("5) Eliminar tarea");
            System.out.println("6) Ordenar tareas (Strategy)");
            System.out.println("7) Generar reporte");
            System.out.println("0) Salir");
            opcion = sc.nextLine();

            if (opcion.equals("1")) mostrarProyectos();
            else if (opcion.equals("2")) agregarProyecto(sc);
            else if (opcion.equals("3")) eliminarProyecto(sc);
            else if (opcion.equals("4")) agregarTarea(sc);
            else if (opcion.equals("5")) eliminarTarea(sc);
            else if (opcion.equals("6")) ordenarTareas(sc);
            else if (opcion.equals("7")) generarReporte();
        }
    }

    // ================= COLABORADOR ===================
    public void menuColaborador(Usuario u, Scanner sc) {

        String opcion = "";

        while (!opcion.equals("0")) {
            System.out.println("\n--- Menú Colaborador ---");
            System.out.println("1) Ver mis tareas");
            System.out.println("2) Cambiar estado tarea");
            System.out.println("3) Aplicar visitor");
            System.out.println("0) Salir");
            opcion = sc.nextLine();

            if (opcion.equals("1")) verMisTareas(u);
            else if (opcion.equals("2")) cambiarEstado(sc, u);
            else if (opcion.equals("3")) aplicarVisitor(u);
        }
    }

    // ================= FUNCIONES ADMIN ===================

    private void mostrarProyectos() {
        System.out.println("\n--- Proyectos ---");

        for (int i = 0; i < proyectos.size(); i++) {
            Proyecto p = proyectos.get(i);
            System.out.println(p.getId() + " - " + p.getNombre());

            for (int j = 0; j < tareas.size(); j++) {
                Tarea t = tareas.get(j);
                if (t.getIdProyecto().equals(p.getId())) {
                    System.out.println("   * " + t.getId() + " - " + t.getDescripcion());
                }
            }
        }
    }

    private void agregarProyecto(Scanner sc) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Responsable: ");
        String resp = sc.nextLine();

        String id = "PR00" + (proyectos.size() + 1);

        Proyecto p = new Proyecto(id, nombre, resp);
        proyectos.add(p);

        System.out.println("Proyecto creado.");
    }

    private void eliminarProyecto(Scanner sc) {
        System.out.print("ID proyecto: ");
        String id = sc.nextLine();

        // eliminar proyecto
        for (int i = 0; i < proyectos.size(); i++) {
            if (proyectos.get(i).getId().equals(id)) {
                proyectos.remove(i);
                break;
            }
        }

        // eliminar tareas asociadas
        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getIdProyecto().equals(id)) {
                tareas.remove(i);
                i--;
            }
        }

        System.out.println("Proyecto y tareas eliminadas.");
    }

    private void agregarTarea(Scanner sc) {
        System.out.print("ID Proyecto: ");
        String p = sc.nextLine();

        System.out.print("Tipo: ");
        String tipo = sc.nextLine();

        System.out.print("Descripción: ");
        String desc = sc.nextLine();

        System.out.print("Estado: ");
        String estado = sc.nextLine();

        System.out.print("Responsable: ");
        String resp = sc.nextLine();

        System.out.print("Complejidad: ");
        String comp = sc.nextLine();

        System.out.print("Fecha (YYYY-MM-DD): ");
        String fecha = sc.nextLine();

        String id = "T00" + (tareas.size() + 1);

        Tarea t = new Tarea(p, id, tipo, desc, estado, resp, comp, fecha);

        tareas.add(t);
        System.out.println("Tarea agregada.");
    }

    private void eliminarTarea(Scanner sc) {
        System.out.print("ID tarea: ");
        String id = sc.nextLine();

        for (int i = 0; i < tareas.size(); i++) {
            if (tareas.get(i).getId().equals(id)) {
                tareas.remove(i);
                break;
            }
        }

        System.out.println("Tarea eliminada.");
    }

    private void ordenarTareas(Scanner sc) {

        System.out.println("1) Por fecha");
        System.out.println("2) Por tipo");
        System.out.println("3) Por complejidad");

        String op = sc.nextLine();

        EstrategiaOrden estrategia = null;

        if (op.equals("1")) estrategia = new EstrategiaFecha();
        if (op.equals("2")) estrategia = new EstrategiaTipo();
        if (op.equals("3")) estrategia = new EstrategiaComplejidad();

        if (estrategia != null) {
            ArrayList<Tarea> ordenadas = estrategia.ordenar(tareas);
            System.out.println("--- Ordenadas ---");
            for (int i = 0; i < ordenadas.size(); i++) {
                System.out.println(ordenadas.get(i));
            }
        }
    }

    private void generarReporte() {
        archivo.guardarReporte("reporte.txt", proyectos, tareas);
        System.out.println("Reporte generado.");
    }

    // ================= FUNCIONES COLABORADOR ===================

    private void verMisTareas(Usuario u) {
        System.out.println("\n--- Mis tareas ---");

        for (int i = 0; i < tareas.size(); i++) {
            Tarea t = tareas.get(i);
            if (t.getResponsable().equals(u.getUsuario())) {
                System.out.println(t);
            }
        }
    }

    private void cambiarEstado(Scanner sc, Usuario u) {
        System.out.print("ID tarea: ");
        String id = sc.nextLine();

        for (int i = 0; i < tareas.size(); i++) {
            Tarea t = tareas.get(i);
            if (t.getId().equals(id) && t.getResponsable().equals(u.getUsuario())) {
                System.out.print("Nuevo estado: ");
                String estado = sc.nextLine();
                t.setEstado(estado);
                System.out.println("Estado actualizado.");
                return;
            }
        }

        System.out.println("No existe o no eres responsable.");
    }

    private void aplicarVisitor(Usuario u) {

        VisitadorTarea visitador = new VisitadorTarea();

        for (int i = 0; i < tareas.size(); i++) {
            Tarea t = tareas.get(i);
            if (t.getResponsable().equals(u.getUsuario())) {
                visitador.visitar(t);
            }
        }
    }
}
