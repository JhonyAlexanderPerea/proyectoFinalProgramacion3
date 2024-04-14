package controller;

import application.App;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ventanaPublicacionesController {

    @FXML
    private Button btnArchivos;

    @FXML
    private Button btnSubirArchivo;

    @FXML
    private Label lblArchivoParaSubir;
    private Application app;

    @FXML
    void escogerArchivo(ActionEvent event) {

    }

    @FXML
    void subirArchivo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Archivo");

        // Mostrar el FileChooser y obtener el archivo seleccionado
        java.io.File selectedFile = fileChooser.showOpenDialog(btnArchivos.getScene().getWindow());
        if (selectedFile != null) {
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
            // Aquí puedes manejar el archivo seleccionado según tus necesidades
        }

    }

    public void init(Stage stage, ventanaLoginController ventanaLoginController) {
    }

    public void setAplicacion(App aplicacion) {
        this.app = aplicacion;
    }
}
