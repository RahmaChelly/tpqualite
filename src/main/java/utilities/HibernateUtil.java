package utilities;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static { // L'initialisation statique guarantie que la factory est un SINGLETON

		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			System.err.println("ATTENTION, il y a eu une exception lors de la creation de la factory : " + e);
			throw new ExceptionInInitializerError(e);
		}
	}

    
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
