package com.nik.me.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.nik.me.dao.AdvertDAO;
import com.nik.me.dao.CategoryDAO;
import com.nik.me.dao.UserDAO;
import com.nik.me.exception.AdvertException;
import com.nik.me.pojo.Advert;
import com.nik.me.pojo.Category;
import com.nik.me.pojo.User;


// Controller for the item (Extension of lab 8 and Lab 10)

@Controller
@RequestMapping("/advert/*")
public class AdvertController {

	@Autowired
	@Qualifier("advertDao")
	AdvertDAO advertDao;

	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDAO;

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;


	
	//To add the item
	
	@RequestMapping(value = "/advert/add", method = RequestMethod.POST)
	public ModelAndView addCategory(@ModelAttribute("advert") Advert advert, BindingResult result,
			HttpServletRequest request) throws Exception {

		try {

			User u = userDao.get(advert.getPostedBy());
			advert.setUser(u);

			
			
			
			for (Advert a : advertDao.list()) {

				if (advert.getTitle().equalsIgnoreCase(a.getTitle())) {

					return new ModelAndView("same-item", "user", u);

				}

			}

			
			
			advert = advertDao.create(advert);

			System.out.println(advert.getData().getBytes());
			System.out.println(advert.getTitle());

			CommonsMultipartFile fileInMemory = advert.getData();

			String extension = FilenameUtils.getExtension(fileInMemory.getOriginalFilename());

			String id = Long.toString(advert.getId());

			File destFile = new File("C:\\images", id + "." + extension);

			fileInMemory.transferTo(destFile);

			for (Category c : advert.getCategories()) {
				c = categoryDAO.get(c.getTitle());
				c.getAdverts().add(advert);
				categoryDAO.update(c);
			}

			return new ModelAndView("advert-success", "advert", advert);

		} catch (AdvertException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}

	}

	//To delete the Item
	
	@RequestMapping(value = "/advert/delete{id}")
	@ResponseBody
	public ModelAndView helloWorld(@PathVariable long id) throws AdvertException {

		List<Advert> adverts = advertDao.list();

		for (Advert ad : adverts) {

			if (ad.getId() == id) {


				advertDao.delete(ad.getId());

				return new ModelAndView("admin-deleted", "advert", null);

			}

		}
		return null;

	}

	
	//To get item list
	
	@RequestMapping(value = "/advert/list", method = RequestMethod.GET)
	public ModelAndView addCategory(HttpServletRequest request) throws Exception {

		try {

			List<Advert> adverts = advertDao.list();

			return new ModelAndView("advert-list", "adverts", adverts);

		} catch (AdvertException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}

	}

	// Ton get the item
	
	@RequestMapping(value = "/advert/add", method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("advert", new Advert());
		mv.setViewName("advert-form");
		return mv;
	}

}