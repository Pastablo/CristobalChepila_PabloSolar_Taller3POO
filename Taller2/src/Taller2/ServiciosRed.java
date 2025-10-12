package Taller2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ServiciosRed {
    public static String claseIP(String ip) {
        String[] p = ip.split("\\.");
        if (p.length < 4) return "C";
        int a = Integer.parseInt(p[0]);
        if (a >= 0 && a <= 127) return "A";
        if (a >= 128 && a <= 191) return "B";
        return "C";
    }

    public static ArrayList<PC> ordenarPCsPorClaseIP(ArrayList<PC> pcs) {
        ArrayList<PC> r = new ArrayList<>(pcs);
        Collections.sort(r, new Comparator<PC>() {
            public int compare(PC x, PC y) {
                String cx = claseIP(x.getIp());
                String cy = claseIP(y.getIp());
                if (!cx.equals(cy)) return cx.compareTo(cy);
                return x.getIp().compareTo(y.getIp());
            }
        });
        return r;
    }

    public static ArrayList<Puerto> puertosAbiertosDeTodaLaRed(ArrayList<PC> pcs) {
        ArrayList<Puerto> r = new ArrayList<>();
        for (PC pc : pcs) for (Puerto p : pc.getPuertos()) if (p.estaAbierto()) r.add(p);
        return r;
    }
}

