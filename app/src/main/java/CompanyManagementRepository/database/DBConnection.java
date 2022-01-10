package CompanyManagementRepository.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Singleton connection with sqlite database
 */
public class DBConnection {
    private static Connection connection = null;

    private static final String url = "jdbc:sqlite:data.db";

    static{
        try{
            connection = DriverManager.getConnection(url);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }
}
