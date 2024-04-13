package controller;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ventanaPrincipalController {


    private App aplicacion;
    private Stage stage;

    @FXML
    void cerrarPrincipal(ActionEvent event) {
        stage.close();
    }

    @FXML
    void initialize() {

    }


    public void setAplicacion(App app) {
        this.aplicacion = app;
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void show() {
        stage.show();
    }
}
