package Taller;
import java.util.ArrayList;

public class EstrategiaFecha implements EstrategiaOrden {

    public ArrayList<Tarea> ordenar(ArrayList<Tarea> lista) {

        ArrayList<Tarea> copia = new ArrayList<Tarea>();

        for (int i = 0; i < lista.size(); i++) {
            copia.add(lista.get(i));
        }

        // ordenamiento burbuja
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {

                String f1 = copia.get(j).getFecha();
                String f2 = copia.get(j + 1).getFecha();

                if (f1.compareTo(f2) > 0) {
                    Tarea aux = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, aux);
                }
            }
        }
        return copia;
    }
}
