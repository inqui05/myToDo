package main.java.DAO.DAOImpl;

import main.java.DAO.LogDAO;
import main.java.entity.Log;
import main.java.entity.Task;
import main.java.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artsiom Tratsiuk
 */

public class LogDAOImpl implements LogDAO{

    @Override
    public void addLog(Log log) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(log);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public void updateLog(Log log) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(log);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public void deleteLog(Log log) throws SQLException {
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(log);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) session.close();
        }
    }

    @Override
    public List<Log> getLogByTask(Task task) throws SQLException {
        List<Log> logs = null;
        Session session = null;
        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hqlSelect = "FROM Log WHERE TASK_ID = :id";
            logs = session.createQuery(hqlSelect).setParameter("id", task.getId()).list();
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) session.close();
        }
        return logs;
    }

}
