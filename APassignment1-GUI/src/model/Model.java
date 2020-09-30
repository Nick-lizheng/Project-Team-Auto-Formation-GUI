package model;

public class Model {
	private static Model model;

	public static Model getInstance() {
		if(model == null) {
			model = new Model();
		}
		return model;
	}

	
	public void addStudentToDatabase(Student student) {
		//write JDBC code to add the student object to database
		
		
	}
}
