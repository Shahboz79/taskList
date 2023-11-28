import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class TaskService {

    public String create(TaskDTO taskDTO){
        Connection con=DbUtill.dbConnection();
        int effectedRows=0;
        try {
            Statement statement = con.createStatement();
            String sql = "insert into tasks(title,content,status,created_date)" +
                    " values('"+ taskDTO.getTitle()+"','"+taskDTO.getContent()+"','"+TaskStatus.ACTIVE+"','"+LocalDateTime.now()+"')";
//            sql = String.format(sql, taskDTO.getTitle(), taskDTO.getContent(),TaskStatus.ACTIVE, LocalDateTime.now());
            effectedRows = statement.executeUpdate(sql);
            con.close();
        } catch (SQLException e) {
           e.printStackTrace();
        }
        if (effectedRows!=0){
            return new String("Successfully added");
        }else return new String("Error");

    }

    public List<Task> getActiveTaskList() {
        Connection con=DbUtill.dbConnection();
        ResultSet resultSet=null;
        List<Task> tasks=new LinkedList<>();
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery("select id, title, content, status, " +
                    "created_date from tasks where finished_date is null");
            while (resultSet.next()){
               Task task=new Task(resultSet.getInt("id"),
                       resultSet.getString("title"),
                       resultSet.getString("content"),
                       TaskStatus.valueOf(resultSet.getString   ("status")),
                       resultSet.getTimestamp("created_date").toLocalDateTime(),
                       null);
               tasks.add(task);

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (resultSet!=null){
            return tasks;
        }else return new LinkedList<>();

    }
    public List<Task> getFinishedTaskList() {
        Connection con=DbUtill.dbConnection();
        ResultSet resultSet=null;
        List<Task> tasks=new LinkedList<>();
        try {
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery("select id, title, content, status, " +
                    "created_date, finished_date from tasks where finished_date is not null");
            while (resultSet.next()){
                Task task=new Task(resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        TaskStatus.valueOf(resultSet.getString   ("status")),
                        resultSet.getTimestamp("created_date").toLocalDateTime(),
                        resultSet.getTimestamp("finished_date").toLocalDateTime());
                tasks.add(task);

            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (resultSet!=null){
            return tasks;
        }else return new LinkedList<>();

    }

    public String update(TaskDTO taskDTO) {
        Connection con=DbUtill.dbConnection();
        int effectedRows=0;
        try {
            Statement statement = con.createStatement();
            String sql = "update tasks set" +
                    " title='"+ taskDTO.getTitle()+"', content='"+taskDTO.getContent()+"' where id= "+taskDTO.getId()+"";
//            sql = String.format(sql, taskDTO.getTitle(), taskDTO.getContent(),TaskStatus.ACTIVE, LocalDateTime.now());
            effectedRows = statement.executeUpdate(sql);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (effectedRows!=0){
            return new String("Successfully updated");
        }else return new String("Error");
    }

    public String delete(Integer id) {
        Connection con=DbUtill.dbConnection();
        int effectedRows=0;
        try {
            Statement statement=con.createStatement();
            String sql="delete from tasks where id="+id+"";
            effectedRows=statement.executeUpdate(sql);
            con.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        if (effectedRows!=0){
            return new String("Successfully deleted");
        }else return new String("Error");
    }

    public String markAsDone(Integer finId) {
        Connection con=DbUtill.dbConnection();
        int effectedRows=0;
        try {
            Statement statement = con.createStatement();
            String sql = "update tasks set" +
                    " status='"+ TaskStatus.DONE+"', finished_date='"+LocalDateTime.now()+"' where id= "+finId+"";
//            sql = String.format(sql, taskDTO.getTitle(), taskDTO.getContent(),TaskStatus.ACTIVE, LocalDateTime.now());
            effectedRows = statement.executeUpdate(sql);
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (effectedRows!=0){
            return new String("Marked as done");
        }else{
            return null;
    }}
}
