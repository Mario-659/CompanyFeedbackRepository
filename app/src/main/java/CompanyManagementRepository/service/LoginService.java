package CompanyManagementRepository.service;

import CompanyManagementRepository.database.DAO.UserDAO;
import CompanyManagementRepository.database.validators.LoginValidator;
import CompanyManagementRepository.model.User;
import CompanyManagementRepository.validators.UserInputValidator;

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
