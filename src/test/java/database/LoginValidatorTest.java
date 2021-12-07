package database;

import Database.validators.LoginValidator;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class LoginValidatorTest {
    private final FeedbackDAOTest feedbackDAOTest = new FeedbackDAOTest();
    private final LoginValidator loginValidator = new LoginValidator();

    @Test
    public void returnsTrue() throws SQLException {
        assertTrue(loginValidator.validateLogin("steph@email.com", "124house"));
    }

    @Test
    public void returnsFalse() throws SQLException {
        assertFalse(loginValidator.validateLogin("steph@email.com", "133house"));
    }

    @BeforeEach
    private void prepareData(){
        feedbackDAOTest.clearAllTables();
        feedbackDAOTest.prepareParams();
    }
}
