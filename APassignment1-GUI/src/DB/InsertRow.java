package DB;

import java.sql.Connection;
import java.sql.Statement;

public class InsertRow {
	public static void main(String[] args) {
		final String DB_NAME = "test.db";
		final String TABLE_NAME = "student";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "INSERT INTO " + TABLE_NAME + 
					" VALUES (1, 's3388490', 'Peter', 'Tilmanis')";
//					" VALUES (2, 's3089940', 'Tom', 'Bruster')";
//					" VALUES (3, 's3802030', 'ABC', 'ZYZ')";
			
			int result = stmt.executeUpdate(query);
			
			// no need to commit because auto-commit is done in this case
//			con.commit();
			
			System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}