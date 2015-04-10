package main.java.general;

import main.java.DAO.LogDAO;
import main.java.DAO.PersonDAO;
import main.java.DAO.TaskDAO;
import main.java.entity.Log;
import main.java.entity.Person;
import main.java.entity.Task;
import main.java.util.HibernateUtil;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
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
        person.setFirstName("Masha");
        person.setMiddleName("Zvezdeevna");
        person.setSecondName("Zhopko");
        person.setLogin("zasada");
        person.setPassword("rolf");
        Set<Task> tasks = new HashSet<Task>();

        Log log1 =  new Log();
        log1.setComment("SLEEP HARD PLAY HARD");
        log1.setTime(55);
        Set<Log> logs = new HashSet<Log>();
        logs.add(log1);

        Task task1 = new Task();
        task1.setName("DECRET");
        task1.setPerson(person);
        task1.setStatus("ВЫПОЛНЕНО");
        task1.setLogs(logs);

        log1.setTask(task1);
        tasks.add(task1);
        //person.setTasks(tasks);

        personDAO.addPerson(person);
        taskDAO.addTask(task1);
        logDAO.addLog(log1);

        /*List<Person> list = personDAO.getAllPeople();

        list.forEach((Person person) -> System.out.println(person.getFirstName() + " " + person.getMiddleName() + " " + person.getSecondName() + " " + person.getLogin() + " " + person.getPassword()));

        HibernateUtil.shutdown();*/
    }
}
