package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.EmployeeService;

import java.io.IOException;

public class AddEmployeeController {
    Main main = new Main();
    EmployeeService employeeService = new EmployeeService();

    @FXML private TextField firstNameInput;
    @FXML private TextField lastNameInput;
    @FXML private Label userAddedLabel;
    @FXML private Label incorrectInputDataLabel;

    @FXML
    void addEmployee(ActionEvent event) {
        if(employeeService.addEmployee(firstNameInput.getText(), lastNameInput.getText())) {
            printEmployeeAdded();
            clearInputs();
        }
        else printInvalid("Incorrect input");
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        main.changeScene("../resources/fxml/home.fxml");
    }

    private void clearInputs(){
        firstNameInput.clear();
        lastNameInput.clear();
    }

    private void printInvalid(String message){
        userAddedLabel.setText("");
        incorrectInputDataLabel.setText(message);
    }

    private void printEmployeeAdded(){
        incorrectInputDataLabel.setText("");
        userAddedLabel.setText("Employee has been added");
    }
}