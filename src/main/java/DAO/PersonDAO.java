package main.java.DAO;

import main.java.entity.Person;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artsiom Tratsiuk
 */

public interface PersonDAO {

    void addPerson(Person person) throws SQLException;
    void updatePerson(Person person) throws SQLException;
    void deletePerson(Person person) throws SQLException;
    Person getPersonById(int id) throws SQLException;
    List<Person> getAllPeople() throws SQLException;

}
