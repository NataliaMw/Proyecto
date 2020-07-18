/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.common;

import ec.edu.espol.util.FormularioAtencion;
import ec.edu.espol.util.FormularioMedico;
import ec.edu.espol.util.FormularioPuestos;
import ec.edu.espol.util.Medico;
import ec.edu.espol.util.Paciente;
import ec.edu.espol.util.Puesto;
import ec.edu.espol.util.Turno;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Nati
 */
public class ProgramadeTurnos extends Application{
    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        //PRUEBA BORRAR DATOS DESPUES y en el archivo tmb
        Medico mediP=new Medico("0985647754","Jinna Miriam","Martinex Cedeño",45,"Femenino","Fiebrelogiaxd");
        Paciente prueba=new Paciente("Jorge Daniel","Castro Macias",45,"Masculino","Fiebre");
        Scene sc = new Scene(new FormularioAtencion(new Turno(mediP,prueba,new Puesto("001"),"A055")).getRoot(), 900, 700);
        primaryStage.setTitle("PROGRAMA DE TURNOS");
        primaryStage.getIcons().add(new Image("file:icono.png"));
        primaryStage.setScene(sc);
        primaryStage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    
}
