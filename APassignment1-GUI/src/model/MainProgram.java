package model;

import java.util.Scanner;

import main.Main;

public class MainProgram {

	public static void main(String[] args) {
	
		DataHandler dh = new DataHandler();
		dh.readAll();
		
		while (true) {
			showMenu();
			System.out.println("Please select option from [A] - [J]");
			Scanner sc = new Scanner(System.in);
			String option = sc.next().toUpperCase();//Switch all input to uppercase letter
			
			switch (option) {
			case "A":
				dh.addCompany();
				break;
			case "B":
				dh.addProjectOwner();
				break;
			case "C":
				dh.addProject();
				break;
			case "D":
				dh.captureStudentPersonalities();
				break;
			case "E":
				dh.addStudentPreferences();
				break;
			case "F":
				dh.shortlistProjects();
				break;
			case "G":
				dh.formTeams();
				break;
			case "H":
				dh.disPlayTeamFinessMetrics();
				break;
			case "I":
				Main.main(args);
			case "J":
				System.out.println("Quit sucess!!!");
				System.exit(0);
			default:
				System.err.println("Sorry, available input in option [A] - [J] , please try again");
				break;
			}

		}
		
	}

	private static void showMenu() {

		System.out.println("*****************MENU******************");
		System.out.println("A. Add Company");
		System.out.println("B. Add Project Owner");
		System.out.println("C. Add Project");
		System.out.println("D. Capture Student Personalities");
		System.out.println("E. Add Student Preferences");
		System.out.println("F. Shortlist Projects");
		System.out.println("G. Form Teams");
		System.out.println("H. Display Team Fitness Metrics");
		System.out.println("I. UI Mode");
		System.out.println("J. Exit");
		System.out.println("***************************************");
	}

}
