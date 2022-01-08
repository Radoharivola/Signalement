package signalement.app.Models;
import java.sql.*;
public class Log{
    Connection con;
    public Log() throws Exception{
        try{
            Class.forName("org.postgresql.Driver");
            String url="jdbc:postgresql://localhost:5432/signalement";
	   		String user="postgres";
	   		String password="neverdie1122";
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