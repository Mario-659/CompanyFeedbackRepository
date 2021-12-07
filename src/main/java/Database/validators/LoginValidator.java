package Database.validators;

import Database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginValidator implements LoginValidation{
    private static final Connection connection = DBConnection.getConnection();

    @Override
    public boolean validateLogin(String email, String password) throws SQLException {
        String query = "select * from users where Email=? and Password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet result = preparedStatement.executeQuery();
        int i = 0;
        while(result.next()) i++;
        if(i==1) return true;
        else if(i==0) return false;
        else throw new SQLException("Multiple records matching login");
    }
}
