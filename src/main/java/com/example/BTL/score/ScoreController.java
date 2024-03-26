package com.example.BTL.score;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.BTL.term.TermDAO;
import com.example.BTL.user.User;
import com.example.BTL.user.UserController;

import Subject.ScoreRate;
import Subject.Subject;
import Subject.SubjectDAO;

@Controller
@SessionAttributes("user")
public class ScoreController {
	ScoreDAO scoreDAO = new ScoreDAO();
	SubjectDAO subjectDAO = new SubjectDAO();
	TermDAO termDAO = new TermDAO();
	
	@GetMapping("/user/score/{id}")
	public String getScoresByStudent(Model model, @PathVariable String id) {
		User user = UserController.user;
		if(user.getId() == 0){
			return "redirect:/home";
		}
		//lay toan bo diem cua sinh vien co id phia tren
		List<Score>scores = scoreDAO.getStudentScore(Integer.parseInt(id));
		List<Integer> termIds = new ArrayList<>();
		List<List<Score>> scoreInTerm = new ArrayList<>();
		//Loc diem ra theo cac ky
		for(int i = 0; i < scores.size(); i++) {
			int termId = scores.get(i).getTermId();
			if(!termIds.contains(termId)) {
				termIds.add(termId);
				List<Score> sc = new ArrayList<>();
				scoreInTerm.add(sc);
			}
			scoreInTerm.get(termIds.indexOf(termId)).add(scores.get(i));
		}
		
		
		float cpa10 = 0, cpa4 = 0;
		float totalSc10 = 0, totalSc4 = 0;
		int totalCre = 0;
		List<ResultScore> termResults = new ArrayList<>();
		for(int i = 0; i < termIds.size(); i++) {
			
			ResultScore rs = CalGPA(scoreInTerm.get(i));
			
			totalCre += rs.getTotalCre();
			totalSc10 += rs.getTotalSc10();
			totalSc4 += rs.getTotalSc4();
			
			cpa10 =(float) Math.round((totalSc10/totalCre)*100)/100;
			cpa4 =(float) Math.round((totalSc4/totalCre)*100)/100;
			
			rs.setCpa10(cpa10);
			rs.setCpa4(cpa4);
			rs.setCpaCre(totalCre);
			rs.setTerm(termDAO.getTermById(termIds.get(i)).toString());
			
			termResults.add(rs);
		}
		
		model.addAttribute("user", user);
		model.addAttribute("terms", termResults);
		return "scores";
	}
	
	@GetMapping("/score/{id}")
	@ResponseBody
	public Score getScoreById(@PathVariable String id) {
		Score score = scoreDAO.getScoreById(id);
		return score;
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
