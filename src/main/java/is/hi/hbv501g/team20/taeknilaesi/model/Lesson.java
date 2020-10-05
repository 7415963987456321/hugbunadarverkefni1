package is.hi.hbv501g.team20.taeknilaesi.model;

import javax.persistence.*;

import lombok.Data;

import static is.hi.hbv501g.team20.taeknilaesi.constants.ApplicationConstants.*;

@Entity
@Table
public class Lesson {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // @ManyToOne
    // @JoinColumn(name="course_id", nullable=false)
    // private Course course;
    // @Column
    // private int course_id;

    // @ManyToOne 
    // @JoinColumn(name="course_id") 
    @Column
    private int courseId;

    @Column
    private String title;

    @Column
    private String text;

    @Column
    private String filename;

     public Lesson(){
     }

    public Lesson(int courseId, String title, String text, String filename){
        this.courseId = courseId;
        this.title = title;
        this.text = text;
        this.filename = VIDEO_STORE + filename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
