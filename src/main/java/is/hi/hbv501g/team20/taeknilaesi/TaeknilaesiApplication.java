package is.hi.hbv501g.team20.taeknilaesi;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import is.hi.hbv501g.team20.taeknilaesi.model.*;
import is.hi.hbv501g.team20.taeknilaesi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.transaction.Transactional;

import is.hi.hbv501g.team20.taeknilaesi.model.*;
import is.hi.hbv501g.team20.taeknilaesi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import is.hi.hbv501g.team20.taeknilaesi.model.Comment;
import is.hi.hbv501g.team20.taeknilaesi.model.Course;
import is.hi.hbv501g.team20.taeknilaesi.model.Lesson;
import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.repository.CommentRepository;
import is.hi.hbv501g.team20.taeknilaesi.repository.CourseRepository;
import is.hi.hbv501g.team20.taeknilaesi.repository.LessonRepository;
import is.hi.hbv501g.team20.taeknilaesi.repository.UserRepository;
import is.hi.hbv501g.team20.taeknilaesi.spring.SecurityConfig;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@SpringBootApplication
public class TaeknilaesiApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class[] { SecurityConfig.class, TaeknilaesiApplication.class }, args);
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
@Component
class DemoCommandLineRunner implements CommandLineRunner {


    @Autowired
    private UserRepository sr;

    @Autowired
    private CourseRepository cr;

    @Autowired
    private LessonRepository lr;

    @Autowired
    private ProgressRepository pr;

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionRepo questionRepo;

    @Autowired
    private AnswerRepo answerRepo;


    private CommentRepository cmr;
    
    @Override
    @Transactional
    public void run(String... args) throws Exception {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        String encodedPassword1 = bCryptPasswordEncoder.encode("1234");
        String encodedPassword2 = bCryptPasswordEncoder.encode("qwerty");
        User u1 = new User("unnur", 1935, "unnur@gmail.com", encodedPassword1);
        //  sr.save(new User("unnur", 1935, "unnur@gmail.com",encodedPassword1));
        sr.save(u1);
        User u2 = new User("jon", 1935, "jon@gmail.com", encodedPassword2);
        // sr.save(new User("jon", 1935, "jon@gmail.com",encodedPassword2)) ;
        sr.save(u2);

        // List<Comment> new ArrayList<>()sdf = new ArrayList<>();
        Lesson l1 = new Lesson(1,"Hvað er spjaldtölva","Hvernig virkar spjaldtölva og hvernig stjórnar maður henni.","1.1_Hvad_er_spjaldtolva.mp4",new ArrayList<>());
        lr.save(l1);

        Lesson l2 = new Lesson(1,"Hækka og lækka","","1.2.2_Haekka_og_laekka.mp4", new ArrayList<>());
        lr.save(l2);

        Lesson l3 = new Lesson(3, "Hvað er internetið","Internetið er alþjóðlegt kerfi tölvuneta sem á sinn uppruna í bandaríska hernum á kalda stríðs tímabilinu og het arpanet, en háskólar sáu um þróun og veittu sínu starfsfólki og nemum aðgengi. Nétið eins og það er helst notað í dag var þróað af tölvunarfæðingnum Tim Berner Lee hjá CERN um 1990 og kallaði hann það hypertext. Hann átti síðan líka stóran hlut í því að tryggja að netið sé hlutlaust og ekki háð ríkisstjórnum.","3.1.1_Hvad_er_internetid.mp4",
                new ArrayList<>());
        lr.save(l3);

        Lesson l4 = new Lesson(3, "Hvað er lén","","3.1.2_Hvar_er_len.mp4", new ArrayList<>());
        lr.save(l4);

        Lesson l5 = new Lesson(3, "Leitarstikan", "", "3.1.2_Hvar_er_len.mp4", new ArrayList<>());
        lr.save(l5);

        Lesson l6 = new Lesson(3, "Flipar", "", "3.3_Flipar.mp4", new ArrayList<>());
        lr.save(l6);

        Lesson l7 = new Lesson(3, "Bókamerki", "", "3.4_Bokamerki.mp4", new ArrayList<>());
        lr.save(l7);

        ArrayList<Lesson> tmpLessons = new ArrayList<>();
        tmpLessons.add(l1);
        tmpLessons.add(l2);

        Course c1 = new Course(1,"Grunnatriði","Þetta námskeið fjallar um grunnotkun á spjaltölvu og utśkýrir mikilvæg hugtök sem þar koma fram.", tmpLessons);
        cr.save(c1);

        tmpLessons.clear();
        tmpLessons.add(l3);
        tmpLessons.add(l4);
        tmpLessons.add(l5);
        tmpLessons.add(l6);
        tmpLessons.add(l7);

        Course c2 = new Course(3, "Internetið", "Hér verður fjallað um internetið, hvað það er, hvernig það varð til og hvernig notar maður það.", tmpLessons);
        cr.save(c2);
        Comment cm1 = new Comment(1, "blablabla", u1.getName());
        cmr.save(cm1);
        Comment cm2 = new Comment(1, "meira blablabla", u2.getName());
        cmr.save(cm2);
        Comment cm3 = new Comment(2, "blablabla", u1.getName());
        cmr.save(cm3);

        Set<Lesson> tmp2Lessons = new HashSet<>();
        tmp2Lessons.add(l3);
        tmp2Lessons.add(l6);

        Progress p = new Progress(l6,user2);
        pr.save(p);

        //Búa til quiz fyrir kúrs 1
        Quiz quiz1 = new Quiz(c1);

        //Spurning 1
        String spurning1 = "Hver er tilgangur fyrirlestranna?";
        Answer svar1 = new Answer("Læra að nota spjaldtölvur");
        Answer svar2 = new Answer("Læra að hringja úr síma");
        Answer svar3 = new Answer("Læra að forrita");
        Set<Answer> svor = new HashSet<>();
        svor.add(svar1);
        svor.add(svar2);
        svor.add(svar3);
        Question q1 = new Question(spurning1,svor,"Læra að nota spjaldtölvur");
        svar1.setQuestion(q1);
        svar2.setQuestion(q1);
        svar3.setQuestion(q1);
        q1.setQuiz(quiz1);

        String spurning2 = "Hvernig skal hækka hljóð í spjaldtölvunni?";
        Answer svar4 = new Answer("Slá spjaldtölvunni létt í harðann hlut svosem borð");
        Answer svar5 = new Answer("Hrista spjaldtölvuna");
        Answer svar6 = new Answer("Nota takkann hægra megin efst uppi");
        Set<Answer> svor2 = new HashSet<>();
        svor2.add(svar4);
        svor2.add(svar5);
        svor2.add(svar6);
        Question q2 = new Question(spurning2, svor2, "Nota takkann hægra megin efst uppi");
        svar4.setQuestion(q2);
        svar5.setQuestion(q2);
        svar6.setQuestion(q2);
        q2.setQuiz(quiz1);

        Set<Question> questions = new HashSet<>();
        questions.add(q1);
        questions.add(q2);
        quiz1.setQuestions(questions);


        Quiz quiz2 = new Quiz(c2);

        String spurning3 = "Þetta er random spurning";
        Answer svar7 = new Answer("með random svari");
        Set<Answer> svor3 = new HashSet<>();
        svor3.add(svar7);
        Question q3 = new Question(spurning3, svor3, "með random svari");
        svar7.setQuestion(q3);
        q3.setQuiz(quiz2);

        quizRepo.save(quiz1);
        quizRepo.save(quiz2);
        questionRepo.save(q1);
        questionRepo.save(q2);
        questionRepo.save(q3);
//        answerRepo.save(svar1);
//        answerRepo.save(svar2);
//        answerRepo.save(svar3);

        Progress p2 = new Progress(quiz1, user2, 5.0);
        pr.save(p2);















    }
}
