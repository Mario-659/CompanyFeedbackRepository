package service;

import database.DAO.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import validators.UserInputValidator;

import java.io.IOException;
import java.sql.SQLException;

public class UserService {
    private static UserDAO userDAO = new UserDAO();
    private static UserInputValidator userInputValidator = new UserInputValidator();
    private static ObservableList<User> users = FXCollections.observableArrayList();

    public ObservableList<User> getUsers(){
        updateUsers();
        return users;
    }

    public boolean addUser(String firstName, String lastName, String email, String password) throws IOException {
        try {
            User user = userInputValidator.validate(new String[]{firstName, lastName, email, password});
            userDAO.save(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void updateUsers(){
        try {
            users.setAll(userDAO.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
