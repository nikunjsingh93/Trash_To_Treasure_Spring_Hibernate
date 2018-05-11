package com.nik.me.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.nik.me.exception.AdvertException;
import com.nik.me.exception.CategoryException;
import com.nik.me.exception.MessageException;
import com.nik.me.exception.UserException;
import com.nik.me.pojo.Advert;
import com.nik.me.pojo.Category;
import com.nik.me.pojo.Message;
import com.nik.me.pojo.User;

public class MessageDAO extends DAO {
	
	public MessageDAO() {
		
	}
	
		

    public Message create(Message advert)
            throws MessageException {
        try {
            begin();            
            getSession().save(advert);     
            commit();
            return advert;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create advert", e);
            throw new MessageException("Exception while creating advert: " + e.getMessage());
        }
    }

    public void delete(Message advert)
            throws MessageException {
        try {
            begin();
            getSession().delete(advert);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new MessageException("Could not delete advert", e);
        }
    }
    
    public List<Message> list() throws MessageException{
    	
    	try {
            begin();
            Query q = getSession().createQuery("from Message");
            List<Message> adverts = q.list();
            commit();
            return adverts;
        } catch (HibernateException e) {
            rollback();
            throw new MessageException("Could not load Message list ", e);
        }
    	
    }
	
	
	
	
}
