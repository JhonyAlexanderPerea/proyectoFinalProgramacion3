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

public class ventanaRegistroController {

    private App app;
    private ventanaLoginController ventanaLoginController;
    private Stage stage;
    private controller.ventanaregistroClienteController ventanaregistroClienteController;
    @FXML
    private Button btnSalir;

    @FXML
    private Button btnRegistroCliente;

    @FXML
    private Button btnRegistroPublicador;
    private controller.ventanaLoginPublicadorController ventanaLoginPublicadorController;


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
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/News.png")));
        loginController.initRegistro(stage, this);
        stage.show();
        this.stage.close();
    }

    @FXML
    void abrirRegistroCliente(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(App.class.getResource("/view/ventanaRegistroCliente.fxml"));
        AnchorPane anchorPane= (AnchorPane)loader.load();
        ventanaregistroClienteController registroClienteController = loader.getController();
        registroClienteController.setAplicacion(app);
        Scene scene= new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/News.png")));
        registroClienteController.initRegistroCliente(stage, this);
        stage.show();
        this.stage.close();
    }

   @FXML
    void abrirRegistroPublicador(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(App.class.getResource("/view/ventanaRegistroPublicador.fxml"));
        AnchorPane anchorPane= (AnchorPane)loader.load();
        ventanaLoginPublicadorController registroPublicadorController = loader.getController();
        registroPublicadorController.setAplicacion(app);
        Scene scene= new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/News.png")));
        registroPublicadorController.initRegistroPublicador(stage, this);
        stage.show();
        this.stage.close();
    }

    public void init(Stage stage, ventanaLoginController ventanaLoginController) {
        this.ventanaLoginController = ventanaLoginController;
        this.stage = stage;
    }

    public void initRegistroCliente(Stage stage, ventanaregistroClienteController ventanaregistroClienteController) {
        this.ventanaregistroClienteController = ventanaregistroClienteController;
        this.stage = stage;
    }

    public void setApp(App app) {
        this.app = app;
    }


    public void initRegistroPublicador(Stage stage, ventanaLoginPublicadorController ventanaLoginPublicadorController) {
        this.ventanaLoginPublicadorController = ventanaLoginPublicadorController;
        this.stage = stage;
    }
}
