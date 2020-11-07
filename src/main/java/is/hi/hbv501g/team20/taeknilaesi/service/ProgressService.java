package is.hi.hbv501g.team20.taeknilaesi.service;

import is.hi.hbv501g.team20.taeknilaesi.model.Progress;
import is.hi.hbv501g.team20.taeknilaesi.model.Quiz;
import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProgressService {
    @Autowired
    ProgressRepository progressRepository;

    public Progress save(Progress progress){
       return progressRepository.save(progress);
    }

    public List<Progress> findAllByUserId(long ID){
        List<Progress> temp = new ArrayList<>();
        Iterable<Progress> iter = progressRepository.findAll();

        for(Progress x : iter){
            if (x.getUser().getId()==ID) {
                temp.add(x);
            }
        }
        return temp;
    }

    public boolean findIfContains(Progress progress){
        Iterable<Progress> p = progressRepository.findAll();

        for(Progress x : p){
            if(x.getUser()==progress.getUser() && x.getLesson()==progress.getLesson()){
                return true;
            }
        }
        return false;
    }

    //finna hæstu einkunn nemanda fyrir quiz í kúrsi
    public double findHighestGradeForCourse(int courseId, long userId){

        List<Double> grades = new ArrayList<>();

        // mögulega sniðugra að finna by user id i staðinn fyrir filteringu her, skoða seinna
        Iterable<Progress> allProgress = progressRepository.findAll();

        // fer i gegnum progress og finnur allar einkunnir fyrir tiltekið quiz í kúrsi
        for (Progress progress : allProgress){
            if (progress.getQuiz()!=null && progress.getUser()!=null){
                Quiz quiz = progress.getQuiz();
                User user = progress.getUser();
                if (user.getId()==userId && quiz.getCourse().getId() == courseId){
                    grades.add(progress.getQuizGrade());
                }
            }
        }

        // finnur svo hæstu einkunn ur einkunnum
        double highestGrade = 0.0;

        for(double grade : grades){
            if (grade > highestGrade){
                highestGrade = grade;
            }
        }
        return highestGrade;
    }
}
