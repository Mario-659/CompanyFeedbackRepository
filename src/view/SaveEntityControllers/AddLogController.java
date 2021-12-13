package view.SaveEntityControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.LogsService;
import view.LoginController;
import view.Main;

import java.io.IOException;

public class AddLogController {
    Main main = new Main();
    LogsService logsService = new LogsService();

    @FXML private TextField significanceInput;
    @FXML private TextField subjectInput;
    @FXML private DatePicker dateInput;
    @FXML private Label incorrectInputDataLabel;
    @FXML private TextField descriptionInput;
    @FXML private Label userAddedLabel;

    @FXML
    void addLog(ActionEvent event) {
        if(logsService.addLog(dateInput.getValue().atTime(0, 0), subjectInput.getText(), LoginController.getCurrentUser(), significanceInput.getText(), descriptionInput.getText())) {
            printUserAdded();
            clearInputs();
        }
        else printInvalid("Incorrect input");
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        main.changeScene("/resources/fxml/home.fxml");
    }

    private void printInvalid(String message){
        userAddedLabel.setText("");
        incorrectInputDataLabel.setText(message);
    }

    private void printUserAdded(){
        incorrectInputDataLabel.setText("");
        userAddedLabel.setText("Log has been added");
    }

    private void clearInputs(){
        subjectInput.clear();
        significanceInput.clear();
    }
}
