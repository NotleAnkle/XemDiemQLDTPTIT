package com.example.BTL.score;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.BTL.user.User;
import com.example.BTL.user.UserController;

import Subject.ScoreRate;
import Subject.Subject;
import Subject.SubjectDAO;

@Controller
public class ScoreController {
	ScoreDAO scoreDAO = new ScoreDAO();
	SubjectDAO subjectDAO = new SubjectDAO();
	
	@GetMapping("/user/score/{id}")
	public String getScoresByTerm(Model model, @PathVariable String id) {
		User user = UserController.user;
		if(user.getId() == 0){
			return "redirect:/home";
		}
		//lay toan bo diem cua sinh vien co id phia tren
		List<Score>scores = scoreDAO.getStudentScore(Integer.parseInt(id));
		List<String> terms = new ArrayList<>();
		List<List<Score>> scoreInTerm = new ArrayList<>();
		//Loc diem ra theo cac ky
		for(int i = 0; i < scores.size(); i++) {
			String term = scores.get(i).getTerm();
			if(!terms.contains(term)) {
				terms.add(term);
				List<Score> sc = new ArrayList<>();
				scoreInTerm.add(sc);
			}
			scoreInTerm.get(terms.indexOf(term)).add(scores.get(i));
		}
		
		
		float cpa10 = 0, cpa4 = 0;
		float totalSc10 = 0, totalSc4 = 0;
		int totalCre = 0;
		List<ResultScore> termResults = new ArrayList<>();
		for(int i = 0; i < terms.size(); i++) {
			
			ResultScore rs = CalGPA(scoreInTerm.get(i));
			
			totalCre += rs.getTotalCre();
			totalSc10 += rs.getTotalSc10();
			totalSc4 += rs.getTotalSc4();
			
			cpa10 =(float) Math.round((totalSc10/totalCre)*100)/100;
			cpa4 =(float) Math.round((totalSc4/totalCre)*100)/100;
			
			rs.setCpa10(cpa10);
			rs.setCpa4(cpa4);
			rs.setCpaCre(totalCre);
			rs.setTerm(terms.get(i));
			
			termResults.add(rs);
		}
		
		model.addAttribute("user", user);
		model.addAttribute("terms", termResults);
		return "scores";
	}
	
	private ResultScore CalGPA(List<Score> scores) {

		int totalCredit = 0;
		float totalSc10 = 0;
		float totalSc4 = 0;
		
		for(int i = 0; i < scores.size(); i++) {
			Score sc = scores.get(i);
			Subject sub = subjectDAO.getSubject(sc.getSubjectId());
			
			sc.setSubject(sub);
			
			ScoreRate rate = sub.getRate();
			
			float fnSc = (sc.attendance*rate.getAttendance() + sc.exercise*rate.getExercise()
							+ sc.test*rate.getTest() + sc.exam*rate.getExam() + sc.practice * rate.getPractice());
			fnSc = (float) (Math.ceil(fnSc * 10) / 10);
			sc.FinalGrace(fnSc);
			
			if(sub.isAccum()) {
				totalSc10 += fnSc*sub.getCredit();
				totalSc4 += sc.fn4*sub.getCredit();
				totalCredit += sub.getCredit();
			}
		}
		float gpa10 =(float) Math.round((totalSc10/totalCredit)*100)/100;
		float gpa4 =(float) Math.round((totalSc4/totalCredit)*100)/100;
		
		ResultScore rs = new ResultScore(scores, gpa10, gpa4,totalCredit, totalSc10, totalSc4);
		return rs;
	}
}

class ResultScore{
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
	
}
