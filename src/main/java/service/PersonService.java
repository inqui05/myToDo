package main.java.service;

import main.java.DAO.DAOImpl.PersonDAOImpl;
import main.java.DAO.PersonDAO;
import main.java.entity.Person;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artsiom Tratsiuk on 09.04.2015.
 */
public class PersonService {
    private PersonDAO personDAO = new PersonDAOImpl();

    void addPerson(Person person) throws SQLException {
        personDAO.addPerson(person);
    }
    void updatePerson(Person person) throws SQLException {
        personDAO.updatePerson(person);
    }
    void deletePerson(Person person) throws SQLException {
        personDAO.deletePerson(person);
    }
    Person getPersonById(int id) throws SQLException {
        return personDAO.getPersonById(id);
    }

    List<Person> getAllPeople() throws SQLException {
        return personDAO.getAllPeople();
    }
}
