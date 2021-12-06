package Database.DAO;

import Database.DBConnection;
import Database.model.Employee;
import Database.model.Feedback;
import Database.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO implements DAO<Feedback> {
    private static Connection connection = null;

    private UserDAO userDAO = new UserDAO();
    private EmployeeDAO empDAO = new EmployeeDAO();

    static{
        connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(15);
            statement = connection.createStatement();
            statement.executeUpdate(
                    "create table if not exists feedbacks(" +
                            "FeedbackId integer PRIMARY KEY," +
                            "Date date, " +
                            "UserId integer, " +
                            "EmployeeId integer, " +
                            "Positive integer, " +
                            "Significance integer, " +
                            "Description string, " +
                            "FOREIGN KEY(UserId, EmployeeId) REFERENCES users(UserId), employees(EmployeeId) )");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Feedback feedback) throws SQLException {
        String query = "insert into feedbacks (Date, UserId, EmployeeId, Positive, Significance, Description) values(?, ?, ?, ?, ?, ?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        setStatement(preStat, feedback);
        preStat.executeUpdate();
    }

    @Override
    public void delete(Feedback feedback) throws SQLException {
        String query = "delete from feedbacks where FeedbackId=(?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setInt(1, feedback.getId());
        preStat.executeUpdate();
    }

    @Override
    public Feedback get(Feedback feedback) throws SQLException {
        return getByID(feedback.getId());
    }

    @Override
    public Feedback getByID(int id) throws SQLException {
        String query = "select * from feedbacks where FeedbackId=(?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setInt(1, id);
        ResultSet result = preStat.executeQuery();
        return getFeedback(result);
    }

    @Override
    public List<Feedback> getAll() throws SQLException {
        String query = "select * from feedbacks";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet results = preStat.executeQuery();
        List<Feedback> feedbacks = new ArrayList<>();
        while(results.next()){
            feedbacks.add(getFeedback(results));
        }
        return feedbacks;
    }

    @Override
    public void update(Feedback feedback) throws SQLException {
        String query = "update feedbacks set " +
                "Date=?, " +
                "UserId=?, " +
                "EmployeeId=?, " +
                "Positive=?, " +
                "Significance=?, " +
                "Description=? where FeedbackId=(?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        setStatement(preStat, feedback);
        preStat.setInt(7, feedback.getId());
        preStat.executeUpdate();
    }

    private Feedback getFeedback(ResultSet result) throws SQLException{
        Feedback feedback = new Feedback();
        feedback.setId(result.getInt("FeedbackId"));
        feedback.setDateTime(result.getTimestamp("Date").toLocalDateTime());
        User user = userDAO.getByID(result.getInt("UserId"));
        feedback.setSubmitter(user);
        Employee employee = empDAO.getByID(result.getInt("EmployeeId"));
        feedback.setEmployee(employee);
        feedback.setPositive(result.getBoolean("Positive"));
        feedback.setSignificance(result.getInt("Significance"));
        feedback.setDescription(result.getString("Description"));
        return feedback;
    }

    private void setStatement(PreparedStatement statement, Feedback feedback) throws SQLException{
        java.sql.Date date = java.sql.Date.valueOf(feedback.getDateTime().toLocalDate());
        statement.setDate(1,date);
        statement.setInt(2, feedback.getSubmitter().getId());
        statement.setInt(3, feedback.getEmployee().getId());
        statement.setInt(4, feedback.isPositive()? 1:0);
        statement.setInt(5, feedback.getSignificance());
        statement.setString(6, feedback.getDescription());
    }
}
