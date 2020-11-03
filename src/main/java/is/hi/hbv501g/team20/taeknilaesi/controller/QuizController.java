package is.hi.hbv501g.team20.taeknilaesi.controller;

import is.hi.hbv501g.team20.taeknilaesi.model.*;
import is.hi.hbv501g.team20.taeknilaesi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class QuizController {
    @Autowired
    QuizService quizService;

    @Autowired
    QuestionService questionService;

    @Autowired
    ProgressService progressService;

    @Autowired
    CourseService courseService;

    @Autowired
    UserService userService;


    @GetMapping("/quiz/{courseId}")
    private String takeQuiz(@PathVariable("courseId") int courseId,  @CurrentSecurityContext(expression = "authentication.name") String username, HttpSession session, Model model) throws Exception {
        List<Question> q = questionService.getQuestions(courseId);

        model.addAttribute("courseId", courseId);
        model.addAttribute("questions",q);
        return "quiz";
    }

    @GetMapping("/quiz/{courseId}/finish")
    private ModelAndView finishQuiz(@PathVariable("courseId") int courseId,  @CurrentSecurityContext(expression = "authentication.name") String username, HttpSession session, Model model) throws Exception {

        // Ehv reikingar til að finna einkunn og ehv fleira
        //Mögulega tala við progressService til að bera saman einkunn ef a að taka quiz oft til að fa betri einkunn

        // Þetta er bara til að setja i progress, þarf að utfæra betur!

        User user = userService.findUserByUsername(username);
        Quiz q = quizService.findQuizByCourseId(courseId);
        if(user!=null && q !=null) {
            Progress p = new Progress(q, user);
            progressService.save(p);
            System.out.println(user.getName()+" just finished quiz in course "+q.getCourse().getDescription());
        }


        model.addAttribute("courseId", courseId);
        model.addAttribute("questions",q);
        return new ModelAndView("redirect:/");
    }


}
