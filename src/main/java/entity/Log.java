package main.java.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Artsiom Tratsiuk on 31.03.2015.
 */

//@Entity
//@Table(name = "log")
public class Log {

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "log_id", unique = true, nullable = false)
    private IntegerProperty id;

    //@Column(name = "elapsed_time", nullable = true)
    private IntegerProperty time;

    //@Column(length = 1000, nullable = false)
    private StringProperty comment;

    //@ManyToOne(fetch=FetchType.EAGER)
    //@JoinColumn(name = "task_id")
    private Task task;

    public Log() {
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty logIdProperty(){
        return id;
    }

    public void setTime(int time) {
        this.time.set(time);
    }

    public int getTime() {
        return time.get();
    }

    public IntegerProperty timeProperty(){
        return time;
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public String getComment() {
        return comment.get();
    }

    public StringProperty commentProperty(){
        return comment;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
