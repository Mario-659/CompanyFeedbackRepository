package service;

import database.DAO.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import validators.EmployeeInputValidation;
import validators.InputValidator;

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
