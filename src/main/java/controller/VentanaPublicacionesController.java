package controller;

import networking.PublicadorArchivos;
import networking.ServidorArchivos;
import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class VentanaPublicacionesController {

    private App app;
    private Stage stage1;
    private Thread hiloServidor;
    private ServidorArchivos servidorArchivos;

    @FXML
    private Button btnArchivos;

    @FXML
    private Button btnSubirArchivo;

    @FXML
    private Label lblArchivoParaSubir;

    @FXML
    private Label lblArchivoSeleccionado;

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
        if (nombreArchivo.isEmpty()) {
            System.out.println("No se ha seleccionado ningún archivo.");
            return;
        }

        // Llamar a los métodos para conectar el cliente y el servidor
        String direccionIP = "127.0.0.1"; // Dirección IP del servidor
        int puerto = 5000; // Puerto del servidor

        // Crear cliente de archivos y establecer la conexión con el servidor
        PublicadorArchivos cliente = new PublicadorArchivos(direccionIP, puerto);
        try {
            // Llamar al método para enviar el archivo
            cliente.enviarArchivo(new File(nombreArchivo));

            // Notificar al usuario que el archivo se ha enviado
            System.out.println("Archivo enviado con éxito.");
        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor.");
            System.err.println("Error al enviar el archivo: " + e.getMessage());
        }
    }

    @FXML
    void montarServidor(ActionEvent actionEvent) {
        servidorArchivos = new ServidorArchivos(5000);
        hiloServidor = new Thread(servidorArchivos::iniciarServidor);
        hiloServidor.start();
    }

    @FXML
    void abrirViewLogin(ActionEvent actionEvent) throws IOException {
        if (servidorArchivos != null) {
            servidorArchivos.cerrarServidor();
        }

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ventanaLogin.fxml"));
        AnchorPane anchorPane = loader.load();
        VentanaLoginController loginController = loader.getController();
        loginController.setAplicacion(app);
        loginController.initPublicaciones(stage, this);
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("LOGIN");
        stage.show();
        stage1.close();
    }

    public void setApp(App app) {
        this.app = app;
    }

    public void init(Stage stage, VentanaLoginController ventanaLoginController) {
        this.stage1 = stage;
    }
}

