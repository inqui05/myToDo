package main.java.entity;

import javafx.beans.property.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Artsiom Tratsiuk
 */

public class Task {

    private IntegerProperty id;

    private StringProperty name;

    private StringProperty status;

    private Person person;

    private Set<Log> logs = new HashSet<Log>();

    public Task() {
        this(0, null, null);
    }

    public Task(int id, String name, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.status = new SimpleStringProperty(status);
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
