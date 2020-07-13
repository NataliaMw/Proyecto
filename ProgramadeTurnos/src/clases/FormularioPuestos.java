/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Nati
 */
public class FormularioPuestos {

    private VBox root;
    private List<Puesto> puestos;
    private static List<Medico> medicos;

    public FormularioPuestos() {
        puestos = Puesto.cargarPuesto();
        try {
            root = new VBox();
            root.setSpacing(15);
            root.setAlignment(Pos.BASELINE_CENTER);
            mostrarMenuPuesto();
        } catch (IOException | RuntimeException e) {
            root.getChildren().clear();
            root.getChildren().add(new Label("Ha ocurrido un error"));
            root.getChildren().add(new Label(e.getMessage()));
        }
    }

    public VBox getRoot() {
        return root;
    }

    public void mostrarMenuPuesto() throws IOException {
        root.getChildren().clear();
        Image img = new Image(new FileInputStream("admP119-2.png"));
        ImageView imgv = new ImageView(img);
        imgv.setFitHeight(125);
        Button crear = new Button("Crear");
        crear.setOnAction((ActionEvent event) -> {
            try {
                mostrarVentanaCrear();
            } catch (IOException ex) {
                root.getChildren().clear();
                root.getChildren().add(new Label("Ha ocurrido un error"));
            }
        });
        Button eliminar = new Button("Eliminar");
        eliminar.setOnAction((ActionEvent event) -> {
            mostrarVentanaEliminar();
        });
        Button editar = new Button("Editar");
        eliminar.setOnAction((ActionEvent event) -> {
            mostrarVentanaEditar();
        });
        HBox linea = new HBox(crear, eliminar, editar);
        Button regresar = new Button("Volver");
        regresar.setOnAction((ActionEvent event) -> {
            regresarMenu();
        });
        root.getChildren().addAll(imgv, linea, regresar);
    }

    public void mostrarVentanaCrear() throws IOException {
        root.getChildren().clear();
        Image img = new Image(new FileInputStream("crear.png"));
        ImageView imgv = new ImageView(img);
        imgv.setFitHeight(125);
        Label l1 = new Label("ID: ");
        TextField id = new TextField();
        HBox c1 = new HBox(l1, id);
        Label l2 = new Label("Asignar medico (Opcional): ");
        TextField ced = new TextField("Ingrese cedula");
        HBox c2 = new HBox(l2, ced);
        Button b1 = new Button("Crear");
        root.getChildren().addAll(c1, c2, b1);
        b1.setOnAction((ActionEvent event) -> {
            boolean select = true;
            Medico m = buscarMedicos(ced.getText());
            for (Puesto p : puestos) {
                if (p.getIdPuesto().equals(id.getText())) {
                    JOptionPane.showMessageDialog(null, "Ya existe ese id\nVuelva a llenar los datos", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    try {
                        mostrarVentanaCrear();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "ERROR", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            if (m == null) {
                Puesto p = new Puesto(id.getText());
                JOptionPane.showMessageDialog(null, "ID: " + id.getText() + "\nNo ingresó medico", "Puesto creado", JOptionPane.PLAIN_MESSAGE);
            } else {
                Puesto p = new Puesto(id.getText(), m);
                JOptionPane.showMessageDialog(null, "ID: " + id.getText() + "\nMedico: Dr/a. " + m.getApellidos(), "Puesto creado", JOptionPane.PLAIN_MESSAGE);
            }

        });
    }

    public void mostrarVentanaEliminar() {
        root.getChildren().clear();
    }

    public void mostrarVentanaEditar() {
        root.getChildren().clear();
        TextField id = new TextField();
        HBox h = new HBox(new Label("Ingrese ID:"), id);
        Button b1 = new Button("Ingresar");
        root.getChildren().addAll(new Label("Añadir médico"), h, b1);
        b1.setOnAction((ActionEvent event) -> {
            for (Puesto p : puestos) {
                if (p.getIdPuesto().equals(id.getText())) {
                    TextField ced = new TextField();
                    HBox h1 = new HBox(new Label("Ingrese cédula del Medico:"), ced);
                    Button b2 = new Button("Aceptar");
                    root.getChildren().addAll(h1, b2);
                    b2.setOnAction((ActionEvent e) -> {
                        Medico m = buscarMedicos(ced.getText());
                        if (m == null) {
                            JOptionPane.showMessageDialog(null, "Medico no existe", "Advertencia", JOptionPane.ERROR_MESSAGE);
                            mostrarVentanaEditar();
                        }else{
                        p.setMedicoA(m);
                        Label l1=new Label("Medico correctamente asignado");
                       root.getChildren().add(l1);
                        }
                    });
                }
            }
        });
    }

    public void regresarMenu() {

    }

    public static Medico buscarMedicos(String ced) {
        for (Medico c : medicos) {
            if (c.getCedula().equals(ced)) {
                return c;
            }
        }
        return null;
    }
}
