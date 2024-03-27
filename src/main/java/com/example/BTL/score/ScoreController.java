package com.example.BTL.score;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.BTL.Subject.ScoreRate;
import com.example.BTL.Subject.Subject;
import com.example.BTL.Subject.SubjectDAO;
import com.example.BTL.Subject.SubjectInput;
import com.example.BTL.term.Term;
import com.example.BTL.term.TermDAO;
import com.example.BTL.user.User;
import com.example.BTL.user.UserController;

@Controller
@SessionAttributes("user")
public class ScoreController {
	ScoreDAO scoreDAO = new ScoreDAO();
	SubjectDAO subjectDAO = new SubjectDAO();
	TermDAO termDAO = new TermDAO();

	@GetMapping("/user/score/{id}")
	public String getScoresByStudent(Model model, @PathVariable String id) {
		User user = UserController.user;
		if (user.getId() == 0) {
			return "redirect:/home";
		}
		// lay toan bo diem cua sinh vien co id phia tren
		List<Score> scores = scoreDAO.getStudentScore(Integer.parseInt(id));
		List<Integer> termIds = new ArrayList<>();
		List<List<Score>> scoreInTerm = new ArrayList<>();
		// Loc diem ra theo cac ky
		for (int i = 0; i < scores.size(); i++) {
			int termId = scores.get(i).getTermId();
			if (!termIds.contains(termId)) {
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
		for (int i = 0; i < termIds.size(); i++) {

			ResultScore rs = CalGPA(scoreInTerm.get(i));

			totalCre += rs.getCpaCre();
			totalSc10 += rs.getTotalSc10();
			totalSc4 += rs.getTotalSc4();

			cpa10 = (float) Math.round((totalSc10 / totalCre) * 100) / 100;
			cpa4 = (float) Math.round((totalSc4 / totalCre) * 100) / 100;

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

	public List<Score> getScoreByTermForInput(String termId, String id) {
		List<String> subIds = subjectDAO.getSubInTerm(termId);
		List<Score> scoreInTerm = new ArrayList<>();
		for (int i = 0; i < subIds.size(); i++) {
			Score score = scoreDAO.getScoreBySubject(subIds.get(i));
			Subject sub = subjectDAO.getSubject(subIds.get(i));
			score.setSubjectId(subIds.get(i));
			score.setSubject(sub);
			score.setStudentId(Integer.valueOf(id));
			scoreInTerm.add(score);
		}
		return scoreInTerm;
	}
	@GetMapping("/score/user/{id}/term/{termId}")
	public String getScoreById(Model model, @PathVariable String termId, @PathVariable String id) {
		model.addAttribute("user", UserController.user);
		List<Term> terms = termDAO.getAllTerm();
		model.addAttribute("terms", terms);
		
		List<Score> scores = getScoreByTermForInput(termId, id);
		model.addAttribute("scores",scores);
		
		ResultScore termScore = CalGPA(scores);
		
		List<Score> allScore = scoreDAO.getStudentScore(Integer.parseInt(id));
		ResultScore cpaScore = CalGPA(allScore);
		termScore.setCpa10(cpaScore.getCpa10());
		termScore.setCpa4(cpaScore.getCpa4());
		termScore.setCpaCre(cpaScore.getCpaCre());
		
		model.addAttribute("termScore", termScore);
		return "input";
	}
	@PostMapping("/score/user/{id}/term/{termId}")
	public String tryScore(@RequestBody RequestScore rs, Model model, @PathVariable String termId, @PathVariable String id) {
		model.addAttribute("user", UserController.user);
		List<Term> terms = termDAO.getAllTerm();
		model.addAttribute("terms", terms);
		
		List<Score> tryTermScore = rs.getScores();
		
		ResultScore termScore = CalGPA(tryTermScore);
		
		model.addAttribute("scores",tryTermScore);
		
		List<Score> allScore = scoreDAO.getStudentScore(Integer.parseInt(id));
		List<String> allScoreSubjectId = new ArrayList<>();
		for(int i = 0; i < allScore.size(); i++) {
			allScoreSubjectId.add(allScore.get(i).getSubjectId());
		}
		
		for(int i = 0; i < tryTermScore.size(); i ++) {
			String scoreSubjectId = tryTermScore.get(i).getSubjectId();
			//nếu list id chứa id này thì thay thế điểm đó với điểm mới
			if(allScoreSubjectId.contains(scoreSubjectId)) {
				allScore.set(allScoreSubjectId.indexOf(scoreSubjectId), tryTermScore.get(i));
			}
		}
		
		ResultScore cpaScore = CalGPA(allScore);
		termScore.setCpa10(cpaScore.getCpa10());
		termScore.setCpa4(cpaScore.getCpa4());
		termScore.setCpaCre(cpaScore.getCpaCre());
		
		model.addAttribute("termScore", termScore);
		
		return "input";
	}
	@PutMapping("/score/user/{id}/term/{termId}")
	public String saveScore(@RequestBody RequestScore rs, Model model, @PathVariable String termId, @PathVariable String id) {
		
		List<Score> tryTermScore = rs.getScores();
		
		List<Score> allScore = scoreDAO.getStudentScore(Integer.parseInt(id));
		List<String> allScoreSubjectId = new ArrayList<>();
		for(int i = 0; i < allScore.size(); i++) {
			allScoreSubjectId.add(allScore.get(i).getSubjectId());
		}
		
		for(int i = 0; i < tryTermScore.size(); i ++) {
			Score scoreForSave = tryTermScore.get(i);
			String scoreSubjectId = scoreForSave.getSubjectId();
			
			//nếu list id chứa id này thì thay thế điểm đó với điểm mới
			if(allScoreSubjectId.contains(scoreSubjectId)) {
				Score scoreForId = scoreDAO.getScoreBySubject(scoreSubjectId);
				scoreForSave.setId(scoreForId.getId());
				
				scoreDAO.UpdateScore(scoreForSave);
			}
			else {
				scoreForSave.setTermId(Integer.parseInt(termId));
				scoreForSave.setStudentId(rs.getStudentId());
				scoreForSave.setSubjectId(scoreSubjectId);
				
				scoreDAO.InsertScore(scoreForSave);
			}
		}
		
		return "redirect:/user/score/" + rs.getStudentId();
	}

	@GetMapping("/score/{id}")
	@ResponseBody
	public Score getScoreById(@PathVariable String id) {
		Score score = scoreDAO.getScoreById(id);
		return score;
	}

	private ResultScore CalGPA(List<Score> scores) {

		int totalCredit = 0, credit = 0;
		float totalSc10 = 0, sc10 = 0;
		float totalSc4 = 0, sc4 = 0;

		for (int i = 0; i < scores.size(); i++) {
			Score sc = scores.get(i);
			if(!sc.note.trim().equals("I")) {
				Subject sub = subjectDAO.getSubject(sc.getSubjectId());

				sc.setSubject(sub);

				ScoreRate rate = sub.getRate();

				float fnSc = (sc.attendance * rate.getAttendance() + sc.exercise * rate.getExercise()
						+ sc.test * rate.getTest() + sc.exam * rate.getExam() + sc.practice * rate.getPractice());
				fnSc = (float) (Math.ceil(fnSc * 10) / 10);
				sc.FinalGrace(fnSc);

				if (sub.isAccum()) {
					totalSc10 += fnSc * sub.getCredit();
					totalSc4 += sc.fn4 * sub.getCredit();
					totalCredit += sub.getCredit();
					if(!sc.note.trim().equals("R") && !sc.note.trim().equals("M")) {
						sc10 += fnSc * sub.getCredit();
						sc4 += sc.fn4 * sub.getCredit();
						credit += sub.getCredit();
					}
				}
			}
		}
		float gpa10 = (float) Math.round((sc10 / credit) * 100) / 100;
		float gpa4 = (float) Math.round((sc4 / credit) * 100) / 100;
		
		float cpa10 = (float) Math.round((totalSc10 / totalCredit) * 100) / 100;
		float cpa4 = (float) Math.round((totalSc4 / totalCredit) * 100) / 100;

		ResultScore rs = new ResultScore(scores, gpa10, gpa4, credit, totalSc10, totalSc4);
		rs.setCpaCre(totalCredit);
		rs.setCpa10(cpa10);
		rs.setCpa4(cpa4);
		return rs;
	}
}
