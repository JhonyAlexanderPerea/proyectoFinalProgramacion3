package controller;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaPrincipalController {


    private App app;

    private Stage stage1;

    private VentanaLoginController ventanaLoginController;

    @FXML
    void cerrarPrincipal(ActionEvent event) {
        stage1.close();
    }

    @FXML
    void initialize() {

    }


    public void setApp(App app) {
        this.app = app;
    }

    public void setStage1(Stage primaryStage) {

        stage1 = primaryStage;
    }

    public void show() {
        stage1.show();
    }

    public void abrirViewInicioSesion(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(App.class.getResource("/view/ventanaLogin.fxml"));
        AnchorPane anchorPane= (AnchorPane)loader.load();
        VentanaLoginController loginController = loader.getController();
        loginController.setAplicacion(app);
        Scene scene= new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("LOGIN");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/News.png")));
        loginController.initPrincipal(stage, this);
        stage.show();
        this.stage1.close();
    }

    public void init(Stage stage, VentanaLoginController loginController) {
        this.ventanaLoginController = loginController;
        this.stage1 = stage;
    }

    public void finalizarEjecucion(ActionEvent actionEvent) {
        System.exit(0);
    }
}
