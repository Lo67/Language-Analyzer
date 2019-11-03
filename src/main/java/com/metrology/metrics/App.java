package com.metrology.metrics;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Go.png")));
        this.primaryStage.setTitle("Jilb metrics");
        this.primaryStage.centerOnScreen();

        initRootLayout();
    }

    private void initRootLayout() {
        try {
            Parent mainLayout = FXMLLoader.load(getClass().getResource("/Main.fxml"));
            Scene scene = new Scene(mainLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch();
    }

}
