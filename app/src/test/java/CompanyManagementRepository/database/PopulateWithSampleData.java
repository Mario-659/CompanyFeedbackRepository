package CompanyManagementRepository.database;

import CompanyManagementRepository.database.DAO.*;
import CompanyManagementRepository.model.Employee;
import CompanyManagementRepository.model.Feedback;
import CompanyManagementRepository.model.Log;
import CompanyManagementRepository.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PopulateWithSampleData {
    static private User user1;
    static private User user2;
    static private User user3;
    static private User user4;

    static private Employee employee1;
    static private Employee employee2;
    static private Employee employee3;
    static private Employee employee4;

    static private Feedback feedback1;
    static private Feedback feedback2;
    static private Feedback feedback3;
    static private Feedback feedback4;

    static private Log log1;
    static private Log log2;
    static private Log log3;
    static private Log log4;

    static private UserDAO userDAO = new UserDAO();
    static private EmployeeDAO employeeDAO = new EmployeeDAO();
    static private LogDAO logDAO = new LogDAO();
    static private FeedbackDAO feedbackDAO = new FeedbackDAO();

    static private void initUsers(){
        user1 = new User("Steven", "Morgan", "steven.morgan@gmail.com", "potato");
        user2 = new User("Stephanie", "Alsop", "steph@email.com", "124house");
        user3 = new User("Gordon", "Rutherford", "gordon@gmail.com", "password");
        user4 = new User("Carl", "Blake", "blake@mail.com", "abcd");
    }

    static private void initEmployees(){
        employee1 = new Employee("Ian", "Newman");
        employee2 = new Employee("Army", "May");
        employee3 = new Employee("Frank", "Clark");
        employee4 = new Employee("Molly", "Allan");
    }

    static private void initFeedbacks(){
        feedback1 = new Feedback(LocalDateTime.parse("2020-11-14T18:30:00"), user1, employee1, true, 3, "Always on time");
        feedback2 = new Feedback(LocalDateTime.parse("2020-12-15T07:30:00"), user2, employee2, false, 5, "Rude to other employees");
        feedback3 = new Feedback(LocalDateTime.parse("2020-12-17T18:30:00"), user2, employee1, false, 1, "Did not want to take extra hours");
        feedback4 = new Feedback(LocalDateTime.parse("2020-12-24T19:30:00"), user4, employee4, true, 2, "Gave everyone a christmas gift");
    }

    static private void initLogs(){
        log1 = new Log(LocalDateTime.parse("2020-11-14T18:30:00"), "Headquarters location", user1, 2, "Deciding that new headquarters will be located in Wroclaw");
        log2 = new Log(LocalDateTime.parse("2020-12-15T07:30:00"), "Hiring employee", user1, 1, "Hiring John");
        log3 = new Log(LocalDateTime.parse("2020-12-17T18:30:00"), "Marketing strategy" , user3, 4, "Hiring new marketing team");
        log4 = new Log(LocalDateTime.parse("2020-12-24T19:30:00"), "Remote work", user2, 5, "60% of team shifted to remote work");
    }


    static public void clearUsers() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "delete from users";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.executeUpdate();
    }

    static public void clearEmployees() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "delete from employees";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.executeUpdate();
    }

    static public void clearLogs() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "delete from logs";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.executeUpdate();
    }

    static public void clearFeedbacks() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "delete from feedbacks";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.executeUpdate();
    }

    static public void clearAllTables() throws SQLException {
        clearUsers();
        clearEmployees();
        clearFeedbacks();
        clearLogs();
    }

    static public void saveUsers() throws SQLException {
        user1 = userDAO.save(user1);
        user2 = userDAO.save(user2);
        user3 = userDAO.save(user3);
        user4 = userDAO.save(user4);
    }

    static public void saveEmployees() throws SQLException {
        employee1 = employeeDAO.save(employee1);
        employee2 = employeeDAO.save(employee2);
        employee3 = employeeDAO.save(employee3);
        employee4 = employeeDAO.save(employee4);
    }

    static public void saveLogs() throws SQLException {
        log1 = logDAO.save(log1);
        log2 = logDAO.save(log2);
        log3 = logDAO.save(log3);
        log4 = logDAO.save(log4);
    }

    static public void saveFeedbacks() throws SQLException {
        feedback1 = feedbackDAO.save(feedback1);
        feedback2 = feedbackDAO.save(feedback2);
        feedback3 = feedbackDAO.save(feedback3);
        feedback4 = feedbackDAO.save(feedback4);
    }

    static public void saveAll() throws SQLException {
        initUsers();
        saveUsers();
        initEmployees();
        saveEmployees();
        initLogs();
        saveLogs();
        initFeedbacks();
        saveFeedbacks();
    }

    public static void main(String[] args) throws SQLException {
        clearAllTables();
        saveAll();
    }
}
