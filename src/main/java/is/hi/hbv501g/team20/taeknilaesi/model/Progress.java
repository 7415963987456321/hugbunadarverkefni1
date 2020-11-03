package is.hi.hbv501g.team20.taeknilaesi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Progress")
@Table
public class Progress {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="lesson_id")
    private Lesson lesson;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="quiz_id")
    private Quiz quiz;

    public Progress() {
    }

    public Progress(Lesson lesson, User user) {
        this.lesson = lesson;
        this.user = user;
    }

    public Progress(Quiz quiz, User user) {
        this.quiz = quiz;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
