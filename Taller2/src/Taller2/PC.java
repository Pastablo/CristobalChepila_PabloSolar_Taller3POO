package Taller2;

import java.util.ArrayList;

public class PC {
    private String id;
    private String ip;
    private String so;
    private ArrayList<Puerto> puertos;

    public PC(String id, String ip, String so) {
        this.id = id;
        this.ip = ip;
        this.so = so;
        this.puertos = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getIp() { return ip; }
    public String getSo() { return so; }
    public ArrayList<Puerto> getPuertos() { return puertos; }

    public void agregarPuerto(Puerto p) { puertos.add(p); }

    public ArrayList<Puerto> puertosAbiertos() {
        ArrayList<Puerto> r = new ArrayList<>();
        for (Puerto p : puertos) if (p.estaAbierto()) r.add(p);
        return r;
    }

    public int totalVulnerabilidadesAbiertas() {
        int c = 0;
        for (Puerto p : puertos) if (p.estaAbierto()) c += p.getVulnerabilidades().size();
        return c;
    }

    public String nivelRiesgo() {
        int v = totalVulnerabilidadesAbiertas();
        if (v >= 3) return "Alto";
        if (v >= 1 && v <= 2) return "Medio";
        return "Bajo";
    }

    @Override
    public String toString() {
        return id + " | " + ip + " | " + so;
    }
}
