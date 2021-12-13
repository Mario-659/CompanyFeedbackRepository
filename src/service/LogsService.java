package service;

import Database.DAO.LogDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Log;
import model.User;
import validators.InputValidator;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class LogsService {
    private static LogDAO logDAO = new LogDAO();
    private static ObservableList<Log> logs = FXCollections.observableArrayList();

    public ObservableList<Log> getLogs(){
        update();
        return logs;
    }

    public boolean addLog(LocalDateTime dateTime, String subject, User user, String significance, String description){
        if(!InputValidator.validateSignificance(significance)) return false;

        subject = InputValidator.checkBlank(subject);
        description = InputValidator.checkBlank(description);
        if(subject == null || description == null) return false;

        Log log = new Log(dateTime, subject, user, Integer.parseInt(significance), description);
        try {
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
}
