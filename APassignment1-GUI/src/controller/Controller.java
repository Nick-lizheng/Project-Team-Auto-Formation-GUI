package controller;
/**
 * Li Zheng 
 */
import java.util.*;

import command.CommandManager;
import command.action.AddAction;
import command.action.DeleteAction;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import model.DataHandler;
import model.Project;
import model.StanderDeviation;
import model.Student;
import model.Team;

public class Controller {

	DataHandler dh = new DataHandler();
	Team t1;
	Team t2;
	Team t3;
	Team t4;
	Team t5;
//	HashMap<String, CheckBox> swapCheckBox = new HashMap<String, CheckBox>();
	List<CheckBox> swapCheckBox = new ArrayList<CheckBox>();
	List<String> swapCheckBox1 = new ArrayList<String>();
	HashMap<String, Map<String, Student>> swapMap = new HashMap<String, Map<String, Student>>();
	HashMap<String, CheckBox> checkBoxMap = new HashMap<String, CheckBox>();
	HashMap<String, TextField> textMap = new HashMap<String, TextField>();
	Map<String, Student> sMap1 = new HashMap<String, Student>();
	Map<String, Student> sMap2 = new HashMap<String, Student>();
	Map<String, Student> sMap3 = new HashMap<String, Student>();
	Map<String, Student> sMap4 = new HashMap<String, Student>();
	Map<String, Student> sMap5 = new HashMap<String, Student>();
	Map<String, Student> allStudents = new HashMap<String, Student>();

	ObservableList<Student> list1 =FXCollections.observableArrayList();
	ObservableList<Student> list2 =FXCollections.observableArrayList();
	ObservableList<Student> list3 =FXCollections.observableArrayList();
	ObservableList<Student> list4 =FXCollections.observableArrayList();
	ObservableList<Student> list5 =FXCollections.observableArrayList();
	
	
	
	@FXML
	private Button redo;
	
	@FXML
	private TableView<Student> tableTeam;

	@FXML
	private TableColumn<Student, String> studentid;

	@FXML
	private TableColumn<Student, String> skillset;

	@FXML
	private TableColumn<Student, Character> type;

	@FXML
	private TableColumn<Student, String> confic;

	@FXML
	private TableColumn<Student, String> preference;

	@FXML
	private TableColumn<Student, Double> competencylevel;

	@FXML
	private TableColumn<Student, String> skillgap;

	@FXML
	private Button add;

	@FXML
	private GridPane team1;

	@FXML
	private CheckBox team1checkbox1;
	
	@FXML
	private CheckBox team1checkbox2;

	@FXML
	private CheckBox team1checkbox3;

	@FXML
	private CheckBox team1checkbox4;

	@FXML
	private Label team1ProID;

	@FXML
	private GridPane team2;

	@FXML
	private CheckBox team2checkbox1;

	@FXML
	private CheckBox team2checkbox2;

	@FXML
	private CheckBox team2checkbox3;

	@FXML
	private CheckBox team2checkbox4;

	@FXML
	private Label team2ProID;

	@FXML
	private GridPane team3;

	@FXML
	private CheckBox team3checkbox1;

	@FXML
	private CheckBox team3checkbox2;

	@FXML
	private CheckBox team3checkbox3;

	@FXML
	private CheckBox team3checkbox4;

	@FXML
	private Label team3ProID;

	@FXML
	private GridPane team4;

	@FXML
	private CheckBox team4checkbox1;

	@FXML
	private CheckBox team4checkbox2;

	@FXML
	private CheckBox team4checkbox3;

	@FXML
	private CheckBox team4checkbox4;

	@FXML
	private Label team4ProID;

	@FXML
	private GridPane team5;

	@FXML
	private CheckBox team5checkbox1;

	@FXML
	private CheckBox team5checkbox2;

	@FXML
	private CheckBox team5checkbox3;

	@FXML
	private CheckBox team5checkbox4;

	@FXML
	private Label team5ProID;

	@FXML
	private BarChart<?, ?> bc2;

	@FXML
	private CategoryAxis xaxis2;

	@FXML
	private NumberAxis yaxis2;

	@FXML
	private BarChart<?, ?> bc3;

	@FXML
	private CategoryAxis xaxis3;

	@FXML
	private NumberAxis yaxis3;

	@FXML
	private BarChart<String, Number> bc1;

	@FXML
	private CategoryAxis xaxis1;

	@FXML
	private NumberAxis yaxis1;

	@FXML
	private Button Swap;

	@FXML
	private TextField studentidtext;

	@FXML
	private Label studentDetial;

	@FXML
	private Button delete;

	@FXML
	private Button Caculation;
	
    @FXML
    private Label sd1;

    @FXML
    private Label sd3;

	@FXML
	private ChoiceBox<Student> Studentbox;

	public Label getStudentDetial() {
		return studentDetial;
	}

	public void setStudentDetial(Label studentDetial) {
		this.studentDetial = studentDetial;
	}

	@FXML
	public void initialize() {
		// add all check box and team and student in map
		checkBoxMap.put("t11", team1checkbox1);
		checkBoxMap.put("t12", team1checkbox2);
		checkBoxMap.put("t13", team1checkbox3);
		checkBoxMap.put("t14", team1checkbox4);
		checkBoxMap.put("t21", team2checkbox1);
		checkBoxMap.put("t22", team2checkbox2);
		checkBoxMap.put("t23", team2checkbox3);
		checkBoxMap.put("t24", team2checkbox4);
		checkBoxMap.put("t31", team3checkbox1);
		checkBoxMap.put("t32", team3checkbox2);
		checkBoxMap.put("t33", team3checkbox3);
		checkBoxMap.put("t34", team3checkbox4);
		checkBoxMap.put("t41", team4checkbox1);
		checkBoxMap.put("t42", team4checkbox2);
		checkBoxMap.put("t43", team4checkbox3);
		checkBoxMap.put("t44", team4checkbox4);
		checkBoxMap.put("t51", team5checkbox1);
		checkBoxMap.put("t52", team5checkbox2);
		checkBoxMap.put("t53", team5checkbox3);
		checkBoxMap.put("t54", team5checkbox4);
		swapMap.put("t11", sMap1);
		swapMap.put("t12", sMap1);
		swapMap.put("t13", sMap1);
		swapMap.put("t14", sMap1);
		swapMap.put("t21", sMap2);
		swapMap.put("t22", sMap2);
		swapMap.put("t23", sMap2);
		swapMap.put("t24", sMap2);
		swapMap.put("t31", sMap3);
		swapMap.put("t32", sMap3);
		swapMap.put("t33", sMap3);
		swapMap.put("t34", sMap3);
		swapMap.put("t41", sMap4);
		swapMap.put("t42", sMap4);
		swapMap.put("t43", sMap4);
		swapMap.put("t44", sMap4);
		swapMap.put("t51", sMap5);
		swapMap.put("t52", sMap5);
		swapMap.put("t53", sMap5);
		swapMap.put("t54", sMap5);

		// read the data from milestone2
		dh.readAll();
		dh.readFormTeam();

		// add student in ChoiceBox
		for (Student s : dh.studentSkillSetList) {
			Studentbox.getItems().addAll(s);
			allStudents.put(s.getId(), s);
		}

		// setting the Barchar.
		xaxis1.setLabel("Skill Gap");
		yaxis1.setLabel("Number of Gap");
		xaxis2.setLabel("The percentage of getting 1st and 2nd preference");
		yaxis2.setLabel("Persentage");
		xaxis3.setLabel("Average Competency Level");
		yaxis3.setLabel("Number of Average Competency Level");

		// setting the label with team information.
		team1ProID.setText(dh.projectList.get(0).getId() + " " + dh.projectList.get(0).getRanking());
		team2ProID.setText(dh.projectList.get(1).getId() + " " + dh.projectList.get(1).getRanking());
		team3ProID.setText(dh.projectList.get(2).getId() + " " + dh.projectList.get(2).getRanking());
		team4ProID.setText(dh.projectList.get(3).getId() + " " + dh.projectList.get(3).getRanking());
		team5ProID.setText(dh.projectList.get(4).getId() + " " + dh.projectList.get(4).getRanking());

//		 add checkbox at eventlistenter.
		for (String s : checkBoxMap.keySet()) {
			checkBoxMap.get(s).selectedProperty()
					.addListener((ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
						if (old_val == false && new_val == true) {
							swapCheckBox.add(checkBoxMap.get(s));
							swapCheckBox1.add(s);
							System.out.println(checkBoxMap.get(s).getText());
							System.out.println(swapCheckBox.toString());
							System.out.println(swapCheckBox1.toString());

						} else if (old_val == true && new_val == false) {

							swapCheckBox.remove(checkBoxMap.get(s));
							swapCheckBox1.remove(s);
							System.out.println(checkBoxMap.get(s).getText());
							System.out.println(swapCheckBox.toString());
							System.out.println(swapCheckBox1.toString());
						}

					});

		}

		// teableview setting
		tableTeam.setEditable(true);
		studentid.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
		skillset.setCellValueFactory(new PropertyValueFactory<Student,String>("skillset"));
		type.setCellValueFactory(new PropertyValueFactory<Student,Character>("persionalityType"));
		confic.setCellValueFactory(new PropertyValueFactory<Student,String>("conflict"));
		preference.setCellValueFactory(new PropertyValueFactory<Student,String>("preferences"));
		competencylevel.setCellValueFactory(new PropertyValueFactory<Student,Double>("competencyLevel"));
//		skillgap.setCellValueFactory(new PropertyValueFactory<Student,String>("skillgap"));
		
		
	
		
		

	}

	@FXML
	void addButton(ActionEvent event) {
		if (swapCheckBox.size() == 1) {

			for (String s : checkBoxMap.keySet()) {
				if (checkBoxMap.get(s).isSelected() && checkBoxMap.get(s).getText().isEmpty()) {

					CommandManager manager = CommandManager.getInstance();
					manager.execute(new AddAction("Add "+s+","+Studentbox.getValue(),s,checkBoxMap.get(s),Studentbox,Studentbox.getValue(),sMap1,sMap2,sMap3,sMap4,sMap5));

//					checkBoxMap.get(s).setText(Studentbox.getValue().getId().toUpperCase());
//					checkBoxMap.get(s).setSelected(false);
//					if (s.substring(0, 2).equals("t1")) {
//						sMap1.put(checkBoxMap.get(s).getText(), Studentbox.getValue());
//						System.out.println(s.substring(0, 2));
//						System.out.println(sMap1);
//					} else if (s.substring(0, 2).equals("t2")) {
//						sMap2.put(checkBoxMap.get(s).getText(), Studentbox.getValue());
//						System.out.println(s.substring(0, 2));
//						System.out.println(sMap2);
//					} else if (s.substring(0, 2).equals("t3")) {
//						sMap3.put(checkBoxMap.get(s).getText(), Studentbox.getValue());
//						System.out.println(s.substring(0, 2));
//						System.out.println(sMap3);
//					} else if (s.substring(0, 2).equals("t4")) {
//						sMap4.put(checkBoxMap.get(s).getText(), Studentbox.getValue());
//						System.out.println(s.substring(0, 2));
//						System.out.println(sMap4);
//					} else {
//						sMap5.put(checkBoxMap.get(s).getText(), Studentbox.getValue());
//						System.out.println(s.substring(0, 2));
//						System.out.println(sMap5);
//					}
//
//					Studentbox.getItems().remove(Studentbox.getValue());
				} else if (checkBoxMap.get(s).isSelected() && !checkBoxMap.get(s).getText().isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setContentText(ErrorMessages.STUDENT_ALREADY_ADDED);
					alert.show();
				}
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(ErrorMessages.INVALID_ADDITION_CHECKBOX);
			alert.show();

		}

		t1 = new Team(dh.projectList.get(0), sMap1);
		t2 = new Team(dh.projectList.get(1), sMap2);
		t3 = new Team(dh.projectList.get(2), sMap3);
		t4 = new Team(dh.projectList.get(3), sMap4);
		t5 = new Team(dh.projectList.get(4), sMap5);

		System.out.println("add a student in a team");
	}

	@FXML
	void swapButton(ActionEvent event) {
		if (swapCheckBox.size() == 2) {
			swap(swapCheckBox.get(0), swapCheckBox.get(1));
			swapMap(swapCheckBox1.get(0), swapCheckBox1.get(1));
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(ErrorMessages.INVALID_SWAP_CHECKBOX);
			alert.show();

		}

		System.out.println("swap a student in a team");

	}

	public void swap(CheckBox a, CheckBox b) {
		String c = "";
		c = a.getText();
		a.setText(b.getText());
		b.setText(c);
	}

	public void swapMap(String a, String b) {
		// get the checkBoxMap key.

		System.out.println("==========print the swapMap============");
		System.out.println(swapCheckBox.get(1).getText());
		swapMap.get(a).remove(swapCheckBox.get(1).getText());
		System.out.println(swapMap.get(a));

		swapMap.get(a).put(swapCheckBox.get(0).getText(), allStudents.get(swapCheckBox.get(0).getText()));
		System.out.println(swapMap.get(a));

		System.out.println(swapCheckBox.get(0).getText());
		swapMap.get(b).remove(swapCheckBox.get(0).getText());
		System.out.println(swapMap.get(b));
		swapMap.get(b).put(swapCheckBox.get(1).getText(), allStudents.get(swapCheckBox.get(1).getText()));
		swapMap.get(b);
		System.out.println("==========print the swapMap============");

	}
//get the key from a map.
	public static Object getKey(Map map, Object value) {
		Object kk = null;
		List<Object> keyList = new ArrayList<>();
		for (Object key : map.keySet()) {
			if (map.get(key).equals(value)) {
				keyList.add(key);
			}
		}
		for (Object k : keyList) {
			kk = k;
		}

		return kk.toString();
	}


	
	
	
	
	
	
	@FXML
	void caculationBotton(ActionEvent event) {

		XYChart.Series dataSeries1 = new XYChart.Series();
		XYChart.Series dataSeries2 = new XYChart.Series();

		dataSeries1.getData().add(new XYChart.Data(dh.projectList.get(0).getId(), t1.getSkillgap()));
		dataSeries1.getData().add(new XYChart.Data(dh.projectList.get(1).getId(), t2.getSkillgap()));
		dataSeries1.getData().add(new XYChart.Data(dh.projectList.get(2).getId(), t3.getSkillgap()));
		dataSeries1.getData().add(new XYChart.Data(dh.projectList.get(3).getId(), t4.getSkillgap()));
		dataSeries1.getData().add(new XYChart.Data(dh.projectList.get(4).getId(), t5.getSkillgap()));
		bc1.getData().clear();
		bc1.getData().add(dataSeries1);

		dataSeries2.getData().add(new XYChart.Data(dh.projectList.get(0).getId(), t1.getPerCenTageStu()));
		dataSeries2.getData().add(new XYChart.Data(dh.projectList.get(1).getId(), t2.getPerCenTageStu()));
		dataSeries2.getData().add(new XYChart.Data(dh.projectList.get(2).getId(), t3.getPerCenTageStu()));
		dataSeries2.getData().add(new XYChart.Data(dh.projectList.get(3).getId(), t4.getPerCenTageStu()));
		dataSeries2.getData().add(new XYChart.Data(dh.projectList.get(4).getId(), t5.getPerCenTageStu()));
		bc2.getData().clear();
		bc2.getData().add(dataSeries2);

		XYChart.Series dataSeries3 = new XYChart.Series();

		dataSeries3.getData().add(new XYChart.Data(dh.projectList.get(0).getId(), t1.getAverageCompetencyLevel()));
		dataSeries3.getData().add(new XYChart.Data(dh.projectList.get(1).getId(), t2.getAverageCompetencyLevel()));
		dataSeries3.getData().add(new XYChart.Data(dh.projectList.get(2).getId(), t3.getAverageCompetencyLevel()));
		dataSeries3.getData().add(new XYChart.Data(dh.projectList.get(3).getId(), t4.getAverageCompetencyLevel()));
		dataSeries3.getData().add(new XYChart.Data(dh.projectList.get(4).getId(), t5.getAverageCompetencyLevel()));

		bc3.getData().clear();
		bc3.getData().add(dataSeries3);
		
		
		
		//display the SD for AverageCompetencyLevel
		double []result = {t1.getAverageCompetencyLevel(),t2.getAverageCompetencyLevel(),t3.getAverageCompetencyLevel(),t4.getAverageCompetencyLevel(),t5.getAverageCompetencyLevel()};
		sd3.setText("Stander Deviation: " + StanderDeviation.calculateSD(result));

	}

	@FXML
	void deleteBotton(ActionEvent event) {

		CommandManager manager = CommandManager.getInstance();
		manager.execute(new DeleteAction("Delete ",checkBoxMap,dh,Studentbox,sMap1,sMap2,sMap3,sMap4,sMap5));

//		for (String s : checkBoxMap.keySet()) {
//			if (checkBoxMap.get(s).isSelected()) {
//
//
//				for (Student student : dh.studentSkillSetList) {
//					if (student.getId().equals(checkBoxMap.get(s).getText()))
//						Studentbox.getItems().add(student);
//				}
//
//				if (s.substring(0, 2).equals("t1")) {
//
//					sMap1.remove(checkBoxMap.get(s).getText());
//					System.out.println(sMap1);
//				} else if (s.substring(0, 2).equals("t2")) {
//					sMap2.remove(checkBoxMap.get(s).getText());
//					System.out.println(sMap2);
//				} else if (s.substring(0, 2).equals("t3")) {
//					sMap3.remove(checkBoxMap.get(s).getText());
//					System.out.println(sMap3);
//				} else if (s.substring(0, 2).equals("t4")) {
//					sMap4.remove(checkBoxMap.get(s).getText());
//					System.out.println(sMap4);
//				} else {
//					sMap5.remove(checkBoxMap.get(s).getText());
//					System.out.println(sMap5);
//				}
//				checkBoxMap.get(s).setText("");
//				checkBoxMap.get(s).setSelected(false);
//			}
//
//		}

	}
	
	
	
    @FXML
    void team1detail(MouseEvent event) {
    	tableTeam.getItems().clear();
		for(String stu1: sMap1.keySet()) {
			list1.add(sMap1.get(stu1));			
		}
		tableTeam.setItems(list1);
		System.out.println(sMap1);
		
    }

    @FXML
    void team2detail(MouseEvent event) {
    	tableTeam.getItems().clear();
    	for(String stu1: sMap2.keySet()) {
			list2.add(sMap2.get(stu1));			
		}
		tableTeam.setItems(list2);
		System.out.println(sMap2);
    }

    @FXML
    void team3detail(MouseEvent event) {
    	tableTeam.getItems().clear();
    	for(String stu1: sMap3.keySet()) {
			list3.add(sMap3.get(stu1));			
		}
		tableTeam.setItems(list3);
		System.out.println(sMap3);

    }

    @FXML
    void team4detail(MouseEvent event) {
    	tableTeam.getItems().clear();
    	for(String stu1: sMap4.keySet()) {
			list4.add(sMap4.get(stu1));			
		}
		tableTeam.setItems(list4);
		System.out.println(sMap4);

    }

    @FXML
    void team5detail(MouseEvent event) {
    	tableTeam.getItems().clear();
    	for(String stu1: sMap5.keySet()) {
			list5.add(sMap5.get(stu1));			
		}
		tableTeam.setItems(list5);
		//below is for backend test
		for(String s : sMap5.keySet()) {
			System.out.println(sMap5.get(s));
			System.out.println(sMap5.get(s).getCompetencyLevel());
		}
		

    }

	public void autoSwapAction(ActionEvent actionEvent) {


//		ArrayList<Student> studentSkillSetList = dh.studentSkillSetList;
//
//		Map<String, Student> sMap1 = new HashMap<String, Student>();
//		sMap1.put(studentSkillSetList.get(0).getId().toUpperCase(), studentSkillSetList.get(0));
//		sMap1.put(studentSkillSetList.get(1).getId().toUpperCase(), studentSkillSetList.get(1));
//		sMap1.put(studentSkillSetList.get(2).getId().toUpperCase(), studentSkillSetList.get(2));
//		sMap1.put(studentSkillSetList.get(3).getId().toUpperCase(), studentSkillSetList.get(3));
//
//		Map<String, Student> sMap2 = new HashMap<String, Student>();
//		sMap2.put(studentSkillSetList.get(4).getId().toUpperCase(), studentSkillSetList.get(4));
//		sMap2.put(studentSkillSetList.get(5).getId().toUpperCase(), studentSkillSetList.get(5));
//		sMap2.put(studentSkillSetList.get(6).getId().toUpperCase(), studentSkillSetList.get(6));
//		sMap2.put(studentSkillSetList.get(7).getId().toUpperCase(), studentSkillSetList.get(7));
//
//		Map<String, Student> sMap3 = new HashMap<String, Student>();
//		sMap3.put(studentSkillSetList.get(8).getId().toUpperCase(), studentSkillSetList.get(8));
//		sMap3.put(studentSkillSetList.get(9).getId().toUpperCase(), studentSkillSetList.get(9));
//		sMap3.put(studentSkillSetList.get(10).getId().toUpperCase(), studentSkillSetList.get(10));
//		sMap3.put(studentSkillSetList.get(11).getId().toUpperCase(), studentSkillSetList.get(11));
//
//		Map<String, Student> sMap4 = new HashMap<String, Student>();
//		sMap4.put(studentSkillSetList.get(12).getId().toUpperCase(), studentSkillSetList.get(12));
//		sMap4.put(studentSkillSetList.get(13).getId().toUpperCase(), studentSkillSetList.get(13));
//		sMap4.put(studentSkillSetList.get(14).getId().toUpperCase(), studentSkillSetList.get(14));
//		sMap4.put(studentSkillSetList.get(15).getId().toUpperCase(), studentSkillSetList.get(15));
//
//		Map<String, Student> sMap5 = new HashMap<String, Student>();
//		sMap5.put(studentSkillSetList.get(16).getId().toUpperCase(), studentSkillSetList.get(16));
//		sMap5.put(studentSkillSetList.get(17).getId().toUpperCase(), studentSkillSetList.get(17));
//		sMap5.put(studentSkillSetList.get(18).getId().toUpperCase(), studentSkillSetList.get(18));
//		sMap5.put(studentSkillSetList.get(19).getId().toUpperCase(), studentSkillSetList.get(19));
//
//		Team t1 = new Team(dh.projectList.get(0), sMap1);
//		Team t2 = new Team(dh.projectList.get(1), sMap2);
//		Team t3 = new Team(dh.projectList.get(2), sMap3);
//		Team t4 = new Team(dh.projectList.get(3), sMap4);
//		Team t5 = new Team(dh.projectList.get(4), sMap5);
//

		//add thread
		Thread autoThread=new Thread(){
			public void run(){
				ArrayList<Team> teams=new ArrayList<>();
				teams.add(t1);
				teams.add(t2);
				teams.add(t3);
				teams.add(t4);
				teams.add(t5);


				double gap=Double.MAX_VALUE;
				ArrayList<Team> calcTeams=swapTeamMaxMaxAndTeamMinMin(teams,gap);

//				Platform.runLater(new Runnable() {
//					@Override
//					public void run() {
//						//set ui
//						for (int i = 0; i < calcTeams.size(); i++) {
//							Team team=calcTeams.get(i);
//							Map<String, Student> sMap=team.getStudentMap();
//							int j=0;
//							for(String str: sMap.keySet()) {
//								j=j+1;
//								String checkBoxKeyStr="t"+(i+1)+""+j;
//								CheckBox checkBox=checkBoxMap.get(checkBoxKeyStr);
//								Student student=sMap.get(str);
//								checkBox.setText(student.getId().toUpperCase());
//								Studentbox.getItems().remove(student);
//							}
//						}
//					}
//				});
			}
		};
		autoThread.setDaemon(true);
		autoThread.start();
	}

	private ArrayList<Team> swapTeamMaxMaxAndTeamMinMin(ArrayList<Team> teams,double gap){

		double tMaxAcl=Double.MIN_VALUE;
		double tMinAcl= Double.MAX_VALUE;

		Team maxAclTeam=null;
		Team minAclTeam=null;

		//get max ACL team and min ACL team
		for (int i = 0; i < teams.size(); i++) {
			Team tmpTeam=teams.get(i);
			double acl=tmpTeam.getAverageCompetencyLevel();
			if(acl>tMaxAcl){
				tMaxAcl=acl;
				maxAclTeam=tmpTeam;
			}
			if(acl<tMinAcl){
				tMinAcl=acl;
				minAclTeam=tmpTeam;
			}
		}

		System.out.println("MaxACLTeam:"+maxAclTeam.getProject().getId()+" "+tMaxAcl);
		System.out.println("MinACLTeam:"+minAclTeam.getProject().getId()+" "+tMinAcl);

		double tmpGap=Math.abs(tMaxAcl-tMinAcl);
		if(tmpGap<gap){

			//swap max acl team max
			Map<String, Student> maxSMap=maxAclTeam.getStudentMap();
			Student maxStu=null;
			double maxCl= Double.MIN_VALUE;
			for(String str: maxSMap.keySet()) {
				Student tmpStu=maxSMap.get(str);
				double tmpCl=tmpStu.getCompetencyLevel();
				if(tmpCl>maxCl){
					maxCl=tmpCl;
					maxStu=tmpStu;
				}
			}

			Map<String, Student> minSMap=minAclTeam.getStudentMap();
			Student minStu=null;
			double minCl= Double.MAX_VALUE;
			for(String str: minSMap.keySet()) {
				Student tmpStu=minSMap.get(str);
				double tmpCl=tmpStu.getCompetencyLevel();
				if(tmpCl<minCl){
					minCl=tmpCl;
					minStu=tmpStu;
				}
			}

			//swap smap
			maxSMap.remove(maxStu.getId().toUpperCase());
			maxSMap.put(minStu.getId().toUpperCase(),minStu);

			minSMap.remove(minStu.getId().toUpperCase());
			minSMap.put(maxStu.getId().toUpperCase(),maxStu);

			System.out.println("Swap:"+maxStu.getId().toUpperCase()+","+minStu.getId().toUpperCase());

			Student finalMaxStu = maxStu;
			Student finalMinStu = minStu;

			Platform.runLater(new Runnable() {
				@Override
				public void run() {

					CheckBox maxCheckBox = null;
					CheckBox minCheckBox = null;

					String maxStr=null;
					String minStr=null;

					//clear all checkbox selection
					for (String str:checkBoxMap.keySet()) {
						checkBoxMap.get(str).setSelected(false);
						if(checkBoxMap.get(str).getText().equals(finalMaxStu.getId().toUpperCase())){
							maxCheckBox=checkBoxMap.get(str);
							maxStr=str;
						}
						if(checkBoxMap.get(str).getText().equals(finalMinStu.getId().toUpperCase())){
							minCheckBox=checkBoxMap.get(str);
							minStr=str;
						}
					}

					//swap checkbox String
					String c = "";
					c = maxCheckBox.getText();
					maxCheckBox.setText(minCheckBox.getText());
					minCheckBox.setText(c);
				}
			});

			return swapTeamMaxMaxAndTeamMinMin(teams,tmpGap);
		}
		else{
			return teams;
		}
	}

	public void undoAction(ActionEvent actionEvent) {
		CommandManager manager = CommandManager.getInstance();
		manager.undo();
	}
	

    @FXML
    void redoAction(ActionEvent event) {
    	CommandManager manager = CommandManager.getInstance();
		manager.redo();
    }
	
	
}
