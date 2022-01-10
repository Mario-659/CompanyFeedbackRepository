package CompanyManagementRepository.database.DAO;

import CompanyManagementRepository.database.DBConnection;
import CompanyManagementRepository.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements DAO<Employee> {
    private static Connection connection = null;

    static{
        connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(15);
            statement = connection.createStatement();
            statement.executeUpdate(
                    "create table if not exists employees(" +
                            "EmployeeId integer PRIMARY KEY," +
                            "FirstName string, " +
                            "LastName string)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee save(Employee employee) throws SQLException {
        String query = "insert into employees (FirstName, LastName) values(?,?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        setStatement(preStat, employee);
        preStat.executeUpdate();
        return getLastInsertedEmployee();
    }

    @Override
    public void delete(Employee employee) throws SQLException {
        String query = "delete from employees where EmployeeId=(?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setInt(1, employee.getId());
        preStat.executeUpdate();
    }

    @Override
    public Employee get(Employee employee) throws SQLException {
        return get(employee.getId());
    }

    @Override
    public Employee get(int id) throws SQLException {
        String query = "select * from employees where EmployeeId=(?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setInt(1, id);
        ResultSet result = preStat.executeQuery();
        return getEmployee(result);
    }

    @Override
    public List<Employee> getAll() throws SQLException {
       String query = "select * from employees";
       PreparedStatement preStat = connection.prepareStatement(query);
       ResultSet results = preStat.executeQuery();
       List<Employee> employees = new ArrayList<>();
       while(results.next()){
           employees.add(getEmployee(results));
       }
       return employees;
    }

    @Override
    public void update(Employee employee) throws SQLException {
        String query = "update employees set " +
                "FirstName=?, " +
                "LastName=? where EmployeeId=(?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        setStatement(preStat, employee);
        preStat.setInt(3, employee.getId());
        preStat.executeUpdate();
    }

    private void setStatement(PreparedStatement statement, Employee employee) throws SQLException{
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
    }

    private Employee getEmployee(ResultSet result) throws SQLException{
        Employee employee = new Employee();
        employee.setId(result.getInt("EmployeeId"));
        employee.setFirstName(result.getString("FirstName"));
        employee.setLastName(result.getString("LastName"));
        return employee;
    }

    private Employee getLastInsertedEmployee() throws SQLException{
        String query = "select * from employees where EmployeeId=last_insert_rowid()";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet result = preStat.executeQuery();
        return getEmployee(result);
    }
}
