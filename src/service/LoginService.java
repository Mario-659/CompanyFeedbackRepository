package service;

import Database.DAO.UserDAO;
import Database.validators.LoginValidator;
import model.User;

import java.sql.SQLException;

public class LoginService {
    private final LoginValidator loginValidator = new LoginValidator();
    private UserDAO userDAO = new UserDAO();

    public boolean login(String email, String password){
        try{
            return loginValidator.validateLogin(email, password);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //TODO improve way of settling current user
    public User getUser(String email){
        try {
            return userDAO.get(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
