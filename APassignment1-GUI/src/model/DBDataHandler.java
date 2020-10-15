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

        Connection con = null;
        Statement stmt = null;
        ResultSet resultSet =null;
        ResultSet resultSet2 = null;
        try {
            con = ConnectionTest.getConnection(DB_NAME);
            stmt = con.createStatement();
            String query = "SELECT * FROM " + TABLE_NAME;
            String query2 = "SELECT * FROM " + TABLE_NAME2;
            try {
                resultSet = stmt.executeQuery(query);
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

                    int num = SkillSet.indexOf("P");
                    student.getSkillMap().put("P", Integer.parseInt(SkillSet.substring(num + 1, num + 2)));

                    int num2 = SkillSet.indexOf("N");
                    student.getSkillMap().put("N", Integer.parseInt(SkillSet.substring(num2 + 1, num2 + 2)));

                    int num3 = SkillSet.indexOf("A");
                    student.getSkillMap().put("A", Integer.parseInt(SkillSet.substring(num3 + 1, num3 + 2)));

                    int num4 = SkillSet.indexOf("W");
                    student.getSkillMap().put("W", Integer.parseInt(SkillSet.substring(num4 + 1, num4 + 2)));

                    System.out.println(student.getSkillMap());

                    studentList.add(student);
                    studentSkillSetList.add(student);
                    students.put(student.getId(),student);
                }

                System.out.println("====================================================================================");

                resultSet2 = stmt.executeQuery(query2);
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
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(resultSet!=null){
                    resultSet.close();
                }
                if(resultSet2!=null){
                    resultSet2.close();
                }
                if(stmt!=null){
                    stmt.close();
                }
                if(con!=null){
                    con.close();
                }
                System.out.println("close stmt and conn");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static boolean save2DB(ArrayList<Team> teamList){
        boolean isSuc=true;

        if(teamList!=null && !teamList.isEmpty()) {

            //use try-with-resources Statement
            Connection con = null;
            Statement stmt = null;
            try{
                con = ConnectionTest.getConnection(DB_NAME);
                stmt = con.createStatement();
                //clear table1
                String query = "DELETE FROM " + TABLE_NAME + " WHERE 1=1";
                int result = stmt.executeUpdate(query);
                System.out.println(query);
                System.out.println("Delete from table " + TABLE_NAME + " executed successfully");
                System.out.println(result + " row(s) affected");

                //insert table1
                for (int i = 0; i < teamList.size(); i++) {
                    Set<String> stuIdSet= teamList.get(i).getStudentMap().keySet();
                    for (String stuIdStr:stuIdSet) {
                        Student student=teamList.get(i).getStudentMap().get(stuIdStr);

                        String insertStr = "INSERT INTO " + TABLE_NAME +"(ID,Name,SKILLSET,PERSONALITYTYPE,PERSONALICONFILC)"+ " VALUES ('"+student.getId()+"','"+student.getId()+ "','"+student.getSkillset()+"','"+student.getPersionalityType()+"','"+student.getConflict()+"')";
                        result = stmt.executeUpdate(insertStr);
                        System.out.println(insertStr);
                        System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
                        System.out.println(result + " row(s) affected");
                    }
                }


                //clear table2
                query = "DELETE FROM " + TABLE_NAME2 + " WHERE 1=1";
                result = stmt.executeUpdate(query);
                System.out.println(query);
                System.out.println("Delete from table " + TABLE_NAME2 + " executed successfully");
                System.out.println(result + " row(s) affected");

//                insert table2
                for (int i = 0; i < teamList.size(); i++) {
                    Project project=teamList.get(i).getProject();
                    Set<String> stuIdSet= teamList.get(i).getStudentMap().keySet();
                    String sid1="";
                    String sid2="";
                    String sid3="";
                    String sid4="";
                    int j=0;
                    for (String stuIdStr:stuIdSet) {
                        Student student=teamList.get(i).getStudentMap().get(stuIdStr);
                        if (j == 0) {
                            sid1=student.getId();
                        }
                        if (j == 1) {
                            sid2=student.getId();
                        }
                        if (j == 2) {
                            sid3=student.getId();
                        }
                        if (j == 3) {
                            sid4=student.getId();
                        }
                        j=j+1;

                    }
                    String insertStr = "INSERT INTO " + TABLE_NAME2 + "(ID,NAME,DECRISTION,OWNID,SID1,SID2,SID3,SID4)"+" VALUES ('"+project.getId()+"','"+project.getTitle()+ "','"+project.getDesc()+"','"+project.getPoID()+"','"+sid1+"','"+sid2+"','"+sid3+"','"+sid4+"')";
                    result = stmt.executeUpdate(insertStr);
                    System.out.println(insertStr);
                    System.out.println("Insert into table " + TABLE_NAME2 + " executed successfully");
                    System.out.println(result + " row(s) affected");
                }

                // no need to commit because auto-commit is done in this case
                //con.commit();

            } catch (Exception e) {
                e.printStackTrace();
                isSuc=false;
            }finally {
                try {
//                    if(resultSet!=null){
//                        resultSet.close();
//                    }
//                    if(resultSet2!=null){
//                        resultSet2.close();
//                    }
                    if(stmt!=null){
                        stmt.close();
                    }
                    if(con!=null){
                        con.close();
                    }
                    System.out.println("close stmt and conn");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }else{
            isSuc=false;
        }

        return isSuc;
    }

}
