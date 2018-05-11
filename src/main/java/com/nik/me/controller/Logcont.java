package com.nik.me.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nik.me.dao.UserDAO;
import com.nik.me.pojo.Advert;
import com.nik.me.pojo.Email;
import com.nik.me.pojo.User;

// Inital run controller for index

@Controller
public class Logcont {

	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request, HttpServletResponse response ) throws Exception {
		
		User us = userDao.get("admin", "admin");
		
		if(us == null) {
			
			User u = new User();
			
			Email email = new Email("Admin@admin.com");
			
			u.setEmail(email);
			email.setUser(u);
			
			u.setUsername("admin");
			u.setFirstName("admin");
			u.setLastName("admin");
			u.setPassword("admin");
			
			userDao.register(u);
			
		}
		
		
	    Cookie ck[]=request.getCookies();  
	    for(Cookie c : ck){  
	    	
	    	for ( User u: userDao.list()) {
	    	 
	   if ( c.getValue() == u.getUsername() ) {
		   
		   HttpSession session = (HttpSession) request.getSession();
		   
		   session.getAttribute("username");
			session.getAttribute("password");
			
			
		   session.setAttribute("user", u);
		   
		   return "user-home";
		   
		     
	   }
	     
	     
	    }  
	    }
		
		return "index";
	}
	

	
}
