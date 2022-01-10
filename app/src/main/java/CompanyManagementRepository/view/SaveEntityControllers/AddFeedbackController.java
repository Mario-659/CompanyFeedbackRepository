package CompanyManagementRepository.view.SaveEntityControllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import CompanyManagementRepository.model.Employee;
import CompanyManagementRepository.service.EmployeeService;
import CompanyManagementRepository.service.FeedbackService;
import CompanyManagementRepository.view.LoginController;
import CompanyManagementRepository.view.Main;

import java.io.IOException;

public class AddFeedbackController {
    Main main = new Main();
    FeedbackService feedbackService = new FeedbackService();
    EmployeeService employeeService = new EmployeeService();

    @FXML private ChoiceBox<Employee> employeeChoice = new ChoiceBox<>();
    @FXML private ChoiceBox<String> isPositiveChoice = new ChoiceBox<>();
    @FXML private ChoiceBox<Integer> significanceChoice = new ChoiceBox<>();
    @FXML private DatePicker dateInput;
    @FXML private Label incorrectInputDataLabel;
    @FXML private TextArea descriptionInput;
    @FXML private Label userAddedLabel;

    private ObservableList<Employee> employees;
    private Employee selectedEmployee;
    private boolean selectedPositive;
    private int selectedSignificance;

    @FXML
    void addLog(ActionEvent event) {
        try {
            feedbackService.addFeedback(dateInput.getValue(), LoginController.getCurrentUser(), selectedEmployee, selectedPositive, selectedSignificance, descriptionInput.getText());
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

    @FXML
    private void initialize(){
        employees = employeeService.getEmployees();
        employeeChoice.setItems(employees);
        isPositiveChoice.setItems(FXCollections.observableArrayList("Positive", "Negative"));
        significanceChoice.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5));

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

    private void printUserAdded(){
        incorrectInputDataLabel.setText("");
        userAddedLabel.setText("Feedback has been added");
    }

    private boolean getBool(int index){
        return index == 0;
    }

    private void clearInputs(){
        descriptionInput.clear();
    }
}
