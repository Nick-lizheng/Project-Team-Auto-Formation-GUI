package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import Exception.InvalidMemberException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataHandler {
	Map<String, Project> projectValidator = new HashMap<>();
	public ArrayList<Student> studentList = new ArrayList<>();
	public ArrayList<Student> studentSkillSetList = new ArrayList<>();
	public Set<Student> studentSet = new HashSet<>();
	public ArrayList<Project> projectList = new ArrayList<>();
	public Map<String, Integer> projectMap = new HashMap<>();
	public Set<Company> companySet = new HashSet<>();
	public Map<String, ProjectOwner> projectOwnerMap = new HashMap<>();
	public Map<String, Student> studentMap = new HashMap<>();
	public ArrayList<Team> teamList = new ArrayList<>();
	Map<String, Student> sMapValidator = new HashMap<>();

	public void addCompany() {
		System.out.println("Adding a company...");

		Scanner scan = new Scanner(System.in);
		try {
			while (true) {

				// readCompaniese();
				System.out.print("Unique Company ID: C");
				String id = "C" + Integer.parseInt(scan.nextLine());

				System.out.print("Company Name: ");
				String name = scan.nextLine();

				System.out.print("ABN Number: ");

				String abn = scan.next();
				while (abn.length() != 11) {
					System.err.println("ABN must be 11 digit number, please retry again! ");
					System.out.println("ABN Number: ");
					abn = scan.next();
				}
				scan.nextLine();

				System.out.print("URL: ");
				String url = scan.nextLine();

				System.out.print("Address: ");
				String address = scan.nextLine();

				Company company = new Company(id, name, abn, url, address);

				//
				if (!(companySet.contains(company))) {
					companySet.add(company);

					System.out.println("new record added!");
				} else {
					System.err.println("the companyID hava already exists!");
				}
				writeToFile(companySet);// write to Ser type file and txt type file.
				return;

			}
		} catch (NumberFormatException nex) {
			// nex.printStackTrace();
			System.err.println("Please input number, please try again!!");
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void addProjectOwner() {
		System.out.println("Adding a project owner...");
		Scanner scan = new Scanner(System.in);
		while (true)
			try {
				System.out.print("First name: ");
				String firstName = scan.nextLine();

				System.out.print("Surname: ");
				String surname = scan.nextLine();

				System.out.print("ID: Own");
				String id = "Own" + Integer.parseInt(scan.nextLine());

				System.out.print("Role: ");
				String role = scan.nextLine();

				System.out.print("Email: ");
				String email = scan.nextLine();

				System.out.print("Company ID: C");
				String companyID = "C" + Integer.parseInt(scan.next());
				// System.out.println(id+" " +companyID );

				scan.nextLine();

				ProjectOwner projectOwner = new ProjectOwner(firstName, surname, id, role, email, companyID);

				if (!projectOwnerMap.keySet().contains(id)) {
					projectOwnerMap.put(id, projectOwner);

					System.out.println("New Record Added!");
				} else {
					System.err.println(" The ProjectOwnerID hava already exists! ");
					System.out.println("");
					continue;
				}
				writeToFile(projectOwnerMap);

				return;

			} catch (NumberFormatException nex) {
				System.err.println("Please input number, please try again!!");
			} catch (Exception e) {
				e.printStackTrace();

			}

	}

	public void addProject() {

		System.out.println("Adding a Project...");
		Scanner scan = new Scanner(System.in);
		while (true)
			try {
				System.out.print("Title: ");
				String title = scan.nextLine();

				System.out.print("ID: Pr");
				String id = "Pr" + Integer.parseInt(scan.nextLine());

				System.out.print("Brief desc: ");
				String desc = scan.nextLine();

				System.out.print("ID: Own");
				String poID = "Own" + Integer.parseInt(scan.nextLine());
				System.out.print("Ranking (e.g. P4 N3 A2 W1):");
				String ranking = scan.nextLine();
				Project project = new Project(title, id, desc, poID, ranking);

//				String ranking="";
//
//				Project project = new Project(title, id, desc, poID, ranking);
//				System.out.print("Ranking (e.g. P4 N3 A2 W1):");
//				System.out.print("Progamming: P");
//				project.getRankingMap().put("P",Integer.parseInt(scan.next()));
//				System.out.print("Networking: N");
//				project.getRankingMap().put("N",Integer.parseInt(scan.next()));
//				System.out.print("Analytics: A");
//				project.getRankingMap().put("A",Integer.parseInt(scan.next()));
//				System.out.print("Web: W");
//				project.getRankingMap().put("W",Integer.parseInt(scan.next()));
//				
//				List<HashMap.Entry<String, Integer>> list2 = new ArrayList<>();
//				list2.addAll(project.getRankingMap().entrySet());
//				Collections.sort(list2, (o1, o2) -> o2.getValue()-o1.getValue());										
//				ranking = "P"+project.getRankingMap().get("P")+" "+"N"+project.getRankingMap().get("N")+" "
//						+"A"+project.getRankingMap().get("A")+" "+"W"+project.getRankingMap().get("W");	
//				project.setRanking(ranking);

				if (!projectValidator.keySet().contains(id)) {
					projectValidator.put(project.getId(), project);
					writeToFile(project);
				} else {
					System.err.println(" The ProjectID hava already exists!, please try another one.");
					System.err.println("");
					continue;
				}

				return;
			} catch (NumberFormatException nex) {
				System.err.println("Please input number, please try again!!");
			} catch (Exception e) {
				e.printStackTrace();

			}

	}

	public void captureStudentPersonalities() {
		String currentDirectory = System.getProperty("user.dir");
		System.out.println("The current working directory is " + currentDirectory);
		File file = new File("studentinfo.txt");

		if (file.exists() && studentSkillSetList.isEmpty()) {
			readFile("studentinfo.txt");
			System.out.println("Available students " + studentSkillSetList.size());
			for (Student s : studentSkillSetList) {
				System.out.print(s.getId() + " ");
				System.out.print(s.getSkillset() + " ");
				System.out.print(s.getPersionalityType() + " ");
				System.out.println(s.getConflict());
			}

			boolean found = false;
			do {
				System.out.println("Which student you want to update? (input Student id , x to cancel)");
				Scanner scan = new Scanner(System.in);
				String id = scan.nextLine().toUpperCase();
				if (id.toLowerCase().equals("x")) {
					studentSkillSetList.clear();
					break;
				}
				Student astudent = null;
				// if the input id equals the student's id, then astudent is the input student.
				for (Student s : studentSkillSetList) {
					if (s.getId().equals(id)) {
						astudent = s;
					}
				}

				if (astudent != null) {
					System.out.println("Matching student as follows: ");
					System.out.println(astudent.toString());

					System.out.println("Enter " + astudent.getId() + "s personality type: (A,B,C,D)");
					System.out.println("====================================");
					System.out.println("Characteristics Personality Type");
					System.out.println("Likes to be a Leader (Director)  A");
					System.out.println("Outgoing and maintains good\r\n" + "relationships (Socializer)  B");
					System.out.println("Detail oriented (Thinker) C");
					System.out.println("Less assertive (Supporter) D");
					System.out.println("====================================");
					scan = new Scanner(System.in);

					char t = scan.nextLine().toUpperCase().charAt(0);
					System.out.println("personality type is :" + t);

					if (astudent.setPersionalityType(t))
						System.out.println("personality type update");
					System.out.println(astudent.toString2());

					System.out.println("Enter up 2 student IDs that have potential confilc:");
					scan = new Scanner(System.in);
					String IDs = scan.nextLine().toUpperCase();
					if (astudent.setConflict(IDs))
						System.out.println("potential confilc updated");
					for (Student s : studentSkillSetList) {
						System.out.println(s.toString2());
					}

					writeToFile(studentSkillSetList, "info");

					// found = true;

				} else {
					System.out.println("No such student");
				}

			} while (!found);
			studentSkillSetList.clear();

		} else {
			readFile(currentDirectory + "\\student.txt");
			System.out.println("Available students " + studentSkillSetList.size());
			for (Student s : studentSkillSetList) {
				System.out.print(s.getId() + " ");
				System.out.print(s.getSkillset() + " ");
				System.out.print(s.getPersionalityType() + " ");
				System.out.println(s.getConflict());
			}

			boolean found = false;
			do {
				System.out.println("Which student you want to update? (input Student id , x to cancel)");
				Scanner scan = new Scanner(System.in);
				String id = scan.nextLine().toUpperCase();
				if (id.toLowerCase().equals("x")) {
					studentSkillSetList.clear();
					break;
				}
				Student astudent = null;
				// if the input id equals the student's id, then astudent is the input student.
				for (Student s : studentSkillSetList) {
					if (s.getId().equals(id)) {
						astudent = s;
					}
				}

				if (astudent != null) {
					System.out.println("Matching student as follows: ");
					System.out.println(astudent.toString());

					System.out.println("Enter " + astudent.getId() + "s personality type: (A,B,C,D)");
					System.out.println("====================================");
					System.out.println("Characteristics Personality Type");
					System.out.println("Likes to be a Leader (Director)  A");
					System.out.println("Outgoing and maintains good\r\n" + "relationships (Socializer)  B");
					System.out.println("Detail oriented (Thinker) C");
					System.out.println("Less assertive (Supporter) D");
					System.out.println("====================================");
					scan = new Scanner(System.in);

					char t = scan.nextLine().toUpperCase().charAt(0);
					System.out.println("personality type is :" + t);

					if (astudent.setPersionalityType(t))
						System.out.println("personality type update");
					System.out.println(astudent.toString2());

					System.out.println("Enter up 2 student IDs that have potential confilc:");
					scan = new Scanner(System.in);
					String IDs = scan.nextLine().toUpperCase();
					if (astudent.setConflict(IDs))
						System.out.println("potential confilc updated");

//					System.out.println(astudent.toString2());
					for (Student s : studentSkillSetList) {
						System.out.println(s.toString2());
					}

					writeToFile(studentSkillSetList, "info");

					// found = true;

				} else {
					System.out.println("No such student");
				}

			} while (!found);
		}

		studentSkillSetList.clear();
	}

	public void addStudentPreferences() {
		boolean found = false;
		File file = new File("studentinfo.txt");
		if (file.exists() && studentSkillSetList.isEmpty()) {
			readFile("studentinfo.txt");

		} else {
			readFile("student.txt");
		}

		System.out.println("Avaliable student " + studentSkillSetList.size());
		for (Student s : studentSkillSetList) {
			System.out.println(s.getId() + " ");
			System.out.println(s.getPreferences());
		}
		do {
			System.out.println("Avalible students " + studentSkillSetList.size());
			for (Student s : studentSkillSetList) {
				System.out.println(s.getId());
			}

			System.out.println("Which student do you want to update preference for? ");
			System.out.println("input x to go back home page");
			Scanner scan = new Scanner(System.in);
			String id = scan.nextLine().toUpperCase();

			if (id.toLowerCase().equals("x"))
				break;

			Student astudent = null;
			for (Student s : studentSkillSetList) {
				if (id.equals(s.getId()))
					astudent = s;
			}

			if (astudent != null) {
				System.out.println("Matching student as follows: ");
				System.out.println(astudent.toString());

				System.out.println("Enter " + astudent.getId() + "s preference: e.g. Pr6 4 Pr8 3 Pr6 2 Pr4 1");
				scan = new Scanner(System.in);
				String pref = scan.nextLine();

				if (astudent.setPreferences(pref))
					System.out.println("Preference updated");
				System.out.println(astudent.getPreferences());

				String[] words = pref.split("\\s");
				for (int i = 0; i < words.length - 1; i += 2) {
					System.out.println("pair :" + words[i] + " " + words[i + 1]);

					astudent.getPreferenceMap().putIfAbsent(words[i], 0);
					astudent.getPreferenceMap().put(words[i],
							astudent.getPreferenceMap().get(words[i]) + Integer.parseInt(words[i + 1]));

				}

				writeToFile(studentSkillSetList, "preference");
				studentSkillSetList.clear();

				found = true;

			} else {
				System.out.println("No such student");
			}
		} while (!found);

	}

	// for reading the student file and then put it in the studentList.

	public void shortlistProjects() {
		// reading the preference to studentList
		readPreference();
//		readFile("studentinfo.txt");

		for (Student s : studentList) {
			String line = s.getPreferences();
			System.out.println(line);

			String[] words = line.split("\\s");
			for (int i = 0; i < words.length - 1; i += 2) {
				System.out.println("pair :" + words[i] + " " + words[i + 1]);

				projectMap.putIfAbsent(words[i], 0);
				projectMap.put(words[i], projectMap.get(words[i]) + Integer.parseInt(words[i + 1]));
//				s.getPreferenceMap().putIfAbsent(words[i], 0);
//				s.getPreferenceMap().put(words[i],Integer.parseInt(words[i + 1]));

			}

		}

		// print keys and values
		for (String i : projectMap.keySet()) {
			System.out.println("key: " + i + " value: " + projectMap.get(i));
		}

		// sort it

		projectMap = sortByValue(projectMap);// the method is referencing to tutor
		String[] p = new String[projectMap.size()];
		int counter = 0;

		System.out.println("Sorted Projects");
		// print keys and values
		for (String i : projectMap.keySet()) {
			System.out.println("key :" + i + " value: " + projectMap.get(i));
			p[counter++] = i;
		}

		double projectToDelete = p.length - Math.ceil((double) studentList.size() / 4); // max number of student per
																						// projects;
		double remainder = studentList.size() % 4;// a remainder to promt user there is not enough student or how many
													// student is redundant.
		System.out.println("Availalbe projects" + p.length);
		System.out.println("Student size " + studentList.size());
		System.out.println("there are " + remainder + "students can't form a team.");
		System.out.println("Project to deleted based on number of students: " + projectToDelete);

		p = Arrays.copyOfRange(p, 0, (int) projectToDelete);
		System.out.println(Arrays.toString(p));

//		System.out.println("this is for test " + projectList);
		try {
			if (projectList.isEmpty())
				// System.out.println(projects.isEmpty());
				readProjects();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < p.length; i++) {
			for (Iterator<Project> iterator = projectList.iterator(); iterator.hasNext();) {
				String integer = iterator.next().getId();
				if (integer.equals(p[i])) {
					iterator.remove();
				}
			}
		}
		// System.out.println("this is for test " + projects);
		// save to file
		writeToFile(projectList);
		writeToFileSer(projectList);

	}

	private Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {
		return wordCounts.entrySet().stream().sorted((Map.Entry.<String, Integer>comparingByValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public void formTeams() throws Exception {

		try {

//		readPreference1();
			Map<String, Student> sMap = new HashMap<String, Student>();

			for (Project p : projectList) {
				System.out.println("Title :" + p.getTitle());
				System.out.println("ProjectID :" + p.getId() + " Ranking :" + p.getRanking());
//			for (Student s : studentList) {
//				if (s.getPreferences().contains(p.getId())) {
//
//					System.out.println(s.getId() + " " + s.getPreferences());
//
//				}
//				else {
//					System.out.println("this is the rest of student");
//					System.out.println(s.getId() + " " + s.getPreferences());
//				}
//			}
				System.out.println();
			}

			for (Student s : studentSkillSetList) {
				System.out.println(
						s.getId() + " " + s.getPreferences() + " " + "Personality Type:" + s.getPersionalityType());
			}

			System.out.print("Please select a ProjectID to assign student: Pr");
			Scanner sc = new Scanner(System.in);
			String projectId = "Pr" + Integer.parseInt(sc.nextLine());

			if (!projectList.toString().contains(projectId)) {
				System.err.println("there has not this project, please choose again.");
				return;
			}

			System.out.println("Please choice priorty the students who like project first and second:");

			int sum = 0;

			while (sum < 4) {

				for (Student s : studentSkillSetList) {
					if (s.getPreferences().contains(projectId)) {
						System.out.println(s.getId() + " " + s.getPreferences() + " " + s.getPersionalityType());

					}
				}
				System.out.println("Above is student and project preference maching table.");
				System.out.print("Please assign " + (sum + 1) + " of 4 studentID: S");

				String sutdentId = "S" + Integer.parseInt(sc.nextLine());
				try {
					if (sMapValidator.containsKey(sutdentId)) {
						throw new InvalidMemberException(sutdentId);

					} else {
						Iterator<Student> iterator = studentSkillSetList.iterator();
						while (iterator.hasNext()) {
							Student s = iterator.next();
							if (s.getId().equals(sutdentId)) {
								for (Student student : studentSkillSetList) {
									if (student.getId().equals(sutdentId)) {
										sMap.put(sutdentId, student);
										sMapValidator.putAll(sMap);
									}

								}

								iterator.remove();
								System.out.println("Added " + (sum + 1) + " of 4 student in " + projectId + "'s Team!");
							}

						}
					}
				} catch (InvalidMemberException e) {
					System.err.println(sutdentId + " is already in some team, please choose anohter student to add.");
					continue;
				}

				sum++;
			}

			Team team;

			for (Project p : projectList) {
				if (p.getId().equals(projectId)) {
					System.out.println(p.getTitle() + "," + p.getId() + "," + sMap.keySet());
					team = new Team(p, sMap);
					teamList.add(team);

				}
			}

			Iterator<Project> iterator = projectList.iterator();
			while (iterator.hasNext()) {
				Project p = iterator.next();
				if (p.getId().equals(projectId)) {
					iterator.remove();
				}
			}
			WriteToFileFromTeam(teamList);
			System.out.println();

//		studentSkillSetList.clear();

//		readFromFile("FormTeam.txt");

			return;
		} catch (NumberFormatException nex) {
			System.err.println("Please input number, please try again!!");
		}
	}

	public void disPlayTeamFinessMetrics() {
		if (!teamList.isEmpty()) {
			System.out.println("Average student skill competency for each project team:");
			List<Double> al = new ArrayList<Double>();

			for (Team team : teamList) {
				System.out.print(team);
				System.out.println("Programming: " + team.getAveStuSkillForP());
				System.out.println("Networking: " + team.getAveStuSkillForN());
				System.out.println("Analytics: " + team.getAveStuSkillForA());
				System.out.println("Web: " + team.getAveStuSkillForW());
				System.out.println(team.getProject().getId() + ":" + team.getPerCenTageStu() + "%");
//				System.out.println("Team skill shortfall: "+team.getSkillShortfall());
				System.out.println("Team skill shortfall: " + team.getSkillgap());
//				al.add(team.getSkillShortfall());
				al.add(team.getSkillgap());
				System.out.println("========================================================================");

			}
			double[] target = new double[al.size()];
			for (int i = 0; i < target.length; i++) {
				target[i] = al.get(i); // java 1.5+ style (outboxing)
			}
			System.out.println("StanderDeviation: " + StanderDeviation.calculateSD(target));

		} else {
			System.err.println("Please run the FormTeam First!");
		}

	}

	public void readProjects() throws IOException {
		// open the file
		File file = new File("projects.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));

		// read lines from file.

		while (true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}
			// split line on comma.
			String[] parts = line.split(",");
			Project project = new Project(parts[0], parts[1], parts[2], parts[3], parts[4]);
			int num = parts[4].indexOf("P");
			project.getRankingMap().put("P", Integer.parseInt(parts[4].substring(num + 1, num + 2)));

			int num2 = parts[4].indexOf("N");
			project.getRankingMap().put("N", Integer.parseInt(parts[4].substring(num2 + 1, num2 + 2)));

			int num3 = parts[4].indexOf("A");
			project.getRankingMap().put("A", Integer.parseInt(parts[4].substring(num3 + 1, num3 + 2)));

			int num4 = parts[4].indexOf("W");
			project.getRankingMap().put("W", Integer.parseInt(parts[4].substring(num4 + 1, num4 + 2)));

			System.out.println(project.getRankingMap());

			if (!projectList.contains(project))
				projectList.add(project);
		}

		reader.close();
	}

	public void readPreference1() {
		readFile("studentinfo.txt");
		File file = new File("preferences.txt");
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			// check if there is another line of input
			String id = "";
			String preference = "";
			while (sc.hasNextLine()) {

				String string = sc.nextLine();
				if (string.toLowerCase().contains("s"))
					id = string.trim();
				string = sc.nextLine();
				if (string.toLowerCase().contains("pr"))
					preference = string;
				System.out.println(id);
				System.out.println(preference);

				Student s = new Student(id, "");
				boolean found = false;
				for (Student student : studentSkillSetList) {
					if (student.getId().equals(s.getId()))
						found = true;
				}
				if (!found)
					studentSkillSetList.add(s);
//				studentSkillSetList.add(s);

				for (Student student : studentSkillSetList) {
					System.out.println(student.toString());
					if (student.getId().equals(id)) {
						String[] words = preference.split("\\s");
						for (int i = 0; i < words.length - 1; i += 2) {
							student.getPreferenceMap().putIfAbsent(words[i], 0);
							student.getPreferenceMap().put(words[i], Integer.parseInt(words[i + 1]));
						}

						student.setPreferences(preference);
					}
				}
				System.out.println("data is loaded into the studentList and studentSet\n");
			}
		} catch (IOException exp) {
			exp.printStackTrace();

		}
		sc.close();

	}

	public void readPreference() {

		File file = new File("preferences.txt");
		Scanner sc = null;
		try {
			sc = new Scanner(file);
			// check if there is another line of input
			String id = "";
			String preference = "";
			while (sc.hasNextLine()) {

				String string = sc.nextLine();
				if (string.toLowerCase().contains("s"))
					id = string.trim();
				string = sc.nextLine();
				if (string.toLowerCase().contains("pr"))
					preference = string;
				System.out.println(id);
				System.out.println(preference);

				Student s = new Student(id, "");
				boolean found = false;
				for (Student student : studentList) {
					if (student.getId().equals(s.getId()))
						found = true;
				}
				if (!found)
					studentList.add(s);
				studentSet.add(s);

				for (Student student : studentList) {
					System.out.println(student.toString());
					if (student.getId().equals(id)) {
						student.setPreferences(preference);
					}
				}
				System.out.println("data is loaded into the studentList and studentSet\n");
			}
		} catch (IOException exp) {
			exp.printStackTrace();

		}
		sc.close();

	}

	public void writeToFile(Set<Company> companySet) {
		try {

			String fileNameSer = "companies.ser";
			File fileSer = new File(fileNameSer);
			FileOutputStream myWriterSer = new FileOutputStream(fileSer);
			ObjectOutputStream objectoutputstream = new ObjectOutputStream(myWriterSer);
			companySet.forEach(company -> {
				try {
					objectoutputstream.writeObject(company);
				} catch (IOException e) {

					e.printStackTrace();
				}
			});
			objectoutputstream.close();
			myWriterSer.close();
			System.out.println("Successfully wrote to the " + fileSer);

			String fileName = "companies.txt";
			File file = new File(fileName);
			FileWriter myWriter = new FileWriter(file);
			BufferedWriter bufferWriter = new BufferedWriter(myWriter);
			companySet.forEach(company -> {
				try {
					bufferWriter.write(company.toString() + "\n");
				} catch (IOException e) {

					e.printStackTrace();
				}
			});
			bufferWriter.close();
			myWriter.close();
			System.out.println("Successfully wrote to the " + file);

		} catch (IOException e) {
			System.err.println("Oops! somethings go worng!");
			e.printStackTrace();
		}

	}

	public void writeToFile(Map<String, ProjectOwner> projectownerMap) {
		try {
			String fileName = "owners.txt";
			File file = new File(fileName);
			FileWriter myWriter = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(myWriter);
			Set<String> keySet = projectownerMap.keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				bw.write(projectownerMap.get(key) + "\n");
			}

			bw.close();
			myWriter.close();

			String fileNameSer = "owners.ser";
			File fileSer = new File(fileNameSer);
			FileOutputStream fileoutputstream = new FileOutputStream(fileSer);
			ObjectOutputStream objoutputstream = new ObjectOutputStream(fileoutputstream);
			objoutputstream.writeObject(projectownerMap);
			objoutputstream.close();
			fileoutputstream.close();

			System.out.println("Successfully wrote to the " + fileName);
			System.out.println("Successfully wrote to the " + fileNameSer);
		} catch (IOException e) {
			System.out.println("Oops! somethings go worng!");
			e.printStackTrace();

		}

	}

	public void writeToFile(Project p) {
		try {
			String fileName = "projects.txt";
			FileWriter myWriter = new FileWriter(fileName, true);
			BufferedWriter bw = new BufferedWriter(myWriter);
			bw.write(p.toString());
			bw.close();
			myWriter.close();
			System.out.println("Successfully wrote to the " + fileName);
		} catch (IOException e) {
			System.out.println("Oops! somethings go worng!");
			e.printStackTrace();

		}

	}

	public void writeToFile(List<Student> studentSkillSetList, String type) {
		File f = new File("studentinfo.ser");
		try {
			FileOutputStream op = new FileOutputStream(f);
			ObjectOutputStream bfop = new ObjectOutputStream(op);
			bfop.writeObject(studentSkillSetList);
			bfop.close();
			op.close();
		} catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}

		String fileName = "";
		if (type.equals("info"))
			fileName = "studentinfo.txt";

		else if (type.equals("preference"))
			fileName = "preferences.txt";
		try {

			FileWriter myWriter = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(myWriter);

			if (type.equals("info")) {
				for (Student s : studentSkillSetList) {
					bw.write(s.toString2().trim() + "\n");

				}
			} else if (type.equals("preference")) {
				for (Student s : studentSkillSetList) {
					/**
					 * here just change isblank to isEmpty
					 */
					if (!s.getPreferences().isEmpty()) {
						bw.write(s.getId() + "\n");
						bw.write(s.getPreferences() + "\n");
					}
				}

			}

			bw.close();
			myWriter.close();
			System.out.println("Successfully wrote to the " + fileName);
		} catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();

		}

	}

	public void readFile(String fileName) {

		File file = new File(fileName);
		if (file.exists()) {
			Scanner sc = null;
			try {
				sc = new Scanner(file);
				// check the other line input, if yes, it loop.
				while (sc.hasNextLine()) {
					String str = sc.nextLine();
					String id = "";
					String skillset = "";
					char persionalityType = 0;
					String conflict = "";

					if (str.charAt(15) == ('A') || str.charAt(15) == ('B') || str.charAt(15) == ('C')
							|| str.charAt(15) == ('D')) {
						id = str.substring(0, 2).trim();
						skillset = str.substring(3, 15).trim();
						persionalityType = str.substring(15, 17).charAt(0);
						conflict = str.substring(17).trim();
					} else if (str.charAt(16) == ('A') || str.charAt(16) == ('B') || str.charAt(16) == ('C')
							|| str.charAt(16) == ('D')) {
						id = str.substring(0, 3).trim();
						skillset = str.substring(4, 16).trim();
						persionalityType = str.substring(16, 18).charAt(0);
						conflict = str.substring(18).trim();
					} else {
						id = str.substring(0, 3).trim();
						skillset = str.substring(3).trim();
					}

					Student s = new Student(id, skillset, persionalityType, conflict);

					int num = skillset.indexOf("P");
					s.getSkillMap().put("P", Integer.parseInt(skillset.substring(num + 1, num + 2)));

					int num2 = skillset.indexOf("N");
					s.getSkillMap().put("N", Integer.parseInt(skillset.substring(num2 + 1, num2 + 2)));

					int num3 = skillset.indexOf("A");
					s.getSkillMap().put("A", Integer.parseInt(skillset.substring(num3 + 1, num3 + 2)));

					int num4 = skillset.indexOf("W");
					s.getSkillMap().put("W", Integer.parseInt(skillset.substring(num4 + 1, num4 + 2)));

					System.out.println(s.getSkillMap());

					if (new Character(persionalityType).equals("")) {
						System.out.println(id + " " + skillset);
					} else {
						System.out.println(s.toString1());
					}

					boolean found = false;
					// read from student file, if the studentList has the same student id, so not
					// add, if have, add it in the studentList.
					for (Student student : studentSkillSetList) {
						if (student.getId().equals(s.getId()))
							found = true;
					}

					studentSkillSetList.add(s);

					System.out.println("Data loaded into studentSkillSetList\n");
				}

			} catch (IOException exp) {
				exp.printStackTrace();
			}
			sc.close();

		} else {
			System.err.println("The File is not exist");
		}

	}

	public void writeToFile(ArrayList<Project> projectList) {
		try {
			String filename = "Project_shortlist.txt";
			FileWriter myWriter = new FileWriter(filename, false);
			BufferedWriter out = new BufferedWriter(myWriter);
			for (Project s : projectList) {
				// System.out.println(s.toString());
				out.write(s.toString());
			}
			out.close();
			myWriter.close();
			// System.out.println("Successfully wrote to the " + filename);
		} catch (IOException e) {
			// System.out.println("An error occured.");
			e.printStackTrace();
		}
	}

	public void writeToFileSer(ArrayList<Project> projectList) {
		try {
			String filename = "Project_shortlist_Serialize.txt";
			FileOutputStream myWriter = new FileOutputStream(filename, false);
			ObjectOutputStream out = new ObjectOutputStream(myWriter);
			for (Project s : projectList) {
				System.out.println(s.toString());
				out.writeObject(s);
			}
			out.close();
			myWriter.close();
			System.out.println("Successfully wrote to the " + filename);
		} catch (IOException e) {
			System.out.println("An error occured.");
			e.printStackTrace();
		}
	}

	public void readProjectShortList() {
		Project s = null;
		System.out.println("Deserialized Project_shortlist_serialize.txt...." + "\n" + "Loaded in projectList");
		try {
			FileInputStream fileIn = new FileInputStream("Project_shortlist_Serialize.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			while (fileIn.available() > 0) {
				s = (Project) in.readObject();// read the object from TXT, and the type is Project.

				projectList.add(s);

			}
			in.close();
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			System.err.println("Project class not found.");
			e.printStackTrace();
			return;
		}

	}

	public void readFormTeamFromFile(File file) {
		teamList.clear();
		Team t = null;

		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			while (fileIn.available() > 0) {
				t = (Team) in.readObject();// read the object from TXT, and the type is Project.

				teamList.add(t);

			}
			in.close();
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			System.err.println("Project class not found.");
			e.printStackTrace();
			return;
		}

	}

	public void readFormTeam() {
		Team t = null;

		try {
			FileInputStream fileIn = new FileInputStream("FormTeam_serialize.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			while (fileIn.available() > 0) {
				t = (Team) in.readObject();// read the object from TXT, and the type is Project.

				teamList.add(t);

			}
			in.close();
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			System.err.println("Project class not found.");
			e.printStackTrace();
			return;
		}

	}

	public void WriteToFileFromTeam(ArrayList<Team> teamList) {
		try {
			String filename = "FormTeam_serialize.txt";
			FileOutputStream myWriter = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(myWriter);
			for (Team team : teamList) {
				out.writeObject(team);
			}
			out.close();
			myWriter.close();
			System.out.println("Successfully wrote to the " + filename);

			String filename2 = "FormTeam.txt";
			FileWriter filewriter = new FileWriter(filename2);
			BufferedWriter bfw = new BufferedWriter(filewriter);
			for (Team team : teamList) {
				bfw.write(team.toString());
			}
			bfw.close();
			filewriter.close();
			System.out.println("Successfully wrote to the " + filename2);

		} catch (IOException e) {
			System.out.println("An error occured.");
			e.printStackTrace();
		}
	}

	public void readFromFile(String filename) {
		File file = new File(filename);
		try {
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Team team;
			while (fileIn.available() > 0) {
				team = (Team) in.readObject();

				System.out.println(team.toString());

			}

			in.close();
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			System.err.println("Project class not found.");
			e.printStackTrace();
			return;
		}

	}

	public void readAll() {
		readPreference1();// load student id and preference into studentList
		readProjectShortList();// load the project and put it in projectList, and Deserialization the
								// Project_shortlist_Serialize.txt

	}

}
