package Database.DAO;

import Database.DBConnection;
import model.Employee;
import model.Feedback;
import model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO implements DAO<Feedback> {
    private static Connection connection = null;

    private final UserDAO userDAO = new UserDAO();
    private final EmployeeDAO empDAO = new EmployeeDAO();

    static{
        connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(15);
            statement = connection.createStatement();
            statement.executeUpdate(
                    "create table if not exists feedbacks(" +
                            "FeedbackId integer PRIMARY KEY," +
                            "Datetime string, " +
                            "UserId integer, " +
                            "EmployeeId integer, " +
                            "Positive integer, " +
                            "Significance integer, " +
                            "Description string, " +
                            "FOREIGN KEY(UserId) REFERENCES users(UserId), " +
                            "FOREIGN KEY (EmployeeId) REFERENCES employees(EmployeeId))");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Feedback save(Feedback feedback) throws SQLException {
        String query = "insert into feedbacks (Datetime, UserId, EmployeeId, Positive, Significance, Description) values(?, ?, ?, ?, ?, ?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        setStatement(preStat, feedback);
        preStat.executeUpdate();
        return getLastInsertedFeedback();
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
        return get(feedback.getId());
    }

    @Override
    public Feedback get(int id) throws SQLException {
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
                "Datetime=?, " +
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
        feedback.setDateTime(LocalDateTime.parse(result.getString("Datetime")));
        User user = userDAO.get(result.getInt("UserId"));
        feedback.setSubmitter(user);
        Employee employee = empDAO.get(result.getInt("EmployeeId"));
        feedback.setEmployee(employee);
        feedback.setPositive(result.getBoolean("Positive"));
        feedback.setSignificance(result.getInt("Significance"));
        feedback.setDescription(result.getString("Description"));
        return feedback;
    }

    private void setStatement(PreparedStatement statement, Feedback feedback) throws SQLException{
        statement.setString(1,feedback.getDateTime().toString());
        statement.setInt(2, feedback.getSubmitter().getId());
        statement.setInt(3, feedback.getEmployee().getId());
        statement.setInt(4, feedback.isPositive()? 1:0);
        statement.setInt(5, feedback.getSignificance());
        statement.setString(6, feedback.getDescription());
    }

    private Feedback getLastInsertedFeedback() throws SQLException{
        String query = "select * from feedbacks where FeedbackId=last_insert_rowid()";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet result = preStat.executeQuery();
        return getFeedback(result);
    }
}
