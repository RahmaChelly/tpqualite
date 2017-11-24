package GenericRepository;

import java.util.List;
import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import entities.Client;
import entities.Reservation;
import entities.Chambre;
import utilities.HibernateUtil;


public class GenericRepositoryImplementation<T, PK extends Serializable> implements IGenericRepository<T, PK>
{
	
	private Class<T> persistentClass;
	
	public GenericRepositoryImplementation(Class<T> type) {
		this.persistentClass = type;
	}

	
	
	public  Session getSession() {

		Session s;
		try
		{
			s = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		catch(Exception e) {
			s = HibernateUtil.getSessionFactory().openSession();
		}
		
		return s;
		
	}
	
	
	public boolean create(T obj_in)
	{
		
		boolean a_retourner = false;
		Transaction transaction = null;
		Session s= getSession();
		try {
				transaction = s.beginTransaction();

				s.save(obj_in);
				transaction.commit();
				s.close();
				a_retourner = true;
			

		} catch (Exception e) {
			
			System.out.println("ATTENTION : il y a eu l'exception : " + e);
			if ((transaction != null) && transaction.isActive()) {
				transaction.rollback();
				s.close();
			}
		}

		return a_retourner;

	}
	
	public T findByID(PK id) {
		T entity = null;
		Transaction tx = null;
		Session s= getSession();
		try {
			tx = s.beginTransaction();
			entity = (T) getSession().get(persistentClass, id);
			tx.commit();
			s.close();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			s.close();
			e.printStackTrace();
		}
		return entity;
	}




	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		List<T> toExport = null;
		Transaction tx = null;
		Session s= getSession();
		try {
			tx = s.beginTransaction();
			Query q = s.createQuery("from " + persistentClass.getSimpleName());
			toExport = (List<T>) q.list();
			tx.commit();
			s.close();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			s.close();
			e.printStackTrace();
		}
		return toExport;
	}





	public void update(T object_in) {
		Transaction tx = null;
		Session s= getSession();
		try {
			tx = s.beginTransaction();
			s.update(object_in);
			tx.commit();
			s.close();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
				s.close();
				System.out.println("ali");
			e.printStackTrace();

		}
	}




	public void delete(PK id) {
		Transaction tx = null;
		try {
			Session s= getSession();
			tx = s.beginTransaction();
			T a_supprimer = (T) getSession().get(persistentClass, id);
			s.delete(a_supprimer);
			tx.commit();
			s.close();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
	}
	
	
	public boolean reserver(long clientId, long chambreId) {
		Transaction tx = null;
		try {
			Session s= getSession();
			tx = s.beginTransaction();
			Chambre chambre = s.get(Chambre.class,chambreId);
			Client client = s.get(Client.class,clientId);
			if(chambre.getEtat()=="reservee")
				return false;
			else
			{
				Reservation reservation = new Reservation(chambre.getChambreId());
				chambre.reserver();
				client.ajouterResevation(reservation);
				chambre.ajouterResevation(reservation);
				reservation.setClient(client);
				reservation.setChambre(chambre);
				s.save(reservation);
				s.update(client);s.update(chambre);
			}
				
			
			tx.commit();
			s.close();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
}
