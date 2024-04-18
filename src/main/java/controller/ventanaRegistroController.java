package controller;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ventanaRegistroController {

    private App app;
    private ventanaLoginController ventanaLoginController;
    private Stage stage;

    @FXML
    private Button btnSalir;

    @FXML
    void abrirViewLogin(ActionEvent event)throws IOException {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(App.class.getResource("/view/ventanaLogin.fxml"));
        AnchorPane anchorPane= (AnchorPane)loader.load();
        ventanaLoginController loginController = loader.getController();
        loginController.setAplicacion(app);
        Scene scene= new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        loginController.initRegistro(stage, this);
        stage.show();
        this.stage.close();
    }

    public void init(Stage stage, ventanaLoginController ventanaLoginController) {
        this.ventanaLoginController = ventanaLoginController;
        this.stage = stage;
    }

    public void setApp(App app) {
        this.app = app;
    }
}