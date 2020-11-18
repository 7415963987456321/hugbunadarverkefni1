package is.hi.hbv501g.team20.taeknilaesi.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name="USERS")
public class User
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "það vantar nafn.")
    private String name;

    private int year;

    @Email(message = "það vantar tölvupóst")
    private String email;

    @NotEmpty(message = "það vantar lykilorð")
    private String password;

    //þarf að breyta þegar application.properties er sett á 'update'
    // @NotEmpty(message = "það vantar staðfestingu af lykilorðinu")
    @Transient
    private String passwordConfirmation;

    @OneToMany(mappedBy = "user", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Progress> progress = new HashSet<>();


    public User(){

    }
    public User(String name,int year, String email, String password){
        this.name = name;
        this.year = year;
        this.email = email;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }
    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public Set<Progress> getProgress() { return progress; }
    public void setProgress(Set<Progress> progress) { this.progress = progress; }
}
