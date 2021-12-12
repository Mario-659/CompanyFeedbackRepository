package service;

import Database.DAO.EmployeeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Employee;

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
}
