package command.action;

import command.Action;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import model.DataHandler;
import model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeleteAction implements Action {

    private String name;
    private HashMap<String, CheckBox> checkBoxMap;
    private ChoiceBox<Student> Studentbox;
    private DataHandler dh;
    private Map<String, Student> sMap1;
    private Map<String, Student> sMap2;
    private Map<String, Student> sMap3;
    private Map<String, Student> sMap4;
    private Map<String, Student> sMap5;

    private ArrayList selectCheckBoxList=new ArrayList();

    public DeleteAction(String name, HashMap<String, CheckBox> checkBoxMap, DataHandler dh, ChoiceBox<Student> Studentbox, Map<String, Student> sMap1, Map<String, Student> sMap2, Map<String, Student> sMap3, Map<String, Student> sMap4, Map<String, Student> sMap5) {
        this.name=name;
        this.checkBoxMap=checkBoxMap;
        this.dh=dh;
        this.Studentbox=Studentbox;
        this.sMap1=sMap1;
        this.sMap2=sMap2;
        this.sMap3=sMap3;
        this.sMap4=sMap4;
        this.sMap5=sMap5;
    }

    @Override
    public void execute() {

        for (String s : checkBoxMap.keySet()) {
            if (checkBoxMap.get(s).isSelected()) {

                ArrayList tempDataList=new ArrayList();
                selectCheckBoxList.add(tempDataList);

                tempDataList.add(s);
                tempDataList.add(checkBoxMap.get(s));
                tempDataList.add(checkBoxMap.get(s).getText());

                for (Student student : dh.studentSkillSetList) {
                    if (student.getId().equals(checkBoxMap.get(s).getText()))
                        Studentbox.getItems().add(student);
                }

                if (s.substring(0, 2).equals("t1")) {

                    tempDataList.add(sMap1.get(checkBoxMap.get(s).getText()));
                    sMap1.remove(checkBoxMap.get(s).getText());
                    System.out.println(sMap1);
                } else if (s.substring(0, 2).equals("t2")) {
                    tempDataList.add(sMap2.get(checkBoxMap.get(s).getText()));
                    sMap2.remove(checkBoxMap.get(s).getText());
                    System.out.println(sMap2);
                } else if (s.substring(0, 2).equals("t3")) {
                    tempDataList.add(sMap3.get(checkBoxMap.get(s).getText()));
                    sMap3.remove(checkBoxMap.get(s).getText());
                    System.out.println(sMap3);
                } else if (s.substring(0, 2).equals("t4")) {
                    tempDataList.add(sMap4.get(checkBoxMap.get(s).getText()));
                    sMap4.remove(checkBoxMap.get(s).getText());
                    System.out.println(sMap4);
                } else {
                    tempDataList.add(sMap5.get(checkBoxMap.get(s).getText()));
                    sMap5.remove(checkBoxMap.get(s).getText());
                    System.out.println(sMap5);
                }
                checkBoxMap.get(s).setText("");
                checkBoxMap.get(s).setSelected(false);
            }

        }

    }

    @Override
    public void undo() {


        for (int i = 0; i < selectCheckBoxList.size(); i++) {
            ArrayList tempDataList= (ArrayList) selectCheckBoxList.get(i);

            String s= (String) tempDataList.get(0);
            CheckBox checkBox= (CheckBox) tempDataList.get(1);
            String checkBoxOldText= (String) tempDataList.get(2);
            Student mapStudent= (Student) tempDataList.get(3);

            checkBoxMap.get(s).setSelected(true);
            checkBoxMap.get(s).setText(checkBoxOldText);

            if (s.substring(0, 2).equals("t1")) {
                sMap1.put(checkBoxMap.get(s).getText(),mapStudent);
                System.out.println(sMap1);
            } else if (s.substring(0, 2).equals("t2")) {
                sMap2.put(checkBoxMap.get(s).getText(),mapStudent);
                System.out.println(sMap2);
            } else if (s.substring(0, 2).equals("t3")) {
                sMap3.put(checkBoxMap.get(s).getText(),mapStudent);
                System.out.println(sMap3);
            } else if (s.substring(0, 2).equals("t4")) {
                sMap4.put(checkBoxMap.get(s).getText(),mapStudent);
                System.out.println(sMap4);
            } else {
                sMap5.put(checkBoxMap.get(s).getText(),mapStudent);
                System.out.println(sMap5);
            }


            for (Student student : dh.studentSkillSetList) {
                if (student.getId().equals(checkBoxMap.get(s).getText()))
                    Studentbox.getItems().remove(student);
            }
        }

        selectCheckBoxList.clear();

    }
    
    @Override
    public void redo() {
    	
    }

    @Override
    public String getName() {
        return this.name;
    }
}
