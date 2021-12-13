package database.validators;

import java.sql.SQLException;

public interface LoginValidation{
    boolean validateLogin(String email, String password) throws SQLException;
}
