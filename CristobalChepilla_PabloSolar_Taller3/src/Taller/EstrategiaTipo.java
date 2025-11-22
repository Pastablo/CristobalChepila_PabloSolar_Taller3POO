package Taller;
import java.util.ArrayList;

public class EstrategiaTipo implements EstrategiaOrden {

    public int valorTipo(String tipo) {
        if (tipo.equals("Bug")) return 1;
        if (tipo.equals("Feature")) return 2;
        return 3;
    }

    public ArrayList<Tarea> ordenar(ArrayList<Tarea> lista) {

        ArrayList<Tarea> copia = new ArrayList<Tarea>();
        for (int i = 0; i < lista.size(); i++) copia.add(lista.get(i));

        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - 1 - i; j++) {

                int v1 = valorTipo(copia.get(j).getTipo());
                int v2 = valorTipo(copia.get(j + 1).getTipo());

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
