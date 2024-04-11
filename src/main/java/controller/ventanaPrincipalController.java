package controller;

import application.Aplicacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ventanaPrincipalController {


    private Aplicacion aplicacion;
    private Stage stage;

    @FXML
    void cerrarPrincipal(ActionEvent event) {
        stage.close();
    }

    @FXML
    void initialize() {

    }


    public void setAplicacion(Aplicacion aplicacion) {
        this.aplicacion = aplicacion;
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void show() {
        stage.show();
    }
}
