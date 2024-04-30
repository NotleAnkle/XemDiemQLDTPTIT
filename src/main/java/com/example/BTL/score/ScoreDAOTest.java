package com.example.BTL.score;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ScoreDAOTest {

    private ScoreDAO scoreDAO;

    @Before
    public void setUp() throws Exception {
        // Khởi tạo đối tượng ScoreDAO trước mỗi lần chạy test
        scoreDAO = new ScoreDAO();
        scoreDAO.SetDAOForTest();
    }

    @After
    public void tearDown() throws Exception {
        // Đóng các tài nguyên sau khi test hoàn tất
        // Ở đây có thể thêm phương thức để giải phóng tài nguyên liên quan đến cơ sở dữ liệu
    	scoreDAO.RollBack();
    }

    @Test
    public void testGetStudentScore() {
        // Test lấy điểm của sinh viên với id đã biết
        int studentId = 1;
        List<Score> scores = scoreDAO.getStudentScore(studentId);

        // Kiểm tra xem danh sách điểm có tồn tại không
        assertNotNull(scores);
    }

    @Test
    public void testGetStudentScoreByTerm() {
        // Test lấy điểm của sinh viên với id và học kỳ đã biết
        int studentId = 1;
        int termId = 1;
        List<Score> scores = scoreDAO.getStudentScoreByTerm(studentId, termId);

        // Kiểm tra xem danh sách điểm có tồn tại không
        assertNotNull(scores);
    }

	@Test
    public void testGetScoreById() {
        // Test lấy điểm bởi id
        String scoreId = "1";
        Score score = scoreDAO.getScoreById(scoreId);
        Score fScore = new Score(1, 1, "BAS1105M", 0,0,0,0,7,1, null);
        // Kiểm tra xem điểm có tồn tại không
        assertNotNull(score);
//        System.out.println(score.toString());
        assertEquals(score, fScore);
    }
    @Test
    public void testGetScoreBySubjectAndTerm() {
        // Test lấy điểm bởi mã môn học và học kỳ
        String subjectId = "BAS1105M";
        int termId = 1;
        Score score = scoreDAO.getScoreBySubjectAndTerm(subjectId, String.valueOf(termId));

        Score fScore = new Score(1, 1, "BAS1105M", 0,0,0,0,7,1, null);
        // Kiểm tra xem điểm có tồn tại không
        assertNotNull(score);
        assertEquals(score, fScore);
    }

    @Test
    public void testUpdateScoreValid() {
    	
    	Score newScore = new Score(1, 0,"BAS1105M", 0, 10, 0, 0, 0, 1, null);
    	
    	assertEquals(scoreDAO.UpdateScore(newScore), true);
    	
    }
    @Test
    public void testInsertScoreValid() {
    	
    	Score newScore = new Score(0, 1,"BAS1105M", 0, 10, 0, 0, 0, 1, null);
    	
    	assertEquals(scoreDAO.InsertScore(newScore), true);
    	
    }
}

