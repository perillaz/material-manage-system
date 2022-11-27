package org.xzgtemp.web;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.xzgtemp.entity.Document;
import org.xzgtemp.entity.User;
import org.xzgtemp.service.BookService;
import org.xzgtemp.service.DocumentService;
import org.xzgtemp.service.UserService;

@RequestMapping("/user")
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
		return new ModelAndView("redirect:/signin");
	}

	@GetMapping("/information")
	public ModelAndView userinfo(HttpSession session){
		Map<String, Object> model = new HashMap<>();
		if(getuser(session,model)) {
			return new ModelAndView("userdetial.html", model);
		}
		return new ModelAndView("redirect:/signin");
	}

	@PostMapping("/information")
	public ModelAndView getuserinfo(@RequestParam("change") String change,HttpSession session){
		Map<String, Object> model = new HashMap<>();
		getuser(session,model);
		try{
			if (change.equals("name")) {
				model.put("changeinfo", "name");
			} else if (change.equals("password")) {
				model.put("changeinfo", "password");
			}
			return new ModelAndView("changeuserinformation.html", model);

		}catch(RuntimeException e){
			model.put("error","Unknown error.");
			return new ModelAndView("userdetial.html",model);
		}
	}

	@GetMapping("/information/change")
	public  ModelAndView changeuserinfo(HttpSession session){
		Map<String, Object> model = new HashMap<>();
		if(getuser(session,model)) {
			return new ModelAndView("userdetial.html", model);
		}
		return new ModelAndView("redirect:/signin");

	}

	@PostMapping("/information/change")
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
					return new ModelAndView("changeuserinformation.html", model);
				}
				if(newinfo1=="" || newinfo2==""){
					model.put("error", "The password is null.");
					model.put("changeinfo","password");
					return new ModelAndView("changeuserinformation.html", model);
				}
				if (newinfo1.equals(user.getPassword())) {
					model.put("error", "The password is the same as your old password.");
					model.put("changeinfo","password");
					return new ModelAndView("changeuserinformation.html", model);
				} else {
					user.setPassword(newinfo1);
					userservice.ChangeUserPassword(user);
					model.put("changedpassword", true);
					return new ModelAndView("changeuserinformation.html", model);
				}
			} else{
				if(!newinfo1.equals(newinfo2)){
					model.put("error", "The two inputs are different.");
					model.put("changeinfo","name");
					return new ModelAndView("changeuserinformation.html", model);
				}
				if(newinfo1=="" || newinfo2==""){
					model.put("error", "The name is null.");
					model.put("changeinfo","name");
					return new ModelAndView("changeuserinformation.html", model);
				}
				if (newinfo1.equals(user.getName())) {
					model.put("error", "The name is the same as your old name.");
					model.put("changeinfo","name");
					return new ModelAndView("changeuserinformation.html", model);
				} else {
					user.setName(newinfo1);
					userservice.ChangeUserName(user);
					model.put("changedname", true);
					return new ModelAndView("changeuserinformation.html", model);
				}
			}
		}catch(RuntimeException e){
			Map<String, Object> model = new HashMap<>();
			getuser(session,model);
			model.put("fault","Change information fault.");
			return new ModelAndView("userdetial.html",model);
		}
	}

	@GetMapping("/changeusertype")
	public ModelAndView changeUsertype(HttpSession session){
		Map<String, Object>  model = new HashMap<>();
		if(getuser(session,model)){
			return new ModelAndView("changeusertype.html",model);
		}
		return new ModelAndView("redirect:/signin");
	}

	@PostMapping("/changeusertype")
	public ModelAndView dochangeusertype(
			@RequestParam("setwhat") String setwhat,
			@RequestParam("setlevel") String setlevel,
			@RequestParam("users") String users,
			HttpSession session
	){
		try{
			Map<String, Object> model = new HashMap<>();
			User user = (User) session.getAttribute(KEY_USER);
			model.put("user",user);
			String[] userarr=users.split(",");
			List<User> userlist = new ArrayList<>();
			if(userarr[0]==""){
				model.put("error","No user input, please input again.");
				return new ModelAndView("changeusertype.html",model);
			}else {
				if (setwhat.equals("setadmini")) {
					for (String target : userarr) {
						User getuser = userservice.GetUserbyID(target);
						userlist.add(getuser);
					}
					if(setlevel.equals("high")){
						userservice.AddAdmini(user, userlist,true,true);
					}
					else if(setlevel.equals("middle")){
						userservice.AddAdmini(user, userlist,false,true);
					}
					else{
						userservice.AddAdmini(user, userlist,false,false);
					}
				} else {
					for (String target : userarr) {
						User getuser = userservice.GetUserbyID(target);
						userlist.add(getuser);
					}
					userservice.AddStudent(user, userlist);
				}
				model.put("success","Set successfully.");
				return new ModelAndView("changeusertype.html",model);
			}
		}catch(RuntimeException e){
			Map<String, Object> model = new HashMap<>();
			getuser(session,model);
			model.put("fault","Change userpermission fault.");
			return new ModelAndView("userdetial.html",model);
		}
	}
}
