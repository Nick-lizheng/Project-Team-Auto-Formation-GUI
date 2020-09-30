package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
public static void main(String[] args) {
	final String DB_NAME = "test.db";
	
	//ues try-with-resourse Statement
	try(Connection con = getConnection(DB_NAME)){
		System.out.println("Connection to database "+ DB_NAME + " created succes:");
		
	}catch(Exception e) { 
		System.out.println(e.getMessage());
	}
}


public static Connection getConnection(String dbname) throws SQLException, ClassNotFoundException{
	String url ="jdbc:sqlite:Sqlite/"+dbname;
	
	Connection con = DriverManager.getConnection(url);
	
	return con;
}


}
