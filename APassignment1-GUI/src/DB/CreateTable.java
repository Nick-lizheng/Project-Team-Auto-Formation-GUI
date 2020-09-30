package DB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	public static void main(String[] args) throws SQLException {
		
		final String DB_NAME = "test.db";
		final String TABLE_NAME = "student";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE " + TABLE_NAME + " ("
										+ "id INT NOT NULL,"
										+ "student_number VARCHAR(8) NOT NULL," 
										+ "first_name VARCHAR(20) NOT NULL,"
										+ "last_name VARCHAR(20) NOT NULL,"
										+ "PRIMARY KEY (id))");
			if(result == 0) {
				System.out.println("Table " + TABLE_NAME + " has been created successfully");
			} else {
				System.out.println("Table " + TABLE_NAME + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}