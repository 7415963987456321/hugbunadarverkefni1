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
        List<Lesson> lessons = new ArrayList<Lesson>();
        lessonRepository.findAllById(Collections.singleton(id)).forEach(lesson -> lessons.add(lesson));
        System.out.println("@@++++" + lessons.size());
        return lessons;
    }

    public List<Lesson> getAllLessonByCourse(int courseId){
        List<Lesson> lessons = new ArrayList<Lesson>();

        //lessonRepository.findByCourseId(Collections.singleton(CourseId)).forEach(lesson -> lessons.add(lesson));

        // lessonRepository.findByCourseIdIn(courseId).forEach(lesson -> lessons.add(lesson));
        System.out.println("@@++++" + lessons.size());
        return lessons;
    }

    public List<Lesson> findAllLessonsByCourseId(int courseId) {
        List<Lesson> temp = new ArrayList<>();
        Iterable<Lesson> iter = lessonRepository.findAll();

        for (Lesson x : iter) {
            if (x.getCourseId() == courseId) {
                temp.add(x);
            }
        }
        return temp;
    }

    public Lesson getLessonById(int LessonId) {return lessonRepository.findById(LessonId).get();}
}
