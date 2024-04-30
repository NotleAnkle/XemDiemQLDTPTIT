package com.example.BTL.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDAOTest {

    private UserDAO userDAO;
    
    @Before
    public void setUp() throws Exception {
        // Khởi tạo đối tượng UserDAO trước mỗi lần chạy test
        userDAO = new UserDAO();
    }

    @After
    public void tearDown() throws Exception {
        // Đóng các tài nguyên sau khi test hoàn tất
        // Ở đây có thể thêm phương thức để giải phóng tài nguyên liên quan đến cơ sở dữ liệu
    }

    @Test
    public void testCheckLoginValid() {
        // Tạo một đối tượng User hợp lệ
        User user = new User();
        user.setUsername("B20DCCN070");
        user.setPassword("123");
        
        // Kiểm tra phương thức checkLogin với user hợp lệ
        assertTrue(userDAO.checkLogin(user));
    }
    
    @Test
    public void testCheckLoginInvalid() {
        // Tạo một đối tượng User không hợp lệ
        User user = new User();
        user.setUsername("invalidUser");
        user.setPassword("invalidPassword");
        
        // Kiểm tra phương thức checkLogin với user không hợp lệ
        assertFalse(userDAO.checkLogin(user));
    }
    
    @Test
    public void testAddUser() {
        // Tạo một đối tượng User mới
        User user = new User();
        user.setUsername("newUser");
        user.setPassword("newPassword");
        user.setName("New User");
        
        // Thêm user vào cơ sở dữ liệu và kiểm tra kết quả
        userDAO.SetDAOForTest();
        
        assertEquals("Đăng ký thành công", userDAO.addUser(user));
        
        userDAO.RollBack();
    }
    
    @Test
    public void testAddUserExistingUsername() {
        // Tạo một đối tượng User với username đã tồn tại trong cơ sở dữ liệu
        User user = new User();
        user.setUsername("B20DCCN070");
        user.setPassword("existingPassword");
        user.setName("Existing User");
        
        // Thêm user vào cơ sở dữ liệu và kiểm tra kết quả
        assertEquals("username đã được sử dụng", userDAO.addUser(user));
    }
    
    @Test
    public void testSelectUser() {
        // Chọn một user có id đã biết từ cơ sở dữ liệu và kiểm tra thông tin trả về
        User user = userDAO.selectUser(1);
        
        // Kiểm tra xem thông tin user được chọn có đúng không
        assertEquals("B20DCCN070", user.getUsername());
        assertEquals("123", user.getPassword());
        assertEquals("Phạm Đắc Anh", user.getName());
        assertEquals("admin", user.getRole());
    }
}


