/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import TDAs.Medico;
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

/**
 *
 * @author Hp Corporations
 */
public class FormularioMedico {
    private VBox root;
    Button guardar;

    public FormularioMedico() {
        root= new VBox();
        OrganizarVistaMedico();
    }
    private void OrganizarVistaMedico(){
        root.setAlignment(Pos.CENTER);
        
        root.setSpacing(30);
        crearVistaMedico();
    }
    public void crearVistaMedico(){
        root.getChildren().clear();
        guardar=new Button("Guardar");
        Label medico= new Label("  DATOS DEL MEDICO  ");
        medico.setTextFill(Color.WHITE);
        
        medico.setStyle("-fx-background-color: #52EC54; -fx-font-color: WHITE; -fx-font-size: 20;-fx-font-family: Segoe UI Black; -fx-font-weight : bold 3px");
        guardar.setStyle("-fx-text-fill: WHITE; -fx-font-size: 14; -fx-font-family: Segoe UI Black; -fx-background-color: #2522C6");
        HBox contenedor= new HBox();
        VBox seccionLabel = new VBox();
        
        Label l5= new Label("Cedula:");
        Label l= new Label("Nombres:");
        Label l1= new Label("Apellidos:");
        Label l2= new Label("Edad:");
        Label l3= new Label("Genero:");
        Label l4= new Label("Especialidad:");
        seccionLabel.getChildren().addAll(medico,l5,l,l1,l2,l3,l4);
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
        root.getChildren().addAll(medico, contenedor, volver , guardar);
        
        volver.setOnAction(eb -> {
            volverMenu();
        });
        
        guardar.setOnAction(eb->{
            String cedula=t5.getText();
            String nombre=t.getText();
            String apellido=t1.getText();
            String edad=String.valueOf(t2.getText());
            String genero=t3.getText();
            String especialidad=t4.getText();
            String datos= cedula+","+nombre+","+apellido+","+edad+","+genero+","+especialidad;
            
            boolean e = false;
            if(Medico.cargarMedico()!=null){
            for(Medico m:Medico.cargarMedico()){                
                if(m.getCedula().equals(cedula)){
                    JOptionPane.showMessageDialog(null, "Ya existe ese numero de cedula\nVuelva a llenar los datos", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    e= true;
                     break;
                }                   
                
            }    
            }
            
            if (!e) {
                    generarArchivo(datos);
                }
            
           crearVistaMedico();
        });
        
    }

    public VBox getRoot() {
        return root;
    }
    public void generarArchivo(String datos){
        File fl = new File("src/recursos/datos del medico.txt");
        if(!fl.exists()){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/recursos/datos del medico.txt",true))){
                bw.write("Cedula,Nombre,Apellido,edad,genero,especialidad");
                bw.newLine();
            } catch (IOException ex) {
                Logger.getLogger(FormularioMedico.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/recursos/datos del medico.txt",true))){
            bw.write(datos+"\n");
        } catch (IOException ex) {
            Logger.getLogger(FormularioMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void volverMenu() {
        PanelPrincipal pp = new PanelPrincipal();
       root.getScene().getWindow().hide();
        pp.start(new Stage());
    }
}
