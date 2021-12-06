package Database.DAO;

import Database.DBConnection;
import Database.model.Log;
import Database.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogDAO implements DAO<Log> {
    private static Connection connection = null;

    private final UserDAO userDAO = new UserDAO();

    static{
        connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(15);
            statement = connection.createStatement();
            statement.executeUpdate(
                    "create table if not exists logs(" +
                            "LogId integer PRIMARY KEY," +
                            "Datetime string, " +
                            "Subject string, " +
                            "UserId integer, " +
                            "Significance integer, " +
                            "Description string, " +
                            "FOREIGN KEY(UserId) REFERENCES users(UserId)) ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Log save(Log log) throws SQLException {
        String query = "insert into logs (Datetime, Subject, UserId, Significance, Description) values(?, ?, ?, ?, ?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        setStatement(preStat, log);
        preStat.executeUpdate();
        return getLastInsertedLog();
    }

    @Override
    public void delete(Log log) throws SQLException {
        String query = "delete from logs where LogId=(?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setInt(1, log.getId());
        preStat.executeUpdate();
    }

    @Override
    public Log get(Log log) throws SQLException {
        return get(log.getId());
    }

    @Override
    public Log get(int id) throws SQLException {
        String query = "select * from logs where LogId=(?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setInt(1, id);
        ResultSet result = preStat.executeQuery();
        return getLog(result);
    }

    @Override
    public List<Log> getAll() throws SQLException {
        String query = "select * from logs";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet results = preStat.executeQuery();
        List<Log> logs = new ArrayList<>();
        while(results.next()){
            logs.add(getLog(results));
        }
        return logs;
    }

    @Override
    public void update(Log log) throws SQLException {
        String query = "update logs set " +
                "Datetime=?, " +
                "Subject=?, " +
                "UserId=?, " +
                "Significance=?, " +
                "Description=? where LogId=(?)";
        PreparedStatement preStat = connection.prepareStatement(query);
        setStatement(preStat, log);
        preStat.setInt(6, log.getId());
        preStat.executeUpdate();
    }

    private Log getLog(ResultSet result) throws SQLException{
        Log log = new Log();
        log.setId(result.getInt("LogId"));
        log.setDateTime(LocalDateTime.parse(result.getString("Datetime")));
        log.setSubject(result.getString("Subject"));
        User user = userDAO.get(result.getInt("UserId"));
        log.setUser(user);
        log.setSignificance(result.getInt("Significance"));
        log.setDescription(result.getString("Description"));
        return log;
    }

    private void setStatement(PreparedStatement statement, Log log) throws SQLException{
        statement.setString(1,log.getDateTime().toString());
        statement.setString(2,log.getSubject());
        statement.setInt(3, log.getUser().getId());
        statement.setInt(4, log.getSignificance());
        statement.setString(5, log.getDescription());
    }

    private Log getLastInsertedLog() throws SQLException{
        String query = "select * from logs where LogId=last_insert_rowid()";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet result = preStat.executeQuery();
        return getLog(result);
    }
}
