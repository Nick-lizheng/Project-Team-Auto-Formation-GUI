package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Team implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5221446472741437808L;
	private Project project;
	private Map<String, Student> sMap = new HashMap<String, Student>();
	private double aveStuSkillForP;
	private double aveStuSkillForN;
	private double aveStuSkillForW;
	private double aveStuSkillForA;
	private double perCenTageStu;
	private double skillShortfall;
	private double skillgap;

	public Team(Project project, Map<String, Student> sMap) {
		super();
		this.project = project;
		this.setStudentMap(sMap);
		this.aveStuSkillForP = 0;
		this.aveStuSkillForN = 0;
		this.aveStuSkillForW = 0;
		this.aveStuSkillForA = 0;
		this.perCenTageStu = 0;
		this.skillShortfall = 0;

	}
	
	

	public double getSkillgap() {
		double P,W,A,N;				
		P=(getProject().getRankingMap().get("P")-getAveStuSkillForP());
		W=(getProject().getRankingMap().get("W")-getAveStuSkillForW());
		A=(getProject().getRankingMap().get("A")-getAveStuSkillForA());
		N=(getProject().getRankingMap().get("N")-getAveStuSkillForN());
		
		if(P<0) {
			P=0;
		}
		
		if(W<0) {
			W=0;
		}
		
		if(A<0) {
			A=0;
		}
		
		if(N<0) {
			N=0;
		}	
		
		double sum = P+W+A+N;
		setSkillgap(sum);
		
		return skillgap;
	}



	public void setSkillgap(double skillgap) {
		this.skillgap = skillgap;
	}



	public double getAveStuSkillForP() {
		double sum = 0.0;
		for (Student student : sMap.values()) {
			sum = sum + student.getSkillMap().get("P");
		}
		setAveStuSkillForP(sum / 4);
		return aveStuSkillForP;
	}

	public void setAveStuSkillForP(double aveStuSkillForP) {
		this.aveStuSkillForP = aveStuSkillForP;
	}

	public double getAveStuSkillForN() {
		double sum = 0.0;
		for (Student student : sMap.values()) {
			sum = sum + student.getSkillMap().get("N");
		}
		setAveStuSkillForN(sum / 4);

		return aveStuSkillForN;
	}

	public void setAveStuSkillForN(double aveStuSkillForN) {
		this.aveStuSkillForN = aveStuSkillForN;
	}

	public double getAveStuSkillForW() {
		double sum = 0.0;
		for (Student student : sMap.values()) {
			sum = sum + student.getSkillMap().get("W");
		}
		setAveStuSkillForW(sum / 4);
		return aveStuSkillForW;
	}

	public void setAveStuSkillForW(double aveStuSkillForW) {
		this.aveStuSkillForW = aveStuSkillForW;
	}

	public double getAveStuSkillForA() {
		double sum = 0.0;
		for (Student student : sMap.values()) {
			sum = sum + student.getSkillMap().get("A");
		}
		setAveStuSkillForA(sum / 4);
		return aveStuSkillForA;
	}

	public void setAveStuSkillForA(double aveStuSkillForA) {
		this.aveStuSkillForA = aveStuSkillForA;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;

	}

	public double getPerCenTageStu() {
		double count = 0;
		for (Student student : sMap.values()) {
			if(student.getPreferenceMap().containsKey(getProject().getId())) {
				if (student.getPreferenceMap().get(getProject().getId()).equals(4)
						|| student.getPreferenceMap().get(getProject().getId()).equals(3)) {
					count++;
				}
			}else {
				setPerCenTageStu(0);
			}
			
		}

		setPerCenTageStu((count / 4) * 100);
		return perCenTageStu;
	}

	public void setPerCenTageStu(double perCenTageStu) {
		this.perCenTageStu = perCenTageStu;
	}

	public double getSkillShortfall() {
		double P,W,A,N;				
		P=(getProject().getRankingMap().get("P")-getAveStuSkillForP());
		W=(getProject().getRankingMap().get("W")-getAveStuSkillForW());
		A=(getProject().getRankingMap().get("A")-getAveStuSkillForA());
		N=(getProject().getRankingMap().get("N")-getAveStuSkillForN());
		
		if(P<0) {
			P=0;
		}
		
		if(W<0) {
			W=0;
		}
		
		if(A<0) {
			A=0;
		}
		
		if(N<0) {
			N=0;
		}	
		
		double sum = P+W+A+N;
		setSkillShortfall(sum/4);
		
		
		return skillShortfall;
	}
	
	


	public void setSkillShortfall(double skillShortfall) {
		this.skillShortfall = skillShortfall;
	}

	@Override
	public String toString() {
		return project.toString() + sMap.values().toString() + "\n";
	}

	public Map<String, Student> getStudentMap() {
		return sMap;
	}

	public void setStudentMap(Map<String, Student> studentMap) {
		this.sMap = studentMap;
	}

}
