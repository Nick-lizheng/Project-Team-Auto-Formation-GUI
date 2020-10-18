package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Exception.InvalidMemberException;
import Exception.NoLeaderException;
import Exception.PersonalityImbalanceException;
import Exception.RepeatedMemberException;
import Exception.StudentConfilcException;

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
	private double AverageCompetencyLevel;
	List<Student> sMapValidator = new ArrayList<Student>();
	Set<Character>personalityValidator = new HashSet<Character>();
	

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
	
	

	public double getAverageCompetencyLevel() {
		double ave = (getAveStuSkillForP()+getAveStuSkillForW()+getAveStuSkillForA()+getAveStuSkillForN())/4;
//		double sum=0;
//		for(String s :sMap.keySet()) {
//			 double CompetencyLevel =sMap.get(s).getCompetencyLevel();
//			sum = sum +CompetencyLevel;
//		}
		
		setAverageCompetencyLevel(ave);
		
		return AverageCompetencyLevel;
	}



	public void setAverageCompetencyLevel(double averageCompetencyLevel) {
		AverageCompetencyLevel = averageCompetencyLevel;
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
	
	
	
	public void formteam(String project, Student s1, Student s2, Student s3, Student s4) throws InvalidMemberException,NoLeaderException,RepeatedMemberException,PersonalityImbalanceException,StudentConfilcException{
		char [] PT = {s1.getPersionalityType(),s2.getPersionalityType(),s3.getPersionalityType(),s4.getPersionalityType()};
		personalityValidator.add(s1.getPersionalityType());
		personalityValidator.add(s2.getPersionalityType());
		personalityValidator.add(s3.getPersionalityType());
		personalityValidator.add(s4.getPersionalityType());
		String [] repeat= {s1.getId(),s2.getId(),s3.getId(),s4.getId()};
		if(sMapValidator.contains(s1)||sMapValidator.contains(s2)||sMapValidator.contains(s3)||sMapValidator.contains(s4)) {
			throw new InvalidMemberException("can not add this student in this team");
		}else {
			sMapValidator.add(s1);
			sMapValidator.add(s2);
			sMapValidator.add(s3);
			sMapValidator.add(s4);
		}
		
		
		if(s1.getConflict().contains(s2.getId())||s1.getConflict().contains(s3.getId())||s1.getConflict().contains(s4.getId())) {
			throw new StudentConfilcException();
		}
		
		if(s2.getConflict().contains(s1.getId())||s2.getConflict().contains(s3.getId())||s2.getConflict().contains(s4.getId())) {
			throw new StudentConfilcException();
		}
		
		if(s3.getConflict().contains(s1.getId())||s3.getConflict().contains(s2.getId())||s3.getConflict().contains(s4.getId())) {
			throw new StudentConfilcException();
		}
		
		if(s4.getConflict().contains(s1.getId())||s4.getConflict().contains(s3.getId())||s4.getConflict().contains(s2.getId())) {
			throw new StudentConfilcException();
		}
		
		String pt = new String(PT);
		
		if(!pt.toString().contains("A")) {
			throw new NoLeaderException();
		}
		else if(personalityValidator.size()<3) {
			throw new PersonalityImbalanceException();
			}
		
		if(findDupicateInArray(repeat)) {
			throw new RepeatedMemberException();
		}
		
		
		
		
		}
	
	
	
		
		
		public static boolean findDupicateInArray(String[] a) {
			
	        int count=0;
	        for(int j=0;j<a.length;j++) {
	            for(int k =j+1;k<a.length;k++) {
	                if(a[j]==a[k]) {
	                    count++;
	                }
	            }
	            if(count==1) {
	            	System.out.println( "duplicate element : " +  a[j] );
	            	return true;
	            }
	               
	            count = 0;
	            
	          
	            
	        }
			return false;
		
		
		
	}

}
