package is.hi.hbv501g.team20.taeknilaesi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table
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
	@Lob
	private String description;

	@OneToOne(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Quiz quiz;



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

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public boolean isCourseStarted(List<Progress> progress){
		List<Integer> ids = new ArrayList<>();
		for (Lesson x : this.lessons){
			ids.add(x.getId());
		}

		for(Progress p : progress){
			if(p.getLesson()!=null) {
				if (ids.contains(p.getLesson().getId()))
					return true;
			}
		}
		return false;
	}

	public boolean isCourseFinished(List<Progress> progress){
		if(progress.isEmpty()){
			return false;
		}
		List<Integer> pids = new ArrayList<>();
		for (Progress p : progress) {
			if(p.getLesson()!=null)
				pids.add(p.getLesson().getId());
		}

		for (Lesson x : this.lessons){
			if(!pids.contains(x.getId())){
				return false;
			}
		}
		return true;
	}
}
