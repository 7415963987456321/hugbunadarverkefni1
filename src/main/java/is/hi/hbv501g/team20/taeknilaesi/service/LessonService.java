package is.hi.hbv501g.team20.taeknilaesi.service;

import is.hi.hbv501g.team20.taeknilaesi.model.Lesson;
import is.hi.hbv501g.team20.taeknilaesi.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LessonService {
    @Autowired
    LessonRepository lessonRepository;

    public List<Lesson> getAllLesson(int id){
        java.util.List<Lesson> lessons = new ArrayList<Lesson>();
        lessonRepository.findAllById(Collections.singleton(id)).forEach(lesson -> lessons.add(lesson));
        return lessons;
    }

    public Lesson getLessonById(int LessonId) {return lessonRepository.findById(LessonId).get();}
}
