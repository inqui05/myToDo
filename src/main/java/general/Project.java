package main.java.general;

import main.java.DAO.LogDAO;
import main.java.DAO.PersonDAO;
import main.java.DAO.TaskDAO;
import main.java.entity.Person;
import main.java.util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artsiom Tratsiuk on 01.04.2015.
 */

public class Project {

    public static void main(String[] args) throws SQLException {



        Factory factory = Factory.getInstance();
        PersonDAO personDAO = factory.getPersonDAO();
        TaskDAO taskDAO = factory.getTaskDAO();
        LogDAO logDAO = factory.getLogDAO();

        //Person person = new Person();
        /*person.setFirstName("Nadia");
        person.setMiddleName("Nikolaevna");
        person.setSecondName("Tratsiuk");
        person.setLogin("loshara");
        person.setPassword("lol");
        Set<Task> tasks = new HashSet<Task>();*/

        //Log log1 =  new Log();
        /*log1.setComment("SLEEP HARD PLAY HARD");
        log1.setTime(19999999);
        Set<Log> logs = new HashSet<Log>();
        logs.add(log1);*/

        //Task task1 = new Task();
        /*task1.setName("DECRET");
        task1.setPerson(person);
        task1.setLogs(logs);

        log1.setTask(task1);
        tasks.add(task1);
        person.setTasks(tasks);

        personDAO.addPerson(person);
        taskDAO.addTask(task1);
        logDAO.addLog(log1);*/

        //person.setSecondName("Popko");
        //person.setLogin("zvezdofeevna");
        //task1.setStatus("in process");

        List<Person> list = personDAO.getAllPeople();

        list.forEach((Person person) -> System.out.println(person.getFirstName() + " " + person.getMiddleName() + " " + person.getSecondName() + " " + person.getLogin() + " " + person.getPassword()));

        HibernateUtil.shutdown();
    }
}
