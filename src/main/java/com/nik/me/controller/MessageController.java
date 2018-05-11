package com.nik.me.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nik.me.dao.AdvertDAO;
import com.nik.me.dao.CategoryDAO;
import com.nik.me.dao.MessageDAO;
import com.nik.me.dao.UserDAO;
import com.nik.me.exception.AdvertException;
import com.nik.me.exception.CategoryException;
import com.nik.me.exception.MessageException;
import com.nik.me.pojo.Advert;
import com.nik.me.pojo.Category;
import com.nik.me.pojo.Message;
import com.nik.me.pojo.User;
import com.nik.me.validator.CategoryValidator;
import com.nik.me.validator.MessageValidator;


//Controller to Send Messages (Extension of Lab 8)


@Controller
@RequestMapping("/message/*")
public class MessageController {
	
	
	@Autowired
	@Qualifier("advertDao")
	AdvertDAO advertDao;
	
	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDAO;
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	
	@Autowired
	@Qualifier("messageValidator")
	MessageValidator messageValidator;
	
	@Autowired
	@Qualifier("messageDao")
	MessageDAO messageDAO;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(messageValidator);
	}
	
	public MessageController(){
	
	}
	
	@RequestMapping(value = "/message/add", method = RequestMethod.POST)
	public ModelAndView addCategory(@ModelAttribute("message") Message advert, BindingResult result) throws Exception {

		try {			
			

			advert = messageDAO.create(advert);
			
			return new ModelAndView("message-sent", "advert", advert);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
		
	}
	
	@RequestMapping(value = "/message/reply", method = RequestMethod.POST)
	public ModelAndView addCategory1(@ModelAttribute("message") Message advert, BindingResult result) throws Exception {

		try {			
			

			advert = messageDAO.create(advert);
			
			return new ModelAndView("user-replied", "advert", advert);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
		
	}
	
	
	@RequestMapping(value = "/message/adminadd", method = RequestMethod.POST)
	public ModelAndView adminmsg(@ModelAttribute("message") Message advert, BindingResult result) throws Exception {

		try {			
			

			advert = messageDAO.create(advert);
			
			return new ModelAndView("flag-reported", "advert", advert);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
		
	}
	
	@RequestMapping(value = "/message/adminlist", method = RequestMethod.GET)
	public ModelAndView adminm(HttpServletRequest request) throws Exception {

		try {			
			
			List<Message> adverts = messageDAO.list();
			return new ModelAndView("admin-msglist", "adverts", adverts);
			
		} catch (MessageException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
		
	}
	
	
	@RequestMapping(value = "/message/list", method = RequestMethod.GET)
	public ModelAndView addCategory(HttpServletRequest request) throws Exception {

		try {			
			
			List<Message> adverts = messageDAO.list();
			return new ModelAndView("message-list", "adverts", adverts);
			
		} catch (MessageException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
		
	}

	@RequestMapping(value="/message/add", method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request) throws Exception {		
		ModelAndView mv = new ModelAndView();
//		mv.addObject("categories", categoryDAO.list());			
		mv.addObject("message", new Message());
		mv.setViewName("send-message");
		return mv;
	}
	
	@RequestMapping(value="/message/reply", method = RequestMethod.GET)
	public ModelAndView initializeForm1(HttpServletRequest request) throws Exception {		
		ModelAndView mv = new ModelAndView();
//		mv.addObject("categories", categoryDAO.list());			
		mv.addObject("message", new Message());
		mv.setViewName("user-reply");
		return mv;
	}
	
	@RequestMapping(value="/message/adminadd", method = RequestMethod.GET)
	public ModelAndView initializeForm2(HttpServletRequest request) throws Exception {		
		ModelAndView mv = new ModelAndView();
//		mv.addObject("categories", categoryDAO.list());			
		mv.addObject("message", new Message());
		mv.setViewName("report-flag");
		return mv;
	}

	

}
