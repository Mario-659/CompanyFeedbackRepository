package validators;

import model.Employee;

import java.io.IOException;
import java.sql.SQLException;

public class EmployeeInputValidation extends InputValidation<Employee> {
    @Override
    public Employee validate(String[] args) throws IOException, SQLException {
        String firstName = validateName(args[0]);
        String lastName = validateName(args[1]);
        return new Employee(firstName, lastName);
    }
}
