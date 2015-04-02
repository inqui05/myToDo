package main.java.general;

import main.java.DAO.DAOImpl.LogDAOImpl;
import main.java.DAO.DAOImpl.PersonDAOImpl;
import main.java.DAO.DAOImpl.TaskDaoImpl;
import main.java.DAO.LogDAO;
import main.java.DAO.PersonDAO;
import main.java.DAO.TaskDAO;

/**
 * Created by Artsiom Tratsiuk on 01.04.2015.
 */

public class Factory {

    public static Factory instance = new Factory();
    public PersonDAO personDAO;
    public LogDAO logDAO;
    public TaskDAO taskDAO;

    public Factory() {
    }

    public static synchronized Factory getInstance(){
        if (instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public PersonDAO getPersonDAO(){
        if(personDAO == null) personDAO = new PersonDAOImpl();
        return personDAO;
    }

    public TaskDAO getTaskDAO(){
        if (taskDAO == null) taskDAO = new TaskDaoImpl();
        return taskDAO;
    }

    public LogDAO getLogDAO(){
        if (logDAO == null) logDAO = new LogDAOImpl();
        return logDAO;
    }

}
