package main.java.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.StringProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Artsiom Tratsiuk on 31.03.2015.
 */

//@Entity
//@Table(name = "task")
public class Task {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "task_id", unique = true, nullable = false)
    private IntegerProperty id;

    //@Column(length = 1000, nullable = false)
    private StringProperty name;

    //@Column
    private StringProperty status;

    //@ManyToOne(fetch=FetchType.EAGER)
    //@JoinColumn(name = "person_id")
    private Person person;

    //@OneToMany(fetch=FetchType.EAGER, mappedBy = "task")
    private Set<Log> logs = new HashSet<Log>();

    public Task() {
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty taskIdProperty(){
        return id;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty(){
        return name;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty(){
        return status;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

    public Person getPerson() {
        return person;
    }

    public Set<Log> getLogs() {
        return logs;
    }
}
