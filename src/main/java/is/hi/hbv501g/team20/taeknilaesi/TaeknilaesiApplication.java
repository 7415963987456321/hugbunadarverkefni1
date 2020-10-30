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


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        String encodedPassword1 = bCryptPasswordEncoder.encode("1234");
        String encodedPassword2 = bCryptPasswordEncoder.encode("qwerty");
        User user1 = new User("unnur", 1935, "unnur@gmail.com",encodedPassword1);
        User user2 = new User("jon", 1935, "jon@gmail.com",encodedPassword2);
        sr.save(user1);
        sr.save(user2);

        Lesson l1 = new Lesson(1,"Hvað er spjaldtölva","Hvernig virkar spjaldtölva og hvernig stjórnar maður henni.","1.1_Hvad_er_spjaldtolva.mp4");
        lr.save(l1);

        Lesson l2 = new Lesson(1,"Hækka og lækka","","1.2.2_Haekka_og_laekka.mp4");
        lr.save(l2);

        Lesson l3 = new Lesson(3, "Hvað er internetið","Internetið er alþjóðlegt kerfi tölvuneta sem á sinn uppruna í bandaríska hernum á kalda stríðs tímabilinu og het arpanet, en háskólar sáu um þróun og veittu sínu starfsfólki og nemum aðgengi. Nétið eins og það er helst notað í dag var þróað af tölvunarfæðingnum Tim Berner Lee hjá CERN um 1990 og kallaði hann það hypertext. Hann átti síðan líka stóran hlut í því að tryggja að netið sé hlutlaust og ekki háð ríkisstjórnum.","3.1.1_Hvad_er_internetid.mp4");
        lr.save(l3);

        Lesson l4 = new Lesson(3, "Hvað er lén","","3.1.2_Hvar_er_len.mp4");
        lr.save(l4);

        Lesson l5 = new Lesson(3, "Leitarstikan", "", "3.1.2_Hvar_er_len.mp4");
        lr.save(l5);

        Lesson l6 = new Lesson(3, "Flipar", "", "3.3_Flipar.mp4");
        lr.save(l6);

        Lesson l7 = new Lesson(3, "Bókamerki", "", "3.4_Bokamerki.mp4");
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

        Set<Lesson> tmp2Lessons = new HashSet<>();
        tmp2Lessons.add(l3);
        tmp2Lessons.add(l6);

        Progress p = new Progress(l6,user2);
        pr.save(p);

        Quiz quiz1 = new Quiz(c1);

        String spurning1 = "Spurning 1";
        Answer svar1 = new Answer("svar1");
        Answer svar2 = new Answer("svar2");
        Answer svar3 = new Answer("svar3");
        Set<Answer> svor = new HashSet<>();
        svor.add(svar1);
        svor.add(svar2);
        svor.add(svar3);
        Question q1 = new Question(spurning1,svor,"svar2");
        svar1.setQuestion(q1);
        svar2.setQuestion(q1);
        svar3.setQuestion(q1);
        q1.setQuiz(quiz1);
        Set<Question> questions = new HashSet<>();
        questions.add(q1);
        quiz1.setQuestions(questions);
        quizRepo.save(quiz1);
        questionRepo.save(q1);
        answerRepo.save(svar1);
        answerRepo.save(svar2);
        answerRepo.save(svar3);








    }
}
