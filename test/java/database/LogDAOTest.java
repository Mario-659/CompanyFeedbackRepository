package database;

import Database.DAO.LogDAO;
import Database.DAO.UserDAO;
import Database.DBConnection;
import model.Log;
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


public class LogDAOTest {
    private final UserDAO userDAO = new UserDAO();
    private final LogDAO logDAO = new LogDAO();

    private final UserDAOTest userDAOTest = new UserDAOTest();

    private User user1;
    private User user2;
    private User user3;
    private User user4;

    private Log log1;
    private Log log2;
    private Log log3;
    private Log log4;

    @Test
    public void savesAndReturnsWithId(){
        Log prevLog = log1;
        try {
            log1 = logDAO.save(log1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log1.getId();
        prevLog.setId(log1.getId());
        assertEquals(prevLog, log1);
    }


    @Test
    public void getsAll(){
        try {
            logDAO.save(log1);
            logDAO.save(log2);
            logDAO.save(log3);
            logDAO.save(log4);

            List<Log> logList = logDAO.getAll();
            assertEquals(4, logList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void extractsProperObjects(){
        try {
            logDAO.save(log1);
            logDAO.save(log2);
            logDAO.save(log3);
            logDAO.save(log4);

            List<Log> logList = logDAO.getAll();
            User firstUser = logList.get(0).getUser();
            User secondUser = logList.get(1).getUser();
            assertEquals(firstUser, secondUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deletes(){
        try {
            log3 = logDAO.save(log3);
            logDAO.delete(log3);
            isEmpty();

            log2 = logDAO.save(log2);
            log3 = logDAO.save(log3);
            logDAO.delete(log2);
            List<Log> logList = logDAO.getAll();
            assertEquals(1, logList.size());
            assertEquals(log3, logList.get(0));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updates(){
        try {
            String newDescription = "THIS IS NEW DESCRIPTION";
            log1 = logDAO.save(log1);
            Log old = log1;
            log1.setDescription(newDescription);
            logDAO.update(log1);
            Log updated = logDAO.get(log1);
            assertEquals(old.getId(), updated.getId());
            assertEquals(newDescription, updated.getDescription());
            assertEquals(old.getSignificance(), updated.getSignificance());
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

    public void clearAllTables(){
        try {
            userDAOTest.clearUsers();
            clearLogs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearLogs() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "delete from logs";
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

    private void initLogs(){
        log1 = new Log(LocalDateTime.parse("2020-11-14T18:30:00"), "Headquarters location", user1, 2, "Deciding that new headquarters will be located in Wroclaw");
        log2 = new Log(LocalDateTime.parse("2020-12-15T07:30:00"), "Hiring employee", user1, 1, "Hiring John");
        log3 = new Log(LocalDateTime.parse("2020-12-17T18:30:00"), "Marketing strategy" , user3, 4, "Hiring new marketing team");
        log4 = new Log(LocalDateTime.parse("2020-12-24T19:30:00"), "Remote work", user2, 5, "60% of team shifted to remote work");
    }

    private void prepareParams(){
        initUsers();
        saveUsers();
    }

    @BeforeEach
    private void prepareData(){
        clearAllTables();
        prepareParams();
        initLogs();
    }
}
