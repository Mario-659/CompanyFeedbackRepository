package Database.DAO;

import Database.DBConnection;
import Database.model.Employee;
import Database.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {
    private static Connection connection = null;

    static{
        connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(15);
            statement = connection.createStatement();
            statement.executeUpdate(
                    "create table if not exists users(" +
                    "UserId integer PRIMARY KEY," +
                    "FirstName string, " +
                    "LastName string," +
                    "Email string UNIQUE, " +
                    "Password string )");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User save(User user) throws SQLException {
        String query = ("insert into users (FirstName, LastName, Email, Password) values(?,?,?,?)");
        PreparedStatement preStat = connection.prepareStatement(query);
        setStatement(preStat, user);
        preStat.executeUpdate();
        return getLastInsertedUser();
    }

    @Override
    public void delete(User user) throws SQLException {
        String query = "delete from users where UserId=(?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setInt(1, user.getId());
        preStat.executeUpdate();
    }

    @Override
    public User get(User user) throws SQLException {
        return get(user.getId());
    }

    @Override
    public User get(int id) throws SQLException{
        String query = "select * from users where UserId=(?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setInt(1, id);
        ResultSet result = preStat.executeQuery();
        return getUser(result);
    }

    @Override
    public List<User> getAll() throws SQLException {
        String query = "select * from users";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet results = preStat.executeQuery();
        List<User> users = new ArrayList<>();
        while(results.next()){
            users.add(getUser(results));
        }
        return users;
    }

    @Override
    public void update(User user) throws SQLException {
        String query = "update users set " +
                "FirstName=?, " +
                "LastName=?, " +
                "Email=?, " +
                "Password=? where UserId=(?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        setStatement(preStat, user);
        preStat.setInt(5, user.getId());
        preStat.executeUpdate();
    }

    private void setStatement(PreparedStatement statement, User user) throws SQLException{
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getPassword());
    }

    private User getUser(ResultSet result) throws SQLException{
        User user = new User();
        user.setId(result.getInt("UserId"));
        user.setFirstName(result.getString("FirstName"));
        user.setLastName(result.getString("LastName"));
        user.setEmail(result.getString("Email"));
        user.setPassword(result.getString("Password"));
        return user;
    }

    private User getLastInsertedUser() throws SQLException{
        String query = "select * from users where UserId=last_insert_rowid()";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet result = preStat.executeQuery();
        return getUser(result);
    }
}
