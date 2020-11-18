package is.hi.hbv501g.team20.taeknilaesi.service;

import is.hi.hbv501g.team20.taeknilaesi.model.Comment;
import is.hi.hbv501g.team20.taeknilaesi.repository.CommentRepository;
import is.hi.hbv501g.team20.taeknilaesi.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public void SaveNewPost(Comment comment) {

        if( commentRepository.save(comment) != null){
            System.out.println("@@@@@@@@ SAVED COMMENT");
        } else {
            System.out.println("@@@@@@@@ ERROR SAVING COMMENT"); 
        }
    }

    public List<Comment> getByLessonId(int id){
       return commentRepository.findByLessonId(id);

    }
}
