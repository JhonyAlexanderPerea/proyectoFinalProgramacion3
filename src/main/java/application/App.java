package application;

//import com.gluonhq.charm.glisten.application.MobileApplication;
import controller.ventanaPrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {

    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        System.gc();
        // primaryStage.setTitle("Concesionario");
        mostrarVentanaPrincipal();

    }

    private void mostrarVentanaPrincipal() throws IOException {
        //Se establece la ruta de la ventana que desea ejecutar
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ventanaPrincipal.fxml"));
            AnchorPane anchorPane = loader.load();
            ventanaPrincipalController principalController = loader.getController();
            principalController.setAplicacion(this);

            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            primaryStage.show();
            ventanaPrincipalController controller = loader.getController();
            controller.setStage(primaryStage);
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

    public static void main(String[] args) {
        launch(args);
    }
}