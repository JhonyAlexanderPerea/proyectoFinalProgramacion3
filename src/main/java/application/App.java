package application;

import controller.ventanaPrincipalController;
import jakarta.mail.MessagingException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/News.png")));
        System.gc();
        mostrarVentanaPrincipal();

    }

    private void mostrarVentanaPrincipal() throws IOException {
        //Se establece la ruta de la ventana que desea ejecutar
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ventanaPrincipal.fxml"));
            AnchorPane anchorPane = loader.load();
            ventanaPrincipalController principalController = loader.getController();
            principalController.setApp(this);
            Scene scene = new Scene(anchorPane);
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/News.png")));
            primaryStage.setScene(scene);
            primaryStage.setTitle("PRINCIPAL");
            primaryStage.show();
            ventanaPrincipalController controller = loader.getController();
            controller.setStage1(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public static void main(String[] args) throws MessagingException, IOException {launch(args);}
}