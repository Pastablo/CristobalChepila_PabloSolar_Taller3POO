package Taller2;

import java.util.ArrayList;

public class Puerto {
    private int numero;
    private String estado;
    private ArrayList<Vulnerabilidad> vulnerabilidades;

    public Puerto(int numero, String estado) {
        this.numero = numero;
        this.estado = estado;
        this.vulnerabilidades = new ArrayList<>();
    }

    public int getNumero() { return numero; }
    public String getEstado() { return estado; }
    public ArrayList<Vulnerabilidad> getVulnerabilidades() { return vulnerabilidades; }
    public void agregarVulnerabilidad(Vulnerabilidad v) { vulnerabilidades.add(v); }
    public boolean estaAbierto() { return "Abierto".equalsIgnoreCase(estado); }

    @Override
    public String toString() {
        return numero + ":" + estado;
    }
}
