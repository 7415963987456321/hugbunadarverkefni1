package is.hi.hbv501g.team20.taeknilaesi.controller;

import is.hi.hbv501g.team20.taeknilaesi.model.Lesson;
import is.hi.hbv501g.team20.taeknilaesi.model.Progress;
import is.hi.hbv501g.team20.taeknilaesi.model.Question;
import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.service.ProgressService;
import is.hi.hbv501g.team20.taeknilaesi.service.QuestionService;
import is.hi.hbv501g.team20.taeknilaesi.service.QuizService;
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


    @GetMapping("/quiz/{courseId}")
    private String finishLesson(@PathVariable("courseId") int courseId,  @CurrentSecurityContext(expression = "authentication.name") String username, HttpSession session, Model model) throws Exception {
        List<Question> q = questionService.getQuestions(courseId);

        model.addAttribute("questions",q);
        return "quiz";
    }


}
