package validators;

import database.validators.EmailValidation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
        if(!email.contains("@")) throw new IOException("Email must contain @");
        if(emailValidation.isTaken(email)) throw new IOException("Given email is already taken");
        return email;
    }

    protected String validateEmail(String email) throws IOException, SQLException {
        email = email.strip();
        if(email.isBlank()) throw new IOException("Email cannot be empty");
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

    protected String validateSubject(String subject) throws IOException{
        subject = subject.strip();
        if(subject.isBlank()) throw new IOException("Subject cannot be empty");
        if(!subject.matches("[a-zA-Z]+")) throw new IOException("Subject must contain only letters");
        return subject.substring(0, 1).toUpperCase() + subject.substring(1).toLowerCase();
    }


    protected String validateDescription(String description) throws IOException{
        description = description.strip();
        if(description.isBlank()) throw new IOException("Description cannot be empty");
        return description;
    }

    protected LocalDateTime validateDate(LocalDateTime dateTime) throws IOException {
        if(dateTime == null) throw new IOException("Invalid date");
        return dateTime;
    }

    protected LocalDateTime validateDate(LocalDate dateTime) throws IOException {
        if(dateTime == null) throw new IOException("Invalid date");
        return dateTime.atTime(0, 0);
    }
}
