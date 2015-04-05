package main.java.general;

import main.java.DAO.LogDAO;
import main.java.DAO.PersonDAO;
import main.java.DAO.TaskDAO;
import main.java.table.Log;
import main.java.table.Person;
import main.java.table.Task;
import main.java.util.HibernateUtil;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Artsiom Tratsiuk on 01.04.2015.
 */

public class Project {

    public static void main(String[] args) throws SQLException {



        Factory factory = Factory.getInstance();
        PersonDAO personDAO = factory.getPersonDAO();
        TaskDAO taskDAO = factory.getTaskDAO();
        LogDAO logDAO = factory.getLogDAO();

        Person person = new Person();
        person.setFirstName("Artsiom");
        person.setMiddleName("Nikolaevish");
        person.setSecondName("Tratsiuk");
        person.setLogin("inqui05");
        person.setPassword("lol");
        //Set<Task> tasks = new HashSet<Task>();

        //Log log1 =  new Log();
        //log1.setComment("РАБОТАЙ, А НЕ ЗАНИМАЙСЯ КУЙНЕЙ");
        //log1.setTime(240);
        //Set<Log> logs = new HashSet<Log>();
        //logs.add(log1);

        //Task task1 = new Task();
        //task1.setName("KOSIAK");
        //task1.setPerson(person);
        //task1.setLogs(logs);


        //log1.setTask(task1);
        //tasks.add(task1);
        //person.setTasks(tasks);

        personDAO.addPerson(person);
        //taskDAO.addTask(task1);
        //logDAO.addLog(log1);
        HibernateUtil.shutdown();
    }
}
