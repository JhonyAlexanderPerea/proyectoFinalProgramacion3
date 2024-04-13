package controller;

import application.Aplicacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ventanaLoginController {

    @FXML
    private Button btnCrearCuenta;

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnLimpiarCampos;

    @FXML
    private Button btnOlvideContrasenia;

    @FXML
    private ImageView imgLimpiar;

    @FXML
    private Label lblContrasenia;

    @FXML
    private Label lblLogin;

    @FXML
    private Label lblUsuario;

    @FXML
    private PasswordField txtContrasenia;

    @FXML
    private TextField txtUsuario;

    private Aplicacion aplicacion;

    private Stage stage;

    @FXML
    void limpiarCampos(ActionEvent event) {
        txtUsuario.setText("");
        txtContrasenia.setText("");
    }


}


