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
import org.xzgtemp.entity.User;
import org.xzgtemp.service.BookService;
import org.xzgtemp.service.CopyService;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;


@RequestMapping("/books/{bid}")
@Controller
public class BookController{
    
	@Autowired
	BookService bookservice;
	@Autowired
	CopyService copyservice;

	public static final String KEY_USER = "__user__";
    
	@GetMapping("")
	public ModelAndView showBook(@PathVariable("bid") Long bid,HttpSession session) {
		User user = (User)session.getAttribute(KEY_USER);
		if (user ==null){
			return new ModelAndView("redirect:/signin");
		}
		Map<String, Object> model = new HashMap<>();
        model.put("user",user);
        model.put("book",bookservice.GetBookbyBID(bid));
        model.put("copys", copyservice.GetCopybyBID(bid));
		return new ModelAndView("bookdetial.html",model);
	}

	
	@GetMapping("/change")
	public ModelAndView changeBookInfo(
		@PathVariable("bid") Long bid,
		HttpSession session
	) {
		User user = (User)session.getAttribute(KEY_USER);
		if (user ==null){
			return new ModelAndView("redirect:/signin");
		}
		Map<String, Object> model = new HashMap<>();
        model.put("user",user);
        model.put("book",bookservice.GetBookbyBID(bid));
		model.put("change","change");
		return new ModelAndView("bookdetial.html",model);
	}

	@PostMapping("/change/{attribute}")
	public ModelAndView changeBookInfo(
		@PathVariable("bid") Long bid,
		@PathVariable("attribute") String attribute,
		@RequestParam("value") Object value,
		HttpSession session
	) {
		User user = (User)session.getAttribute(KEY_USER);
		if (user ==null){
			return new ModelAndView("redirect:/signin");
		}
		try{
			Map<String, Object> model = new HashMap<>();
			model.put("user",user);
			Book book = bookservice.GetBookbyBID(bid);
			bookservice.ChangeBookAttribute(book, attribute, value);
			return new ModelAndView("redirect:/books/" + bid + "/change");
		}
		catch (Exception e){
			e.printStackTrace();
			return new ModelAndView("redirect:/error/unknown");
		}
	}


	@GetMapping("/delete")
	public ModelAndView DeleteBook(
		@PathVariable("bid") Long bid,
		HttpSession session
	) {
		User user = (User)session.getAttribute(KEY_USER);
		if (user ==null){
			return new ModelAndView("redirect:/signin");
		}
		bookservice.DeleteBook(bid);;
		return new ModelAndView("redirect:/search");
	}

	
}
