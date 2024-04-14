package controller;

import Networking.clienteArchivos;
import Networking.ServidorTask;
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
        conectarServidor();
       // conectarCliente();

        String direccionIP = "127.0.0.1"; // Dirección IP del servidor
        int puerto = 8080; // Puerto del servidor

        // Crear cliente de archivos y establecer la conexión con el servidor
        clienteArchivos cliente = new clienteArchivos(direccionIP, puerto);
        if (cliente.iniciarConexionConServidor()) {
            try {
                // Enviar el nombre del archivo al servidor
                cliente.getFlujoSalida().writeUTF(nombreArchivo);

                // Llamar al método para enviar el archivo
                cliente.enviarArchivo(nombreArchivo);

                // Notificar al usuario que el archivo se ha enviado
                System.out.println("Archivo enviado con éxito.");
            } catch (IOException e) {
                System.err.println("Error al enviar el archivo: " + e.getMessage());
            } finally {
                // Cerrar la conexión con el servidor
                cliente.cerrarRecursos();
            }
        } else {
            System.err.println("Error al conectar con el servidor.");
        }
    }

    // Métodos para conectar cliente y servidor
   /* public void conectarCliente() {
        String direccionIPServidor = "127.0.0.1"; // Dirección IP del servidor
        int puerto = 8080; // Puerto del servidor

        // Crear cliente de archivos
        clienteArchivos cliente = new clienteArchivos(direccionIPServidor, puerto);
        // Iniciar la conexión con el servidor
        if (cliente.iniciarConexionConServidor()) {
            System.out.println("Conexión establecida con el servidor.");
            // Cerrar la conexión después de la verificación
            cliente.cerrarRecursos();
        } else {
            System.err.println("Error al conectar con el servidor.");
        }
    }*/

    public void conectarServidor() {
        int puerto = 8080; // Puerto del servidor

        ServidorTask servidorTask = new ServidorTask(puerto);
        Thread thread = new Thread(servidorTask);
        thread.setDaemon(true); // Hacer que el hilo sea daemon
        thread.start();
    }

    public void init(Stage stage, ventanaLoginController ventanaLoginController) {
    }

    public void setAplicacion(App app) {
        this.app = app;
    }

}

