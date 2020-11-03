package is.hi.hbv501g.team20.taeknilaesi.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="Quiz")
@Table
public class Quiz {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id")
    private Course course;


    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Quiz> quiz = new HashSet<>();


    public Quiz() {
    }

    public Quiz(Course course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Quiz> getQuiz() {
        return quiz;
    }

    public void setQuiz(Set<Quiz> quiz) {
        this.quiz = quiz;
    }
}
