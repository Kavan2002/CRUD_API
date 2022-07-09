package com.rest.dao;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.rest.model.*;

@Repository
public class RegDAOImpl implements RegDAO {

	@Autowired
	SessionFactory sessionfactory;
	
	public void insert(RegVO regvo) {
		
		Session session = this.sessionfactory.getCurrentSession();
		session.saveOrUpdate(regvo);
		
	}

	public List search() {
		
		Session session = this.sessionfactory.getCurrentSession();
		Query q=session.createQuery("from RegVO");
		
		List ls=q.list();
		return ls;
	}

	public void delete(RegVO regvo) {
		
		Session session = this.sessionfactory.getCurrentSession();
		session.delete(regvo);
		
	}

	public List searchById(RegVO regvo) {
		
		Session session = this.sessionfactory.getCurrentSession();
		Query q=session.createQuery("from RegVO where id="+regvo.getId());
		
		List ls=q.list();
		return ls;
		
		//here session and transaction is managed by spring boot 
		//so no need to close or commit session/transaction
	}

	
}
