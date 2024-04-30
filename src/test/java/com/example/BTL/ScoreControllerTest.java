package com.example.BTL;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.BTL.score.RequestScore;
import com.example.BTL.score.Score;
import com.example.BTL.score.ScoreController;
import com.example.BTL.user.User;
import com.example.BTL.user.UserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@WebMvcTest(ScoreController.class)
public class ScoreControllerTest {
    @InjectMocks
    private UserController userController;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testGetScoreByStudent() throws Exception {
        User user = new User();
        user.setId(1); // Assuming a valid user ID
        UserController.user = user;
        
        mockMvc.perform(get("/user/score/1"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("user"))
        .andExpect(model().attributeExists("terms"))
        .andExpect(model().attribute("user", Matchers.notNullValue()))
        .andExpect(model().attribute("terms", Matchers.hasItem(
                Matchers.hasProperty("scores", Matchers.hasSize(6))
            ))) // Kiểm tra term 1 có 6 phần tử trong scores
            .andExpect(model().attribute("terms", Matchers.hasItem(
                Matchers.hasProperty("scores", Matchers.hasSize(8))
            ))) // Kiểm tra term 2 có 8 phần tử trong scores
            .andExpect(model().attribute("terms", Matchers.hasItem(
                Matchers.hasProperty("scores", Matchers.hasSize(3))
            ))); // Kiểm tra term 3 có 3 phần tử trong scores
    }
    
    @Test
    public void testGetScoreByStudentIdAndTermId() throws Exception {
        User user = new User();
        user.setId(1); // Assuming a valid user ID
        UserController.user = user;
        
        mockMvc.perform(get("/score/user/1/term/1"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("user"))
        .andExpect(model().attributeExists("terms"))
        .andExpect(model().attribute("terms", Matchers.hasSize(3)))
        .andExpect(model().attributeExists("scores"))
        .andExpect(model().attribute("scores", Matchers.hasSize(6)))
        ;
    }
    
    @Test
    public void testTryScoreByStudentIdAndTermId() throws Exception {
        User user = new User();
        user.setId(1); // Assuming a valid user ID
        UserController.user = user;
        List<Score> scores = new ArrayList<Score>();
        scores.add(new Score());
        scores.add(new Score());
        scores.add(new Score());
        scores.add(new Score());
        scores.add(new Score());
        scores.add(new Score());
        
        RequestScore requestScore = new RequestScore(1,1,scores);
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson= ow.writeValueAsString(requestScore);
        
        mockMvc.perform(post("/score/user/1/term/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestJson))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("user"))
        .andExpect(model().attributeExists("terms"))
        .andExpect(model().attribute("terms", Matchers.hasSize(3)))
        .andExpect(model().attributeExists("scores"))
        .andExpect(model().attribute("scores", Matchers.hasSize(6)))
        ;
    }
    
    @Test
    public void testGetScoreById() throws Exception {    
    	MvcResult result = mockMvc.perform(get("/score/1"))
        .andExpect(status().isOk())
        .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        
        String[] sc = responseBody.split(",");
        
        assertEquals(sc.length, 14);
        
    }
}
