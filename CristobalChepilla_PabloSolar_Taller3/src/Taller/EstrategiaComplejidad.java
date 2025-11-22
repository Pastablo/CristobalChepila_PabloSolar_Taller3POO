package Taller;
import java.util.ArrayList;

public class EstrategiaComplejidad implements EstrategiaOrden {

    public int valorComp(String c) {
        if (c.equals("Alta")) return 1;
        if (c.equals("Media")) return 2;
        return 3;
    }

    public ArrayList<Tarea> ordenar(ArrayList<Tarea> lista) {

        ArrayList<Tarea> copia = new ArrayList<Tarea>();
        for (int i = 0; i < lista.size(); i++) copia.add(lista.get(i));

        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {

                int v1 = valorComp(copia.get(j).getComplejidad());
                int v2 = valorComp(copia.get(j + 1).getComplejidad());

                if (v1 > v2) {
                    Tarea aux = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, aux);
                }
            }
        }
        return copia;
    }
}
