package main.java.table;

import javax.persistence.*;

/**
 * Created by Artsiom Tratsiuk on 31.03.2015.
 */

@Entity
@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "log_id", unique = true, nullable = false)
    private int id;

    @Column(name = "elapsed_time", nullable = true)
    private int time;

    @Column(name = "comment", length = 1000, nullable = false)
    private String comment;

    @Column(name = "task_id", nullable = false)
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
