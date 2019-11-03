package com.metrology.metrics;

import com.metrology.metrics.model.LanguageAnalyzer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class App extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
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


    public static void main(String[] args) throws IOException {
        launch();
    }
}
