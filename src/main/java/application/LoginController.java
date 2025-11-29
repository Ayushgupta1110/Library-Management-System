package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    public void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            messageLabel.setText("Please enter username and password");
            return;
        }

        User user = UserData.getInstance().authenticate(username.trim(), password);
        if (user == null) {
            messageLabel.setText("Invalid credentials");
            return;
        }

        openDashboardAndCloseLogin();
    }

    private void openDashboardAndCloseLogin() {
        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene dashboardScene = new Scene(
                    FXMLLoader.load(getClass().getResource("/application/dashboard.fxml")),
                    1000,
                    700
            );
            stage.setTitle("Library Management System - Dashboard");
            stage.setScene(dashboardScene);
            stage.setResizable(true);
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
            messageLabel.setText("Failed to open dashboard");
        }
    }
}
