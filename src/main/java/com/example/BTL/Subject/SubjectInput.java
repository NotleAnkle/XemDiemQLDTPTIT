package com.example.BTL.Subject;

import com.example.BTL.score.Score;

public class SubjectInput {
	String id, name, group;
	float credit;
	ScoreRate rate;
	boolean isAccum;
	Score score;
	public SubjectInput() {
		// TODO Auto-generated constructor stub
	}

	public SubjectInput(String id, String name, String group, float credit, ScoreRate rate, boolean isAccum) {
		super();
		this.id = id;
		this.name = name;
		this.group = group;
		this.credit = credit;
		this.rate = rate;
		this.isAccum = isAccum;
		score = new Score();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public ScoreRate getRate() {
		return rate;
	}
	public void setRate(ScoreRate rate) {
		this.rate = rate;
	}

	public boolean isAccum() {
		return isAccum;
	}

	public void setAccum(boolean isAccum) {
		this.isAccum = isAccum;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}
	
}
