/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Nati
 */
public class Medico {

    private String nombres;
    private String apellidos;
    private int edad;
    private String genero;
    private String especialidad;

    public Medico(String nombres, String apellidos, int edad, String genero, String especialidad) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.genero = genero;
        this.especialidad = especialidad;
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
        return "Medico{" + "nombres=" + nombres + ", apellidos=" + apellidos + ", edad=" + edad + ", genero=" + genero + ", especialidad=" + especialidad + '}';
    }
    
    
    
    
    
}
