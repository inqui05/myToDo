package main.java.DAO;

import main.java.entity.Person;
import main.java.entity.Task;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artsiom Tratsiuk
 */

public interface TaskDAO {

    void addTask(Task task) throws SQLException;
    void deleteTask(Task task) throws SQLException;
    void updateTask(Task task) throws SQLException;
    List<Task> getTasksByPerson(Person person) throws SQLException;

}
