package model;

import DB.ConnectionTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DBDataHandler {

    public static final String DB_NAME = "test.db";
    public static final String TABLE_NAME = "student";
    public static final String TABLE_NAME2 = "FORMTEAM";

    public static ArrayList<Student> studentList = new ArrayList<Student>();
    public static ArrayList<Student> studentSkillSetList = new ArrayList<Student>();
    public static ArrayList<Project> projectList = new ArrayList<Project>();
    public static ArrayList<Team> teamList = new ArrayList<Team>();

    public static void readTeamFromDB() {
        studentList = new ArrayList<Student>();
        studentSkillSetList = new ArrayList<Student>();
        projectList = new ArrayList<Project>();
        teamList = new ArrayList<Team>();

        //use try-with-resources Statement
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "SELECT * FROM " + TABLE_NAME;
            String query2 = "SELECT * FROM " + TABLE_NAME2;
            try (ResultSet resultSet = stmt.executeQuery(query)) {

                Map<String,Student> students=new HashMap<String,Student>();

                while (resultSet.next()) {
                    System.out.println(resultSet.getString("id") + "," + resultSet.getString("Name") + ","
                            + resultSet.getString("SkillSet") + "," + resultSet.getString("PERSONALITYTYPE") + "," + resultSet.getString("PERSONALICONFILC"));

                    String id=resultSet.getString("id");
                    String name=resultSet.getString("Name");
                    String SkillSet=resultSet.getString("SkillSet");
                    String PERSONALITYTYPE=resultSet.getString("PERSONALITYTYPE");
                    String PERSONALICONFILC=resultSet.getString("PERSONALICONFILC");

                    Student student=new Student(id,SkillSet,PERSONALITYTYPE.charAt(0),PERSONALICONFILC);
                    studentList.add(student);
                    studentSkillSetList.add(student);
                    students.put(student.getId(),student);
                }

                System.out.println("====================================================================================");

                ResultSet resultSet2 = stmt.executeQuery(query2);
                while (resultSet2.next()) {
                    System.out.println(resultSet.getString("id") + "," + resultSet.getString("Name") + ","
                            + resultSet.getString("DECRISTION") + "," + resultSet.getString("ownid") + "," + resultSet.getString("sid1") + "," + resultSet.getString("sid2") + "," + resultSet.getString("sid3") + "," + resultSet.getString("sid4"));

                    String id=resultSet.getString("id");
                    String title=resultSet.getString("Name");
                    String DECRISTION=resultSet.getString("DECRISTION");
                    String ownid=resultSet.getString("ownid");
                    String sid1=resultSet.getString("sid1");
                    String sid2=resultSet.getString("sid2");
                    String sid3=resultSet.getString("sid3");
                    String sid4=resultSet.getString("sid4");

                    Project project=new Project(title,id,DECRISTION,ownid,"");
                    projectList.add(project);
                    Map<String, Student> sMap=new HashMap<String, Student>();
                    sMap.put(sid1,students.get(sid1));
                    sMap.put(sid2,students.get(sid2));
                    sMap.put(sid3,students.get(sid3));
                    sMap.put(sid4,students.get(sid4));
                    Team team=new Team(project,sMap);
                    teamList.add(team);
                }


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
