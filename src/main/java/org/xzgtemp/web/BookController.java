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

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;


@RequestMapping("/books")
@Controller
public class BookController{
    
	@Autowired
	BookService bookservice;

	public static final String KEY_USER = "__user__";


    
	@GetMapping("/{bid}")
	public ModelAndView showBook(@PathVariable("bid") Long bid,HttpSession session) {
		if (session.getAttribute(KEY_USER) ==null){
			return new ModelAndView("redirect:/signin.html");
		}
		Map<String, Object> model = new HashMap<>();
        model.put("user",(User)session.getAttribute(KEY_USER));
        model.put("book",bookservice.GetBookbyBID(bid));
		//TODO：显示单册
		//TODO：购买者id
		return new ModelAndView("bookdetial.html",model);
	}

	//TODO：预约，借阅

	@GetMapping("/change")
	public ModelAndView changeBookInfo(
		@PathVariable("bid") Long bid,
		HttpSession session
	) {
		if (session.getAttribute(KEY_USER) ==null){
			return new ModelAndView("redirect:/signin.html");
		}
		Map<String, Object> model = new HashMap<>();
        model.put("user",(User)session.getAttribute(KEY_USER));
        model.put("book",bookservice.GetBookbyBID(bid));
		model.put("change","change");
		return new ModelAndView("bookdetial.html",model);
	}

	@PostMapping("/change/{attribute}")
	public ModelAndView changeDocumentInfo(
		@PathVariable("bid") Long bid,
		@PathVariable("attribute") String attribute,
		@RequestParam("value") Object value,
		HttpSession session
	) {
		try{

			if (session.getAttribute(KEY_USER) ==null){
				return new ModelAndView("redirect:/signin.html");
			}
			System.out.println(attribute);
			System.out.println(value);
			Book book = bookservice.GetBookbyBID(bid);
			System.out.println(book.getId());
			System.out.println(book.getTitle());
			bookservice.ChangeBookAttribute(book, attribute, value);
			System.out.println(book.getId());
			System.out.println(book.getTitle());
			return new ModelAndView("redirect:/documents/" + bid + "/change");
		}
		catch (Exception e){
			e.printStackTrace();
			return new ModelAndView("redirect:/error/unknown");
		}
	}

}
