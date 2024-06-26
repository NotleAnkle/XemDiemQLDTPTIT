package com.example.BTL.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UserController {
    private UserDAO userDAO = new UserDAO();
    static public User user = new User();
    static public boolean isLoggin = false;

    @GetMapping("/home")
    public String getLogin(Model model) {
    	if(user.getUsername() != "") {
    		model.addAttribute("user", user);
    	}
    	return "home";
    }

    @PostMapping("/home")
    public String checkLogin(@RequestParam String username, @RequestParam String password, Model model) {
    	User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if(userDAO.checkLogin(user)) {
//        	model.addAttribute("message", "Đăng nhập thành công");
        	this.user = user;
        	isLoggin = true;
        	model.addAttribute("user", user);

        } else {
            model.addAttribute("message", "Tên người dùng hoặc mật khẩu không chính xác");

        }
        return "home"; 
    }
    @GetMapping("/logout")
    public String logOut() {
    	isLoggin = false;
    	user = new User();
    	return "redirect:/home";
    }
    
    @GetMapping("/style.css")
    public String getCSS() {
    	return "style.css";
    }
    @GetMapping("/scripts.js")
    public String getScript() {
    	return "scripts.js";
    }
    
//    @GetMapping("/user/infor/{id}")
//    public String UserInfor(Model model, @PathVariable String id) {
//    	model.addAttribute("user", user);
//    	return "infor";
//    }
        
//	@PostMapping("/register")
//	public String addUser(@RequestBody User user) {
//		return userDAO.addUser(user);
//	}
//	
//	@GetMapping("/user/{id}")
//	public User getUser(@PathVariable int id) {
//		return userDAO.selectUser(id);
//	}
}
