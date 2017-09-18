package com.csw.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
	private static String CONFIG_FILE_lOCATION = "/hibernate.cfg.xml";
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static Configuration configuration = new Configuration();
	private static SessionFactory sessionFactory;
	private static String configFile = CONFIG_FILE_lOCATION;
	static {
		try {
			configuration.configure(configFile);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ªÒ»°session
	 * 
	 * @return
	 * @throws HibernateException
	 */
	public static Session getSession() throws HibernateException {
		Session session = (Session) threadLocal.get();
		if (session == null || !session.isOpen()) {
			session = (sessionFactory != null) ? sessionFactory.openSession()
					: null;
			threadLocal.set(session);
		}
		return session;
	}
}
