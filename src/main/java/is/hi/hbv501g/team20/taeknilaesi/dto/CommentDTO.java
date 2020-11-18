package is.hi.hbv501g.team20.taeknilaesi.dto;

import java.util.Date;

public class CommentDTO {

    private int lessonId;

    private String message;

    private Date date;

    private int userId;

    private String userName;

    public CommentDTO() {

    }

    public CommentDTO(int lessonId, String message, String userName){
        this.lessonId = lessonId;
        this.message = message;
        this.date = new Date();
        this.userName = userName;
    }

	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
