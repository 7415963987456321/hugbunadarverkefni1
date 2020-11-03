package is.hi.hbv501g.team20.taeknilaesi.service;

import is.hi.hbv501g.team20.taeknilaesi.model.Question;
import is.hi.hbv501g.team20.taeknilaesi.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;

    //get random questions for course
    public List<Question> getQuestions(int courseid){

        int numberOfMaxQuestions = 2;
        Iterable<Question> allQuestion = questionRepo.findAll();
        List<Question> temp = new ArrayList<>();

        for (Question q:allQuestion){
            if(q.getQuiz().getCourse()!=null){
                if (q.getQuiz().getCourse().getId()==courseid){
                    temp.add(q);
                }
            }
        }
        while(temp.size()<numberOfMaxQuestions){
            numberOfMaxQuestions--;
        }

        Collections.shuffle(temp);
        List<Question> temp2 = new ArrayList<>();

        for (int i = 0; i <numberOfMaxQuestions; i++){
            temp2.add(temp.get(i));
        }

        return temp2;
    }
}
