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
				String term = resultSet.getString("term");
				scores.add(new Score(id, studentId, subjectId, atten, exer, test, prac, exam, term));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scores;
	}
	
	public List<Score> getStudentScoreByTerm(int studentId, int termNum) {
		List<Score> scores = new ArrayList<>();
		String sql = "select * from tblScore where studentid = ? and term = ?";
		try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2,termNum);
            ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String subjectId = resultSet.getString("subjectId");
				float atten = resultSet.getFloat("atten");
				float exer = resultSet.getFloat("exer");
				float test = resultSet.getFloat("test");
				float prac = resultSet.getFloat("prac");
				float exam = resultSet.getFloat("exam");
				String term = resultSet.getString("term");
				scores.add(new Score(id, studentId, subjectId, atten, exer, test, prac, exam, term));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scores;
	}
}
