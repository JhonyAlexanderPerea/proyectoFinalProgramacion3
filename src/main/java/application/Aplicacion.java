package application;

import controller.ventanaLoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Aplicacion extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Ventana Principal");
        mostrarVentanaPrincipal();
    }

    private void mostrarVentanaPrincipal()throws IOException{
        try {
            // Cargar el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ventanaLogin.fxml"));
            AnchorPane anchorPane = loader.load();

            // Obtener el controlador y pasar la referencia de la aplicación
            ventanaLoginController controller = loader.getController();
            controller.setAplicacion(this);

            // Crear la escena y establecerla en el escenario primario
            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar la ventana principal. Motivo: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}


