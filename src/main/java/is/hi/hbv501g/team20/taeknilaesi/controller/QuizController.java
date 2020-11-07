package is.hi.hbv501g.team20.taeknilaesi.controller;

import is.hi.hbv501g.team20.taeknilaesi.model.*;
import is.hi.hbv501g.team20.taeknilaesi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        QuizResult quizResult = new QuizResult(q);

        model.addAttribute("quizResult", quizResult); //þetta er ekki að virka, alltaf tómt og krassar í post controller (null pointer exception)
        model.addAttribute("courseId", courseId);
        model.addAttribute("questions",q);
        return "quiz";
    }

    @PostMapping("/quiz/{courseId}/finish")
    private String finishQuiz(@PathVariable("courseId") int courseId,
            @CurrentSecurityContext(expression = "authentication.name") String username, HttpSession session,
                                    Model model, @ModelAttribute("quizResult") QuizResult quizResult) throws Exception {
        

        List<Question> quizQuestions = new ArrayList<>();


        int totalQuestions = quizResult.getQuestions().size();
        int correctAnswers = 0;

        for (Integer questionId : quizResult.getQuestions()){
            Question currentQuestion = questionService.getQuestionById(questionId);
            if (currentQuestion!=null){
                quizQuestions.add(currentQuestion);
                String correctAnswer = currentQuestion.getRightAnswer();
                String studentAnswer = quizResult.getAnswers().get(quizResult.getQuestions().indexOf(questionId)).getAnswer();
                if(correctAnswer.equals(studentAnswer)){
                    correctAnswers++;
                }
            }
        }
        double quizGrade = (Double.valueOf(correctAnswers)/Double.valueOf(totalQuestions))*10.0;

        User user = userService.findUserByUsername(username);
        Quiz q = quizService.findQuizByCourseId(courseId);
        if(user!=null && q !=null) {
            double previousHighestGrade = progressService.findHighestGradeForCourse(courseId, user.getId());
            if (quizGrade > previousHighestGrade){
                Progress p = new Progress(q, user, quizGrade);
                progressService.save(p);
            }
            System.out.println(user.getName()+" just finished quiz in course "+q.getCourse().getDescription());
        }

        // setja spurningar, svor og rett svor i model og birta svo einkunn.
        model.addAttribute("quizQuestions",quizQuestions);
        model.addAttribute("QuisResult",quizResult);
        model.addAttribute("quizGrade",quizGrade);

        return "quizResult";
    }


}
