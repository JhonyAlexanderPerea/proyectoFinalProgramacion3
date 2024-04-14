package controller;

import Networking.ServerMain;
import Networking.clienteArchivos;
import application.App;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class ventanaPublicacionesController {

    @FXML
    private Button btnArchivos;

    @FXML
    private Button btnSubirArchivo;

    @FXML
    private Label lblArchivoParaSubir;

    @FXML
    private Label lblArchivoSeleccionado;

    private Application app;


    @FXML
    void escogerArchivo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Archivo");

        // Mostrar el FileChooser y obtener el archivo seleccionado
        File selectedFile = fileChooser.showOpenDialog(btnArchivos.getScene().getWindow());
        if (selectedFile != null) {
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
            lblArchivoSeleccionado.setText(selectedFile.getAbsolutePath());
        }
    }


    @FXML
    void subirArchivo(ActionEvent event) {
        // Verificar si se ha seleccionado un archivo
        String nombreArchivo = lblArchivoSeleccionado.getText();
        if (nombreArchivo.isEmpty())
        {
            System.out.println("No se ha seleccionado ningún archivo.");
            return;
        }

        // Llamar a los métodos para conectar el cliente y el servidor

        String direccionIP = "127.0.0.1"; // Dirección IP del servidor
        int puerto = 10; // Puerto del servidor


        // Crear cliente de archivos y establecer la conexión con el servidor
        clienteArchivos cliente = new clienteArchivos(direccionIP, puerto);
            try {
                // Llamar al método para enviar el archivo
                cliente.enviarArchivo(new File(nombreArchivo));

                // Notificar al usuario que el archivo se ha enviado
                System.out.println("Archivo enviado con éxito.");
            } catch (Exception e) {
                System.err.println("Error al conectar con el servidor.");
                System.err.println("Error al enviar el archivo: " + e.getMessage());
            }
    }


    public void init(Stage stage, ventanaLoginController ventanaLoginController) {
    }

    public void setAplicacion(App app) {
        this.app = app;
    }

    public void montarServidor(ActionEvent actionEvent) {
        Thread hiloServer = new Thread( () -> ServerMain.montar());
        hiloServer.start();
    }
}

