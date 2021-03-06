package CompanyManagementRepository.view.SaveEntityControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import CompanyManagementRepository.service.UserService;
import CompanyManagementRepository.view.Main;

import java.io.IOException;

public class AddUserController {
    Main main = new Main();
    UserService userService = new UserService();

    @FXML private TextField emailInput;
    @FXML private TextField firstNameInput;
    @FXML private Label incorrectInputDataLabel;
    @FXML private TextField lastNameInput;
    @FXML private TextField passwordInput;
    @FXML private Label userAddedLabel;

    @FXML
    void addUser(ActionEvent event) {
        try {
            userService.addUser(firstNameInput.getText(), lastNameInput.getText(), emailInput.getText(), passwordInput.getText());
            printUserAdded();
            clearInputs();
        } catch (IOException e) {
            printInvalid(e.getMessage());
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        main.changeScene("/fxml/home.fxml");
    }

    private void printInvalid(String message){
        userAddedLabel.setText("");
        incorrectInputDataLabel.setText(message);
    }

    private void printUserAdded(){
        incorrectInputDataLabel.setText("");
        userAddedLabel.setText("User has been added");
    }

    private void clearInputs(){
        emailInput.clear();
        firstNameInput.clear();
        lastNameInput.clear();
        passwordInput.clear();
    }
}
