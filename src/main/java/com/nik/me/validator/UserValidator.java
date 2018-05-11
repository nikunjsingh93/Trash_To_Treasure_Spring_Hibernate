package com.nik.me.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;

import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nik.me.dao.UserDAO;
import com.nik.me.exception.UserException;
import com.nik.me.pojo.Email;
import com.nik.me.pojo.User;

public class UserValidator implements Validator {
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	

	public boolean supports(Class aClass) {
		return aClass.equals(User.class);
	}

	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailAddress", "error.invalid.email.emailAddress",
				"Email Required");
		
//		try {
//			List<User> ls = userDao.list();
//		} catch (UserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		Email email = user.getEmail();
		
		if(!(email.getEmailAddress().contains("@husky.neu.edu"))) {
			
			errors.rejectValue("email.emailAddress", "error.invalid.email.emailAddress", "only HUSKY email allowed");
			
			
		}
		
		
		
		// check if user exists
		
	}
}
