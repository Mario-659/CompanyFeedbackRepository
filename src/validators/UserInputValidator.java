package validators;

import database.validators.EmailValidator;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

public class UserInputValidator extends InputValidation<User>{
    @Override
    public User validate(String[] args) throws IOException, SQLException {
        String firstName = validateName(args[0]);
        String lastName = validateName(args[1]);
        String email = validateEmail(args[2], new EmailValidator());
        String password = validatePassword(args[3]);
        return new User(firstName, lastName, email, password);
    }
}
