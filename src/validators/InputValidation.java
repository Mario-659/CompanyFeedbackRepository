package validators;

import database.validators.EmailValidation;

import java.io.IOException;
import java.sql.SQLException;

public abstract class InputValidation<T> {
    /**
     * Validates given args for t
     * @param args array of String arguments
     * @return if valid new entity throws IOException
     */
    public abstract T validate(String[] args) throws IOException, SQLException;

    protected String validateEmail(String email, EmailValidation emailValidation) throws IOException, SQLException {
        email = email.strip();
        if(email.isBlank()) throw new IOException("Email cannot be empty");
        if(emailValidation.isTaken(email)) throw new IOException("Given email is already taken");
        return email;
    }

    protected String validatePassword(String password) throws IOException{
        if(password.isBlank()) throw new IOException("Password cannot be empty");
        return password.strip();
    }

    protected String validateName(String name) throws IOException{
        name = name.strip();
        if(name.isBlank()) throw new IOException("Name cannot be empty");
        if(!name.matches("[a-zA-Z]+")) throw new IOException("Name must contain only letters");
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
