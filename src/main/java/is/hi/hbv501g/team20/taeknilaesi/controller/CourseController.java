package is.hi.hbv501g.team20.taeknilaesi.controller;

import is.hi.hbv501g.team20.taeknilaesi.model.Course;
import is.hi.hbv501g.team20.taeknilaesi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static is.hi.hbv501g.team20.taeknilaesi.constants.ApplicationConstants.*;
import java.util.List;

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;

    // @GetMapping("/courses")
    // private List<Course> getAllCourse() {
    //     return courseService.getAllCourse();
    // }


    @GetMapping("/")
    private String getAllCourse(Model model) {
        List<Course> coursesList = courseService.getAllCourse();
        model.addAttribute("courses", coursesList);
        System.out.println(coursesList);
        return "courses";
    }

    // @GetMapping("/courses")
    // private String getAllCourse(Model model) {
    //     List<Course> coursesList = courseService.getAllCourse();
    //     model.addAttribute("courses", coursesList);
    //     System.out.println(coursesList);
    //     return "courses";
    // }


    @GetMapping("/course/{id}")
    private String getCourse(@PathVariable("id") int id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course",course);
        return "course";
       // return courseService.getCourseById(id);
    }
}
