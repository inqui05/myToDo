package main.java.service;

import main.java.DAO.DAOImpl.TaskDaoImpl;
import main.java.DAO.TaskDAO;
import main.java.entity.Person;
import main.java.entity.Task;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artsiom Tratsiuk on 10.04.2015.
 */
public class TaskService {
    private TaskDAO taskDAO =  new TaskDaoImpl();

    void addTask(Task task) throws SQLException{
        taskDAO.addTask(task);
    }

    void deleteTask(Task task) throws SQLException{
        taskDAO.deleteTask(task);
    }

    void updateTask(Task task) throws SQLException{
        taskDAO.updateTask(task);
    }

    public List<Task> getTasksByPerson(Person person) throws SQLException{
        return taskDAO.getTasksByPerson(person);
    }

    List<Task> getAllTask() throws SQLException {
        return taskDAO.getAllTask();
    }
}
