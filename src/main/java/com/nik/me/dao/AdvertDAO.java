package com.nik.me.dao;

import java.util.HashSet;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.nik.me.exception.AdvertException;
import com.nik.me.pojo.Advert;
import com.nik.me.pojo.Category;

public class AdvertDAO extends DAO {

	public Advert create(Advert advert) throws AdvertException {
		try {
			begin();
			getSession().save(advert);
			commit();
			close();
			return advert;

		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create advert", e);
			throw new AdvertException("Exception while creating advert: " + e.getMessage());
		}
	}

	public void delete(Advert advert) throws AdvertException {
		try {
			begin();
			getSession().delete(advert);
			commit();
			close();
		} catch (HibernateException e) {
			rollback();
			throw new AdvertException("Could not delete advert", e);
		}
	}

	public void delete(long id) throws AdvertException {
		Advert advert = new Advert();
		advert.setId(id);
		try {
			begin();

			String abc = "Delete from category_advert_table where advertID=" + advert.getId();
			Query query = getSession().createSQLQuery(abc.trim());
			query.executeUpdate();

			commit();
			close();

			begin();
			String def = "Delete from advert_table where advertID=" + advert.getId();
            Query query1 = getSession().createSQLQuery(def.trim());

			query1.executeUpdate();

			// query.setLong("id",id);
			// query1.setLong("id",id);

			commit();
			close();

		} catch (HibernateException e) {
			rollback();
			throw new AdvertException("Could not delete advert", e);
		}
	}

	public List<Advert> list() throws AdvertException {

		try {
			begin();
			Query q = getSession().createQuery("from Advert");
			List<Advert> adverts = q.list();
			commit();
			return adverts;
		} catch (HibernateException e) {
			rollback();
			throw new AdvertException("Could not load ITEM list", e);
		}

	}
}