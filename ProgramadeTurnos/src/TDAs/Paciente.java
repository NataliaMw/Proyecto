/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Hp Corporations
 */
public class Paciente implements Comparable<Paciente>{
    private String cedula;
    private String nombres;
    private String apellidos;
    private int edad;
    private String genero;
    private String sintoma;

    public Paciente(String cedula,String nombres, String apellidos, int edad, String genero, String sintoma) {
        this.cedula=cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
        this.sintoma = sintoma;
    }
    public Paciente(String cedula){
        this.cedula=cedula;
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

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Override
    public String toString() {
        return "Paciente{" + "cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos + ", edad=" + edad + ", genero=" + genero + ", sintoma=" + sintoma + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.cedula);
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
        final Paciente other = (Paciente) obj;
        if (!Objects.equals(this.cedula, other.cedula)) {
            return false;
        }
        return true;
    }
    public static List<Paciente> listaPacientes(){
        List<Paciente> lpacientes= new LinkedList<>();
        try ( BufferedReader bf = new BufferedReader(new FileReader("src/recursos/datos del paciente.txt"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String p[] = linea.split(",");
                
                    lpacientes.add(new Paciente(p[0],p[1],p[2],Integer.valueOf(p[3]),p[4],p[5]));
                
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return lpacientes;
    }

    @Override
    public int compareTo(Paciente t) {
        return cedula.compareTo(t.cedula);
    }
    public static Set<Paciente> cargarPaciente() {
        Set<Paciente> t = new TreeSet<>();
        try ( BufferedReader bf = new BufferedReader(new FileReader("src/recursos/datos del paciente.txt"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                String p[] = linea.split(",");     
                    t.add(new Paciente(p[0]));
            }
            return t;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    
    
}
