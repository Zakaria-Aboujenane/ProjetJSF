package service;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import model.Formation;
import model.Lieu;

public class FormationService implements IDAO<Formation>{
	private SessionFactory sessionFactory;
	private Session session;  
	
	public FormationService() {
		 sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		 session  =sessionFactory.openSession();
		
	}

	@Override
	public boolean create(Formation o) {
		boolean added = false;
		session.beginTransaction();
		
		if(session.save(o) != null) {
			added=true;
		}
		
		session.getTransaction().commit();
		return added;
	}

	@Override
	public List<Formation> getAll() {
		Criteria c = session.createCriteria(Formation.class);
		return c.list();
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		session.beginTransaction();
		
		Formation f= this.getById(id);
		session.delete(f);
		deleted=true;
		
		session.getTransaction().commit();
		return deleted;
	}

	@Override
	public Formation getById(int id) {
		session.beginTransaction();
		
		Formation f =session.find(Formation.class, id);
		
		session.getTransaction().commit();
		
		return f;
	}
	
	public List<Formation> getFormationsByLieu(Lieu l){
		Criteria c = session.createCriteria(Lieu.class);
		return c.add(Restrictions.eq("id_lieu",l.getId())).list();
	}

}
