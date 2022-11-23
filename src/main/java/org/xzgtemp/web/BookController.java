package org.xzgtemp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
		return new ModelAndView("bookdetial.html",model);
	}
    
	@PostMapping("/{bid}")
	public ModelAndView changeBook(
		@PathVariable("bid") Long bid,
		@PathVariable("attribute") String Attribute,
		HttpSession session){
		if (session.getAttribute(KEY_USER) ==null){
			return new ModelAndView("redirect:/signin.html");
		}
		Map<String, Object> model = new HashMap<>();
        model.put("user",(User)session.getAttribute(KEY_USER));
        model.put("book",bookservice.GetBookbyBID(bid));
		return new ModelAndView("bookdetial.html",model);
	}

    @GetMapping("/{bid}/{cid}")
    public ModelAndView showCopy(HttpSession session){
		if (session.getAttribute(KEY_USER) ==null){
			return new ModelAndView("redirect:/signin.html");
		}
        return new ModelAndView("redirect:/search.html");
    }

}
