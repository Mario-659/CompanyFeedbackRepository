package database;

import Database.DAO.UserDAO;
import Database.DBConnection;
import Database.model.User;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class UserDAOTest {
    private final UserDAO userDAO = new UserDAO();

    private User user1;
    private User user2;
    private User user3;
    private User user4;

    @BeforeEach
    public void clearUsers() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "delete from users";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.executeUpdate();
        isEmpty();
    }

    @BeforeEach
    private void initUsers(){
        user1 = new User("Steven", "Morgan", "steven.morgan@gmail.com", "potato");
        user2 = new User("Stephanie", "Alsop", "steph@email.com", "124house");
        user3 = new User("Gordon", "Rutherford", "gordon@gmail.com", "password");
        user4 = new User("Carl", "Blake", "blake@mail.com", "abcd");
    }


    private void isEmpty() throws SQLException{
        Connection connection = DBConnection.getConnection();
        String query = "select * from users";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet results = preStat.getResultSet();
        assertFalse(results.next());
    }

    @Test
    public void savesAndReturnsWithId(){
        User previousUser = user1;
        try {
            user1 = userDAO.save(user1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertInstanceOf(Integer.class, user1.getId());
        assertEquals(previousUser.getFirstName(), user1.getFirstName());
        assertEquals(previousUser.getLastName(), user1.getLastName());
    }

    @Test
    public void getsAll(){
        try {
            userDAO.save(user1);
            userDAO.save(user2);
            userDAO.save(user3);
            userDAO.save(user4);

            List<User> userList = userDAO.getAll();
            assertEquals(4, userList.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deletes(){
        try {
            user1 = userDAO.save(user1);
            userDAO.delete(user1);
            isEmpty();

            user2 = userDAO.save(user2);
            user3 = userDAO.save(user3);
            userDAO.delete(user2);
            List<User> userList = userDAO.getAll();
            assertEquals(1, userList.size());
            assertEquals(user3.getFirstName(), userList.get(0).getFirstName());
            assertEquals(user3.getLastName(), userList.get(0).getLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updates(){
        try {
            String newName = "Jonh";
            user1 = userDAO.save(user1);
            User old = user1;
            user1.setFirstName(newName);
            userDAO.update(user1);
            User updated = userDAO.get(user1);
            assertEquals(old.getId(), updated.getId());
            assertEquals(newName, updated.getFirstName());
            assertEquals(old.getLastName(), updated.getLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
