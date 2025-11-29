package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    @Override
    public void start(Stage primaryStage) {
        try {
            Scene scene = new Scene(
                    FXMLLoader.load(getClass().getResource("/application/login.fxml")),
                    400,
                    300
            );
            primaryStage.setTitle("Library Management System - Login");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error launching login UI", e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
