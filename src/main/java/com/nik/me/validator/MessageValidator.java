package com.nik.me.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nik.me.dao.CategoryDAO;
import com.nik.me.dao.MessageDAO;
import com.nik.me.exception.CategoryException;
import com.nik.me.exception.MessageException;
import com.nik.me.pojo.Advert;
import com.nik.me.pojo.Category;
import com.nik.me.pojo.Message;

public class MessageValidator implements Validator {
	
	
	public boolean supports(Class aClass) {
		return aClass.equals(Message.class);
	}

	public void validate(Object obj, Errors errors) {
		Message newAdvert = (Message) obj;

		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
		// "error.invalid.category", "Category Required");
	}
	
		
}
	
	


