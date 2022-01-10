package CompanyManagementRepository.validators;

import CompanyManagementRepository.model.Employee;
import CompanyManagementRepository.model.Feedback;
import CompanyManagementRepository.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FeedbackInputValidation extends InputValidation<Feedback> {
    @Override
    public Feedback validate(String[] args) throws IOException, SQLException {
        String description = validateDescription(args[0]);
        Feedback feedback = new Feedback();
        feedback.setDescription(description);
        return feedback;
    }

    public Feedback validate(LocalDateTime date, User user, Employee employee, Boolean positive, Integer significance, String description) throws SQLException, IOException {
        date = validateDate(date);
        description = validateDescription(description);
        return new Feedback(date, user, employee, positive, significance, description);
    }

    public Feedback validate(LocalDate date, User user, Employee employee, Boolean positive, Integer significance, String description) throws SQLException, IOException {
        LocalDateTime dateTime = validateDate(date);
        description = validateDescription(description);
        return new Feedback(dateTime, user, employee, positive, significance, description);
    }
}
