package com.sai;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	
	
	@RequestMapping("/")
	public ModelAndView home(HttpServletRequest httpServletRequest)
	{
		ModelAndView mav=new ModelAndView("home");
		String str=httpServletRequest.getRemoteUser();
		if(str!=null)
			mav.addObject("isLog", true);
		else
			mav.addObject("isLog", false);
		return mav;
		
		
	}
	
}
