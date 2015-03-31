package main.java.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Artsiom Tratsiuk on 31.03.2015.
 */

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public HibernateUtil() {
    }

    static {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable e){
            throw new ExceptionInInitializerError(e);
        }


    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
