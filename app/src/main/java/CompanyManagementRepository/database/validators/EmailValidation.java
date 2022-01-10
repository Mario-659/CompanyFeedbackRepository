package CompanyManagementRepository.database.validators;

import java.sql.SQLException;

public interface EmailValidation {
    public boolean isTaken(String email) throws SQLException;
}
