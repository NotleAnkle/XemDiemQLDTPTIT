package Subject;

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
		return attendance/100;
	}
	public void setAttendance(float attendance) {
		this.attendance = attendance;
	}
	public float getExercise() {
		return exercise/100;
	}
	public void setExercise(float exercise) {
		this.exercise = exercise;
	}
	public float getTest() {
		return test/100;
	}
	public void setTest(float test) {
		this.test = test;
	}
	public float getExam() {
		return exam/100;
	}
	public void setExam(float exam) {
		this.exam = exam;
	}
	public float getPractice() {
		return practice/100;
	}
	public void setPractice(float practice) {
		this.practice = practice;
	}
	
}
