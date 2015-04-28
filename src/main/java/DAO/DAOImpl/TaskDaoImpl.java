package main.java.DAO.DAOImpl;

import main.java.DAO.TaskDAO;
import main.java.entity.Person;
import main.java.entity.Task;
import main.java.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artsiom Tratsiuk
 */

public class TaskDaoImpl implements TaskDAO{

    @Override
    public void addTask(Task task) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public void deleteTask(Task task) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(task);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public void updateTask(Task task) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(task);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public List<Task> getTasksByPerson(Person person) throws SQLException {
        List<Task> tasks = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hqlSelect = "FROM Task WHERE PERSON_ID = :id";
            tasks = session.createQuery(hqlSelect).setParameter("id", person.getId()).list();
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) session.close();
        }
        return tasks;
    }

}
