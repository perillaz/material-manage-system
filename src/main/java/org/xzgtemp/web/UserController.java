package org.xzgtemp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.xzgtemp.service.UserService;

@Controller
public class UserController {


	public static final String KEY_USER = "__user__";

	@Autowired
	UserService userservice;

	//final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/")
	public ModelAndView Signin(HttpSession session) {
		return new ModelAndView("signin.html");
	}


	@PostMapping("/signin")
	public ModelAndView doSignin(@RequestParam("username") String email, @RequestParam("password") String password,
			HttpSession session) {



		return new ModelAndView("redirect:/search.html");
	}


	@GetMapping("/register")
	public ModelAndView register() {

		
		return new ModelAndView("register.html");
	}
    
	
	@GetMapping("/search")
	public ModelAndView search() {

		
		return new ModelAndView("search.html");
	}

}
