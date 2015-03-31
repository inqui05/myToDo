package main.java.DAO;

import main.java.table.Person;
import main.java.table.Task;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artsiom Tratsiuk on 31.03.2015.
 */

public interface TaskDAO {

    void addTask(Task task) throws SQLException;
    void deleteTask(Task task) throws SQLException;
    void updateTask(Task task) throws SQLException;
    List<Task> getTasksByPerson(Person person) throws SQLException;
    List<Task> getAllTask() throws SQLException;

}
