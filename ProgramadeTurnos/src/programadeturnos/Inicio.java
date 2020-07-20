
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */

package programadeturnos;

import TDAs.Paciente;
import TDAs.Puesto;
import TDAs.Turno;
import espol.edu.ec.util.*;
import interfaz.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author John
 */
public class Inicio extends Application {
    
    public static Set<Puesto>puestos;
    public boolean atendido = false;
    public static Map<String,Integer> riesgos = new TreeMap<>();
    public static Turno turno = null;
    public static Stack<Paciente> pacientes;
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        pacientes = new Stack<>();
        Paciente.listaPacientes().forEach(e->pacientes.add(e));
        puestos = Puesto.cargarPuesto();
        
        mapaRiesgo();
       launch(args);
    }
    
    private static void mapaRiesgo(){
        try {
            Scanner sc= new Scanner(new File("sintomas.txt"));
            while(sc.hasNextLine()){
                String linea= sc.nextLine();
                String[] array=linea.split("\\|");
                riesgos.put(array[0], Integer.valueOf(array[1]));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });
        StackPane root = new StackPane();
        root.getChildren().addAll(btn);
        primaryStage.getIcons().add(new Image("file:icono.png"));
        primaryStage.setTitle("Programa de turnos");
        PanelPrincipal pp = new PanelPrincipal();
        pp.start(primaryStage);
    }


    
}
