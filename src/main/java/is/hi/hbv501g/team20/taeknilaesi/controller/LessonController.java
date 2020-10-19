package is.hi.hbv501g.team20.taeknilaesi.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import is.hi.hbv501g.team20.taeknilaesi.model.Course;
import is.hi.hbv501g.team20.taeknilaesi.model.Progress;
import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.service.CourseService;
import is.hi.hbv501g.team20.taeknilaesi.service.ProgressService;
import is.hi.hbv501g.team20.taeknilaesi.service.UserService;
import is.hi.hbv501g.team20.taeknilaesi.spring.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import is.hi.hbv501g.team20.taeknilaesi.model.Lesson;
import is.hi.hbv501g.team20.taeknilaesi.service.LessonService;

import javax.servlet.http.HttpSession;

//ekki notað eins og er, kannski ekki þörf?
@Controller
public class LessonController {
    @Autowired
    LessonService lessonService;

    @Autowired
    CourseService courseService;

    @Autowired
    ProgressService progressService;

    @Autowired
    SecurityConfig sc;

    @Autowired
    UserService uc;



    // @GetMapping("/course/{courseId}/{id}")
    // private List<Lesson> getAllLesson(@PathVariable("id") int id) {
    //     return LessonService.getAllLesson(id);
    // }

    @GetMapping("/course/{courseId}/{id}")
    private String getLesson(@PathVariable("courseId") int courseId, @PathVariable("id") int id, Model model) {
        // Course course = courseService.getCourseById(id);
        Lesson lesson = lessonService.getLessonById(id);
        model.addAttribute("lesson", lesson);
        return "lesson";
    }

    //ekki notað
    @GetMapping(value = "/videosrc", produces = "video/mp4")
    @ResponseBody
    public FileSystemResource videoSource() {
        return new FileSystemResource(new File("/home/skuli/Documents/taeknlaesi/1.1_Hvad_er_spjaldtolva.mp4")); 
    }

    @GetMapping("/course/{courseId}/{id}/finish")
    private String finishLesson(@PathVariable("courseId") int courseId, @PathVariable("id") int id, @CurrentSecurityContext(expression = "authentication.name") String username, HttpSession session, Model model) throws Exception {
        List<Course> course2 = courseService.getAllCourse();
        Lesson lesson = lessonService.getLessonById(id);
        User user = uc.findUserByUsername(username);


        Progress prog = new Progress(lesson,user);

        // User er til, sækjum Progress listann og bætum við
        if (user!=null) {
            progressService.save(prog);
            List<Progress> p = progressService.findAllByUserId(user.getId());
            session.setAttribute("progress", p);
        }
        else{
            // User er ekki til, sækjum session progress listann
            List<Progress> p = (List<Progress>) session.getAttribute("progress");
            if(p==null){
                //Session Progress listi tómur, initiate og setjum svo Progress P í
                List<Progress> temp = new ArrayList<>();
                p = temp;
            }
            p.add(prog);
            session.setAttribute("progress",p);
        }

        model.addAttribute("courses", course2);

        return "courses";
    }
}
