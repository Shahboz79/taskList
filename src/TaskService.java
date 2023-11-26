import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TaskService {

    public String create(TaskDTO taskDTO){
        Connection con=DbUtill.dbConnection();
        Statement statement = null;
        int effectedRows=0;
        try {
            statement = con.createStatement();
            String sql = "insert into tasks(title,content) values('%s','%s')";
            sql = String.format(sql, taskDTO.getTitle(), taskDTO.getContent());
            effectedRows = statement.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
           e.printStackTrace();
        }
        if (effectedRows!=0){
            return new String("Successfully added");
        }else return new String("Error");

    }

}
