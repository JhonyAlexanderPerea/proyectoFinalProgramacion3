package controller;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ventanaPrincipalController {


    private App app;

    private Stage stage;

    private ventanaLoginController ventanaLoginController;

    @FXML
    void cerrarPrincipal(ActionEvent event) {
        stage.close();
    }

    @FXML
    void initialize() {

    }


    public void setApp(App app) {
        this.app = app;
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void show() {
        stage.show();
    }

    public void abrirViewInicioSesion(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(App.class.getResource("/view/ventanaLogin.fxml"));
        AnchorPane anchorPane= (AnchorPane)loader.load();
        ventanaLoginController loginController = loader.getController();
        loginController.setAplicacion(app);
        Scene scene= new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        loginController.init(stage, this);
        stage.show();
        this.stage.close();
    }

    public void init(Stage stage, ventanaLoginController loginController) {
        this.ventanaLoginController = loginController;
        this.stage = stage;
    }
}
