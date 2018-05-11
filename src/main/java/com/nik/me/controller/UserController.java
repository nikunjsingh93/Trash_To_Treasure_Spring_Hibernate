package com.nik.me.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.captcha.botdetect.web.servlet.Captcha;
import com.nik.me.dao.AdvertDAO;
import com.nik.me.exception.AdvertException;
import com.nik.me.pojo.Advert;
import com.nik.me.dao.UserDAO;
import com.nik.me.exception.UserException;
import com.nik.me.pojo.User;
import com.nik.me.validator.UserValidator;

//Controller to add the Tag (Extension of Lab 8 and lab 10)

@Controller
@RequestMapping("/user/*")
public class UserController {
	
	@Autowired
	@Qualifier("advertDao")
	AdvertDAO advertDao;
	
		
	public UserController(){
	
	}
	

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected ModelAndView goToUserHome(HttpServletRequest request ) throws Exception {
		
		List<Advert> adverts = advertDao.list();
		HttpSession session = (HttpSession) request.getSession();
		

		return new ModelAndView("user-home", "adverts", adverts);
		
	}
	




       @RequestMapping(value = "/user/logout")
        public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("logout()");
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        
//        Cookie ck=new Cookie("name","");//deleting value of cookie  
//        ck.setMaxAge(0);//changing the maximum age to 0 seconds  
//        response.addCookie(ck);//adding cookie in the response  
        
        return new ModelAndView("index", "adverts", null);	
    }


	
	
	
	@RequestMapping(value = "/user/detail", method = RequestMethod.GET)
	protected ModelAndView UserDetail(HttpServletRequest request) throws Exception {
		
		
		try {

						
				List<Advert> adverts = advertDao.list();
				return new ModelAndView("item-detail", "adverts", adverts);			
				
		

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error in detail");

		}

	}
	
	
	
	@RequestMapping(value = "/user/detail.htm", method = RequestMethod.GET)
	protected ModelAndView UserDetail1(HttpServletRequest request) throws Exception {
		
		
		try {

								
				List<Advert> adverts = advertDao.list();
				return new ModelAndView("item-detail", "adverts", adverts);			
				
		


		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error in detail");

		}

	}
	

	
	
	@RequestMapping(value = "/user/login.htm", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView ajaxService(HttpServletRequest request) throws AdvertException
	{
		
		List<Advert> adverts = advertDao.list();
		
		List<Advert> advertFiltered = new ArrayList();

		String queryString = request.getParameter("course");
		String result = "";
		
		for (Advert temp : adverts) {
			
			if(temp.getTitle().toLowerCase().contains(queryString.toLowerCase())){
				
				advertFiltered.add(temp);
			}
			
		}
		
		return new ModelAndView("list-items", "adverts", advertFiltered);
		
	}
	

	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
//	@ResponseBody
	protected ModelAndView loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
	
		HttpSession session = (HttpSession) request.getSession();
		
		try {

			  
        	
			System.out.print("loginUser");
			

			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));
			
			
			
			List<Advert> adverts = advertDao.list();
			
			
//              if (request.getParameter("username").equalsIgnoreCase("admin") && request.getParameter("password").equalsIgnoreCase("admin")) {
//				
//            	
//            	  
//				return new ModelAndView("admin-home", "adverts", adverts);	
//				
//				
//			}
			
			if(u == null){
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return new ModelAndView("error", "errorMessage", "error while login");

//				request.setAttribute("errorMessage",  "error while login");
//				return "redirect:/user/error.htm";
			}
			
			session.setAttribute("user", u);
			
			
			
			Cookie userCookie = new Cookie("name", u.getUsername());
			userCookie.setMaxAge(60*60*24*365); //Store cookie for 1 year
			response.addCookie(userCookie);
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			session.setAttribute("username",username );
			session.setAttribute("password",password );
			
				
			return new ModelAndView("user-home", "adverts", adverts);			

//			return "redirect:/user/userhome.htm";
		

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return new ModelAndView("error", "errorMessage", "error while login");
			
//			request.setAttribute("errorMessage",  "error while login");
//			return "redirect:/error.htm";
		}

	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("register-user", "user", new User());

	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {

		validator.validate(user, result);

		if (result.hasErrors()) {
			return new ModelAndView("register-user", "user", user);
		}
		
		
		
		Captcha captcha = Captcha.load(request, "CaptchaObject");
		String captchaCode = request.getParameter("captchaCode");
		HttpSession session = request.getSession();
		if (captcha.validate(captchaCode)) {
			
			
			try {

				System.out.print("registerNewUser");
				
				for(User u: userDao.list()) {
					
					
					if(user.getUsername().equalsIgnoreCase(u.getUsername())) {
						
						
						
						return new ModelAndView("same-user", "user", u);
						
						
					}

					
					
				}
				

				User u = userDao.register(user);
				
				request.getSession().setAttribute("user", u);
				
				return new ModelAndView("user-registered", "user", u);

			} catch (UserException e) {
				System.out.println("Exception: " + e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
			}
			
			
			
		} else {
			session.setAttribute("errorMessage", "Invalid Captcha!");
			return new ModelAndView("error", "errorMessage", "Invalid Captcha!");
		}
		
		
		
		
	}

}
