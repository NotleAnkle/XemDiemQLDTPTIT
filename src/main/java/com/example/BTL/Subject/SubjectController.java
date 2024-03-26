package com.example.BTL.Subject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.BTL.term.SubInTerm;

@Controller
public class SubjectController {
	SubjectDAO subjectDAO = new SubjectDAO();
	@GetMapping("/subject/term/{id}")
	@ResponseBody
	public List<String> getSubjects(@PathVariable String id){
		return subjectDAO.getSubInTerm(id);
	}
}
