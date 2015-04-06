package main.java.table;

import javax.persistence.*;
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
    private int id;

    //@Column(length = 1000, nullable = false)
    private String name;

    //@Column
    private String status;

    //@ManyToOne(fetch=FetchType.EAGER)
    //@JoinColumn(name = "person_id")
    private Person person;

    //@OneToMany(fetch=FetchType.EAGER, mappedBy = "task")
    private Set<Log> logs = new HashSet<Log>();

    public Task() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Person getPerson() {
        return person;
    }

    public Set<Log> getLogs() {
        return logs;
    }
}
