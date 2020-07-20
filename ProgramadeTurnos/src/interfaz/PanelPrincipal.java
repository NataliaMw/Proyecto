/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import espol.edu.ec.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import programadeturnos.Inicio;

/**
 *
 * @author Cotrina
 */
public class PanelPrincipal extends Application {
    private VBox root;
    private ArrayList<Button> bt;
    private ImageView titulo;
    private boolean cl;
    
    public PanelPrincipal(){
        root = new VBox(5);
        bt = new ArrayList<>();
        root.setStyle("-fx-background-color: #E4F4FF ") ;
        cl = false;
        panelCentral();
      
    }
    
    private void panelCentral(){
        try {
            titulo = new ImageView(new Image(new FileInputStream("Principal.png")));
            titulo.setFitHeight(100);
            titulo.setFitWidth(410);
            root.getChildren().addAll(new VBox(),titulo);
        } catch (FileNotFoundException ex) {}
        root.setAlignment(Pos.TOP_CENTER);
    }
    
    private void botones(Stage primaryStage){
        String[] nombre = {"Medicos","Pacientes","Puestos","Mostrar turnos","Atender Paciente"};        
        
        for (int i = 0; i < 5; i++) {
            Button bot = new Button(nombre[i]);  
            bot.setStyle("-fx-text-fill: BLACK; -fx-font-size: 20; -fx-font-family: Courgette;-fx-background-color: #7DCEA0");
            bot.setMinSize(200,50);
            bot.setMaxSize(200.0, 50);
            bot.setOnMouseEntered(e->bot.setEffect(new DropShadow()));
            bot.setOnMouseExited(e->bot.setEffect(null));
            configBotones(bot, i,primaryStage);                    
            bt.addFirst(bot);
            root.getChildren().add(bot);
        }
        
        
    }
    
    private void configBotones(Button b, int i,Stage primaryStage){
        switch (i){
            case 0:
                b.setOnAction(e -> {
                Scene scene = new Scene(new FormularioMedico().getRoot(), 700, 500);
                primaryStage.setScene(scene);
                   });
                break; 
            case 1:
                b.setOnAction(e -> {
                Scene scene = new Scene(new FormularioPaciente().getRoot(), 700, 500);
                primaryStage.setScene(scene);
                   });
                break;
            case 2:
                  b.setOnAction(e -> {
                Scene scene = new Scene(new FormularioPuestos().getRoot(), 700, 500);
                primaryStage.setScene(scene);
                   });
                break;
            case 3:
                b.setOnAction(e -> {
                FormularioTurnos pn = new FormularioTurnos();
                primaryStage.close();
                pn.start(new Stage());
                });
                break;
            case 4:
                b.setOnAction(e -> {
                    if(Inicio.turno!=null){
                        Scene scene = new Scene(new FormularioAtencion(Inicio.turno).getRoot(), 700, 500);
                        primaryStage.setScene(scene);
                   
                    }else{
                        JOptionPane.showMessageDialog(null, "Debe tener turno primero", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    }
                });
                break;
               }
        
            
    }
    
    
    public VBox getRoot() {
        return root;
    }

    @Override
    public void start(Stage primaryStage) {
        botones(primaryStage);
        Scene scene = new Scene(root,430,400);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Sistema de turnos");
         //primaryStage.initStyle(StageStyle.DECORATED);
        //primaryStage.initStyle(StageStyle.TRANSPARENT); 
        //scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
