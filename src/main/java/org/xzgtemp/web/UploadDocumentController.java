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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.xzgtemp.entity.Document;
import org.xzgtemp.entity.User;
import org.xzgtemp.service.DocumentService;

@RequestMapping("/uploaddocument")
@Controller
public class UploadDocumentController {
    
	public static final String KEY_USER = "__user__";

	@Autowired
	DocumentService documentservice;

	@GetMapping("")
	public ModelAndView uploadDocument(HttpSession session){
		Map<String, Object> model = new HashMap<>();
		if(session.getAttribute(KEY_USER) ==null){
			return new ModelAndView("redirect:/signin.html");
		}
		else{
			return new ModelAndView("uploaddocument.html",model);
		}
	}

	@PostMapping("")
	public ModelAndView uploadDocument(
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
            return new ModelAndView("redirect:/uploaddocument/success/" + document.getId());
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> model = new HashMap<>();
			model.put("error","Failed uploaded.Please upload again.");
			return new ModelAndView("uploaddocument.html",model);
		}
	}

    @GetMapping("/success/{did}")
    public ModelAndView uploadSuccessful(
        @PathVariable("did") Long did,
		HttpSession session
    )
    {
        Document document = documentservice.GetDocumentbyDID(did);
        Map<String, Object> model = new HashMap<>();
        model.put("document",document);
        return new ModelAndView("uploadsuccess.html",model);
    }


   
}
