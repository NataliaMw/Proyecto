/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import TDAs.Paciente;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import programadeturnos.Inicio;

/**
 *
 * @author Hp Corporations
 */
public class FormularioPaciente {
    private VBox root;
    Button guardar;

    public FormularioPaciente() {
        root= new VBox();
        OrganizarVista();
    }
    private void OrganizarVista(){
        
        root.setAlignment(Pos.CENTER);
        
        root.setSpacing(30);
        crearVistaPaciente();
    }
    public void crearVistaPaciente(){
        root.getChildren().clear();
        guardar=new Button("Guardar");
        Label paciente= new Label("  DATOS DEL PACIENTE  ");
        paciente.setTextFill(Color.WHITE);
        paciente.setStyle("-fx-background-color: #2522C6; -fx-font-color: WHITE; -fx-font-size: 20;-fx-font-family: Segoe UI Black; -fx-font-weight : bold 3px");
        HBox contenedor= new HBox();
        VBox seccionLabel = new VBox();
        Label l5= new Label("Cedula:");
        Label l= new Label("Nombres:");
        Label l1= new Label("Apellidos:");
        Label l2= new Label("Edad:");
        Label l3= new Label("Genero:");
        Label l4= new Label("Sintomas:");
        seccionLabel.getChildren().addAll(paciente,l5,l,l1,l2,l3,l4);
        seccionLabel.setSpacing(20);
        seccionLabel.setAlignment(Pos.CENTER);
        seccionLabel.setStyle("-fx-text-fill: BLACK; -fx-font-size: 15;-fx-font-family: Segoe UI Black");
        
        VBox seccionText= new VBox();
        TextField t5=new TextField();
        t5.setStyle("-fx-background-color: #D5D4FC; -fx-text-fill: BLUE; -fx-font-size: 15;-fx-font-family: Segoe UI Black");
        TextField t = new TextField();
        t.setStyle("-fx-background-color: #D5D4FC; -fx-text-fill: BLUE; -fx-font-size: 15;-fx-font-family: Segoe UI Black");
        TextField t1 = new TextField();
        t1.setStyle("-fx-background-color: #D5D4FC; -fx-text-fill: BLUE; -fx-font-size: 15;-fx-font-family: Segoe UI Black");
        TextField t2 = new TextField();
        t2.setStyle("-fx-background-color: #D5D4FC; -fx-text-fill: BLUE; -fx-font-size: 15;-fx-font-family: Segoe UI Black");
        TextField t3 = new TextField();
        t3.setStyle("-fx-background-color: #D5D4FC; -fx-text-fill: BLUE; -fx-font-size: 15;-fx-font-family: Segoe UI Black");
        TextField t4 = new TextField();
        t4.setStyle("-fx-background-color: #D5D4FC; -fx-text-fill: BLUE; -fx-font-size: 15;-fx-font-family: Segoe UI Black");
        seccionText.getChildren().addAll(t5,t,t1,t2,t3,t4);
        seccionText.setSpacing(10);
        seccionText.setAlignment(Pos.CENTER);
        
        contenedor.getChildren().addAll(seccionLabel,seccionText);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setSpacing(15);
        Button volver = new Button("Volver");
        volver.setStyle("-fx-text-fill: WHITE; -fx-font-size: 14; -fx-font-family: Segoe UI Black; -fx-background-color: #2522C6");
        root.getChildren().addAll(paciente, contenedor, volver , guardar);
        
        volver.setOnAction(eb -> {
            volverMenu();
        });
        
        guardar.setStyle("-fx-text-fill: BLACK; -fx-font-size: 14; -fx-font-family: Segoe UI Black; -fx-background-color: #52EC54");
        guardar.setOnAction(eb->{
            String cedula=t5.getText();
            String nombre=t.getText();
            String apellido=t1.getText();
            String edad=String.valueOf(t2.getText());
            String genero=t3.getText();
            String sintoma=t4.getText();
            String datos= cedula+","+nombre+","+apellido+","+edad+","+genero+","+sintoma;
            Paciente pa = new Paciente(cedula,nombre,apellido,Integer.valueOf(edad),genero,sintoma);
            Inicio.pacientes.add(pa);
            //generarArchivo(datos);
            boolean e = false;
            if(Paciente.cargarPaciente()!=null){
            for(Paciente p:Paciente.cargarPaciente()){
                if(p.getCedula().equals(cedula)){
                    JOptionPane.showMessageDialog(null, "Ya existe ese numero de cedula\nVuelva a llenar los datos", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    e= true;
                     break;
                } 
            }    
            }
            
            if (!e) {
                    generarArchivo(datos);
                }
            crearVistaPaciente();
        });
        
        
        
    }
    public VBox getRoot(){
        return root;
    }
    private void volverMenu(){
        PanelPrincipal pp = new PanelPrincipal();
       root.getScene().getWindow().hide();
        pp.start(new Stage());
    }
    public void generarArchivo(String datos){
        File fl = new File("src/recursos/datos del paciente.txt");
        if(!fl.exists()){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/recursos/datos del paciente.txt",true))){
                bw.write("Cedula,Nombre,Apellido,edad,genero,sintoma");
                bw.newLine();
            } catch (IOException ex) {
                Logger.getLogger(FormularioPaciente.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/recursos/datos del paciente.txt",true))){
            bw.write(datos+"\n");
        } catch (IOException ex) {
            Logger.getLogger(FormularioPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
