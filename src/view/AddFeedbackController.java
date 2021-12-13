package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Employee;
import service.EmployeeService;
import service.FeedbackService;
import service.LogsService;
import service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddFeedbackController {
    Main main = new Main();
    FeedbackService feedbackService = new FeedbackService();
    EmployeeService employeeService = new EmployeeService();

    @FXML private ChoiceBox<Employee> employeeChoice = new ChoiceBox<>();
    @FXML private ChoiceBox<String> isPositiveChoice = new ChoiceBox<>();
    @FXML private TextField significanceInput;
    @FXML private DatePicker dateInput;
    @FXML private Label incorrectInputDataLabel;
    @FXML private TextField descriptionInput;
    @FXML private Label userAddedLabel;

    private ObservableList<Employee> employees;
    private Employee selectedEmployee;
    private boolean selectedPositive;

    @FXML
    void addLog(ActionEvent event) {
        if(feedbackService.addFeedback(dateInput.getValue().atTime(0, 0), LoginController.getCurrentUser(), selectedEmployee, selectedPositive, significanceInput.getText(), descriptionInput.getText())) {
            printUserAdded();
            clearInputs();
        }
        else printInvalid("Incorrect input");
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        main.changeScene("/resources/fxml/home.fxml");
    }

    @FXML
    private void initialize(){
        employees = employeeService.getEmployees();
        employeeChoice.setItems(employees);

        String[] posNeg = new String[]{"Positive", "Negative"};
        isPositiveChoice.setItems(FXCollections.observableArrayList("Positive", "Negative"));


        employeeChoice.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue oV, Number value, Number newValue) {
                        selectedEmployee = employees.get(newValue.intValue());
                    }
                }
        );
        employeeChoice.getSelectionModel().selectFirst();


        isPositiveChoice.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue oV, Number value, Number newValue) {
                        selectedPositive = getBool(newValue.intValue());
                    }
                }
        );
        isPositiveChoice.getSelectionModel().selectFirst();

    }

    private void printInvalid(String message){
        userAddedLabel.setText("");
        incorrectInputDataLabel.setText(message);
    }

    private void printUserAdded(){
        incorrectInputDataLabel.setText("");
        userAddedLabel.setText("Feedback has been added");
    }

    private boolean getBool(int index){
        return index == 0;
    }

    private void clearInputs(){
        significanceInput.clear();
        descriptionInput.clear();
    }
}
