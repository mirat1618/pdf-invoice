package me.mirat1618.pdfinvoice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage)  {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 910, 590);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Квитанция PDF");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() {
        //
    }

    public static void main(String[] args) {
        launch();
    }
}