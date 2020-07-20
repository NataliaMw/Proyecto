/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Nati
 */
public class Medico implements Comparable<Medico> {

    private String cedula;
    private String nombres;
    private String apellidos;
    private int edad;
    private String genero;
    private String especialidad;

    public Medico(String cedula, String nombres, String apellidos, int edad, String genero, String especialidad) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
        this.especialidad = especialidad;
    }
    public Medico(String cedula){
        this.cedula = cedula;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Medico{" + "cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", edad=" + edad + ", genero=" + genero + ", especialidad=" + especialidad + '}';
    }
    
    public static LinkedList<Medico> listaMedicos(){
        System.out.println("PRUEBAAAA");
        LinkedList<Medico> lmedicos = new LinkedList<>();
        try ( BufferedReader bf = new BufferedReader(new FileReader("src/recursos/datos del medico.txt"))) {
            System.out.println("PRUEBAAAA");
            String linea;
            bf.readLine();
            while ((linea = bf.readLine()) != null) {
                String p[] = linea.split(",");
                    System.out.println("PRUEBAAAA");
                    lmedicos.add(new Medico(p[0],p[1],p[2],Integer.parseInt(p[3]),p[4],p[5]));
                
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return lmedicos;                
    }

    @Override
    public int compareTo(Medico t) {
        return cedula.compareTo(t.cedula);
    }
    public static Set<Medico> cargarMedico() {
        Set<Medico> t = new TreeSet<>();
        try ( BufferedReader bf = new BufferedReader(new FileReader("src/recursos/datos del medico.txt"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String p[] = linea.split(",");     
                    t.add(new Medico(p[0]));
            }
            return t;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.cedula);
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
        final Medico other = (Medico) obj;
        if (!Objects.equals(this.cedula, other.cedula)) {
            return false;
        }
        return true;
    }
    

}
