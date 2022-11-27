package org.xzgtemp.web;

import java.sql.Date;
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
import org.xzgtemp.entity.Book;
import org.xzgtemp.entity.User;
import org.xzgtemp.service.BookService;

@RequestMapping("/addbook")
@Controller
public class AddBookController {
    
	public static final String KEY_USER = "__user__";

	@Autowired
	BookService bookservice;

	@GetMapping("")
	public ModelAndView addBooks(HttpSession session){
		Map<String, Object> model = new HashMap<>();
		User user = (User)session.getAttribute(KEY_USER);
		if (user ==null){
			return new ModelAndView("redirect:/signin.html");
		}
		else{
            model.put("user", user);
			return new ModelAndView("addbook.html",model);
		}
	}

	@PostMapping("")
	public ModelAndView addBook(
		@RequestParam("title") String title,
		@RequestParam("author") String author,
		@RequestParam("isbn") String isbn,
		@RequestParam("edition") String edition,
		@RequestParam("publishtime") Date publishtime,
		@RequestParam("publisher") String publisher,
        @RequestParam("lang") String lang,
        @RequestParam("briefinfo") String briefinfo,
		HttpSession session
		){
		try {
			User user = (User)session.getAttribute(KEY_USER);
			if (user ==null){
				return new ModelAndView("redirect:/signin.html");
			}
            Long bid = bookservice.AddBookInfo(user,title,author,isbn,edition,publishtime,publisher,lang,briefinfo);
            return new ModelAndView("redirect:/addbook/success/" + bid);
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> model = new HashMap<>();
            model.put("user", (User)session.getAttribute(KEY_USER));
			model.put("error","Failed to add book information.Please do it again.");
			return new ModelAndView("addbook.html",model);
		}
	}

    @GetMapping("/success/{bid}")
    public ModelAndView uploadSuccessful(
        @PathVariable("bid") Long bid,
		HttpSession session
    )
    {	
		User user = (User)session.getAttribute(KEY_USER);
		if (user ==null){
			return new ModelAndView("redirect:/signin.html");
		}
        Book book = bookservice.GetBookbyBID(bid);
        Map<String, Object> model = new HashMap<>();
        model.put("book",book);
		model.put("user", user);
        return new ModelAndView("addbooksuccess.html",model);
    }


}
