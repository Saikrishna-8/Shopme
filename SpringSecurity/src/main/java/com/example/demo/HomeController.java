package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home() {
		return "Home.jsp";
	}
	
	
	@RequestMapping("/home")
	public String homeprivate() {
		return "HomePrivate.jsp";
	}


	@RequestMapping("/loginurl")
	public String logpage() {
		return "login.jsp";
	}
	

	@RequestMapping("/logouturl")
	public String outpage(HttpServletRequest request,HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
		// return "logout.jsp";
		//return "redirect:/";
	}
	
	
	@RequestMapping("/first")
	public String fpage() {
		return "first.jsp";
	}
	
	@RequestMapping("/second")
	public String spage() {
		return "second.jsp";
	}

}
