package controller;

import application.App;
import javafx.stage.Stage;

public class ventanaLoginController {


    private App app;

    private Stage stage;

    public void setAplicacion(App app) {
        this.app = app;
    }

    public void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public void show() {
        stage.show();
    }
}


