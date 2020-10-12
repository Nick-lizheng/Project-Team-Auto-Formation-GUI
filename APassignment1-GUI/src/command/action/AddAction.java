package command.action;

import command.Action;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import model.Student;

import java.util.Map;

public class AddAction implements Action {

    private String name;
    private String s;
    private CheckBox selectCheckBox;
    private ChoiceBox<Student> Studentbox;
    private Student student;
    private Map<String, Student> sMap1;
    private Map<String, Student> sMap2;
    private Map<String, Student> sMap3;
    private Map<String, Student> sMap4;
    private Map<String, Student> sMap5;


    public AddAction(String name, String s, CheckBox selectCheckBox, ChoiceBox<Student> Studentbox,Student student, Map<String, Student> sMap1, Map<String, Student> sMap2, Map<String, Student> sMap3, Map<String, Student> sMap4, Map<String, Student> sMap5){
        this.name=name;
        this.s=s;
        this.selectCheckBox=selectCheckBox;
        this.Studentbox=Studentbox;
        this.student=student;
        this.sMap1=sMap1;
        this.sMap2=sMap2;
        this.sMap3=sMap3;
        this.sMap4=sMap4;
        this.sMap5=sMap5;
    }

    @Override
    public void execute() {

        selectCheckBox.setText(student.getId().toUpperCase());
        selectCheckBox.setSelected(false);
        if (s.substring(0, 2).equals("t1")) {
            sMap1.put(selectCheckBox.getText(), student);
            System.out.println(s.substring(0, 2));
            System.out.println(sMap1);
        } else if (s.substring(0, 2).equals("t2")) {
            sMap2.put(selectCheckBox.getText(), student);
            System.out.println(s.substring(0, 2));
            System.out.println(sMap2);
        } else if (s.substring(0, 2).equals("t3")) {
            sMap3.put(selectCheckBox.getText(), student);
            System.out.println(s.substring(0, 2));
            System.out.println(sMap3);
        } else if (s.substring(0, 2).equals("t4")) {
            sMap4.put(selectCheckBox.getText(), student);
            System.out.println(s.substring(0, 2));
            System.out.println(sMap4);
        } else {
            sMap5.put(selectCheckBox.getText(), student);
            System.out.println(s.substring(0, 2));
            System.out.println(sMap5);
        }
        Studentbox.getItems().remove(student);
    }

    @Override
    public void undo() {

        Studentbox.getItems().add(student);

        if (s.substring(0, 2).equals("t1")) {
            sMap1.remove(selectCheckBox.getText());
            System.out.println(s.substring(0, 2));
            System.out.println(sMap1);
        } else if (s.substring(0, 2).equals("t2")) {
            sMap2.remove(selectCheckBox.getText());
            System.out.println(s.substring(0, 2));
            System.out.println(sMap2);
        } else if (s.substring(0, 2).equals("t3")) {
            sMap3.remove(selectCheckBox.getText());
            System.out.println(s.substring(0, 2));
            System.out.println(sMap3);
        } else if (s.substring(0, 2).equals("t4")) {
            sMap4.remove(selectCheckBox.getText());
            System.out.println(s.substring(0, 2));
            System.out.println(sMap4);
        } else {
            sMap5.remove(selectCheckBox.getText());
            System.out.println(s.substring(0, 2));
            System.out.println(sMap5);
        }

        selectCheckBox.setText("");
        selectCheckBox.setSelected(true);

    }

    @Override
    public String getName() {
        return this.name;
    }
}
