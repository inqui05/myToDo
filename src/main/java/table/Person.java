package main.java.table;

import javax.persistence.*;

/**
 * Created by Artsiom Tratsiuk on 31.03.2015.
 */

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id", unique = true, nullable = false)
    private int id;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 100, nullable = false)
    private String middleName;

    @Column(name = "second_name", length = 100, nullable = false)
    private String secondName;

    @Column(name = "login", length = 30, nullable = true)
    private String login;

    @Column(name = "password", length = 30, nullable = true)
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
