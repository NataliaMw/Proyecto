
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */

package programadeturnos;

import interfaz.*;
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
public class ProgramadeTurnos extends Application {
    
    
    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image("file:icono.png"));
        primaryStage.setTitle("Programa de turnos");
        PanelPrincipal pp = new PanelPrincipal();
        pp.start(primaryStage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
