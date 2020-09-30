package DB;

import java.sql.Connection;
import java.sql.Statement;

public class InsertMultipleRows {
	public static void main(String[] args) {
		final String DB_NAME = "test.db";
		final String TABLE_NAME = "student";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String insertQuery = "INSERT INTO " + TABLE_NAME;
			
			stmt.addBatch(insertQuery + " VALUES (2, 's3089940', 'Tom', 'Bruster')");
			stmt.addBatch(insertQuery + " VALUES (3, 's3802030', 'ABC', 'ZYZ')");
			stmt.addBatch(insertQuery + " VALUES (4, 's3830293', 'QQQQ', 'FFFF')");
			
			int count[] = stmt.executeBatch();
			
			// no need to commit because auto-commit is done in this case
//			con.commit();
			
			System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
			System.out.println(count.length + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}