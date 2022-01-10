package CompanyManagementRepository.view.SaveEntityControllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import CompanyManagementRepository.service.LogsService;
import CompanyManagementRepository.view.LoginController;
import CompanyManagementRepository.view.Main;

import java.io.IOException;

public class AddLogController {
    Main main = new Main();
    LogsService logsService = new LogsService();

    @FXML private ChoiceBox<Integer> significanceChoice = new ChoiceBox<>();
    @FXML private TextField subjectInput;
    @FXML private DatePicker dateInput;
    @FXML private Label incorrectInputDataLabel;
    @FXML private TextArea descriptionInput;
    @FXML private Label userAddedLabel;

    private int selectedSignificance;

    @FXML
    void addLog(ActionEvent event) {
        try {
            logsService.addLog(dateInput.getValue(), subjectInput.getText(), LoginController.getCurrentUser(), selectedSignificance, descriptionInput.getText());
            printLogAdded();
            clearInputs();
        } catch (IOException e) {
            printInvalid(e.getMessage());
        }
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        main.changeScene("/fxml/home.fxml");
    }

    @FXML
    private void initialize(){
        significanceChoice.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));
        significanceChoice.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue oV, Number value, Number newValue) {
                        selectedSignificance = newValue.intValue() + 1;
                    }
                }
        );
        significanceChoice.getSelectionModel().selectFirst();
    }

    private void printInvalid(String message){
        userAddedLabel.setText("");
        incorrectInputDataLabel.setText(message);
    }

    private void printLogAdded(){
        incorrectInputDataLabel.setText("");
        userAddedLabel.setText("Log has been added");
    }

    private void clearInputs(){
        subjectInput.clear();
        descriptionInput.clear();
    }
}
