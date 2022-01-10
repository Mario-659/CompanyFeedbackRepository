package CompanyManagementRepository.validators;

import CompanyManagementRepository.model.Log;
import CompanyManagementRepository.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class LogInputValidation extends InputValidation<Log>{

    @Override
    public Log validate(String[] args) throws IOException, SQLException {
        return null;
    }

    public Log validate(LocalDate date,String subject, User user, int significance, String description) throws IOException {
        LocalDateTime dateTime = validateDate(date);
        subject = validateSubject(subject);
        description = validateDescription(description);
        return new Log(dateTime, subject, user, significance, description);
    }
}
