package database;

import Database.DAO.EmployeeDAO;
import Database.DAO.FeedbackDAO;
import Database.DAO.UserDAO;
import Database.DBConnection;
import model.Employee;
import model.Feedback;
import model.User;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class FeedbackDAOTest {
    private final UserDAO userDAO = new UserDAO();
    private final EmployeeDAO empDAO = new EmployeeDAO();
    private final FeedbackDAO feedbackDAO = new FeedbackDAO();

    private final UserDAOTest userDAOTest = new UserDAOTest();
    private final EmployeeDAOTest employeeDAOTest = new EmployeeDAOTest();

    private User user1;
    private User user2;
    private User user3;
    private User user4;

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private Employee employee4;

    private Feedback feedback1;
    private Feedback feedback2;
    private Feedback feedback3;
    private Feedback feedback4;

    @Test
    public void savesAndReturnsWithId(){
        Feedback prevFeedback = feedback1;
        try {
            feedback1 = feedbackDAO.save(feedback1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        feedback1.getId();
        prevFeedback.setId(feedback1.getId());
        assertEquals(prevFeedback, feedback1);
    }


    @Test
    public void getsAll(){
        try {
            feedbackDAO.save(feedback1);
            feedbackDAO.save(feedback2);
            feedbackDAO.save(feedback3);
            feedbackDAO.save(feedback4);

            List<Feedback> feedbackList = feedbackDAO.getAll();
            assertEquals(4, feedbackList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void extractsProperObjects(){
        try {
            feedbackDAO.save(feedback1);
            feedbackDAO.save(feedback2);
            feedbackDAO.save(feedback3);
            feedbackDAO.save(feedback4);

            List<Feedback> feedbackList = feedbackDAO.getAll();
            User firstUser = feedbackList.get(1).getSubmitter();
            User secondUser = feedbackList.get(2).getSubmitter();
            assertEquals(firstUser.getFirstName(), secondUser.getFirstName());
            assertEquals(firstUser.getId(), secondUser.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deletes(){
        try {
            feedback3 = feedbackDAO.save(feedback3);
            feedbackDAO.delete(feedback3);
            isEmpty();

            feedback2 = feedbackDAO.save(feedback2);
            feedback3 = feedbackDAO.save(feedback3);
            feedbackDAO.delete(feedback2);
            List<Feedback> userList = feedbackDAO.getAll();
            assertEquals(1, userList.size());
            assertEquals(feedback3, userList.get(0));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updates(){
        try {
            String newDescription = "THIS IS NEW DESCRIPTION";
            feedback1 = feedbackDAO.save(feedback1);
            Feedback old = feedback1;
            feedback1.setDescription(newDescription);
            feedbackDAO.update(feedback1);
            Feedback updated = feedbackDAO.get(feedback1);
            assertEquals(old.getId(), updated.getId());
            assertEquals(newDescription, updated.getDescription());
            assertEquals(old.getSignificance(), updated.getSignificance());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearAllTables(){
        try {
            employeeDAOTest.clearEmployees();
            userDAOTest.clearUsers();
            clearFeedbacks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void isEmpty() throws SQLException{
        Connection connection = DBConnection.getConnection();
        String query = "select * from feedbacks";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet results = preStat.getResultSet();
        assertFalse(results.next());
    }

    public void clearFeedbacks() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "delete from feedbacks";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.executeUpdate();
        isEmpty();
    }

    private void initUsers(){
        user1 = new User("Steven", "Morgan", "steven.morgan@gmail.com", "potato");
        user2 = new User("Stephanie", "Alsop", "steph@email.com", "124house");
        user3 = new User("Gordon", "Rutherford", "gordon@gmail.com", "password");
        user4 = new User("Carl", "Blake", "blake@mail.com", "abcd");
    }

    private void initEmployees(){
        employee1 = new Employee("Ian", "Newman");
        employee2 = new Employee("Army", "May");
        employee3 = new Employee("Frank", "Clark");
        employee4 = new Employee("Molly", "Allan");
    }

    private void saveEmployees(){
        try {
            employee1 = empDAO.save(employee1);
            employee2 = empDAO.save(employee2);
            employee3 = empDAO.save(employee3);
            employee4 = empDAO.save(employee4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveUsers(){
        try {
            user1 = userDAO.save(user1);
            user2 = userDAO.save(user2);
            user3 = userDAO.save(user3);
            user4 = userDAO.save(user4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void prepareParams(){
        initEmployees();
        saveEmployees();
        initUsers();
        saveUsers();
    }

    private void initFeedbacks(){
        feedback1 = new Feedback(LocalDateTime.parse("2020-11-14T18:30:00"), user1, employee1, true, 3, "Always on time");
        feedback2 = new Feedback(LocalDateTime.parse("2020-12-15T07:30:00"), user2, employee2, false, 5, "Rude to other employees");
        feedback3 = new Feedback(LocalDateTime.parse("2020-12-17T18:30:00"), user2, employee1, false, 1, "Did not want to take extra hours");
        feedback4 = new Feedback(LocalDateTime.parse("2020-12-24T19:30:00"), user4, employee4, true, 2, "Gave everyone a christmas gift");
    }

    @BeforeEach
    private void prepareData(){
        clearAllTables();
        prepareParams();
        initFeedbacks();
    }
}
