package com.example.BTL.score;

import java.util.List;

public class RequestScore {
	private int studentId, termId;
	private List<Score> scores;
	public RequestScore() {
		// TODO Auto-generated constructor stub
	}
	public RequestScore(int studentId, int termId, List<Score> scores) {
		super();
		this.studentId = studentId;
		this.termId = termId;
		this.scores = scores;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getTermId() {
		return termId;
	}
	public void setTermId(int termId) {
		this.termId = termId;
	}
	public List<Score> getScores() {
		return scores;
	}
	public void setScores(List<Score> scores) {
		this.scores = scores;
	}
	
}
