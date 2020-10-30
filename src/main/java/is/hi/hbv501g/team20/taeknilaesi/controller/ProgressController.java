package is.hi.hbv501g.team20.taeknilaesi.controller;

import is.hi.hbv501g.team20.taeknilaesi.model.Course;
import is.hi.hbv501g.team20.taeknilaesi.model.Lesson;
import is.hi.hbv501g.team20.taeknilaesi.model.Progress;
import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.service.CourseService;
import is.hi.hbv501g.team20.taeknilaesi.service.LessonService;
import is.hi.hbv501g.team20.taeknilaesi.service.ProgressService;
import is.hi.hbv501g.team20.taeknilaesi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProgressController {

    @Autowired
    LessonService lessonService;

    @Autowired
    UserService userService;

    @Autowired
    ProgressService progressService;

    @GetMapping("/course/{courseId}/{id}/finish")
    private ModelAndView finishLesson(@PathVariable("courseId") int courseId, @PathVariable("id") int id, @CurrentSecurityContext(expression = "authentication.name") String username, HttpSession session, Model model) throws Exception {
        Lesson lesson = lessonService.getLessonById(id);
        User user = userService.findUserByUsername(username);


        Progress prog = new Progress(lesson,user);
        session.setAttribute("user",user);

        // User er til, sækjum Progress listann og bætum við
        if (user!=null) {
            if(!progressService.findIfContains(prog))
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
            boolean pcontainsprog = false;
            for(Progress x : p){
                if(x.getLesson().getId()==prog.getLesson().getId()){
                    pcontainsprog =  true;
                    break;
                }
            }

            if(!pcontainsprog)
                p.add(prog);
            session.setAttribute("progress",p);
        }

        return new ModelAndView( "redirect:/");
    }
}
