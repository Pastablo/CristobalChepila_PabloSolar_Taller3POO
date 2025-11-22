package Taller;
public class VisitadorTarea {

    public void visitar(Tarea t) {

        System.out.println("Visitando tarea: " + t.getId());

        if (t.getTipo().equals("Bug")) {
            System.out.println(" -> Es un BUG. Revisión urgente.");
        }
        else if (t.getTipo().equals("Feature")) {
            System.out.println(" -> Es una NUEVA FUNCIÓN. Revisar viabilidad.");
        }
        else {
            System.out.println(" -> Es DOCUMENTACIÓN. Revisar redacción.");
        }
    }
}
