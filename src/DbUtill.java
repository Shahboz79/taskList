import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtill {
    public static Connection dbConnection(){
        Connection con=null;
        try {
           con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db",
                    "jdbc_user", "123456"); // <2>
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
