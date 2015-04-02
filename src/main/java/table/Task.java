package main.java.table;

import javax.persistence.*;

/**
 * Created by Artsiom Tratsiuk on 31.03.2015.
 */

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", length = 1000, nullable = false)
    private String name;

    @Column(name = "status", length = 100, nullable = false)
    private String status;

    @Column(name = "person_id", nullable = false)
    private int personId;

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

    public void setPersonId(int personId) {
        this.personId = personId;
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

    public int getPersonId() {
        return personId;
    }
}
