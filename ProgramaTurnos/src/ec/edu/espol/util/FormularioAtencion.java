/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.common.ProgramadeTurnos;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Nati
 */
public class FormularioAtencion extends Application {

    private BorderPane root;
    private Set<Puesto> puestos;
    private static List<Medico> medicos;
    private Button bVolver;
    private VBox panelDerecho;
    private ScrollPane sp;

    public FormularioAtencion() {
        puestos = Puesto.cargarPuesto();
        root = new BorderPane();
        sp = new ScrollPane();
        panelDerecho=new VBox();
        crearPanelTop();
        crearPanelCentro();
        crearPanelDere();
    }

    public BorderPane getRoot() {
        return root;
    }

    private void crearPanelTop() {
        try {
            root.getChildren().clear();
            Image img = new Image(new FileInputStream("aten.png"));
            ImageView imgv = new ImageView(img);
            root.setTop(imgv);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FormularioAtencion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearPanelCentro() {

    }

    private void crearPanelDere() {
        TextArea diagnostico=new TextArea();
        TextArea receta=new TextArea();
        VBox bq=new VBox(diagnostico,receta);
        panelDerecho.getChildren().add(bq);
        panelDerecho.setSpacing(10);
        sp.setContent(panelDerecho);
        root.setRight(sp);
    }

    public void VolverMenu(ActionEvent e) throws Exception {
        if (e.getSource() == bVolver) {
            ProgramadeTurnos log = new ProgramadeTurnos();
            Stage escenario = null;
            log.start(escenario);
        }
    }

    @Override
    public void start(Stage arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
