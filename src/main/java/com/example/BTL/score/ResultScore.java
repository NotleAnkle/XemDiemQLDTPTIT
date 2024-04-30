package com.example.BTL.score;

import java.text.DecimalFormat;
import java.util.List;

public class ResultScore {
	List<Score> scores;
	float gpa10, gpa4;
	int totalCre;
	float totalSc10, totalSc4;
	float cpa10, cpa4;
	int cpaCre;
	String term;
	
	public ResultScore() {
		// TODO Auto-generated constructor stub
	}

	public ResultScore(List<Score> scores, float gpa10, float gpa4, int totalCre, float totalSc10, float totalSc4) {
		super();
		this.scores = scores;
		this.gpa10 = gpa10;
		this.gpa4 = gpa4;
		this.totalCre = totalCre;
		this.totalSc10 = totalSc10;
		this.totalSc4 = totalSc4;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public float getGpa10() {
		return formatFloat(gpa10);
	}

	public void setGpa10(float gpa10) {
		this.gpa10 = gpa10;
	}

	public float getGpa4() {
		return formatFloat(gpa4);
	}

	public void setGpa4(float gpa4) {
		this.gpa4 = gpa4;
	}

	public int getTotalCre() {
		return totalCre;
	}

	public void setTotalCre(int totalCre) {
		this.totalCre = totalCre;
	}

	public float getTotalSc10() {
		return totalSc10;
	}

	public void setTotalSc10(float totalSc) {
		this.totalSc10 = totalSc;
	}

	public float getTotalSc4() {
		return totalSc4;
	}

	public void setTotalSc4(float totalSc4) {
		this.totalSc4 = totalSc4;
	}

	public float getCpa10() {
		return formatFloat(cpa10);
	}

	public void setCpa10(float cpa10) {
		this.cpa10 = cpa10;
	}

	public float getCpa4() {
		return formatFloat(cpa4);
	}

	public void setCpa4(float cpa4) {
		this.cpa4 = cpa4;
	}

	public int getCpaCre() {
		return cpaCre;
	}

	public void setCpaCre(int cpaCre) {
		this.cpaCre = cpaCre;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	private float formatFloat(float floatValue) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedValue = decimalFormat.format(floatValue);
        float roundedFloatValue = Float.parseFloat(formattedValue);
        return roundedFloatValue;
	}
	
//	public int GetIndexOf(Score score) {
//		int index = -1;
//		
//		String subjectId = score.getSubjectId();
//		for(int i = 0; i < scores.size(); i++) {
//			if(scores.get(i).getSubjectId() == subjectId) {
//				index = i;
//				break;
//			}
//		}
//				
//		return index;
//	}
//	
//	public void ReplaceScore(int index, Score score) {
//		scores.set(index, score);
//	}
}
