package database;

import Database.DAO.EmployeeDAO;
import Database.DBConnection;
import model.Employee;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class EmployeeDAOTest {
    private EmployeeDAO empDAO = new EmployeeDAO();

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private Employee employee4;

    @BeforeEach
    public void clearEmployees() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "delete from employees";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.executeUpdate();
        isEmpty();
    }

    @BeforeEach
    private void initEmployees(){
        employee1 = new Employee("Ian", "Newman");
        employee2 = new Employee("Army", "May");
        employee3 = new Employee("Frank", "Clark");
        employee4 = new Employee("Molly", "Allan");
    }


    private void isEmpty() throws SQLException{
        Connection connection = DBConnection.getConnection();
        String query = "select * from employees";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet results = preStat.getResultSet();
        assertFalse(results.next());
    }

    @Test
    public void savesAndReturnsWithId(){
        Employee previousEmp = employee1;
        try {
            employee1 = empDAO.save(employee1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        employee1.getId();
        assertEquals(previousEmp.getFirstName(), employee1.getFirstName());
        assertEquals(previousEmp.getLastName(), employee1.getLastName());
    }

    @Test
    public void getsAll(){
        try {
            empDAO.save(employee1);
            empDAO.save(employee2);
            empDAO.save(employee3);
            empDAO.save(employee4);

            List<Employee> empList = empDAO.getAll();
            assertEquals(4, empList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deletes(){
        try {
            employee1 = empDAO.save(employee1);
            empDAO.delete(employee1);
            isEmpty();

            employee2 = empDAO.save(employee2);
            employee3 = empDAO.save(employee3);
            empDAO.delete(employee2);
            List<Employee> empList = empDAO.getAll();
            assertEquals(1, empList.size());
            assertEquals(employee3.getFirstName(), empList.get(0).getFirstName());
            assertEquals(employee3.getLastName(), empList.get(0).getLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updates(){
        try {
            String newName = "Jonh";
            employee1 = empDAO.save(employee1);
            Employee old = employee1;
            employee1.setFirstName(newName);
            empDAO.update(employee1);
            Employee updated = empDAO.get(employee1);
            assertEquals(old.getId(), updated.getId());
            assertEquals(newName, updated.getFirstName());
            assertEquals(old.getLastName(), updated.getLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
