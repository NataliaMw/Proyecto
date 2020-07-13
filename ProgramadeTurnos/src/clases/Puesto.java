/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nati
 */
public class Puesto {

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
        this.anadirPuesto();
    }

    /**
     *
     * @param idPuesto
     */
    public Puesto(String idPuesto) {
        this.idPuesto = idPuesto;
        ocupado = false;
        this.anadirPuesto();
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
    }

    public Medico getMedicoA() {
        return medicoA;
    }

    public void setMedicoA(Medico medicoA) {
        this.medicoA = medicoA;
    }

    public void anadirPuesto() {
    }

    public static LinkedList<Puesto> cargarPuesto() {
        LinkedList<Puesto> t = new LinkedList<>();
        try ( BufferedReader bf = new BufferedReader(new FileReader("puestos.txt"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String p[] = linea.split(";");
                if (p.length == 1) {
                    t.add(new Puesto(p[0]));
                } else {
                    Medico m = FormularioPuestos.buscarMedicos(p[1]);
                    if (m != null) {
                        t.add(new Puesto(p[0], m));
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

    @Override
    public String toString() {
        return "Puesto{" + "idPuesto=" + idPuesto + ", ocupado=" + ocupado + ", medicoA=" + medicoA + '}';
    }

    public void eliminarPuesto() {
    }

}
