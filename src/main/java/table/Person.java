package main.java.table;

import javax.persistence.*;

/**
 * Created by Artsiom Tratsiuk on 31.03.2015.
 */

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "person id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first name")
    private String firstName;

    @Column(name = "middle name")
    private String middleName;

    @Column(name = "second name")
    private String secondName;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    public Person() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
