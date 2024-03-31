package com.example.BTL.Subject;

public class ScoreRate {
	private float attendance, exercise, test, practice, exam;
	public ScoreRate() {
		// TODO Auto-generated constructor stub
	}
	public ScoreRate(float attendance, float exercise, float test, float practice, float exam) {
		super();
		this.attendance = attendance;
		this.exercise = exercise;
		this.test = test;
		this.practice = practice;
		this.exam = exam;
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
	public float getPractice() {
		return practice;
	}
	public void setPractice(float practice) {
		this.practice = practice;
	}
	
}
