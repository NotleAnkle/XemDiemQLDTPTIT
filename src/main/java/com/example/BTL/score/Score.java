package com.example.BTL.score;

import Subject.Subject;

public class Score {
	private int id, studentId, termId;
	String subjectId;
	float attendance, exercise, test, practice, exam;
	float fn10, fn4;
	String fnC;
	Subject subject;
	String note;
	
	public Score() {
		// TODO Auto-generated constructor stub
	}

	public Score(int id, int studentId, String subjectId, float attendance, float exercise, float test, float practice, float exam, int termId, String note) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.attendance = attendance;
		this.exercise = exercise;
		this.test = test;
		this.practice = practice;
		this.exam = exam;
		this.termId = termId;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public float getAttendance() {
		return attendance;
	}

	public void setAttendance(float attendance) {
		this.attendance = attendance;
	}

	public float getExercise() {
		return exercise;
	}

	public void setExercise(float exercise) {
		this.exercise = exercise;
	}

	public float getTest() {
		return test;
	}

	public void setTest(float test) {
		this.test = test;
	}

	public float getExam() {
		return exam;
	}

	public void setExam(float exam) {
		this.exam = exam;
	}

	public float getFn10() {
		return fn10;
	}

	public void setFn10(float fn10) {
		this.fn10 = fn10;
	}

	public float getFn4() {
		return fn4;
	}

	public void setFn4(float fn4) {
		this.fn4 = fn4;
	}

	public String getFnC() {
		return fnC;
	}

	public void setFnC(String fnC) {
		this.fnC = fnC;
	}

	public float getPractice() {
		return practice;
	}

	public void setPractice(float practice) {
		this.practice = practice;
	}

	public int getTermId() {
		return termId;
	}

	public void setTermId(int termId) {
		this.termId = termId;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void FinalGrace(float sc10) {
		fn10 = sc10;
		if(sc10< 4.0) {
			fn10 = 0;
			fn4 = 0;
			fnC = "F";
		}
		if(4 <= sc10 && sc10 < 5){
			fn4 = 1;
			fnC = "D";
		}
		if(5 <= sc10 && sc10 < 5.5){
			fn4 = (float)1.5;
			fnC = "D+";
		}
		if(5.5 <= sc10 && sc10 < 6.5){
			fn4 = (float)2;
			fnC = "C";
		}
		if(6.5 <= sc10 && sc10 < 7){
			fn4 = (float)2.5;
			fnC = "C+";
		}
		if(7 <= sc10 && sc10 < 8){
			fn4 = (float)3;
			fnC = "B";
		}
		if(8 <= sc10 && sc10 < 8.5){
			fn4 = (float)3.5;
			fnC = "B+";
		}
		if(8.5 <= sc10 && sc10 < 9){
			fn4 = (float)3.7;
			fnC = "A";
		}
		if(9 <= sc10 && sc10 < 10){
			fn4 = (float)4;
			fnC = "A+";
		}
	}
}
