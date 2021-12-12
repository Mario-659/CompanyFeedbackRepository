package service;

import Database.validators.LoginValidator;

import java.sql.SQLException;

public class LoginService {
    private final LoginValidator loginValidator = new LoginValidator();

    public boolean login(String email, String password){
        try{
            return loginValidator.validateLogin(email, password);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
