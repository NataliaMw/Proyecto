/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

import interfaz.FormularioPuestos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Nati
 */
public class Puesto implements Comparable<Puesto> {
  private String idPuesto;
    private boolean ocupado;
    private Medico medicoA;

    /**
     *
     * @param idPuesto
     * @param medicoA
     */
    public Puesto(String idPuesto, Medico medicoA) {
        this.idPuesto = idPuesto;
        this.medicoA = medicoA;
        ocupado = false;
    }

    /**
     *
     * @param idPuesto
     */
    public Puesto(String idPuesto) {
        this.idPuesto = idPuesto;
        ocupado = false;
    }

    public String getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(String idPuesto) {
        this.idPuesto = idPuesto;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
        if (ocupado) {
            anadirOcupado();
        }
    }

    public Medico getMedicoA() {
        return medicoA;
    }

    public void setMedicoA(Medico medicoA) {
        this.medicoA = medicoA;
        anadirMedico(medicoA.getCedula());
    }

    public static void anadirPuesto(Puesto p) {
        try {
            String texto = "";
            if (p.medicoA == null) {
                texto = p.idPuesto + "\n";
            } else {
                if (p.ocupado) {
                    texto = p.idPuesto + ";" + p.medicoA.getCedula() + ";" + p.ocupado + "\n";
                } else {
                    texto = p.idPuesto + ";" + p.medicoA.getCedula() + "\n";
                }
            }
            Path path = Paths.get("puestos.txt");
            Files.write(path, texto.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ex) {
            Logger.getLogger(Puesto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return idPuesto;
    }

    public static Set<Puesto> cargarPuesto() {
        Set<Puesto> t = new TreeSet<>();
        try ( BufferedReader bf = new BufferedReader(new FileReader("puestos.txt"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String p[] = linea.split(";");
                if (p.length == 1) {
                    t.add(new Puesto(p[0]));
                } else {
                    Medico m = FormularioPuestos.buscarMedicos(p[1]);
                    if (m != null) {
                        Puesto pp = new Puesto(p[0], m);
                        t.add(pp);
                        if (p.length == 3) {
                            pp.setOcupado(true);
                        }
                    }
                }
            }
            return t;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Puesto other = (Puesto) obj;
        if (!Objects.equals(this.idPuesto, other.idPuesto)) {
            return false;
        }
        return true;
    }

    public void eliminarPuesto() {
        BufferedReader reader = null;
        try {
            File actual = new File("puestos.txt");
            File temporal = new File("tempPuestos.txt");
            reader = new BufferedReader(new FileReader(actual));
            BufferedWriter linea = new BufferedWriter(new FileWriter(temporal));
            String a;
            while ((a = reader.readLine()) != null) {
                String temp = a.trim();
                if (medicoA == null) {
                    if (temp.equals(idPuesto)) {
                        continue;
                    }
                } else {
                    if (temp.equals(idPuesto + ";" + medicoA.getCedula())) {
                        continue;
                    }
                }
                linea.write(a + System.getProperty("line.separator"));
            }
            linea.close();
            reader.close();
            actual.delete();
            temporal.renameTo(actual);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void anadirMedico(String ced) {
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("puestos.txt"), StandardCharsets.UTF_8));
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(idPuesto)) {
                    fileContent.set(i, idPuesto + ";" + ced);
                    break;
                }
            }
            Files.write(Paths.get("puestos.txt"), fileContent, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public int compareTo(Puesto p) {
        return idPuesto.compareTo(p.idPuesto);
    }

    /*Añade true a la linea de codigo*/
    private void anadirOcupado() {
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get("puestos.txt"), StandardCharsets.UTF_8));
            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).equals(idPuesto)) {
                    fileContent.set(i, idPuesto + ";" + medicoA.getCedula()+";"+"true");
                    break;
                }
            }
            Files.write(Paths.get("puestos.txt"), fileContent, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }    }
}
