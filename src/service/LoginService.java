package service;

import database.DAO.UserDAO;
import database.validators.LoginValidator;
import model.User;
import validators.UserInputValidator;

import java.io.IOException;
import java.sql.SQLException;

public class LoginService {
    private final LoginValidator loginValidator = new LoginValidator();
    private final UserInputValidator userInVal = new UserInputValidator();
    private UserDAO userDAO = new UserDAO();

    public User login(String email, String password)throws IOException {
        try{
            userInVal.validate(email);
            return loginValidator.validateLogin(email, password);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
