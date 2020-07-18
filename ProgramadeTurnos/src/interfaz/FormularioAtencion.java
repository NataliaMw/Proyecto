/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import TDAs.Turno;
import java.io.BufferedWriter;
import programadeturnos.ProgramadeTurnos;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
import javax.swing.JOptionPane;

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
    private TextArea receta;
    private TextArea diagnostico;

    public FormularioAtencion(Turno turno) {
       this.turno = turno;
        root = new BorderPane();
        sp = new ScrollPane();
        panelFinal = new HBox();
        panelCentro = new VBox();
        panelizq = new VBox();
        receta = new TextArea();
        diagnostico = new TextArea();
        registro = new Button("Registrar");
        root.getStylesheets().add("src/programadeturnos/tr.css");
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
        panelCentro.getChildren().addAll(new Label("Diagnóstico"), diagnostico, new Label("Receta"), receta);
        panelCentro.setSpacing(20);
        sp.setContent(panelCentro);
        root.setCenter(sp);
    }

    private void crearPanelDatos() {
        Label l0 = new Label("PUESTO " + turno.getPuesto().getIdPuesto().toUpperCase());
        Label l1 = new Label("TURNO " + turno.getIdTurno().toUpperCase());
        Label l2 = new Label("Paciente: ");
        Label l21 = new Label(turno.getPaciente().getNombres() + " " + turno.getPaciente().getApellidos());
        l21.setStyle("-fx-text-fill: BLACK");
        Label l3 = new Label("Doctor: ");
        Label l31 = new Label(turno.getMedico().getNombres() + " " + turno.getMedico().getApellidos());
        l31.setStyle("-fx-text-fill: BLACK");
        Label l4 = new Label("ÁREA DE " + turno.getMedico().getEspecialidad().toUpperCase());
        panelizq.setAlignment(Pos.TOP_CENTER);
        panelizq.setSpacing(20);
        panelizq.getChildren().addAll(l0, l1, l2, l21, l3, l31, l4);
        HBox hd = new HBox(new Label(""), panelizq);
        root.setLeft(hd);
    }

    public void VolverMenu() {

    }

    private void crearPanelFinal() {
        registro.setOnAction((ActionEvent e) -> {
            if (receta.getText().isBlank() || diagnostico.getText().isBlank()) {
                JOptionPane.showMessageDialog(null, "No se ha ingresado datos", "Advertencia", JOptionPane.ERROR_MESSAGE);
            } else {
                generarArchivo(receta.getText(), diagnostico.getText());
                turno.getPuesto().setOcupado(false);
                JOptionPane.showMessageDialog(null, "Cita finalizada\nPuesto " + turno.getPuesto().getIdPuesto() + " liberado", "Cita generada", JOptionPane.PLAIN_MESSAGE);
            }
        });
        Button b2 = new Button("Regresar");
        b2.setOnAction((ActionEvent e) -> {
            VolverMenu();
        });

        panelFinal.getChildren().addAll(b2, registro);
        panelFinal.setAlignment(Pos.CENTER);
        panelFinal.setSpacing(80);
        root.setBottom(panelFinal);
    }

    public void generarArchivo(String receta, String diagnostico) {
//CAMBIAR A CEDULA
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter("cita.txt", true))) {
            bw.write(turno.getIdTurno() + ";" + turno.getMedico().getCedula() + ";"
                    + turno.getMedico().getEspecialidad() + ";" + turno.getPaciente().getApellidos()
                    + ";" + turno.getPaciente().getSintoma() + ";" + diagnostico + ";" + receta + "\n");
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }
}