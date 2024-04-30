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
                	score.setStudentId(rs.getInt("studentId"));
                	score.setSubjectId(rs.getString("subjectId"));
                	score.setAttendance(rs.getFloat("atten"));
    				score.setExercise(rs.getFloat("exer"));
    				score.setExam(rs.getFloat("exam"));
    				score.setPractice(rs.getFloat("prac"));
    				score.setTest(rs.getFloat("test"));
    				score.setNote(rs.getString("note"));
    				score.setTermId(rs.getInt("termId"));
                }
        }catch(Exception e) {
                e.printStackTrace();
        }
		return score;
	}
	public Score getScoreBySubjectAndTerm(String subjectId, String termId) {
		Score score = new Score();
		String sql = "SELECT * FROM tblscore WHERE subjectId = ? and termId = ?";
        try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, subjectId);
                ps.setString(2, termId);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                	score.setId(rs.getInt("id"));
                	score.setStudentId(rs.getInt("studentId"));
                	score.setSubjectId(rs.getString("subjectId"));
                	score.setAttendance(rs.getFloat("atten"));
    				score.setExercise(rs.getFloat("exer"));
    				score.setExam(rs.getFloat("exam"));
    				score.setPractice(rs.getFloat("prac"));
    				score.setTest(rs.getFloat("test"));
    				score.setNote(rs.getString("note"));
    				score.setTermId(Integer.parseInt(termId));
                }
        }catch(Exception e) {
                e.printStackTrace();
        }
		return score;
	}
	public boolean UpdateScore(Score score) {
		String sql = "UPDATE `qldt`.`tblscore` SET `atten` = ?, `exer` = ?, `test` = ?, `prac` = ?, `exam` = ?, `note` = ? WHERE (`id` = ?);";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setFloat(1, score.getAttendance());
			ps.setFloat(2, score.getExercise());
			ps.setFloat(3, score.getTest());
			ps.setFloat(4, score.getPractice());
			ps.setFloat(5, score.getExam());
			ps.setString(6, score.getNote());
			
			ps.setInt(7, score.getId());
			int result = ps.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean InsertScore(Score score) {
		String sql = "INSERT INTO `qldt`.`tblscore` (`studentId`, `subjectId`, `atten`, `exer`, `test`, `prac`, `exam`, `termId`, `note`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, score.getStudentId());
			ps.setString(2, score.getSubjectId());
			ps.setFloat(3, score.getAttendance());
			ps.setFloat(4, score.getExercise());
			ps.setFloat(5, score.getTest());
			ps.setFloat(6, score.getPractice());
			ps.setFloat(7, score.getExam());
			ps.setInt(8, score.getTermId());
			ps.setString(9, score.getNote());
	
			int result = ps.executeUpdate();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
