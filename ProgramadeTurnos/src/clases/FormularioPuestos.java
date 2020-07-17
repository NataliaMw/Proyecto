/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private Set<Puesto> puestos;
    private static List<Medico> medicos;

    public FormularioPuestos() {
        puestos = Puesto.cargarPuesto();
        //DESCOMENTAR CUANDO SE REALICE EL CARGAR MEDICOS
        //  medicos= Medico.cargarMedicos();
        root = new VBox();
        root.setSpacing(15);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.getStylesheets().add("ec/edu/espol/common/tr.css");
        mostrarMenuPuesto();
    }

    public VBox getRoot() {
        return root;
    }

    public Set<Puesto> getPuestos() {
        return puestos;
    }

    public void mostrarMenuPuesto() {
        try {
            root.getChildren().clear();
            Image img = new Image(new FileInputStream("admP119-2.png"));
            ImageView imgv = new ImageView(img);
            imgv.setFitHeight(125);
            Button crear = new Button("Crear");
            crear.setOnAction((ActionEvent event) -> {
                mostrarVentanaCrear();
            });
            Button eliminar = new Button("Eliminar");
            eliminar.setOnAction((ActionEvent event) -> {
                mostrarVentanaEliminar();
            });
            Button editar = new Button("Editar");
            editar.setOnAction((ActionEvent event) -> {
                mostrarVentanaEditar();
            });
            HBox linea = new HBox(crear, eliminar, editar);
            linea.setAlignment(Pos.BASELINE_CENTER);
            linea.setSpacing(30);
            Button regresar = new Button("Volver");
            regresar.setOnAction((ActionEvent event) -> {
                regresarMenu();
            });
            root.getChildren().addAll(imgv, linea, regresar);
        } catch (IOException ex) {
            regresarMenu();
        }
    }

    public void mostrarVentanaCrear() {
        try {
            root.getChildren().clear();
            Button b2 = new Button("Volver");
            b2.setOnAction((ActionEvent event) -> {
                mostrarMenuPuesto();
            });
            Image img = new Image(new FileInputStream("crear.PNG"));
            ImageView imgv = new ImageView(img);
            Label l1 = new Label("ID: ");
            TextField id = new TextField();
            HBox c1 = new HBox(l1, id);
            Label l2 = new Label("Asignar medico (Opcional): ");
            TextField ced = new TextField("Ingrese cedula");
            HBox c2 = new HBox(l2, ced);
            Button b1 = new Button("Crear");
            root.getChildren().addAll(imgv, c1, c2, b1, b2);
            b1.setOnAction((ActionEvent event) -> {
                boolean select = false;
                Medico m = buscarMedicos(ced.getText());
                System.out.print(id.getText());
                if ("".equals(id.getText())) {
                    select = true;
                    JOptionPane.showMessageDialog(null, "No ha ingresado datos", "Advertencia", JOptionPane.ERROR_MESSAGE);
                    mostrarVentanaCrear();
                }
                for (Puesto p : puestos) {
                    if (p.getIdPuesto().equals(id.getText())) {
                        select = true;
                        JOptionPane.showMessageDialog(null, "Ya existe ese id\nVuelva a llenar los datos", "Advertencia", JOptionPane.ERROR_MESSAGE);
                        mostrarVentanaCrear();
                        break;
                    }
                }
                if (!select) {
                    if (m == null) {
                        Puesto p = new Puesto(id.getText());
                        Puesto.anadirPuesto(p);
                        puestos.add(p);
                        JOptionPane.showMessageDialog(null, "ID: " + id.getText() + "\nNo ingresó medico", "Puesto creado", JOptionPane.PLAIN_MESSAGE);
                    } else if (tienePuestoMedico(m.getCedula())) {
                        JOptionPane.showMessageDialog(null, "ID: " + id.getText() + "\nEse medico ya tiene un puesto asignado", "VOLVER A CARGAR DATOS", JOptionPane.PLAIN_MESSAGE);
                        mostrarVentanaCrear();
                    } else {
                        Puesto p = new Puesto(id.getText(), m);
                        Puesto.anadirPuesto(p);
                        puestos.add(p);
                        JOptionPane.showMessageDialog(null, "ID: " + id.getText() + "\nMedico: Dr/a. " + m.getApellidos(), "Puesto creado", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            });
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Problema", "Advertencia", JOptionPane.ERROR_MESSAGE);
            regresarMenu();
        }
    }

    public void mostrarVentanaEliminar() {
        try {
            root.getChildren().clear();
            Set<Puesto> lp = buscarPuesto(false);
            Image img = new Image(new FileInputStream("elm.PNG"));
            ImageView imgv = new ImageView(img);
            ComboBox<Puesto> cpuestos = new ComboBox<>();
            cpuestos.setItems(FXCollections.observableArrayList(lp));
            HBox h = new HBox(new Label("Selccionar puesto a eliminar: "), cpuestos);
            EventHandler<ActionEvent> e = new EventHandler() {
                @Override
                public void handle(Event event) {
                    Puesto p = cpuestos.getValue();
                    if (JOptionPane.showConfirmDialog(null, "Seguro eliminar Puesto# " + p.getIdPuesto() + "?", "WARNING",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        puestos.remove(p);
                        p.eliminarPuesto();
                    } else {
                        mostrarVentanaEliminar();
                    }
                }
            };
            cpuestos.setOnAction(e);
            Button b1 = new Button("Volver");
            b1.setOnAction((ActionEvent event) -> {
                mostrarMenuPuesto();
            });
            root.getChildren().addAll(imgv, h, b1);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Problema", "Advertencia", JOptionPane.ERROR_MESSAGE);
            regresarMenu();
        }

    }

    public void mostrarVentanaEditar() {
        root.getChildren().clear();
        TextField id = new TextField();
        HBox h = new HBox(new Label("Ingrese ID: "), id);
        Button b1 = new Button("Ingresar");
        b1.setOnAction((ActionEvent event) -> {
            boolean pls = false;
            for (Puesto p : puestos) {
                if (p.getIdPuesto().equals(id.getText())) {
                    if (p.getMedicoA() != null) {
                        pls = true;
                        break;
                    }
                    TextField ced = new TextField();
                    HBox h1 = new HBox(new Label("Ingrese cédula del Medico: "), ced);
                    Button b2 = new Button("Aceptar");
                    root.getChildren().addAll(h1, b2);
                    b2.setOnAction((ActionEvent e) -> {
                        Medico m = buscarMedicos(ced.getText());
                        if (m == null | tienePuestoMedico(m.getCedula())) {
                            JOptionPane.showMessageDialog(null, "Medico no disponible", "Advertencia", JOptionPane.ERROR_MESSAGE);
                            mostrarVentanaEditar();
                        } else {
                            p.setMedicoA(m);
                            root.getChildren().add(new Label("Medico correctamente asignado"));
                        }
                    });
                    break;
                }
            }
            if (pls == true) {
                JOptionPane.showMessageDialog(null, "Error al ingresar datos\nVolver a ingresar", "Advertencia", JOptionPane.ERROR_MESSAGE);
                mostrarVentanaEditar();
            }

        });
        Button b2 = new Button("Volver");
        b2.setOnAction((ActionEvent event) -> {
            mostrarMenuPuesto();
        });
        root.getChildren().addAll(new Label("Añadir médico"), h, b1, b2);
    }

    public void regresarMenu() {
//HACER ESTE CUANDO ESTE LO DE ALEJANDRA
    }

    public static Medico buscarMedicos(String ced) {
        // medicos = new LinkedList<>();
        for (Medico c : medicos) {
            if (c.getCedula().equals(ced)) {
                return c;
            }
        }
        return null;
    }

    public Set<Puesto> buscarPuesto(boolean est) {
        Set<Puesto> d = new TreeSet<>();
        for (Puesto c : puestos) {
            if (c.isOcupado() == est) {
                d.add(c);
            }
        }
        return d;
    }

    /*
    Verificar que cada medico solo tenga 1 puesto.
    Retorna true si ya tiene un puesto
     */
    public boolean tienePuestoMedico(String ced) {
        for (Puesto c : puestos) {
            if (c.getMedicoA().getCedula() == null ? ced == null : c.getMedicoA().getCedula().equals(ced)) {
                return true;
            }
        }
        return false;
    }
}
