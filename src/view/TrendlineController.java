package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import model.Employee;
import service.EmployeeService;
import service.FeedbackService;

import java.io.IOException;

public class TrendlineController {
    private Main main = new Main();
    private EmployeeService employeeService = new EmployeeService();
    private FeedbackService feedbackService = new FeedbackService();
    private ObservableList<Employee> employees;

    private Employee selectedEmployee;

    @FXML private ChoiceBox<Employee> empChoice = new ChoiceBox<>();
    @FXML private Label outPutLabel;

    @FXML
    private void initialize(){
        employees = employeeService.getEmployees();
        empChoice.setItems(employees);
        empChoice.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue oV, Number value, Number newValue) {
                        selectedEmployee = employees.get(newValue.intValue());
                        outPutLabel.setText(feedbackService.getTrend(selectedEmployee));
                    }
                }
        );
        empChoice.getSelectionModel().selectFirst();
    }

    @FXML
    public void goBack(ActionEvent actionEvent) throws IOException {
        main.changeScene("/resources/fxml/home.fxml");
    }
}
