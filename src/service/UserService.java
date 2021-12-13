package service;

import database.DAO.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import validators.InputValidator;

import java.sql.SQLException;

public class UserService {
    private static UserDAO userDAO = new UserDAO();
    private static ObservableList<User> users = FXCollections.observableArrayList();

    public ObservableList<User> getUsers(){
        updateUsers();
        return users;
    }

    //TODO improve this (maybe with throwing errors)
    public boolean addUser(String firstName, String lastName, String email, String password){
        firstName = InputValidator.validateName(firstName);
        lastName = InputValidator.validateName(lastName);
        email = InputValidator.validateEmail(email);
        password = InputValidator.checkBlank(password);
        if(firstName==null || lastName==null || email==null || password==null) return false;

        User user = new User(firstName, lastName, email, password);
        try {
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
