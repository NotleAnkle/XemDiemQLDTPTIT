package Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.BTL.DAO;

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
}
