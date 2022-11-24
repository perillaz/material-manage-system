package org.xzgtemp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/error")
@Controller
public class ErrorController {

    @GetMapping("/")
	public ModelAndView Visit() {
		return new ModelAndView("unknownerror.html");
	}

}
