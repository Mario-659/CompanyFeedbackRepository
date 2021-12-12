package service;

import Database.DAO.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;

import java.sql.SQLException;

public class UserService {
    private static UserDAO userDAO = new UserDAO();
    private static ObservableList<User> users = FXCollections.observableArrayList();

    public ObservableList<User> getUsers(){
        updateUsers();
        return users;
    }

    private void updateUsers(){
        try {
            users.setAll(userDAO.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
