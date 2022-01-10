package CompanyManagementRepository.validators;

import CompanyManagementRepository.database.validators.EmailValidator;
import CompanyManagementRepository.model.User;

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

    public User validate(String email) throws IOException, SQLException{
        User user = new User();
        user.setEmail(validateEmail(email));
        return user;
    }
}
