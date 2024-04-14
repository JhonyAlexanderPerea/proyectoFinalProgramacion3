package controller;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ventanaLoginController {


    private App app;

    private Stage stage;

    private ventanaPrincipalController principioController;

    private ventanaPublicacionesController publicacionesController;
    @FXML
    private Button btnCrearCuenta;

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnLimpiarCampos;

    @FXML
    private Button btnOlvidoContrsenia;

    @FXML
    private Label lblContrasenia;

    @FXML
    private Label lblUsuario;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private TextField txtUsuario;

    @FXML
    void crearCuenta(ActionEvent event) {

    }

    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(App.class.getResource("/view/ventanaPublicaciones.fxml"));
        AnchorPane anchorPane= (AnchorPane)loader.load();
        ventanaPublicacionesController publicacionesController= loader.getController();
        publicacionesController.setAplicacion(app);
        Scene scene= new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login Empleado");
        publicacionesController.init(stage, this);
        stage.show();
        this.stage.close();
    }

    public void abrirViewPrincipal(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(App.class.getResource("/view/ventanaPrincipal.fxml"));
        AnchorPane anchorPane= (AnchorPane)loader.load();
        ventanaPrincipalController principalController= loader.getController();
        principalController.setApp(app);
        Scene scene= new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login Empleado");
        principalController.init(stage, this);
        stage.show();
        this.stage.close();
    }
    @FXML
    void limpiarCampos(ActionEvent event) {
        txtUsuario.setText("");
        txtContrasenia.setText("");
    }

    @FXML
    void recuperarContrasenia(ActionEvent event) {

    }

    public void setAplicacion(App aplicacion) {
        this.app = aplicacion;
    }

    public void init(Stage stage, ventanaPrincipalController principalController) {
        this.principioController = principalController;
        this.stage = stage;
    }
}


