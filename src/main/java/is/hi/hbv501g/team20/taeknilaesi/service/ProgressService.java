package is.hi.hbv501g.team20.taeknilaesi.service;

import is.hi.hbv501g.team20.taeknilaesi.model.Progress;
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


}
