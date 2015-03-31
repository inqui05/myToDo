package main.java.DAO;

import main.java.table.Person;
import main.java.table.Task;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artsiom Tratsiuk on 31.03.2015.
 */

public interface PersonDAO {

    void addPerson(Person person) throws SQLException;
    void updatePerson(Person person) throws SQLException;
    void deletePerson(Person person) throws SQLException;
    Person getPersonById(int id) throws SQLException;
    List<Person> getAllPeople() throws SQLException;
    Person getPersonByTask(Task task) throws SQLException;

}
