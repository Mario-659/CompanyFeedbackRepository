package service;

import database.DAO.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;
import validators.InputValidator;

import java.sql.SQLException;

public class EmployeeService {
    private static EmployeeDAO employeeDAO = new EmployeeDAO();
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

    public boolean addEmployee(String firstName, String lastName) {
        firstName = InputValidator.validateName(firstName);
        lastName = InputValidator.validateName(lastName);
        if(firstName == null || lastName==null) return false;
        Employee employee = new Employee(firstName, lastName);
        try {
            employeeDAO.save(employee);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
