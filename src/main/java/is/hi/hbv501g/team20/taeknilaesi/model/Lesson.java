package is.hi.hbv501g.team20.taeknilaesi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;



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
    @Lob
    private String text;

    @Column
    private String filename;

    @OneToMany
    private List<Comment> comments = new ArrayList<Comment>();

     public Lesson(){
     }

    public Lesson(int courseId, String title, String text, String filename, List<Comment> comments){
        this.courseId = courseId;
        this.title = title;
        this.text = text;
        this.filename = filename;
        this.comments = comments;
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

    public void addComments(List<Comment> comments){
        this.comments.addAll(comments);
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	// @Override
	// public String toString() {
	// 	return "Lesson [comments=" + comments + ", courseId=" + courseId + ", filename=" + filename + ", id=" + id
	// 			+ ", text=" + text + ", title=" + title + "]";
	// }

}
