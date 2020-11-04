package is.hi.hbv501g.team20.taeknilaesi.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuizResult {
    Answer[] answers;
    List<Question> questions = new ArrayList<>();

    public QuizResult(List<Question> questions) {
        this.questions = questions;
        this.answers = new Answer[questions.size()];


    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setAnswers(Answer[] answers) {
        this.answers = answers;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
