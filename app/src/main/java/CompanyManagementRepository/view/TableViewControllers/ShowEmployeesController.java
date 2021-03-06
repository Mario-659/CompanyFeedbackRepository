package CompanyManagementRepository.view.TableViewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import CompanyManagementRepository.model.Employee;
import CompanyManagementRepository.service.EmployeeService;
import CompanyManagementRepository.view.Main;

import java.io.IOException;

public class ShowEmployeesController {
    EmployeeService employeeService = new EmployeeService();

    @FXML private TableView<Employee> employeesTable = new TableView<Employee>();

    @FXML private TableColumn<Employee, String> firstNameCol;

    @FXML private TableColumn<Employee, Integer> idCol;

    @FXML private TableColumn<Employee, String> lastNameCol;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Main main = new Main();
        main.changeScene("/fxml/home.fxml");
    }

    @FXML
    private void initialize(){
        employeesTable.setItems(employeeService.getEmployees());
        idCol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
    }
}