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

	public static boolean getuser(HttpSession session,Map<String,Object> model){
		User user = (User) session.getAttribute(KEY_USER);
		if(user != null) {
			model.put("user", user);
			model.put("id", user.getId());
			model.put("name", user.getName());
			return true;
		}
		return false;
	}

	@GetMapping("/")
	public ModelAndView Visit(HttpSession session) {
		Map<String, Object> model = new HashMap<>();
		if (getuser(session,model)){
			return new ModelAndView("redirect:/search.html",model);
		}
		return new ModelAndView("signin.html");
	}


	@GetMapping("/signin")
	public ModelAndView SignIn(HttpSession session) {
		Map<String, Object> model = new HashMap<>();
		if (getuser(session,model)){
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
		Map<String, Object> model = new HashMap<>();
		model.put("id", id);
		try {
			userservice.register(id,username,password);
		} catch (RuntimeException e) {
			model.put("error","register failed.");
			return new ModelAndView("register.html",model);
		}
		model.put("success","Registered suscessfully.");
		return new ModelAndView("/register.html",model);
	}

	/**	@GetMapping("/search")
	public ModelAndView Search(HttpSession session) {
	//TODO
	Map<String, Object> model = new HashMap<>();
	getuser(session,model);
	return new ModelAndView("search.html",model);
	}

	 @PostMapping("/search")
	 public ModelAndView doSearch(@RequestParam("searchWhat") String searchwhat,@RequestParam("searchBy") String searchby,@RequestParam("target") String target,HttpSession session) {
	 //TODO
	 Map<String, Object> model = new HashMap<>();
	 getuser(session,model);
	 try {
	 if (searchwhat.equals("book")) {
	 List<Book> books = new ArrayList<>();
	 switch (searchby) {
	 case "title":
	 books = bookservice.GetBooksbyTitle(target);
	 break;
	 case "author":
	 books = bookservice.GetBooksbyAuthor(target);
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
	 model.put("error","Fault.");
	 return new ModelAndView("/search.html",model);
	 }
	 model.put("error","Not found any data by your input.");
	 return new ModelAndView("/search.html",model);
	 }
	 **/

	@GetMapping("/search")
	public ModelAndView Search(HttpSession session) {
		User user = (User) session.getAttribute(KEY_USER);
		if (user == null){
			return new ModelAndView("signin.html");
		}
		Map<String, Object> model = new HashMap<>();
		//model.put("user", user);
		model.put("name",user.getName());
		model.put("searchWhat","default");
		model.put("searchBy","book");
		return new ModelAndView("search.html",model);
	}

	@PostMapping("/search")
	public ModelAndView doSearch(
			@RequestParam("searchWhat") String searchwhat,
			@RequestParam("searchBy") String searchby,
			@RequestParam("target") String target,
			HttpSession session
	) {
		try {
			User user = (User) session.getAttribute(KEY_USER);
			if (user == null){
				return new ModelAndView("signin.html");
			}
			Map<String, Object> model = new HashMap<>();
			//model.put("user", user);
			model.put("name",user.getName());
			model.put("searchWhat",searchwhat);
			model.put("searchBy",searchby);
			model.put("target",target);
			switch (searchwhat + searchby){
				case "book" + "default":
					model.put("books",bookservice.GetBooksbyTitleOrAuthor(target));
					break;
				case "book" + "title":
					model.put("books",bookservice.GetBooksbyTitle(target));
					break;
				case "book" + "author":
					model.put("books",bookservice.GetBooksbyAuthor(target));
					break;
				case "document" + "default":
					model.put("documents",documentservice.GetDocumentsbyTitleOrAuthor(target));
					break;
				case "document" + "title":
					model.put("documents",documentservice.GetDocumentsbyTitle(target));
					break;
				case "document" + "author":
					model.put("documents",documentservice.GetDocumentsbyAuthor(target));
					break;
			}
			return new ModelAndView("/search.html",model);
			/*
			if (searchwhat.equals("book")) {
				List<Book> books = new ArrayList<>();
				switch (searchby) {
					case "title":
						books = bookservice.GetBooksbyTitle(target);
						break;
					case "author":
						books = bookservice.GetBooksbyAuthor(target);
						break;
					default:
						break;
				}
				if(!books.isEmpty()) {
					model.put("books", books);
					//model.put("error", null);
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
					//model.put("error", null);
					return new ModelAndView("search.html", model);
				}

			}*/
		}catch(RuntimeException e){
			e.printStackTrace();
			Map<String ,Object> model = new HashMap<>();
			User user = (User) session.getAttribute(KEY_USER);
			model.put("name",user.getName());
			model.put("searchWhat",searchwhat);
			model.put("searchBy",searchby);
			model.put("target",target);
			model.put("error","Fault.");
			return new ModelAndView("/search.html",model);
		}
		/*
		Map<String ,Object> model = new HashMap<>();
		model.put("error","Not found any data by your input.");
		return new ModelAndView("/search.html",model);
		*/
	}


	@GetMapping("/user")
	public ModelAndView userinfo(HttpSession session){
		Map<String, Object> model = new HashMap<>();
		getuser(session,model);
		return new ModelAndView("/userdetial.html",model);
	}

	@PostMapping("/user")
	public ModelAndView getuserinfo(@RequestParam("change") String change,HttpSession session){
		Map<String, Object> model = new HashMap<>();
		getuser(session,model);
		try{
			if (change.equals("name")) {
				model.put("changeinfo", "name");
			} else if (change.equals("password")) {
				model.put("changeinfo", "password");
			}
			return new ModelAndView("/changeuserinformation.html", model);

		}catch(RuntimeException e){
			model.put("error","Fault.");
			return new ModelAndView("/changeuserinformation.html",model);
		}
	}

	@GetMapping("/user/change")
	public  ModelAndView changeuserinfo(HttpSession session){

		return new ModelAndView("/changeuserinformation.html");

	}

	@PostMapping("/user/change")
	public ModelAndView dochangeuserinfo( @RequestParam("newinfo1") String newinfo1,@RequestParam("newinfo2") String newinfo2,@RequestParam("submit") String submit,HttpSession session){
		Map<String, Object> model = new HashMap<>();
		try {
			User user = (User) session.getAttribute(KEY_USER);
			if(submit.equals("password")) {
				if (newinfo1.equals(newinfo2)) {
					user.setPassword(newinfo1);
					userservice.ChangeUserPassword(user);
					model.put("user", user);
					model.put("changedpassword", true);
					return new ModelAndView("/changeuserinformation.html", model);
				} else {
					model.put("error", "The passwords are not the same.");
					return new ModelAndView("/changeuserinformation.html", model);
				}
			}
		}catch(RuntimeException e){
			model.put("fault","fault.");
			return new ModelAndView("/userdetial.html",model);
		}
		return new ModelAndView("/userdetial.html",model);
	}

	@GetMapping("/signout")
	public String SignOut(HttpSession session) {
		session.removeAttribute(KEY_USER);
		return "redirect:/signin";
	}

}
