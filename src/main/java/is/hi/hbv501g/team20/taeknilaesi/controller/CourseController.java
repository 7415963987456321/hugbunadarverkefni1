package is.hi.hbv501g.team20.taeknilaesi.controller;

import is.hi.hbv501g.team20.taeknilaesi.model.Course;
import is.hi.hbv501g.team20.taeknilaesi.model.Progress;
import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.service.CourseService;
import is.hi.hbv501g.team20.taeknilaesi.service.ProgressService;
import is.hi.hbv501g.team20.taeknilaesi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static is.hi.hbv501g.team20.taeknilaesi.constants.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    ProgressService ps;

    @Autowired
    UserService uc;

    // @GetMapping("/courses")
    // private List<Course> getAllCourse() {
    //     return courseService.getAllCourse();
    // }


    @GetMapping("/")
    private String getAllCourse(@CurrentSecurityContext(expression = "authentication.name") String username, HttpSession session, Model model) {
        List<Course> coursesList = courseService.getAllCourse();
        model.addAttribute("courses", coursesList);
        
        //Initialize-a Progress listann
        List<Progress> p = new ArrayList<>();
        //Ef user er skráður inn, sækjum í gagnagrunn
        if(!username.equals("anonymousUser")){
            User user = uc.findUserByUsername(username);
            List<Progress> temp = ps.findAllByUserId(user.getId());
            p = temp;
        }
        else{
            //Sækjum progress listann úr storage
            p = (List<Progress>)session.getAttribute("progress");
        }
        session.setAttribute("progress", p);

        return "courses";
    }

    // @GetMapping("/courses")
    // private String getAllCourse(Model model) {
    //     List<Course> coursesList = courseService.getAllCourse();
    //     model.addAttribute("courses", coursesList);
    //     System.out.println(coursesList)
    //     return "courses";
    // }


    @GetMapping("/course/{id}")
    private String getCourse(@PathVariable("id") int id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course",course);
        model.addAttribute("url", VIDEO_STORE);
        return "course";
       // return courseService.getCourseById(id);
    }
}
