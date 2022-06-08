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
		 sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		 session  =sessionFactory.openSession();

	@Override
	public boolean create(Formation o) {
	
		session.beginTransaction();
		
		session.save(o)
		
		session.getTransaction().commit();
		return true;
	}

	@Override
	public List<Formation> getAll() {
		return Criteria c = session.createCriteria(Formation.class).list();
	}

	@Override
	public boolean delete(int id) {
		
		session.beginTransaction();

		session.delete(this.getById(id););

		
		session.getTransaction().commit();
		return true;//si pas d erreur on va avoir true
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
		return c.add(Restrictions.eq("l",l.getId())).list();
	}

}
