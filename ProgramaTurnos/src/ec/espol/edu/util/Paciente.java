/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.espol.edu.util;

/**
 *
 * @author Hp Corporations
 */
public class Paciente {
    private String nombres;
    private String apellidos;
    private int edad;
    private String genero;
    private String sintoma;

    public Paciente(String nombres, String apellidos, int edad, String genero, String sintoma) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
        this.sintoma = sintoma;
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

    @Override
    public String toString() {
        return "Paciente{" + "nombres=" + nombres + ", apellidos=" + apellidos + ", edad=" + edad + ", genero=" + genero + ", sintoma=" + sintoma + '}';
    }
    
}
