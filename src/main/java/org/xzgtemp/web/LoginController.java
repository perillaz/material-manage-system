package org.xzgtemp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.xzgtemp.entity.User;
import org.xzgtemp.service.BookService;
import org.xzgtemp.service.DocumentService;
import org.xzgtemp.service.StatisticService;
import org.xzgtemp.service.UserService;

import javax.servlet.http.HttpSession;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserService userservice;

    @Autowired
    BookService bookservice;

    @Autowired
    DocumentService documentservice;
    
    @Autowired
	StatisticService statisticservice;

    public static final String KEY_USER = "__user__";

    public static boolean getuser(HttpSession session, Map<String,Object> model){
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

    @GetMapping("/signout")
    public String SignOut(HttpSession session) {
        session.removeAttribute(KEY_USER);
        return "redirect:/signin";
    }


    @GetMapping("/search")
    public ModelAndView Search(HttpSession session) {
        Map<String, Object> model = new HashMap<>();
        if (getuser(session,model)){
            model.put("searchWhat","default");
            model.put("searchBy","book");
            return new ModelAndView("/search.html",model);
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
                return new ModelAndView("redirect:/signin");
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
                case "book" + "id":
                    model.put("books",Arrays.asList(bookservice.GetBookbyBID(Long.parseLong(target))));
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
                case "document" + "id":
                    model.put("documents",Arrays.asList(documentservice.GetDocumentbyDID(Long.parseLong(target))));
                    break;
            }
            return new ModelAndView("/search.html",model);
        }catch(RuntimeException e){
            e.printStackTrace();
            Map<String ,Object> model = new HashMap<>();
            getuser(session,model);
            model.put("searchWhat",searchwhat);
            model.put("searchBy",searchby);
            model.put("target",target);
            model.put("error","Fault.");
            return new ModelAndView("/search.html",model);
        }
    }
    
    @GetMapping("/StatisticInfo")
    public ModelAndView UserStatistic(HttpSession session) {
    	Map<String, Object> model = new HashMap<>();
    	model.put("userstatistic", statisticservice.GetUserStatistic());
    	model.put("bookrank", statisticservice.GetWeekBorrowRank());
    	model.put("documentrank", statisticservice.GetWeekDownloadRank());
    	return new ModelAndView("statistics.html",model);
    	
    }

   
    
    

}
