package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;
import service.LoginService;

import java.io.IOException;

public class LoginController {
    private final LoginService loginService = new LoginService();
    private static User currentUser = null;

    @FXML private TextField emailInput;
    @FXML private Label invalidField;
    @FXML private Button loginButton;
    @FXML private PasswordField passwordInput;

    @FXML
    void login(ActionEvent event) throws IOException {
        String email = emailInput.getText();
        String password = passwordInput.getText();
        if(loginService.login(email, password)) {
            currentUser = loginService.getUser(email);
            moveToHomepage();
        }
        else printInvalid();
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    private void printInvalid(){
        invalidField.setText("Invalid email or password. Please try again");
    }

    private void moveToHomepage() throws IOException {
        Main main = new Main();
        main.changeScene("../resources/fxml/home.fxml");
    }
}