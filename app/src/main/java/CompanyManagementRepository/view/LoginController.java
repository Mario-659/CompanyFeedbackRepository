package CompanyManagementRepository.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import CompanyManagementRepository.model.User;
import CompanyManagementRepository.service.LoginService;

import java.io.IOException;

public class LoginController {
    private final LoginService loginService = new LoginService();
    private static User currentUser = null;

    @FXML private TextField emailInput;
    @FXML private Label invalidField;
    @FXML private PasswordField passwordInput;

    @FXML
    void login(ActionEvent event) throws IOException {
        String email = emailInput.getText();
        String password = passwordInput.getText();
        try {
            currentUser = loginService.login(email, password);
            moveToHomepage();
        } catch (IOException e) {
            printInvalid(e.getMessage());
        }
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    private void printInvalid(String message){
        invalidField.setText(message);
    }

    private void moveToHomepage() throws IOException {
        Main main = new Main();
        main.changeScene("/fxml/home.fxml");
    }
}