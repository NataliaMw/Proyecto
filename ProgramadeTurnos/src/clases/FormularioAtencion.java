/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import programadeturnos.ProgramadeTurnos;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Nati
 */
public class FormularioAtencion {

    private BorderPane root;
    private VBox panelCentro;
    private VBox panelizq;
    private HBox panelFinal;
    private ScrollPane sp;
    private Turno turno;
    private Button registro;

    public FormularioAtencion(Turno turno) {
        this.turno = turno;
        root = new BorderPane();
        sp = new ScrollPane();
        panelFinal = new HBox();
        panelCentro = new VBox();
        panelizq = new VBox();
        registro=new Button("Registrar");
        root.getStylesheets().add("ec/edu/espol/common/tr.css");
        crearPanelTop();
        crearPanelCentro();
        crearPanelDatos();
        crearPanelFinal();
    }

    public BorderPane getRoot() {
        return root;
    }

    private void crearPanelTop() {
        try {
            root.getChildren().clear();
            Image img = new Image(new FileInputStream("aten.png"));
            ImageView imgv = new ImageView(img);
            VBox a = new VBox(imgv, new Label());
            a.setAlignment(Pos.CENTER);
            root.setTop(a);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FormularioAtencion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearPanelCentro() {
        TextArea receta = new TextArea();
        TextArea diagnostico = new TextArea();
       if(receta.getText()!=null){
       registro.setDisable(false);
       }
        panelCentro.getChildren().addAll(new Label("Receta"), receta, new Label("Diagnostico"), diagnostico);
        panelCentro.setSpacing(20);
        sp.setContent(panelCentro);
        root.setCenter(sp);
    }

    private void crearPanelDatos() {
        Button b1 = new Button("BOTON be");
        Label l1 = new Label("TURNO #1");
        // Label l1=new Label("TURNO "+turno.getIdTurno());
        //  Label l2=new Label("Paciente: "+turno.getPaciente().getNombres()+" "+turno.getPaciente().getApellidos());
        Label l2 = new Label("Paciente: Andrew Suarez");
        panelizq.setAlignment(Pos.TOP_CENTER);
        panelizq.getChildren().addAll(l1, l2, b1);
        root.setLeft(panelizq);
    }

    public void VolverMenu() {
    }

    private void crearPanelFinal() {
        registro.setDisable(true);
        Button b2 = new Button("Registrar");
        panelFinal.getChildren().addAll(b2, registro);
        panelFinal.setAlignment(Pos.CENTER);
        panelFinal.setSpacing(80);
        root.setBottom(panelFinal);
    }
}
