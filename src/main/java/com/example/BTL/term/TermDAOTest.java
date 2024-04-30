package com.example.BTL.term;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TermDAOTest {

    private TermDAO termDAO;

    @Before
    public void setUp() throws Exception {
        // Khởi tạo đối tượng TermDAO trước mỗi lần chạy test
        termDAO = new TermDAO();
    }

    @After
    public void tearDown() throws Exception {
        // Đóng các tài nguyên sau khi test hoàn tất
        // Ở đây có thể thêm phương thức để giải phóng tài nguyên liên quan đến cơ sở dữ liệu
    }

    @Test
    public void testGetAllTerm() {
        // Test lấy tất cả các học kỳ
        List<Term> terms = termDAO.getAllTerm();

        // Kiểm tra xem danh sách học kỳ có tồn tại không
        assertNotNull(terms);
    }

    @Test
    public void testGetTermById() {
        // Test lấy học kỳ theo id đã biết
        int termId = 1;
        Term term = termDAO.getTermById(termId);
        
        Term fTerm = new Term(1, "2020-2021", "1");
        // Kiểm tra xem học kỳ có tồn tại không
        assertNotNull(term);
        assertEquals(term.getId(), fTerm.getId());
        assertEquals(term.getYear(), fTerm.getYear());
        assertEquals(term.getTerm(), fTerm.getTerm());
    }
}

