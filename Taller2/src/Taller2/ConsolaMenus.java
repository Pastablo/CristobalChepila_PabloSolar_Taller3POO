package Taller2;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class ConsolaMenus {
    public static void menuAdmin(Scanner sc, ArrayList<PC> pcs, ArrayList<Usuario> usuarios, String rutaUsuarios) {
        int op = -1;
        while (op != 0) {
            System.out.println("1) Ver PCs completos");
            System.out.println("2) Agregar PC");
            System.out.println("3) Eliminar PC");
            System.out.println("4) Clasificar PCs por riesgo");
            System.out.println("5) Ingresar nuevo usuario");
            System.out.println("0) Salir");
            op = Integer.parseInt(sc.nextLine());
            if (op == 1) {
                for (PC pc : pcs) {
                    System.out.println(pc);
                    for (Puerto p : pc.getPuertos()) {
                        System.out.print("  " + p.getNumero() + ":" + p.getEstado());
                        if (!p.getVulnerabilidades().isEmpty()) {
                            System.out.print("  vulns=");
                            for (Vulnerabilidad v : p.getVulnerabilidades()) System.out.print(v.getNombre() + " ");
                        }
                        System.out.println();
                    }
                }
            } else if (op == 2) {
                System.out.print("ID: ");
                String id = sc.nextLine();
                System.out.print("IP: ");
                String ip = sc.nextLine();
                System.out.print("SO: ");
                String so = sc.nextLine();
                PC pc = new PC(id, ip, so);
                System.out.print("Cantidad de puertos: ");
                int n = Integer.parseInt(sc.nextLine());
                for (int i = 0; i < n; i++) {
                    System.out.print("Puerto numero: ");
                    int num = Integer.parseInt(sc.nextLine());
                    System.out.print("Estado (Abierto/Cerrado): ");
                    String est = sc.nextLine();
                    pc.agregarPuerto(new Puerto(num, est));
                }
                pcs.add(pc);
                System.out.println("PC agregado");
            } else if (op == 3) {
                System.out.print("ID a eliminar: ");
                String id = sc.nextLine();
                PC pc = RepositorioDatos.buscarPC(pcs, id);
                if (pc != null) {
                    pcs.remove(pc);
                    System.out.println("PC eliminado");
                } else {
                    System.out.println("No encontrado");
                }
            } else if (op == 4) {
                for (PC pc : pcs) {
                    System.out.println(pc + " -> Riesgo: " + pc.nivelRiesgo());
                    for (Puerto p : pc.puertosAbiertos()) {
                        if (!p.getVulnerabilidades().isEmpty()) {
                            System.out.print("  Puerto " + p.getNumero() + " vulnerabilidades: ");
                            for (Vulnerabilidad v : p.getVulnerabilidades()) System.out.print(v.getNombre() + " ");
                            System.out.println();
                        }
                    }
                }
            } else if (op == 5) {
                System.out.print("Nombre de usuario: ");
                String nombre = sc.nextLine();
                System.out.print("Clave en texto: ");
                String clave = sc.nextLine();
                System.out.print("Rol (ADMIN/USER): ");
                String rol = sc.nextLine();
                String hash = Autenticacion.hashBase64SHA256(clave);
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(rutaUsuarios, true));
                    bw.write(nombre + ";" + hash + ";" + rol);
                    bw.newLine();
                    bw.close();
                } catch (Exception e) {}
                usuarios.add(new Usuario(nombre, hash, rol));
                System.out.println("Usuario agregado");
            }
        }
    }

    public static void menuUsuario(Scanner sc, ArrayList<PC> pcs, Usuario usuario) {
        int op = -1;
        while (op != 0) {
            System.out.println("1) Ver PCs");
            System.out.println("2) Escanear un PC");
            System.out.println("3) Ver puertos abiertos de toda la red");
            System.out.println("4) Ordenar PCs segun clase de IP");
            System.out.println("0) Salir");
            op = Integer.parseInt(sc.nextLine());
            if (op == 1) {
                for (PC pc : pcs) System.out.println(pc);
            } else if (op == 2) {
                System.out.print("ID de PC: ");
                String id = sc.nextLine();
                PC pc = RepositorioDatos.buscarPC(pcs, id);
                if (pc != null) {
                    System.out.println(pc);
                    for (Puerto p : pc.getPuertos()) {
                        System.out.print("  " + p.getNumero() + ":" + p.getEstado());
                        if (!p.getVulnerabilidades().isEmpty()) {
                            System.out.print("  vulns=");
                            for (Vulnerabilidad v : p.getVulnerabilidades()) System.out.print(v.getNombre() + " ");
                        }
                        System.out.println();
                    }
                    String fecha = java.time.LocalDateTime.now().toString();
                    Reportes.guardarEscaneo("reportes.txt", usuario, pc, fecha);
                    System.out.println("Reporte guardado");
                } else {
                    System.out.println("No encontrado");
                }
            } else if (op == 3) {
                ArrayList<Puerto> abiertos = ServiciosRed.puertosAbiertosDeTodaLaRed(pcs);
                for (PC pc : pcs) {
                    for (Puerto p : pc.getPuertos()) {
                        if (p.estaAbierto()) {
                            System.out.print(pc.getId() + " puerto " + p.getNumero());
                            if (!p.getVulnerabilidades().isEmpty()) {
                                System.out.print(" vulnerabilidades: ");
                                for (Vulnerabilidad v : p.getVulnerabilidades()) System.out.print(v.getNombre() + " ");
                            }
                            System.out.println();
                        }
                    }
                }
            } else if (op == 4) {
                ArrayList<PC> ordenados = ServiciosRed.ordenarPCsPorClaseIP(pcs);
                for (PC pc : ordenados) System.out.println(ServiciosRed.claseIP(pc.getIp()) + " | " + pc);
            }
        }
    }
}

