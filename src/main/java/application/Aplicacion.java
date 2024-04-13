package application;


import java.io.IOException;

import controller.ventanaPrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Aplicacion extends Application{

    //Solo puede existir una instancia de la clase principal, en este caso tenemos una sola intancia
    //de la clase concesionario
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage= primaryStage;
        System.gc();
        primaryStage.setTitle("Ventana login");
        mostrarVentanaPrincipal();

    }

    private void mostrarVentanaPrincipal() throws IOException {
        //Se establece la ruta de la ventana que desea ejecutar
        try{
            FXMLLoader loader= new FXMLLoader();
            loader.setLocation(Aplicacion.class.getResource("../view/ventanaPrincipal.fxml"));
            AnchorPane anchorPane= loader.load();
            ventanaPrincipalController principalController = loader.getController();
            principalController.setAplicacion(this);

            Scene scene= new Scene(anchorPane);
            primaryStage.setScene(scene);
            primaryStage.show();
            ventanaPrincipalController controller= loader.getController();
            controller.setStage(primaryStage);
        }catch(IOException e){
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