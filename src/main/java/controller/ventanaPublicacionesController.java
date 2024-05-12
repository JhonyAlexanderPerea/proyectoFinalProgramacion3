package controller;

import networking.ClienteArchivos;
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

public class ventanaPublicacionesController {

    private App app;
    private Stage stage1;
    private Thread hiloServidor;

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
        ClienteArchivos cliente = new ClienteArchivos(direccionIP, puerto);
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
        hiloServidor = new Thread(() -> {
            ServidorArchivos servidor = new ServidorArchivos(5000);
            servidor.iniciarServidor();
        });
        hiloServidor.start();
    }

    @FXML
    void abrirViewLogin(ActionEvent actionEvent) throws IOException {
        if (hiloServidor != null && hiloServidor.isAlive()) {
            hiloServidor.interrupt();
        }

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ventanaLogin.fxml"));
        AnchorPane anchorPane = loader.load();
        ventanaLoginController loginController = loader.getController();
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

    public void init(Stage stage, ventanaLoginController ventanaLoginController) {
        this.stage1 = stage;
    }
}
