package com.example.BTL.term;

public class Term {
	private int id;
	private String year, term;
	
	public Term() {
		// TODO Auto-generated constructor stub
	}
	

	public Term(int id, String year, String term) {
		super();
		this.id = id;
		this.year = year;
		this.term = term;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}


	@Override
	public String toString() {
		return "Học kỳ " + term + " - Năm học "+year;
	}
	
	
}
