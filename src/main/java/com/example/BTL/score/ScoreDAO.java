package com.example.BTL.score;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.BTL.DAO;

public class ScoreDAO extends DAO {
	public ScoreDAO() {
		super();
	}
	public List<Score> getStudentScore(int studentId) {
		List<Score> scores = new ArrayList<>();
		String sql = "select * from tblScore where studentid = ?";
		try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String subjectId = resultSet.getString("subjectId");
				float atten = resultSet.getFloat("atten");
				float exer = resultSet.getFloat("exer");
				float test = resultSet.getFloat("test");
				float prac = resultSet.getFloat("prac");
				float exam = resultSet.getFloat("exam");
				int termId = resultSet.getInt("termId");
				String note = resultSet.getString("note");
				scores.add(new Score(id, studentId, subjectId, atten, exer, test, prac, exam, termId, note));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scores;
	}
	
	public List<Score> getStudentScoreByTerm(int studentId, int termId) {
		List<Score> scores = new ArrayList<>();
		String sql = "select * from tblScore where studentid = ? and termId = ?";
		try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2,termId);
            ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String subjectId = resultSet.getString("subjectId");
				float atten = resultSet.getFloat("atten");
				float exer = resultSet.getFloat("exer");
				float test = resultSet.getFloat("test");
				float prac = resultSet.getFloat("prac");
				float exam = resultSet.getFloat("exam");
				String note = resultSet.getString("note");
				scores.add(new Score(id, studentId, subjectId, atten, exer, test, prac, exam, termId, note));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scores;
	}
	public Score getScoreById(String id) {
		Score score = new Score();
		String sql = "SELECT * FROM tblscore WHERE id = ?";
        try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                	score.setId(rs.getInt("id"));
                	score.setSubjectId(rs.getString("subjectId"));
                	score.setAttendance(rs.getFloat("atten"));
    				score.setExercise(rs.getFloat("exer"));
    				score.setExam(rs.getFloat("exam"));
    				score.setPractice(rs.getFloat("prac"));
    				score.setTest(rs.getFloat("test"));
    				score.setNote(rs.getString("note"));
                }
        }catch(Exception e) {
                e.printStackTrace();
        }
		return score;
	}
}
