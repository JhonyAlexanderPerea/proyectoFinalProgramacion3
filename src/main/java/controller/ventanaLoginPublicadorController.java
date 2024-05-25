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

public class ventanaLoginPublicadorController {

    @FXML
    private Button btnSalir;
    private App app;
    private controller.ventanaRegistroController ventanaRegistroController;
    private Stage stage;


    @FXML
    void abrirViewRegistro(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/view/ventanaRegistro.fxml"));
        AnchorPane anchorPane = (AnchorPane) loader.load();
        ventanaRegistroController registroController = loader.getController();
        registroController.setApp(app);
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/News.png")));
        stage.setScene(scene);
        stage.setTitle("REGISTRO");
        registroController.initRegistroPublicador(stage, this);
        stage.show();
        this.stage.close();
    }

    public void setAplicacion(App app) {
        this.app = app;
    }

    public void initRegistroPublicador(Stage stage, ventanaRegistroController ventanaRegistroController) {
        this.ventanaRegistroController = ventanaRegistroController;
        this.stage = stage;
    }
}
