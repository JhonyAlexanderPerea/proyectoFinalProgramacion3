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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Seguridad;

import java.io.IOException;

public class VentanaLoginController {

    private App app;

    private Stage stage;

    private VentanaPrincipalController principioController;

    private VentanaPublicacionesController publicacionesController;

    private VentanaRegistroController ventanaRegistroController;

    private VentanaRecuperarContraseñaController ventanaRecuperarContraseñaController;

    private boolean showPassword = false;

    private Seguridad seguridad = new Seguridad();

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
    private PasswordField txtContraseña;

    @FXML
    private TextField txtUsuario;

    @FXML
    void crearCuenta(ActionEvent event) {

    }

    @FXML
    void iniciarSesion(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/view/ventanaPublicaciones.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            VentanaPublicacionesController publicacionesController = loader.getController();
            publicacionesController.setApp(app);
            Scene scene = new Scene(anchorPane);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/News.png")));
            stage.setScene(scene);
            stage.setTitle("PUBLICACIONES");
            publicacionesController.init(stage, this);
            stage.show();
            this.stage.close();
    }



    @FXML
    public void abrirViewPrincipal(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/view/ventanaPrincipal.fxml"));
        AnchorPane anchorPane = (AnchorPane) loader.load();
        VentanaPrincipalController principalController = loader.getController();
        principalController.setApp(app);
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/News.png")));
        stage.setScene(scene);
        stage.setTitle("PRINCIPAL");
        principalController.init(stage, this);
        stage.show();
        this.stage.close();
    }

    @FXML
    public void abrirViewRegistro(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/view/ventanaRegistro.fxml"));
        AnchorPane anchorPane = (AnchorPane) loader.load();
        VentanaRegistroController registroController = loader.getController();
        registroController.setApp(app);
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/News.png")));
        stage.setScene(scene);
        stage.setTitle("REGISTRO");
       registroController.init(stage, this);
        stage.show();
        this.stage.close();
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        txtUsuario.setText("");
        txtContraseña.setText("");
    }

    @FXML
    public void abrirViewRecuperarContraseña(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/view/ventanaRecuperarContraseña.fxml"));
        AnchorPane anchorPane = (AnchorPane) loader.load();
        VentanaRecuperarContraseñaController recuperarContraseñaControllerr = loader.getController();
        recuperarContraseñaControllerr.setApp(app);
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/News.png")));
        stage.setScene(scene);
        stage.setTitle("RECUPERAR CONTRASEÑA");
        recuperarContraseñaControllerr.init(stage, this);
        stage.show();
        this.stage.close();
    }

    @FXML
    public void mostrarContraseña(ActionEvent actionEvent) {
        showPassword = !showPassword;
        if (showPassword) {
            txtContraseña.setPromptText(txtContraseña.getText());
            txtContraseña.setText("");
        } else {
            txtContraseña.setText(txtContraseña.getPromptText());
            txtContraseña.setPromptText("Ingrese contraseña");
        }
    }

    public void setAplicacion(App aplicacion) {
        this.app = aplicacion;
    }

    public void initPrincipal(Stage stage, VentanaPrincipalController principalController) {
        this.principioController = principalController;
        this.stage = stage;
    }

    public void initPublicaciones(Stage stage, VentanaPublicacionesController publicacionesController) {
        this.publicacionesController = publicacionesController;
        this.stage = stage;
    }


    public void initRegistro(Stage stage, VentanaRegistroController ventanaRegistroController) {
        this.ventanaRegistroController = ventanaRegistroController;
        this.stage = stage;
    }


    public void initRecuperar(Stage stage, VentanaRecuperarContraseñaController ventanaRecuperarContraseñaController) {
        this.ventanaRecuperarContraseñaController = ventanaRecuperarContraseñaController;
        this.stage = stage;
    }
}


