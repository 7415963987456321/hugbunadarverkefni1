package is.hi.hbv501g.team20.taeknilaesi.service;

import is.hi.hbv501g.team20.taeknilaesi.model.Course;
import is.hi.hbv501g.team20.taeknilaesi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public List<Course> getAllCourse() {
        List<Course> courses = new ArrayList<Course>();
        courseRepository.findAll().forEach(course -> courses.add(course));
        return courses;
    }

    public Course getCourseById(int id) { return courseRepository.findById(id).get();}
    public void saveOrUpdate(Course course) { courseRepository.save(course);}

}
