package com.example.BTL;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.example.BTL.user.User;
import com.example.BTL.user.UserController;
import com.example.BTL.user.UserDAO;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Mock
    private UserDAO userDAO;
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testGetLoginFormLogged() throws Exception {	
    	UserController.user = new User(1, "B20DCCN070", "123", "Anh", "admin");
    			
    	mockMvc.perform(get("/home"))
		.andExpect(status().isOk()) // Kiểm tra HTTP status code
		.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // Kiểm tra contentType là HTML
		.andExpect(content().string(Matchers.containsString("<title>Home</title>"))); // Kiểm tra nội dung HTML
    	
    }
    @Test
    public void testGetLoginForm() throws Exception {

    	UserController.user= new User();
    	
    	mockMvc.perform(get("/home"))
		.andExpect(status().isOk()) // Kiểm tra HTTP status code
		.andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML)) // Kiểm tra contentType là HTML
		.andExpect(content().string(Matchers.containsString("<title>Home</title>"))); // Kiểm tra nội dung HTML
    	
    }
    @Test
    public void testCheckLoginSuccess() throws Exception {
        // Mocking the userDAO.checkLogin() method to return true
        when(userDAO.checkLogin(any(User.class))).thenReturn(true);
        
        mockMvc.perform(post("/home")
                .param("username", "B20DCCN070")
                .param("password", "123"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("user"));
    }
    
    @Test
    public void testCheckLoginFailure() throws Exception {
        // Mocking the userDAO.checkLogin() method to return false
        when(userDAO.checkLogin(any(User.class))).thenReturn(false);
        
        mockMvc.perform(post("/home")
                .param("username", "testUsername")
                .param("password", "testPassword"))
                .andExpect(view().name("home"));
    }
    
    @Test
    public void testLogout() throws Exception {
        User user = new User();
        user.setId(1); // Assuming a valid user ID
        UserController.user = user;
        
    	mockMvc.perform(get("/logout"))
    	.andExpect(status().is3xxRedirection())
    	.andExpect(redirectedUrl("/home"));
    }
}
