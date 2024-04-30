package com.example.BTL.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.BTL.DAO;
import com.example.BTL.score.Score;
import com.example.BTL.term.SubInTerm;

public class SubjectDAO extends DAO {
	public SubjectDAO() {
		super();
	}
	public ScoreRate getSubjectRate(String id) {
		ScoreRate rate = new ScoreRate();
		String sql = "SELECT * FROM tblSubject WHERE id = ?";
        try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                	rate.setAttendance(rs.getFloat("atten"));
                	rate.setExercise(rs.getFloat("exer"));
                	rate.setTest(rs.getFloat("test"));
                	rate.setPractice(rs.getFloat("prac"));
                	rate.setExam(rs.getFloat("exam"));
                }
        }catch(Exception e) {
                e.printStackTrace();
        }
		return rate;
	}
	public Subject getSubject(String id) {
		Subject sub = new Subject();
		ScoreRate rate = new ScoreRate();
		String sql = "SELECT * FROM tblSubject WHERE id = ?";
        try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                	rate.setAttendance(rs.getFloat("atten"));
                	rate.setExercise(rs.getFloat("exer"));
                	rate.setTest(rs.getFloat("test"));
                	rate.setPractice(rs.getFloat("prac"));
                	rate.setExam(rs.getFloat("exam"));
                	
                	sub.setRate(rate);
                	sub.setName(rs.getString("name"));
                	sub.setGroup(rs.getString("group"));
                	sub.setCredit(rs.getFloat("credit"));
                	sub.setAccum(rs.getBoolean("isaccum"));
                	sub.setId(id);
                }
        }catch(Exception e) {
                e.printStackTrace();
        }
        sub.setRate(rate);
		return sub;
	}
	public List<SubjectInput> getAllSubject(){
		List<SubjectInput> subs = new ArrayList<>();
		String sql = "select * from tblsubject";
		try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String group = resultSet.getString("group");
				float credit = resultSet.getFloat("credit");
				boolean isAccum = resultSet.getBoolean("isaccum");
				
				float atten = resultSet.getFloat("atten");
				float exer = resultSet.getFloat("exer");
				float test = resultSet.getFloat("test");
				float prac = resultSet.getFloat("prac");
				float exam = resultSet.getFloat("exam");
				ScoreRate rate = new ScoreRate(atten, exer, test, prac, exam);
				subs.add(new SubjectInput(id, name, group, credit, rate, isAccum));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subs;
	}
	public List<String> getSubInTerm(String termId){
		
		List<SubInTerm> subInTerms = new ArrayList<>();
		
		String sql = "select * from tblsubinterm where termId = ?";
		try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, termId);
            ResultSet resultSet = ps.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				int termId1 = resultSet.getInt("termId");
				String subId = resultSet.getString("subjectId");
				subInTerms.add(new SubInTerm(id, termId1, subId));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<String> subIds = new ArrayList<>();
		for(int i = 0; i < subInTerms.size(); i++) {
			subIds.add(subInTerms.get(i).getSubjectId());
		}
		return subIds;
	}
}
