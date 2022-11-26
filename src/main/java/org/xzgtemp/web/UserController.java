package org.xzgtemp.web;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
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
			model.put("registersuccessful","registersuccessful");
		} catch (RuntimeException e) {
			model.put("error","Register failed.\nThe id already exists,please input again.");
			return new ModelAndView("register.html",model);
		}
		model.put("success","Registered suscessfully.");
		return new ModelAndView("register.html",model);
	}


	@GetMapping("/search")
	public ModelAndView Search(HttpSession session) {
		Map<String, Object> model = new HashMap<>();
		if (getuser(session,model)){
			model.put("searchWhat","default");
			model.put("searchBy","book");
			return new ModelAndView("search.html",model);
		}
		return new ModelAndView("redirect:/signin.html");
	}

	@PostMapping("/search")
	public ModelAndView doSearch(
			@RequestParam("searchWhat") String searchwhat,
			@RequestParam("searchBy") String searchby,
			@RequestParam("target") String target,
			HttpSession session
	) {
		try {
			Map<String, Object> model = new HashMap<>();
			if (!getuser(session,model)){
				return new ModelAndView("redirect:/signin.html");
			}
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
			return new ModelAndView("search.html",model);
		}catch(RuntimeException e){
			e.printStackTrace();
			Map<String ,Object> model = new HashMap<>();
			getuser(session,model);
			model.put("searchWhat",searchwhat);
			model.put("searchBy",searchby);
			model.put("target",target);
			model.put("error","Fault.");
			return new ModelAndView("search.html",model);
		}
	}


	@GetMapping("/user")
	public ModelAndView userinfo(HttpSession session){
		Map<String, Object> model = new HashMap<>();
		if(getuser(session,model)) {
			return new ModelAndView("userdetial.html", model);
		}
		return new ModelAndView("redirect:/sigin.html");
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
			model.put("error","Unknown error.");
			return new ModelAndView("userdetial.html",model);
		}
	}

	@GetMapping("/user/change")
	public  ModelAndView changeuserinfo(HttpSession session){
		Map<String, Object> model = new HashMap<>();
		if(getuser(session,model)) {
			return new ModelAndView("userdetial.html", model);
		}
		return new ModelAndView("redirect:/signin.html");

	}

	@PostMapping("/user/change")
	public ModelAndView dochangeuserinfo(
			@RequestParam("newinfo1") String newinfo1,
			@RequestParam("newinfo2") String newinfo2,
			@RequestParam("submit") String submit,
			HttpSession session){
		try {
			Map<String, Object> model = new HashMap<>();
			User user = (User) session.getAttribute(KEY_USER);
			model.put("user",user);
			if (submit.equals("password")) {
				if(!newinfo1.equals(newinfo2)){
					model.put("error", "The two inputs are different.");
					model.put("changeinfo","password");
					return new ModelAndView("/changeuserinformation.html", model);
				}
				if(newinfo1=="" || newinfo2==""){
					model.put("error", "The password is null.");
					model.put("changeinfo","password");
					return new ModelAndView("/changeuserinformation.html", model);
				}
				if (newinfo1.equals(user.getPassword())) {
					model.put("error", "The password is the same as your old password.");
					model.put("changeinfo","password");
					return new ModelAndView("/changeuserinformation.html", model);
				} else {
					user.setPassword(newinfo1);
					userservice.ChangeUserPassword(user);
					model.put("changedpassword", true);
					return new ModelAndView("/changeuserinformation.html", model);
				}
			} else{
				if(!newinfo1.equals(newinfo2)){
					model.put("error", "The two inputs are different.");
					model.put("changeinfo","name");
					return new ModelAndView("/changeuserinformation.html", model);
				}
				if(newinfo1=="" || newinfo2==""){
					model.put("error", "The name is null.");
					model.put("changeinfo","name");
					return new ModelAndView("/changeuserinformation.html", model);
				}
				if (newinfo1.equals(user.getName())) {
					model.put("error", "The name is the same as your old name.");
					model.put("changeinfo","name");
					return new ModelAndView("/changeuserinformation.html", model);
				} else {
					user.setName(newinfo1);
					userservice.ChangeUserName(user);
					model.put("changedname", true);
					return new ModelAndView("/changeuserinformation.html", model);
				}
			}
		}catch(RuntimeException e){
			Map<String, Object> model = new HashMap<>();
			getuser(session,model);
			model.put("fault","Change information fault.");
			return new ModelAndView("userdetial.html",model);
		}
	}

	@GetMapping("/uploaddocument")
	public ModelAndView uploadDocument(HttpSession session){
		Map<String, Object> model = new HashMap<>();
		if(getuser(session,model)){
			return new ModelAndView("uploaddocument.html",model);
		}
		else{
			return new ModelAndView("redirect:/signin.html");
		}
	}

	@PostMapping("/uploaddocument")
	public ModelAndView onfile(
		@RequestParam("file")MultipartFile file,
		@RequestParam("title") String title,
		@RequestParam("author") String author,
		@RequestParam("doi") String doi,
		@RequestParam("literature") String literature,
		HttpSession session
		){
		try {
			if (session.getAttribute(KEY_USER) ==null){
				return new ModelAndView("redirect:/signin.html");
			}
			User user = (User) session.getAttribute(KEY_USER);
			Document document = documentservice.UploadDocument(user,file,title,author,doi,literature);
			Map<String, Object> model = new HashMap<>();
			model.put("document",document);
			return new ModelAndView("uploaddocument.html",model);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> model = new HashMap<>();
			model.put("error","Failed uploaded.Please upload again.");
			return new ModelAndView("uploaddocument.html",model);
		}
	}
   
	 /* 
	public ModelAndView doUploadDocument(
			@RequestParam("file") File file,
			@RequestParam("author") String author,
			HttpSession session){
		try{
			Map<String, Object> model = new HashMap<>();
			User user = (User) session.getAttribute(KEY_USER);
			String title = file.getName();
			String filepath = file.getAbsolutePath();
			String uploader = user.getName();
			LocalDate date = LocalDate.now();
			Date datep = Date.valueOf(date);
			if(author.equals("")){
				author = "Unknow";
			}
			Document document = new Document(title,author,uploader,datep,filepath,0);
			documentservice.AddDocument(document);
			model.put("document",document);
			model.put("publishtime",datep.toString());
			return new ModelAndView("uploaddocument.html",model);
		}catch (RuntimeException e){
			Map<String, Object> model = new HashMap<>();
			getuser(session,model);
			model.put("error","File not uploaded.Please upload again.");
			return new ModelAndView("uploaddocument.html",model);
		}
	}
	*/

	@GetMapping("/signout")
	public String SignOut(HttpSession session) {
		session.removeAttribute(KEY_USER);
		return "redirect:/signin";
	}

}
