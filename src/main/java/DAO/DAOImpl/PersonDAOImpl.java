package main.java.DAO.DAOImpl;

import main.java.DAO.PersonDAO;
import main.java.entity.Person;
import main.java.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artsiom Tratsiuk
 */

public class PersonDAOImpl implements PersonDAO {

    @Override
    public void addPerson(Person person) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public void updatePerson(Person person) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(person);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public final void deletePerson(Person person) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(person);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public Person getPersonById(int id) throws SQLException {
        Person result = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            result = (Person) session.load(Person.class, id);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) session.close();
        }
        return result;
    }

    @Override
    public List<Person> getAllPeople() throws SQLException {
        List<Person> people = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            people = session.createCriteria(Person.class).list();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) session.close();
        }
       return people;
    }

}
