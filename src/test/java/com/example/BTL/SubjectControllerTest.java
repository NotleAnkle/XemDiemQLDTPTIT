package com.example.BTL;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.BTL.Subject.SubjectController;

@WebMvcTest(SubjectController.class)
public class SubjectControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testGetSubjectInTerm() throws Exception {
    	
    	MvcResult result = mockMvc.perform(get("/subject/term/1"))
        .andExpect(status().isOk())
        .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        
        String[] sub = responseBody.split(",");
        
        assertEquals(sub.length, 6);
    	
    }
}
