package org.xzgtemp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.xzgtemp.entity.Book;
import org.xzgtemp.entity.Copy;
import org.xzgtemp.entity.User;
import org.xzgtemp.service.BookService;
import org.xzgtemp.service.CopyService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

@RequestMapping("/books/{bid}/addcopy")
@Controller
public class AddCopyController {
    
	@Autowired
	BookService bookservice;
	@Autowired
	CopyService copyservice;


	public static final String KEY_USER = "__user__";

	@GetMapping("")
	public ModelAndView addCopy(@PathVariable("bid") Long bid,HttpSession session) {
		User user = (User)session.getAttribute(KEY_USER);
		if (user ==null){
			return new ModelAndView("redirect:/signin.html");
		}
		Map<String, Object> model = new HashMap<>();
		Book book = bookservice.GetBookbyBID(bid);
        model.put("user",user);
		model.put("book",book);
		return new ModelAndView("addcopy.html",model);
	}


	@PostMapping("")
	public ModelAndView addCopy(
		@PathVariable("bid") Long bid,
		@RequestParam("loc") String loc,
		HttpSession session) {
		User user = (User)session.getAttribute(KEY_USER);
		if (user ==null){
			return new ModelAndView("redirect:/signin.html");
		}
		try {
			Book book = bookservice.GetBookbyBID(bid);
			Long cid = copyservice.doAddCopy(user,book,loc);
			return new ModelAndView("redirect:/books/"+  bid.toString() + "/addcopy/success/" + cid.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> model = new HashMap<>();
			Book book = bookservice.GetBookbyBID(bid);
			model.put("user",user);
			model.put("book",book);
			model.put("error","fail to add copy information");
			return new ModelAndView("addcopy.html",model);
		}
	}

	@GetMapping("/success/{cid}")
	public ModelAndView addCopySuccess(@PathVariable("bid") Long bid,@PathVariable("cid") Long cid,HttpSession session) {
		User user = (User)session.getAttribute(KEY_USER);
		if (user ==null){
			return new ModelAndView("redirect:/signin");
		}
		Map<String, Object> model = new HashMap<>();
		Copy copy = copyservice.GetCopybyCID(cid);
        model.put("user",user);
		model.put("copy",copy);
		model.put("success","success");
		return new ModelAndView("addcopy.html",model);
	}
	


}
