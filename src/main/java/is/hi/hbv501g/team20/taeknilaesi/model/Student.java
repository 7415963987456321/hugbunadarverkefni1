package is.hi.hbv501g.team20.taeknilaesi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
@Entity
@Table
@Data
public class Student
{
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String name;
    @Column
    private int year;
    @Column
    private String email;

    public Student(){
        
    }
    public Student(String name,int year, String email){
        this.name = name;
        this.year = year;
        this.email = email;
    }
    // public long getId() {
    //     return id;
    // }
    // public String getEmail() {
    //     return email;
    // }
    // public void setEmail(String email) {
    //     this.email = email;
    // }
    // public String getName() {
    //     return name;
    // }
    // public void setName(String name) {
    //     this.name = name;
    // }
    // public int getYear() {
    //     return year;
    // }
    // public void setYear(int year) {
    //     this.year = year;
    // }
}
