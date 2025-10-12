package Taller2;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.ArrayList;

public class Autenticacion {
    public static String hashBase64SHA256(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] h = md.digest(texto.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(h);
        } catch (Exception e) {
            return "";
        }
    }

    public static Usuario iniciarSesion(ArrayList<Usuario> usuarios, String nombre, String clavePlano) {
        String h = hashBase64SHA256(clavePlano);
        for (Usuario u : usuarios) if (u.getNombre().equals(nombre) && u.getHash().equals(h)) return u;
        return null;
    }
}
