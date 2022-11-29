package org.xzgtemp.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.xzgtemp.entity.Copy;
import org.xzgtemp.entity.User;
import org.xzgtemp.service.BookService;
import org.xzgtemp.service.CopyService;

@RequestMapping("/books/{bid}/copy/{cid}")
@Controller
public class CopyController {
    

	@Autowired
	BookService bookservice;
	@Autowired
	CopyService copyservice;

	public static final String KEY_USER = "__user__";

	@GetMapping("/borrow")
	public ModelAndView doBorrow(@PathVariable("bid") Long bid,@PathVariable("cid") Long cid,HttpSession session) {
		User user = (User)session.getAttribute(KEY_USER);
		if (user ==null){
			return new ModelAndView("redirect:/signin");
		}
		try{
			Map<String, Object> model = new HashMap<>();
			model.put("user",user);
			model.put("book",bookservice.GetBookbyBID(bid));
			copyservice.doBorrowCopy(cid, user);
			model.put("borrowsucceed", "borrow succeeds!");
			return new ModelAndView("bookdetial.html",model);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:/error/unknown");
		}
	}
	
	@GetMapping("/reserve")
	public ModelAndView doReserve(@PathVariable("bid") Long bid,@PathVariable("cid") Long cid, HttpSession session) {
		User user = (User)session.getAttribute(KEY_USER);
		if (user ==null){
			return new ModelAndView("redirect:/signin");
		}
		try{
			Map<String, Object> model = new HashMap<>();
			model.put("user",user);
			model.put("book",bookservice.GetBookbyBID(bid));
			copyservice.ReserveCopy(cid, user);
			model.put("reservesucceed","reserve succeeds!");
			return new ModelAndView("bookdetial.html",model);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:/error/unknown");
		}
	}
	
	@GetMapping("/return/{bcid}")
	public ModelAndView doreturn(@PathVariable("bid") Long bid, @PathVariable("cid") Long cid, @PathVariable("bcid") Long bcid,HttpSession session) {
		User user = (User)session.getAttribute(KEY_USER);
		if (user ==null){
			return new ModelAndView("redirect:/signin");
		}
		try{
			copyservice.ReturnCopy(cid, bcid);
			return new ModelAndView("redirect:/user/information");
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:/error/unknown");
		}
	}
	

	@GetMapping("/changecopy")
	public ModelAndView changeCopy(
		@PathVariable("bid") Long bid,
		@PathVariable("cid") Long cid,
		HttpSession session
	) {
		User user = (User)session.getAttribute(KEY_USER);
		if (user ==null){
			return new ModelAndView("redirect:/signin");
		}
		Map<String, Object> model = new HashMap<>();
        model.put("user",user);
        model.put("book",bookservice.GetBookbyBID(bid));
        model.put("copy",copyservice.GetCopybyCID(cid));
		model.put("changecopy","changecopy");
		return new ModelAndView("bookdetial.html",model);
	}
	
	@PostMapping("/changecopy/{attribute}")
	public ModelAndView changeCopyInfo(
		@PathVariable("bid") Long bid,
		@PathVariable("cid") Long cid,
		@PathVariable("attribute") String attribute,
		@RequestParam("value") Object value,
		HttpSession session
	) {
		User user = (User)session.getAttribute(KEY_USER);
		if (user ==null){
			return new ModelAndView("redirect:/signin");
		}
		try{
			Copy copy=copyservice.GetCopybyCID(cid);
			copyservice.ChangeCopy(copy, attribute, value);
			return new ModelAndView("redirect:/books/"+bid);
		}catch (Exception e){
			e.printStackTrace();
			return new ModelAndView("redirect:/error/unknown");
		}
	}

}
