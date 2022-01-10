package CompanyManagementRepository.service;

import CompanyManagementRepository.database.DAO.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import CompanyManagementRepository.model.Employee;
import CompanyManagementRepository.validators.EmployeeInputValidation;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeService {
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
    private static EmployeeInputValidation employeeInputValidation = new EmployeeInputValidation();
    private static ObservableList<Employee> employees = FXCollections.observableArrayList();

    public ObservableList<Employee> getEmployees() {
        update();
        return employees;
    }

    private void update(){
        try {
            employees.setAll(employeeDAO.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addEmployee(String firstName, String lastName) throws  IOException {
        try {
            Employee employee = employeeInputValidation.validate(new String[]{firstName, lastName});
            employeeDAO.save(employee);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
