package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Project implements Serializable{
	private HashMap<String,Integer> rankingMap = new HashMap<String,Integer>();
	private static final long serialVersionUID = -8506426631230828831L;
	private String title;
	private String id;
	private String desc;
	private String poID;
	private String ranking;

	public Project(String title, String id, String desc, String poID, String ranking) {
		super();
		this.title = title;
		this.id = id;
		this.desc = desc;
		this.poID = poID;
		this.ranking = ranking;
	}

	public HashMap<String, Integer> getRankingMap() {
		return rankingMap;
	}

	public void setRankingMap(HashMap<String, Integer> rankingMap) {
		this.rankingMap = rankingMap;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPoID() {
		return poID;
	}

	public void setPoID(String poID) {
		this.poID = poID;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	@Override
	public String toString() {
		return title + ","+ id +"," +desc +","+poID +","+ranking+"\n";
	}

}
