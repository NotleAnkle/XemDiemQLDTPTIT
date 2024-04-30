package com.example.BTL.Subject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SubjectDAOTest {

    private SubjectDAO subjectDAO;

    @Before
    public void setUp() throws Exception {
        // Khởi tạo đối tượng SubjectDAO trước mỗi lần chạy test
        subjectDAO = new SubjectDAO();
    }

    @After
    public void tearDown() throws Exception {
        // Đóng các tài nguyên sau khi test hoàn tất
        // Ở đây có thể thêm phương thức để giải phóng tài nguyên liên quan đến cơ sở dữ liệu
    }

    @Test
    public void testGetSubjectRate() {
        // Test lấy tỷ lệ điểm của môn học với id đã biết
        String subjectId = "BAS1105M";
        ScoreRate rate = subjectDAO.getSubjectRate(subjectId);
        
        ScoreRate fRate = new ScoreRate(0, 0, 0, 0, 100);

        // Kiểm tra xem tỷ lệ điểm có tồn tại không
        assertEquals(rate.getAttendance(), fRate.getAttendance(), 0.001);
        assertEquals(rate.getExercise(), fRate.getExercise(), 0.001);
        assertEquals(rate.getTest(), fRate.getTest(), 0.001);
        assertEquals(rate.getExam(), fRate.getExam(), 0.001);
        assertEquals(rate.getPractice(), fRate.getPractice(), 0.001);
    }

    @Test
    public void testGetSubject() {
        // Test lấy thông tin môn học với id đã biết
        String subjectId = "BAS1105M";
        Subject subject = subjectDAO.getSubject(subjectId);
        ScoreRate rate = subject.getRate();
        
        ScoreRate fRate = new ScoreRate(0, 0, 0, 0, 100);
        Subject fSubject = new Subject("BAS1105M", "Giáo dục quốc phòng", "01",(float) 7.5, fRate, false);
        // Kiểm tra xem môn học có tồn tại không
        assertNotNull(subject);
        assertEquals(rate.getAttendance(), fRate.getAttendance(), 0.001);
        assertEquals(rate.getExercise(), fRate.getExercise(), 0.001);
        assertEquals(rate.getTest(), fRate.getTest(), 0.001);
        assertEquals(rate.getExam(), fRate.getExam(), 0.001);
        assertEquals(rate.getPractice(), fRate.getPractice(), 0.001);
        
        assertEquals(subject.getId(), fSubject.getId());
        assertEquals(subject.getName(), fSubject.getName());
        assertEquals(subject.getGroup(), fSubject.getGroup());
        assertEquals(subject.getCredit(), fSubject.getCredit(), 0.001);
    }

    @Test
    public void testGetAllSubject() {
        // Test lấy tất cả các môn học
        List<SubjectInput> subjects = subjectDAO.getAllSubject();

        // Kiểm tra xem danh sách môn học có tồn tại không
        assertNotNull(subjects);
    }

    @Test
    public void testGetSubInTerm() {
        // Test lấy các môn học trong một học kỳ với id đã biết
        String termId = "1";
        List<String> subIds = subjectDAO.getSubInTerm(termId);

        // Kiểm tra xem danh sách mã môn học có tồn tại không
        assertNotNull(subIds);
    }
}

