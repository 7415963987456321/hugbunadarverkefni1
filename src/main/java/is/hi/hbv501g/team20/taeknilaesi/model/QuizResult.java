package is.hi.hbv501g.team20.taeknilaesi.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuizResult {
    List<Answer> answers = new ArrayList<>();
    List<Integer> questions = new ArrayList<>();

    public QuizResult() {
    }

    public QuizResult(List<Question> questions) {
        for(Question q : questions){
            this.questions.add(q.getId());
        }
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Integer> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Integer> questions) {
        this.questions = questions;
    }
}
