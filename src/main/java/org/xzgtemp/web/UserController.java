package org.xzgtemp.web;

import java.util.*;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.xzgtemp.entity.Book;
import org.xzgtemp.entity.Document;
import org.xzgtemp.entity.User;
import org.xzgtemp.service.BookService;
import org.xzgtemp.service.DocumentService;
import org.xzgtemp.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userservice;

	@Autowired
	BookService bookservice;

	@Autowired
	DocumentService documentservice;

	public static final String KEY_USER = "__user__";

	@GetMapping("/")
	public ModelAndView Visit(HttpSession session) {
		User user = (User) session.getAttribute(KEY_USER);
		if (user != null){
			Map<String, Object> model = new HashMap<>();
			model.put("user", user);
			model.put("name",user.GetName());
			return new ModelAndView("redirect:/search.html",model);
		}
		return new ModelAndView("signin.html");
	}


	@GetMapping("/signin")
	public ModelAndView SignIn(HttpSession session) {
		User user = (User) session.getAttribute(KEY_USER);
		if (user != null){
			Map<String, Object> model = new HashMap<>();
			model.put("user", user);
			model.put("name",user.GetName());
			return new ModelAndView("redirect:/search.html",model);
		}
		return new ModelAndView("signin.html");
	}



	@PostMapping("/signin")
	public ModelAndView doSignIn(@RequestParam("id") String id, @RequestParam("password") String password,
			HttpSession session) {
		try {
			User user = userservice.signin(id,password);
			session.setAttribute(KEY_USER, user);
		} catch (RuntimeException e) {
			Map<String, Object> model = new HashMap<>();
			model.put("id", id);
			model.put("error","login failed.");
			return new ModelAndView("signin.html",model);
		}
		return new ModelAndView("redirect:/search.html");
	}


	@GetMapping("/register")
	public ModelAndView Register() {

		return new ModelAndView("register.html");
	}

	@PostMapping("/register")
	public ModelAndView doRegister(@RequestParam("id") String id, @RequestParam("username") String username,@RequestParam("password") String password) {
		try {
			userservice.register(id,username,password);
		} catch (RuntimeException e) {
			Map<String, Object> model = new HashMap<>();
			model.put("id", id);
			model.put("error","register failed");
			return new ModelAndView("register.html",model);
		}
		return new ModelAndView("/register.html");
	}

	@GetMapping("/search")
	public ModelAndView Search(HttpSession session) {
	//TODO
		User user = (User) session.getAttribute(KEY_USER);
		Map<String, Object> model = new HashMap<>();
		model.put("user", user);
		model.put("name",user.GetName());
		return new ModelAndView("search.html",model);
	}

	@PostMapping("/search")
	public ModelAndView doSearch(@RequestParam("searchWhat") String searchwhat,@RequestParam("searchBy") String searchby,@RequestParam("target") String target,HttpSession session) {
	//TODO
		try {
			User user = (User) session.getAttribute(KEY_USER);
			Map<String, Object> model = new HashMap<>();
			model.put("user", user);
			model.put("name",user.GetName());
			if (searchwhat.equals("book")) {
				List<Book> books = new ArrayList<>();
				switch (searchby) {
					case "title":
						//books = bookservice.GetBookbyTitle(target);
						books.add(new Book("title","author","user1",null,"library",true));
						break;
					case "author":
						books = bookservice.GetBookbyAuthor(target);
						break;
					default:
						break;
				}
				if(!books.isEmpty()) {
					model.put("books", books);
					model.put("error", null);
					return new ModelAndView("search.html", model);
				}
			} else {
				List<Document> documnets = new ArrayList<>();
				switch (searchby) {
					case "title":
						documnets = documentservice.GetDocumentsbyTitle(target);
						break;
					case "author":
						documnets = documentservice.GetDocumentsbyAuthor(target);
						break;
					default:
						break;
				}
				if(!documnets.isEmpty()) {
					model.put("documents", documnets);
					model.put("error", null);
					return new ModelAndView("search.html", model);
				}
			}
		}catch(RuntimeException e){
			Map<String ,Object> model = new HashMap<>();
			model.put("error","Fault.");
			return new ModelAndView("/search.html",model);
		}
		Map<String ,Object> model = new HashMap<>();
		model.put("error","Not found any data by your input.");
		return new ModelAndView("/search.html",model);
	}
	
	@GetMapping("/signout")
	public String SignOut(HttpSession session) {
		session.removeAttribute(KEY_USER);
		return "redirect:/signin";
	}

}
