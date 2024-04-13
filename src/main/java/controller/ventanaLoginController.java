package controller;

import application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ventanaLoginController {


    private App app;

    private Stage stage;

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
    void iniciarSesion(ActionEvent event) {

    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        txtUsuario.setText("");
        txtContrasenia.setText("");
    }

    @FXML
    void recuperarContrasenia(ActionEvent event) {

    }

}


