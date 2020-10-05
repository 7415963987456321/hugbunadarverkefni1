package is.hi.hbv501g.team20.taeknilaesi.model;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table
@Data
public class Course {
    @Id
    @Column
    private int id;

    @OneToMany
    // @JoinColumn(name="course_id")
    private List<Lesson> lessons = new ArrayList<Lesson>();

    @Column
    private String title;

    @Column
    private String description;

    public Course(){}

    public Course(int id,String title, String description, List<Lesson> lessons){
        this.id = id;
        this.title = title;
        this.description = description;
        this.lessons = lessons;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(List<Lesson> lessons) {
		this.lessons = lessons;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
