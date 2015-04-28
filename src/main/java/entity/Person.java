package main.java.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Artsiom Tratsiuk
 */

public class Person {

    private IntegerProperty id;

    private StringProperty firstName;

    private StringProperty middleName;

    private StringProperty secondName;

    private StringProperty login;

    private StringProperty password;

    private Set<Task> tasks = new HashSet<Task>() {
    };

    public Person() {
        this(0, null,null, null, null, null);
    }

    public Person(int id, String firstName, String middleName, String secondName, String login, String password){
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.middleName = new SimpleStringProperty(middleName);
        this.secondName = new SimpleStringProperty(secondName);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty(){
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty(){
        return firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName.set(middleName);
    }

    public String getMiddleName() {
        return middleName.get();
    }

    public StringProperty middleNameProperty(){
        return middleName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public StringProperty secondNameProperty(){
        return secondName;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty(){
        return login;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty(){
        return password;
    }
    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Task> getTasks() {
        return tasks;
    }



}
