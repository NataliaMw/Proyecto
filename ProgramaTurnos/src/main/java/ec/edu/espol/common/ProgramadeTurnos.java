package ec.edu.espol.common;

import ec.edu.espol.util.FormularioPuestos;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX ProgramadeTurnos
 */
public class ProgramadeTurnos extends Application {

    @Override
    public void start(Stage stage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });
        StackPane root = new StackPane();
        root.getChildren().addAll(btn);
        Scene sc = new Scene(new FormularioPuestos().getRoot(), 730, 300);
        stage.setTitle("Hello World!");
        stage.setScene(sc);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
