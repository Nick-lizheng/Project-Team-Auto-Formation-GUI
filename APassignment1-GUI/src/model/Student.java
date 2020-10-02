package model;

import java.io.Serializable;
import java.util.HashMap;

public class Student implements Serializable {
	private HashMap<String, Integer> skillMap = new HashMap<String, Integer>();
	private HashMap<String, Integer> preferenceMap = new HashMap<String, Integer>();
	private static final long serialVersionUID = -2554739865448665974L;
	private String id;
	private String skillset;
	private char persionalityType;
	private String conflict;
	private double competencyLevel;




	public double getCompetencyLevel() {
		double sum = (double)(this.skillMap.get("P") + this.skillMap.get("W") + this.skillMap.get("A") + this.skillMap.get("N"))/4;

		setCompetencyLevel(sum);

		return competencyLevel;
	}

	public void setCompetencyLevel(double competencyLevel) {
		this.competencyLevel = competencyLevel;
	}

	// E capture student preference
	private String preferences;

	public Student(String id, String skillset, char persionalityType, String conflict) {
		// super();
		this.id = id;
		this.skillset = skillset;
		this.persionalityType = persionalityType;
		this.conflict = conflict;
		this.preferences = "";
	}

	public Student(String id, String skillset) {
		// super();
		this.id = id;
		this.skillset = skillset;
		this.persionalityType = ' ';
		this.conflict = "";
		this.preferences = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSkillset() {
		return skillset;
	}

	public void setSkillset(String skillset) {
		this.skillset = skillset;
	}

	public char getPersionalityType() {
		return persionalityType;
	}

	public boolean setPersionalityType(char type) {
		this.persionalityType = type;
		return true;
	}

	public String getConflict() {
		return conflict;
	}

	public boolean setConflict(String IDs) {
		this.conflict = IDs;
		return true;
	}

	public String getPreferences() {
		return preferences;
	}

	public boolean setPreferences(String preferences) {
		this.preferences = preferences;
		return true;
	}

	public HashMap<String, Integer> getSkillMap() {
		return skillMap;
	}

	public void setSkillMap(HashMap<String, Integer> skillMap) {
		this.skillMap = skillMap;
	}

	public String toString2() {

		String personality = "";
		if (this.persionalityType != '\0')
			personality = String.valueOf(this.persionalityType) + " ";
		return this.id + " " + this.skillset + " " + personality + this.conflict;

	}

	public String toString1() {

		return this.id + " " + this.skillset + " " + this.persionalityType + " " + this.conflict;

	}

	@Override
	public String toString() {

		return this.id + " " + this.skillset + " " + this.persionalityType + " " + this.conflict + " "
				+ this.preferences;

	}

	public HashMap<String, Integer> getPreferenceMap() {
		return preferenceMap;
	}

	public void setPreferenceMap(HashMap<String, Integer> preferenceMap) {
		this.preferenceMap = preferenceMap;
	}

	public String toString3() {
		return this.id + " " + this.skillset;
	}

}
