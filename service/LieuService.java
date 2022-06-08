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
		boolean added = false;
		session.beginTransaction();

		if (session.save(o) != null) {
			added = true;
		}

		session.getTransaction().commit();
		return added;
	}

	@Override
	public List<Lieu> getAll() {
		Criteria c = session.createCriteria(Lieu.class);
		return c.list();
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = false;
		session.beginTransaction();

		Lieu l = this.getById(id);
		session.delete(l);
		deleted = true;

		session.getTransaction().commit();
		return deleted;
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
