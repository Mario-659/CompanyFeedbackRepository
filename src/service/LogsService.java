package service;

import Database.DAO.LogDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Log;

import java.sql.SQLException;

public class LogsService {
    private static LogDAO logDAO = new LogDAO();
    private static ObservableList<Log> logs = FXCollections.observableArrayList();

    public ObservableList<Log> getLogs(){
        update();
        return logs;
    }

    private void update(){
        try {
            logs.setAll(logDAO.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
