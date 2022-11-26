package org.xzgtemp.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.xzgtemp.entity.Document;
import org.xzgtemp.entity.User;
import org.xzgtemp.service.DocumentService;
import org.xzgtemp.service.UserService;

@RequestMapping("/documents/{did}")
@Controller
public class DocumentController {
    

	@Autowired
    UserService userservice;

	@Autowired
    DocumentService documentservice;

	public static final String KEY_USER = "__user__";

	@GetMapping("")
	public ModelAndView showDocument(@PathVariable("did") Long did,HttpSession session) {
		if (session.getAttribute(KEY_USER) ==null){
			return new ModelAndView("redirect:/signin.html");
		}
		Map<String, Object> model = new HashMap<>();
        model.put("user",(User)session.getAttribute(KEY_USER));
		Document document = documentservice.GetDocumentbyDID(did);
        model.put("document",document);
		model.put("uploader",userservice.GetUserbyID(document.getUploaderid()));
		return new ModelAndView("documentdetial.html",model);
	}
    
	@GetMapping("/change")
	public ModelAndView changeDocumentInfo(
		@PathVariable("did") Long did,
		HttpSession session
	) {
		if (session.getAttribute(KEY_USER) ==null){
			return new ModelAndView("redirect:/signin.html");
		}
		Map<String, Object> model = new HashMap<>();
        model.put("user",(User)session.getAttribute(KEY_USER));
		Document document = documentservice.GetDocumentbyDID(did);
        model.put("document",document);
		model.put("uploader",userservice.GetUserbyID(document.getUploaderid()));
		model.put("change","change");
		return new ModelAndView("documentdetial.html",model);
	}

	@PostMapping("/change/{attribute}")
	public ModelAndView changeDocumentInfo(
		@PathVariable("did") Long did,
		@PathVariable("attribute") String attribute,
		@RequestParam("value") Object value,
		HttpSession session
	) {
		try{

			if (session.getAttribute(KEY_USER) ==null){
				return new ModelAndView("redirect:/signin.html");
			}
			Document document = documentservice.GetDocumentbyDID(did);
			documentservice.ChangeDocumentAttribute(document, attribute, value);
			return new ModelAndView("redirect:/documents/" + did + "/change");
		}
		catch (Exception e){
			e.printStackTrace();
			return new ModelAndView("redirect:/error/unknown");
		}
	}

	@RequestMapping("/download")
	public ResponseEntity<byte[]> DownloadDocument(
		@PathVariable("did") Long did,
		HttpSession session
	) throws IOException{
		Document document = documentservice.GetDocumentbyDID(did);
		String filepath= documentservice.DocumentFileRootPath + document.getFilepath();
		File file =new File(filepath);
		InputStream in = new FileInputStream(file);
		byte[] body = new byte[in.available()];
		in.read(body);
		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.add("Content-Disposition", "attchement;filename=" + file.getName());
		HttpStatus statusCode = HttpStatus.OK;
		in.close();
		ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
		return entity;
	}


}
