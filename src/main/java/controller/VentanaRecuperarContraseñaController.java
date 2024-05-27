package controller;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaRecuperarContraseñaController {

    @FXML
    private Button btnSalir;
    private Stage stage1;
    private App app;
    private VentanaLoginController ventanaLoginController;

    @FXML
    void abrirViewLogin(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/view/ventanaLogin.fxml"));
        AnchorPane anchorPane = loader.load();
        VentanaLoginController loginController = loader.getController();
        loginController.setAplicacion(app);
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("LOGIN");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/News.png")));
        loginController.initRecuperar(stage, this);
        stage.show();
        this.stage1.close();
    }

    public void init(Stage stage, VentanaLoginController ventanaLoginController) {
        this.ventanaLoginController = ventanaLoginController;
        this.stage1 = stage;
    }

    public void setApp(App app) {
        this.app = app;
    }
}
