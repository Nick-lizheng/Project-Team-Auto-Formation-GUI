package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectQuery {
	public static void main(String[] args) {
		final String DB_NAME = "test.db";
		final String TABLE_NAME = "student";
		final String TABLE_NAME2 = "FORMTEAM";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "SELECT * FROM " + TABLE_NAME;
			String query2 = "SELECT * FROM " + TABLE_NAME2;
			try (ResultSet resultSet = stmt.executeQuery(query)) {
//				while(resultSet.next()) {
//					System.out.printf("Id: %s , Name: %s , SkillSet: %s , PersonalityTpye: %s , PersonalityConfilc: %s\n",
//							resultSet.getInt("id"), resultSet.getString("Name"), 
//							resultSet.getString("SkillSet"), resultSet.getString("PERSONALITYTYPE"),resultSet.getString("PERSONALICONFILC"));
//				}
				while(resultSet.next()) {
					System.out.println(resultSet.getString("id")+","+resultSet.getString("Name")+","
				+ resultSet.getString("SkillSet")+","+resultSet.getString("PERSONALITYTYPE")+","+resultSet.getString("PERSONALICONFILC"));
				}
				
				System.out.println("====================================================================================");
				
				ResultSet resultSet2 = stmt.executeQuery(query2);
				while(resultSet2.next()) {
					System.out.println(resultSet.getString("id")+","+resultSet.getString("Name")+","
				+ resultSet.getString("DECRISTION")+","+resultSet.getString("ownid")+","+resultSet.getString("sid1")+","+resultSet.getString("sid2")+","+resultSet.getString("sid3")+","+resultSet.getString("sid4"));
				}
				
				
				
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}