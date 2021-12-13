package database.validators;

import model.User;

import java.io.IOException;
import java.sql.SQLException;

public interface LoginValidation{
    User validateLogin(String email, String password) throws SQLException, IOException;
}
