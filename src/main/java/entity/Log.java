package main.java.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Artsiom Tratsiuk
 */

public class Log {

    private IntegerProperty id;

    private IntegerProperty time;

    private StringProperty comment;

    private Task task;

    public Log() {
        this(0, 0, null);
    }

    public Log(int id, int time, String comment) {
        this.id = new SimpleIntegerProperty(id);
        this.time = new SimpleIntegerProperty(time);
        this.comment = new SimpleStringProperty(comment);
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
