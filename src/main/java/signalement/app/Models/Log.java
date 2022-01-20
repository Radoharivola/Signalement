package signalement.app.Models;
import java.sql.*;
public class Log{
    Connection con;
    public Log() throws Exception{
        try{
            Class.forName("org.postgresql.Driver");
            String url="jdbc:postgresql://ec2-34-236-34-103.compute-1.amazonaws.com:5432/dedpq4ce0knhdp";
	   		String user="yybwsfvuyxnjwo";
	   		String password="277b668d95cb7d82531039d6d0e5c0ec1be3dec10b9878504c67b62fe6d425ed";
	   		con=DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    public void close() throws Exception{
        try{
            con.close();
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    public Connection getCon(){
        return con;
    }
}
