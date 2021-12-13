package database.validators;

import database.DBConnection;
import model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginValidator implements LoginValidation{
    private static final Connection connection = DBConnection.getConnection();

    @Override
    public User validateLogin(String email, String password) throws SQLException, IOException {
        String query = "select * from users where Email=? and Password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet result = preparedStatement.executeQuery();
        User user = new User();
        int i = 0;
        while(result.next()) {
            i++;
            user.setId(result.getInt("UserId"));
            user.setFirstName(result.getString("FirstName"));
            user.setLastName(result.getString("LastName"));
            user.setEmail(result.getString("Email"));
            user.setPassword(result.getString("Password"));
        }
        if(i==1) return user;
        else if(i==0) throw new IOException("Invalid email or password");
        else throw new IOException("Multiple records matching login");
    }
}
