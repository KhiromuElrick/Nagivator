package pavelev.DB.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtilUpdate {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final String PATH_TO_CFG = "src/main/resources/hibernateUpdate.cfg.xml";

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure(new File(PATH_TO_CFG)).buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);

        }
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory==null)
            buildSessionFactory();
        return sessionFactory;
    }

    public static void close() {
        getSessionFactory().close();
    }
}
