package service;

import database.DAO.LogDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Log;
import model.User;
import validators.LogInputValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class LogsService {
    private static LogDAO logDAO = new LogDAO();
    private static LogInputValidation logVal = new LogInputValidation();
    private static ObservableList<Log> logs = FXCollections.observableArrayList();

    public ObservableList<Log> getLogs(){
        update();
        return logs;
    }

    public boolean addLog(LocalDate dateTime, String subject, User user, Integer significance, String description) throws IOException {
        try {
            Log log = logVal.validate(dateTime, subject, user, significance, description);
            logDAO.save(log);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void update(){
        try {
            logs.setAll(logDAO.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Log log) {
        try {
            logDAO.delete(log);
            update();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
