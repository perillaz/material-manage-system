package org.xzgtemp.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.xzgtemp.entity.User;
import org.xzgtemp.service.DocumentService;

@RequestMapping("/documents")
@Controller
public class DocumentController {
    
	@Autowired
    DocumentService documentservice;

	public static final String KEY_USER = "__user__";

	@GetMapping("/{did}")
	public ModelAndView showBook(@PathVariable("did") Long did,HttpSession session) {
		if (session.getAttribute(KEY_USER) ==null){
			return new ModelAndView("redirect:/signin.html");
		}
		Map<String, Object> model = new HashMap<>();
        model.put("user",(User)session.getAttribute(KEY_USER));
        model.put("document",documentservice.GetDocumentsbyDID(did));
		return new ModelAndView("documentdetial.html",model);
	}
    

}
