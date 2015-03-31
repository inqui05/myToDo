package DAO;

import table.Person;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Artsiom Tratsiuk on 31.03.2015.
 */

public interface PersonDAO {

    public void addPerson(Person person) throws SQLException;
    public void updatePerson(Person person) throws SQLException;
    public void deletePerson(Person person) throws SQLException;
    public Person getPerson(int id) throws SQLException;
    public List<Person> getPeople() throws SQLException;

}
