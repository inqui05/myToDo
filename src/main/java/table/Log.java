package main.java.table;

import javax.persistence.*;

/**
 * Created by Artsiom Tratsiuk on 31.03.2015.
 */

@Entity
@Table(name = "log")
public class Log {

    @Id
    @Column(name = "log id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "elapsed time")
    private int time;

    @Column(name = "comment")
    private String comment;

    @Column(name = "task id")
    private int taskId;

    public Log() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getId() {
        return id;
    }

    public int getTime() {
        return time;
    }

    public String getComment() {
        return comment;
    }

    public int getTaskId() {
        return taskId;
    }
}
