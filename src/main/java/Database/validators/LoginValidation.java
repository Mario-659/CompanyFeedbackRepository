package Database.validators;

import java.sql.SQLException;

public interface LoginValidation{
    public boolean validateLogin(String email, String password) throws SQLException;
}
