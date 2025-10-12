package Taller2;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Reportes {
    public static void guardarEscaneo(String ruta, Usuario usuario, PC pc, String fechaHora) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true));
            StringBuilder sb = new StringBuilder();
            sb.append("PC=").append(pc.getId());
            sb.append("|IP=").append(pc.getIp());
            sb.append("|SO=").append(pc.getSo());
            sb.append("|PUERTOS=[");
            boolean primero = true;
            for (Puerto p : pc.getPuertos()) {
                if (!primero) sb.append(",");
                sb.append(p.getNumero()).append(":").append(p.getEstado());
                primero = false;
            }
            sb.append("]");
            sb.append("|USUARIO=").append(usuario.getNombre());
            sb.append("|NIVEL=").append(pc.nivelRiesgo());
            sb.append("|FECHA=").append(fechaHora);
            bw.write(sb.toString());
            bw.newLine();
            bw.close();
        } catch (Exception e) {}
    }
}

