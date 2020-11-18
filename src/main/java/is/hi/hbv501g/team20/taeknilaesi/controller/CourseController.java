package is.hi.hbv501g.team20.taeknilaesi.controller;

import is.hi.hbv501g.team20.taeknilaesi.model.Comment;
import is.hi.hbv501g.team20.taeknilaesi.model.Course;
import is.hi.hbv501g.team20.taeknilaesi.model.Progress;
import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.service.CourseService;
import is.hi.hbv501g.team20.taeknilaesi.service.ProgressService;
import is.hi.hbv501g.team20.taeknilaesi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.parameters.P;
import is.hi.hbv501g.team20.taeknilaesi.model.Lesson;
import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.service.CommentService;
import is.hi.hbv501g.team20.taeknilaesi.service.CourseService;
import is.hi.hbv501g.team20.taeknilaesi.service.LessonService;
import is.hi.hbv501g.team20.taeknilaesi.service.UserService;
import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static is.hi.hbv501g.team20.taeknilaesi.constants.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    ProgressService ps;

    @Autowired
    UserService uc;

    //UserService userService;

    @Autowired
    CommentService commentService;

    @Autowired
    LessonService lessonService;

    @Autowired
    ObjectMapper om;
    // @GetMapping("/courses")
    // private List<Course> getAllCourse() {
    //     return courseService.getAllCourse();
    // }


    @GetMapping("/")
    private String getAllCourse(@CurrentSecurityContext(expression = "authentication.name") String username, HttpSession session, Model model) {
        List<Course> coursesList = courseService.getAllCourse();
        model.addAttribute("courses", coursesList);
        User user = uc.findUserByUsername(username);
        session.setAttribute("user",user);
        
        //Initialize-a Progress listann
        List<Progress> p = new ArrayList<>();
        //Ef user er skráður inn, sækjum í gagnagrunn
        if(!username.equals("anonymousUser")){
            //User user = uc.findUserByUsername(username);
            List<Progress> temp = ps.findAllByUserId(user.getId());
            p = temp;
        }
        else{
            //Sækjum progress listann úr storage ef hann er ekki tómur og yfirskrifum
            if((List<Progress>)session.getAttribute("progress")!=null)
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
    private String getCourse(@PathVariable("id") int id, Model model) throws JsonProcessingException {
        //get all lessons from course 
        //iterate through lessons
        List<Lesson> ll = lessonService.findAllLessonsByCourseId(id);
        List<Comment> lc = new ArrayList<>();
        // System.out.println("@@@@@ COUNT @@@: " + ll.size());
         for (Lesson lesson : ll) {
            //search for comment and add to coments in lesson
             lc = commentService.getByLessonId(lesson.getId());
             lesson.addComments(lc);
            // lesson.setComments(lc);
            //System.out.println("@@@@@@@@@ " + lesson.getComments());
            // System.out.println("@@@@@@@@@ " + lc.toString());
            
                 }
        
        Course course = courseService.getCourseById(id);
        // System.out.println("@@@@@@@ -> " + om.writeValueAsString(course));
        // om.writeValueAsString(course);
        model.addAttribute("course",course);
        model.addAttribute("url", VIDEO_STORE);
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() != "anonymousUser") {
        // System.out.println("@@@@@@@ " + authentication.getPrincipal());
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            User user = new User();

            user = uc.getUserByEmail(userDetails.getUsername());

            model.addAttribute("user", user);
        }
        return "course";
       // return courseService.getCourseById(id);
    }
}
