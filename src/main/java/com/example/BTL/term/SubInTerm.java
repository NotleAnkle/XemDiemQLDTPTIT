package com.example.BTL.term;

public class SubInTerm {
	int id, termId ;
	String subjectId;
	
	public SubInTerm() {
		// TODO Auto-generated constructor stub
	}

	public SubInTerm(int id, int termId, String subjectId) {
		super();
		this.id = id;
		this.termId = termId;
		this.subjectId = subjectId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTermId() {
		return termId;
	}

	public void setTermId(int termId) {
		this.termId = termId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	
	
}
