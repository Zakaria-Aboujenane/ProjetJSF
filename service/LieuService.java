package service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import model.Formation;
import model.Lieu;

public class LieuService implements IDAO<Lieu> {
	private SessionFactory sessionFactory;
	private Session session;

	public LieuService() {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		session = sessionFactory.openSession();
	}

	@Override
	public boolean create(Lieu o) {
	
		session.beginTransaction();

		session.save(o);

		session.getTransaction().commit();
		return true;
	}

	@Override
	public List<Lieu> getAll() {
		return  session.createCriteria(Lieu.class).list();
	}

	@Override
	public boolean delete(int id) {
	
		session.beginTransaction();


		session.delete(this.getById(id));
	

		session.getTransaction().commit();
		return true;
	}

	@Override
	public Lieu getById(int id) {
		session.beginTransaction();

		Lieu l = session.find(Lieu.class, id);

		session.getTransaction().commit();

		return l;
	}
	
	public List<Lieu> getVillesByLieu(String nomVille) {
		Criteria c = session.createCriteria(Lieu.class);
		return c.add(Restrictions.eq("ville",nomVille)).list();
	}
	

}
