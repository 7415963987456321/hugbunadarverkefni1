package is.hi.hbv501g.team20.taeknilaesi.model;

import java.util.Date;

import javax.persistence.*;
import is.hi.hbv501g.team20.taeknilaesi.model.User;

@Entity
@Table
public class Comment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // @ManyToOne
    // @JoinColumn(name = "lessonId")
    private int lessonId;

    @Column
    private String message;

    @Column
    private Date date;

    //@ManyToOne
    // @JoinColumn(name = "user_id")
    private String userName;

    public Comment(){

    }


    public Comment(int lessonId, String message, String userName) {
        // this.lessonId = lessonId;
        this.lessonId = lessonId;
        this.message = message;
        this.date = new Date();
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


	@Override
	public String toString() {
		return "Comment [date=" + date + ", id=" + id + ", lesson=" + lessonId + ", message=" + message + ", user=" + userName
				+ "]";
	}


	public int getLessonId() {
		return lessonId;
	}


	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}



}
