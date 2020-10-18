package controller;
/**
 * Li Zheng 
 */
import java.io.File;
import java.util.*;

import command.CommandManager;
import command.action.AddAction;
import command.action.DeleteAction;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.stage.FileChooser;
import model.*;

public class Controller {

	DataHandler dh = new DataHandler();
	Team t1;
	Team t2;
	Team t3;
	Team t4;
	Team t5;
	List<CheckBox> swapCheckBox = new ArrayList<CheckBox>();
	List<String> swapCheckBox1 = new ArrayList<String>();
	HashMap<String, Map<String, Student>> swapMap = new HashMap<String, Map<String, Student>>();
	HashMap<String, CheckBox> checkBoxMap = new HashMap<String, CheckBox>();
	HashMap<String, TextField> textMap = new HashMap<String, TextField>();
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


		//read from DB
		DBDataHandler.readTeamFromDB();
		dh.studentList = DBDataHandler.studentList;
		dh.studentSkillSetList = DBDataHandler.studentSkillSetList;
		dh.projectList = DBDataHandler.projectList;
		dh.teamList = DBDataHandler.teamList;

		// add student in ChoiceBox
		for (Student s : dh.studentSkillSetList) {
			Studentbox.getItems().addAll(s);
			allStudents.put(s.getId(), s);
		}

		// setting the Bar chart.
		xaxis1.setLabel("Skill Gap");
		yaxis1.setLabel("Number of Gap");
		xaxis2.setLabel("The percentage of getting 1st and 2nd preference");
		yaxis2.setLabel("Persentage");
		xaxis3.setLabel("Average Competency Level");
		yaxis3.setLabel("Number of Average Competency Level");

		// setting the label with team information.
		if(dh.projectList.size()==5) {
			team1ProID.setText(dh.projectList.get(0).getId() + " " + dh.projectList.get(0).getRanking());
			team2ProID.setText(dh.projectList.get(1).getId() + " " + dh.projectList.get(1).getRanking());
			team3ProID.setText(dh.projectList.get(2).getId() + " " + dh.projectList.get(2).getRanking());
			team4ProID.setText(dh.projectList.get(3).getId() + " " + dh.projectList.get(3).getRanking());
			team5ProID.setText(dh.projectList.get(4).getId() + " " + dh.projectList.get(4).getRanking());
		}

//		 add check box at event listener.
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

		// table view setting
		tableTeam.setEditable(true);
		studentid.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
		skillset.setCellValueFactory(new PropertyValueFactory<Student,String>("skillset"));
		type.setCellValueFactory(new PropertyValueFactory<Student,Character>("persionalityType"));
		confic.setCellValueFactory(new PropertyValueFactory<Student,String>("conflict"));
		preference.setCellValueFactory(new PropertyValueFactory<Student,String>("preferences"));
		competencylevel.setCellValueFactory(new PropertyValueFactory<Student,Double>("competencyLevel"));


		initUIFromData();
	}

	@FXML
	void addButton(ActionEvent event) {
		if (swapCheckBox.size() == 1) {

			for (String s : checkBoxMap.keySet()) {
				if (checkBoxMap.get(s).isSelected() && checkBoxMap.get(s).getText().isEmpty()) {

					CommandManager manager = CommandManager.getInstance();
					manager.execute(new AddAction("Add "+s+","+Studentbox.getValue(),s,checkBoxMap.get(s),Studentbox,Studentbox.getValue(),t1.getStudentMap(),t2.getStudentMap(),t3.getStudentMap(),t4.getStudentMap(),t5.getStudentMap()));

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
		
		double []result3 = {t1.getAverageCompetencyLevel(),t2.getAverageCompetencyLevel(),t3.getAverageCompetencyLevel(),t4.getAverageCompetencyLevel(),t5.getAverageCompetencyLevel()};
		xaxis3.setLabel("Stander Deviation: " + StanderDeviation.calculateSD(result3));
		
		
		//display the SD for skillgap
		double []result = {t1.getSkillgap(),t2.getSkillgap(),t3.getSkillgap(),t4.getSkillgap(),t5.getSkillgap()};
		xaxis1.setLabel("Stander Deviation: " + StanderDeviation.calculateSD(result));
				
				
		//display the SD for preference persentage
		double []result2 = {t1.getPerCenTageStu(),t2.getPerCenTageStu(),t3.getPerCenTageStu(),t4.getPerCenTageStu(),t5.getPerCenTageStu()};
		xaxis2.setLabel("Stander Deviation: " + StanderDeviation.calculateSD(result2));


	}

	@FXML
	void deleteBotton(ActionEvent event) {

		CommandManager manager = CommandManager.getInstance();
		manager.execute(new DeleteAction("Delete ",checkBoxMap,dh,Studentbox,t1.getStudentMap(),t2.getStudentMap(),t3.getStudentMap(),t4.getStudentMap(),t5.getStudentMap()));

	}
		
	
    @FXML
    void team1detail(MouseEvent event) {
    	tableTeam.getItems().clear();
		for(String stu1: t1.getStudentMap().keySet()) {
			list1.add(t1.getStudentMap().get(stu1));
		}
		tableTeam.setItems(list1);
		System.out.println(t1.getStudentMap());
		
    }

    @FXML
    void team2detail(MouseEvent event) {
    	tableTeam.getItems().clear();
    	for(String stu1: t2.getStudentMap().keySet()) {
			list2.add(t2.getStudentMap().get(stu1));
		}
		tableTeam.setItems(list2);
		System.out.println(t2.getStudentMap());
    }

    @FXML
    void team3detail(MouseEvent event) {
    	tableTeam.getItems().clear();
    	for(String stu1: t3.getStudentMap().keySet()) {
			list3.add(t3.getStudentMap().get(stu1));
		}
		tableTeam.setItems(list3);
		System.out.println(t3.getStudentMap());

    }

    @FXML
    void team4detail(MouseEvent event) {
    	tableTeam.getItems().clear();
    	for(String stu1: t4.getStudentMap().keySet()) {
			list4.add(t4.getStudentMap().get(stu1));
		}
		tableTeam.setItems(list4);
		System.out.println(t4.getStudentMap());

    }

    @FXML
    void team5detail(MouseEvent event) {
    	tableTeam.getItems().clear();
    	for(String stu1: t5.getStudentMap().keySet()) {
			list5.add(t5.getStudentMap().get(stu1));
		}
		tableTeam.setItems(list5);
		//below is for back end test
		for(String s : t5.getStudentMap().keySet()) {
			System.out.println(t5.getStudentMap().get(s));
			System.out.println(t5.getStudentMap().get(s).getCompetencyLevel());
		}
		

    }

	public void autoSwapAction(ActionEvent actionEvent) {

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
			System.out.println("***************************************************************"+"\n");

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


    private void initUIFromData(){
		try {
			ArrayList<Team> teams = dh.teamList;
			for (int i = 0; i < teams.size(); i++) {
				Team tmpTeam = teams.get(i);
				Project tmpProject = tmpTeam.getProject();
				if (i == 0) {
					team1ProID.setText(tmpProject.getId() + " " + tmpProject.getRanking());
					t1 = teams.get(0);
					swapMap.put("t11", t1.getStudentMap());
					swapMap.put("t12", t1.getStudentMap());
					swapMap.put("t13", t1.getStudentMap());
					swapMap.put("t14", t1.getStudentMap());

				}
				if (i == 1) {
					team2ProID.setText(tmpProject.getId() + " " + tmpProject.getRanking());
					t2 = teams.get(1);
					swapMap.put("t21", t2.getStudentMap());
					swapMap.put("t22", t2.getStudentMap());
					swapMap.put("t23", t2.getStudentMap());
					swapMap.put("t24", t2.getStudentMap());

				}
				if (i == 2) {
					team3ProID.setText(tmpProject.getId() + " " + tmpProject.getRanking());
					t3 = teams.get(2);
					swapMap.put("t31", t3.getStudentMap());
					swapMap.put("t32", t3.getStudentMap());
					swapMap.put("t33", t3.getStudentMap());
					swapMap.put("t34", t3.getStudentMap());

				}
				if (i == 3) {
					team4ProID.setText(tmpProject.getId() + " " + tmpProject.getRanking());
					t4 = teams.get(3);
					swapMap.put("t41", t4.getStudentMap());
					swapMap.put("t42", t4.getStudentMap());
					swapMap.put("t43", t4.getStudentMap());
					swapMap.put("t44", t4.getStudentMap());

				}
				if (i == 4) {
					team5ProID.setText(tmpProject.getId() + " " + tmpProject.getRanking());
					t5 = teams.get(4);
					swapMap.put("t51", t5.getStudentMap());
					swapMap.put("t52", t5.getStudentMap());
					swapMap.put("t53", t5.getStudentMap());
					swapMap.put("t54", t5.getStudentMap());
				}

				Map<String, Student> studentMap = tmpTeam.getStudentMap();
				int j = 1;
				for (String str : studentMap.keySet()) {
					CheckBox tmpCheckBox = checkBoxMap.get("t" + (i + 1) + "" + j);
					tmpCheckBox.setText(studentMap.get(str).getId().toUpperCase());

					j = j + 1;
				}

				Studentbox.getItems().clear();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("init UI");
	}

	public void saveAction(ActionEvent actionEvent) {

		boolean isAllFill=true;

		for (String str:checkBoxMap.keySet()) {
			String checkBoxStr=checkBoxMap.get(str).getText().trim();
			if(checkBoxStr.equals("")){
				isAllFill=false;
				break;
			}
			
			
		}
		if(!isAllFill){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(ErrorMessages.CHECK_BOX_BLANK);
			alert.show();
		}else {
			ArrayList<Team> teams = new ArrayList<>();
			teams.add(t1);
			teams.add(t2);
			teams.add(t3);
			teams.add(t4);
			teams.add(t5);
			boolean isSuc = DBDataHandler.save2DB(teams);
			if (!isSuc) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(ErrorMessages.SAVE_DB_EXCEPTION);
				alert.show();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText(ErrorMessages.SAVE_TO_DB);
				alert.show();
			}
		}
		
		
	}

	public void importTxtAction(ActionEvent actionEvent) {

		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(tableTeam.getScene().getWindow());
		if (file != null) {
			dh.readFormTeamFromFile(file);
		}

		initUIFromData();

	}
}
