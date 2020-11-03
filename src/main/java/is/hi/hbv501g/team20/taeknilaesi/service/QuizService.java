package is.hi.hbv501g.team20.taeknilaesi.service;

import is.hi.hbv501g.team20.taeknilaesi.model.Answer;
import is.hi.hbv501g.team20.taeknilaesi.model.Quiz;
import is.hi.hbv501g.team20.taeknilaesi.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    @Autowired
    QuizRepo qs;
    // fá slembið quiz


    public Quiz findQuizByCourseId(int courseId){
        Iterable<Quiz> all = qs.findAll();

        for(Quiz q : all){
            if(q.getCourse()!=null){
                if(q.getCourse().getId()==courseId){
                    return q;
                }
            }
        }
        return null;

    }

}
