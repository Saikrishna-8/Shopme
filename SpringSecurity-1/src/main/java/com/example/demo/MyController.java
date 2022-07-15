package com.example.demo;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

	
	@RequestMapping("/")
	public String home1() {
		return "Home.jsp";
	}
	@RequestMapping("/first")
	public String fpage() {
		return "first.jsp";
	}

	@RequestMapping("/second")
	public String spage() {
		return "second.jsp";
	}
	
	
	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal principal)
	{
		return principal;
	}
}
