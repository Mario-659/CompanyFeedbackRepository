package database.validators;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailValidator implements EmailValidation{
    private static final Connection connection = DBConnection.getConnection();

    /**
     * Validates Email
     * @param email
     * @return true if email is not in database
     * @throws SQLException
     */
    @Override
    public boolean isTaken(String email) throws SQLException {
        String query = "select * from users where Email=?";
        PreparedStatement preStat = connection.prepareStatement(query);
        preStat.setString(1, email);
        ResultSet result = preStat.executeQuery();
        int count = 0;
        while(result.next()) count++;
        return count == 0;
    }
}
