package is.hi.hbv501g.team20.taeknilaesi;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Validator;

import is.hi.hbv501g.team20.taeknilaesi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import is.hi.hbv501g.team20.taeknilaesi.model.Answer;
import is.hi.hbv501g.team20.taeknilaesi.model.Comment;
import is.hi.hbv501g.team20.taeknilaesi.model.Course;
import is.hi.hbv501g.team20.taeknilaesi.model.Lesson;
import is.hi.hbv501g.team20.taeknilaesi.model.Progress;
import is.hi.hbv501g.team20.taeknilaesi.model.Question;
import is.hi.hbv501g.team20.taeknilaesi.model.Quiz;
import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.repository.AnswerRepo;
import is.hi.hbv501g.team20.taeknilaesi.repository.CommentRepository;
import is.hi.hbv501g.team20.taeknilaesi.repository.CourseRepository;
import is.hi.hbv501g.team20.taeknilaesi.repository.LessonRepository;
import is.hi.hbv501g.team20.taeknilaesi.repository.ProgressRepository;
import is.hi.hbv501g.team20.taeknilaesi.repository.QuestionRepo;
import is.hi.hbv501g.team20.taeknilaesi.repository.QuizRepo;
import is.hi.hbv501g.team20.taeknilaesi.repository.UserRepository;
import is.hi.hbv501g.team20.taeknilaesi.service.EmailService;
import is.hi.hbv501g.team20.taeknilaesi.service.UserService;
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

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setProtocol("smtps");
        javaMailSender.setPort(465);
        //þarf að setja google mail til að virka
        javaMailSender.setUsername("taeknilaesifyrirfullordna@gmail.com");
        javaMailSender.setPassword("taeknilaesi");
        return javaMailSender;
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
        ////////////////////////////////////////////
        //below needs to be commented to run on mysql
        ////////////////////////////////////////////

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        String encodedPassword1 = bCryptPasswordEncoder.encode("1234");
        String encodedPassword2 = bCryptPasswordEncoder.encode("qwerty");
        User u1 = new User("unnur", "1940", "unnur@gmail.com", encodedPassword1);
        sr.save(u1);
        User u2 = new User("jon", "1939", "jon@gmail.com", encodedPassword2);
        sr.save(u2);

        Lesson l1 = new Lesson(1,"Hvað er spjaldtölva","Hvernig virkar spjaldtölva og hvernig stjórnar maður henni.","1.1_Hvad_er_spjaldtolva.mp4",new ArrayList<>());
        lr.save(l1);
        Lesson l2 = new Lesson(1, "Hækka og lækka", "", "1.2.2_Haekka_og_laekka.mp4", new ArrayList<>());
        lr.save(l2);
        Lesson l3 = new Lesson(1, "Snertiskjár (almennt)", "", "1.2.4.1_Snertiskjar_inngangur.mp4", new ArrayList<>());
        lr.save(l3);
        Lesson l4 = new Lesson(1, "Snertiskjár notkun", "", "1.2.4.2_Snertiskjar_notkun.mp4", new ArrayList<>());
        lr.save(l4);
        Lesson l5 = new Lesson(1, "Heimatakkinn", "", "1.2.5_1_Dokkan_nidri.mp4", new ArrayList<>());
        lr.save(l5);
        Lesson l6 = new Lesson(1, "Heimaskjár", "", "1.2.5_2_Uppsetning_heimaskjar.mp4", new ArrayList<>());
        lr.save(l6);
        Lesson l7 = new Lesson(1, "Lyklaborð almennt", "", "1.2.6.1_Lyklabord.mp4", new ArrayList<>());
        lr.save(l7);
        Lesson l8 = new Lesson(1, "Lyklaborð sjálfvirkar tillögur", "", "1.2.6.2_Lyklabord_autocomplete.mp4", new ArrayList<>());
        lr.save(l8);
        Lesson l9 = new Lesson(1, "Lyklaborð bendir", "", "1.2.6.2_Lyklabord_bendir.mp4", new ArrayList<>());
        lr.save(l9);
        Lesson l10 = new Lesson(1,"Lyklaborð emoji","","1.2.6.2_Lyklabord_emoji.mp4", new ArrayList<>());
        lr.save(l10);
        Lesson l11 = new Lesson(1,"Lyklaborð sjálfleiðrétting","","1.2.6.3_Lyklabord_autocorrect.mp4", new ArrayList<>());
        lr.save(l11);
        Lesson l12 = new Lesson(1,"Lyklaborð tala","","1.2.6.3_Lyklabord_tala.mp4", new ArrayList<>());
        lr.save(l12);
        Lesson l13 = new Lesson(1,"Stillingar almennt","","1.3.1_Stillingar_hvar.mp4", new ArrayList<>());
        lr.save(l13);
        Lesson l14 = new Lesson(1,"Flýtistillingar","","1.3.2_Flytistillingar.mp4", new ArrayList<>());
        lr.save(l14);
        Lesson l15 = new Lesson(1,"Stillingarappið","","1.3.3_Stillingarapp.mp4", new ArrayList<>());
        lr.save(l15);
        Lesson l16 = new Lesson(1,"Stilling tungumál","","1.3.4_Stilling_tungumal.mp4", new ArrayList<>());
        lr.save(l16);
        Lesson l17 = new Lesson(3, "Hvað er internetið","Internetið er alþjóðlegt kerfi tölvuneta sem á sinn uppruna í bandaríska hernum á kalda stríðs tímabilinu og het arpanet, en háskólar sáu um þróun og veittu sínu starfsfólki og nemum aðgengi. Nétið eins og það er helst notað í dag var þróað af tölvunarfæðingnum Tim Berner Lee hjá CERN um 1990 og kallaði hann það hypertext. Hann átti síðan líka stóran hlut í því að tryggja að netið sé hlutlaust og ekki háð ríkisstjórnum.","3.1.1_Hvad_er_internetid.mp4", new ArrayList<>());
        lr.save(l17);
        Lesson l18 = new Lesson(3, "Hvað er lén?","","3.1.2_Hvar_er_len.mp4", new ArrayList<>());
        lr.save(l18);
        Lesson l19 = new Lesson(3, "Leitarstikan", "", "3.2_Leitarstikan.mp4", new ArrayList<>());
        lr.save(l19);
        Lesson l21 = new Lesson(3, "Flipar", "", "3.3_Flipar.mp4", new ArrayList<>());
        lr.save(l21);
        Lesson l22 = new Lesson(3, "Bókamerki", "", "3.4_Bokamerki.mp4", new ArrayList<>());
        lr.save(l22);
        Lesson l23 = new Lesson(8, "Rafræn skilríki", "", "8._rafraen_skilriki.mp4", new ArrayList<>());
        lr.save(l23);
        Lesson l24 = new Lesson(8, "Innskráning", "", "8.1_rafraen_hvarog_hvernig.mp4", new ArrayList<>());
        lr.save(l24);
        Lesson l25 = new Lesson(8, "Hvar og hvernig", "", "8.2_rafraen_inskraning.mp4", new ArrayList<>());
        lr .save(l25);
        Lesson l26 = new Lesson(9, "Öryggi og Nethegðun", "", "9.1_Nethegdun.mp4", new ArrayList<>());
        lr.save(l26);
        Lesson l27 = new Lesson(9, "Netsvindl", "", "9.2_Netsvindl.mp4", new ArrayList<>());
        lr.save(l27);
        Lesson l28 = new Lesson(9, "Tröll", "", "9.3_Troll.mp4", new ArrayList<>());
        lr.save(l28);
        Lesson l29 = new Lesson(9, "Nethegðun", "", "9.4_Nethegdun.mp4", new ArrayList<>());
        lr.save(l29);

        ArrayList<Lesson> tmpLessons = new ArrayList<>();

        tmpLessons.add(l1);
        tmpLessons.add(l2);
        tmpLessons.add(l3);
        tmpLessons.add(l4); 
        tmpLessons.add(l5);
        tmpLessons.add(l6);
        tmpLessons.add(l7);
        tmpLessons.add(l8);
        tmpLessons.add(l9);
        tmpLessons.add(l10);
        tmpLessons.add(l11);
        tmpLessons.add(l12);
        tmpLessons.add(l13);
        tmpLessons.add(l14);
        tmpLessons.add(l15);
        tmpLessons.add(l16);
        Course c1 = new Course(1,"Grunnatriði","Þetta námskeið fjallar um grunnotkun á spjaltölvu og utśkýrir mikilvæg hugtök sem þar koma fram.", tmpLessons);
        cr.save(c1);

        tmpLessons.clear();
        tmpLessons.add(l17);
        tmpLessons.add(l18);
        tmpLessons.add(l19);
        tmpLessons.add(l21);
        tmpLessons.add(l22);
        Course c2 = new Course(3, "Internetið", "Hér verður fjallað um internetið, hvað það er, hvernig það varð til og hvernig notar maður það.", tmpLessons);
        cr.save(c2);

        tmpLessons.clear();
        tmpLessons.add(l23);
        tmpLessons.add(l24);
        tmpLessons.add(l25);
        Course c3 = new Course(8, "Rafræn skilríki", "Rafræn skilriki eru eins konar auðkenning og sameiginlegt innskraningakerfi fyrir margar þjonustur a islandi.", tmpLessons);
        cr.save(c3);

        tmpLessons.clear();
        tmpLessons.add(l26);
        tmpLessons.add(l27);
        tmpLessons.add(l28);
        Course c4 = new Course(9, "Öryggi og nethegðun", "Nokkur ráð og ábendingar varðandi netöryggi og nethegðun", tmpLessons);
        cr.save(c4);


        Set<Lesson> tmp2Lessons = new HashSet<>();
        tmp2Lessons.add(l3);
        tmp2Lessons.add(l6);

        // Láta unni klára alla nema 1 lesson í course 1
        List<Progress> allButOneLessonInCourse1 = new ArrayList<>();


        Iterable<Course> allCourses = cr.findAll();
        Course course3 = null;
        for (Course c : allCourses){
            if (c.getId()==1)
                course3 = c;
        }

        if(course3!=null) {
            for (Lesson lesson : course3.getLessons()) {
                Progress temp = new Progress(lesson, u1);
                allButOneLessonInCourse1.add(temp);
            }
            allButOneLessonInCourse1.remove(allButOneLessonInCourse1.size()-1);

            for (Progress p : allButOneLessonInCourse1){
                pr.save(p);
            }
        }

        // Temp breytur til að búa til allar spurningar
        String tempQuestionString;
        Set<Answer> tempAnswer;
        Question tempQuestion;
        Set<Question> tempQuestions;
        Quiz tempQuiz;

        // búa til quiz og tengja við kúrs
        // Quiz er samansamn spurninga úr hverjum course
        tempQuiz = new Quiz(c1);
        // Samansafn spurninga fyrir kúrs
        tempQuestions = new HashSet<>();

        //////////////////////////////////////
        tempQuestionString = "Hver er tilgangur fyrirlestranna?";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Læra að nota spjaldtölvur"));
        tempAnswer.add(new Answer("Læra að hringja úr síma"));
        tempAnswer.add(new Answer("Læra að forrita"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Læra að nota spjaldtölvur");

        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = "Hvernig skal hækka hljóðið í spjaldtölvunni?";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Slá spjaldtölvunni létt í harðan hlut, svosem borð"));
        tempAnswer.add(new Answer("Hrista spjaldtölvuna"));
        tempAnswer.add(new Answer("Nota takkann hægra megin efst uppi"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Nota takkann hægra megin efst uppi");

        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = "Hvernig er best að ýta á skjá spjaldtölvunnar";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Ýta fast til að skjárinn nemi snertingu"));
        tempAnswer.add(new Answer("Nota marga putta"));
        tempAnswer.add(new Answer("Nota aðeins einn eða tvo putta og ýta laust"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Nota aðeins einn eða tvo putta og ýta laust");

        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = "Hvernig skal fletta á milli á skjánum";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Nota einn fingur og renna örsnöggt upp, niður, hægri eða vinstri"));
        tempAnswer.add(new Answer("Snúa spjaldtölvunni á hlið þar til hún flettir"));
        tempAnswer.add(new Answer("Ekki er hægt að fletta á spjaldtölvu"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Nota einn fingur og renna örsnöggt upp, niður, hægri eða vinstri");

        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = "Hvað gerir heimatakkinn";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Fer beint aftur á heimaskjáinn"));
        tempAnswer.add(new Answer("Sýnir yfirlit yfir opin forrit í bakrunni"));
        tempAnswer.add(new Answer("Fer til baka í síðasta forrit sem var opnað"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Fer beint aftur á heimaskjáinn");

        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = "Hvernig skal slá inn séríslenska stafi svosem á, í, é, ý á lyklaborðinu";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Halda skal inni a til að fá á og svo framvegis"));
        tempAnswer.add(new Answer("Ýta skal tvisvar örsnöggt á a til að fá á og svo framvegis"));
        tempAnswer.add(new Answer("Til að fá upp þessa stafi þarf að snúa spjaldtölvunni á hvolf"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Halda skal inni a til að fá á og svo framvegis");

        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = " Fyrir hvað stendur þetta merki?";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Stendur fyrir stillingar"));
        tempAnswer.add(new Answer("Stendur fyrir þráðlaust internet "));
        tempAnswer.add(new Answer("Stendur fyrir skeiðklukku"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Stendur fyrir stillingar");
        tempQuestion.setLogo("settings");

        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = " Fyrir hvað stendur þetta merki?";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Stendur fyrir birtustig skjás"));
        tempAnswer.add(new Answer("Stendur fyrir þráðlaust internet"));
        tempAnswer.add(new Answer("Stendur fyrir hljóðstillingar"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Stendur fyrir þráðlaust internet");
        tempQuestion.setLogo("wifi");

        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);



        // Að lokum, tengja quiz við allar spurningar
        tempQuiz.setQuestions(tempQuestions);
        quizRepo.save(tempQuiz);


        //////////////////////////////////////


        // búa til quiz og tengja við kúrs
        // Quiz er samansamn spurninga úr hverjum course
        tempQuiz = new Quiz(c2);
        // Samansafn spurninga fyrir kúrs
        tempQuestions = new HashSet<>();


        //////////////////////////////////////
        tempQuestionString = "Hvað er lén eða slóð";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Heimilisfang vefsíðu"));
        tempAnswer.add(new Answer("Einskonar slóð sem vefsíður mynda og ber að varast"));
        tempAnswer.add(new Answer("Stillingaratriði í vafra spjaldtölvunnar til að gera nettenginuna hraðvirkari"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Heimilisfang vefsíðu");


        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = "Hver er ending á vefslóð íslenskra vefsíðna";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer(".is"));
        tempAnswer.add(new Answer(".com"));
        tempAnswer.add(new Answer(".ice"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, ".is");


        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = "Hvað er flipi í vafra spjaldtölvunnar";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Leið til þess að opna margar vefsíður í einu"));
        tempAnswer.add(new Answer("Spjaldtölvan birt myndir af peysum með krögum"));
        tempAnswer.add(new Answer("Íslensk þýðing af enska orðinu flip, skjárinn snýst á hlið"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Leið til þess að opna margar vefsíður í einu");


        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = " Fyrir hvað stendur þetta merki?";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Bókamerki"));
        tempAnswer.add(new Answer("Endurhlaða vefsíðuna"));
        tempAnswer.add(new Answer("Heimasíða"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Bókamerki");
        tempQuestion.setLogo("bookmark");


        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);



        // Að lokum, tengja quiz við allar spurningar
        tempQuiz.setQuestions(tempQuestions);
        quizRepo.save(tempQuiz);


        //////////////////////////////////////


        // búa til quiz og tengja við kúrs
        // Quiz er samansamn spurninga úr hverjum course
        tempQuiz = new Quiz(c3);
        // Samansafn spurninga fyrir kúrs
        tempQuestions = new HashSet<>();


        //////////////////////////////////////
        tempQuestionString = "Hvernig fær maður rafræn skilríki";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Mæta með símann og skilríki í banka eða símafyrirtæki"));
        tempAnswer.add(new Answer("Panta skilríkin með því að hringja í sýslumann"));
        tempAnswer.add(new Answer("Í næstu innkaupaverslun"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Mæta með símann og skilríki í banka eða símafyrirtæki");


        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = "Hvernig skráir maður sig inn með rafrænum skilríkjum";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Slegið er inn notendanafn og lykilorð"));
        tempAnswer.add(new Answer("Slegið er inn símanúmer og svo staðfest í síma með pin númeri"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Hvernig skráir maður sig inn með rafrænum skilríkjum");


        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = "Hvað er hægt að gera með rafrænum skilríkjum";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Skráð sig inn í vefbanka og tekið lán, skoðað heilsuveru eða Íslendingabók og margt fleira"));
        tempAnswer.add(new Answer("Borga innkaup rafrænt"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Skráð sig inn í vefbanka og tekið lán, skoðað heilsuveru eða Íslendingabók og margt fleira");


        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);






        // Að lokum, tengja quiz við allar spurningar
        tempQuiz.setQuestions(tempQuestions);
        quizRepo.save(tempQuiz);




        //////////////////////////////////////


        // búa til quiz og tengja við kúrs
        // Quiz er samansamn spurninga úr hverjum course
        tempQuiz = new Quiz(c4);
        // Samansafn spurninga fyrir kúrs
        tempQuestions = new HashSet<>();


        //////////////////////////////////////
        tempQuestionString = "Góð venja er að senda aldrei kreditkortanúmer í gegnum tölvupóst eða facebook einkaskilaboð";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Satt "));
        tempAnswer.add(new Answer("Ósatt "));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Satt ");


        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = "Öruggt er að ýta á alla hlekki í tölvupósti";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Satt"));
        tempAnswer.add(new Answer("Ósatt"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Ósatt");


        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = "Hvað er ruslpóstur";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Tölvupóstur sem inniheldur texta sem enginn skilur"));
        tempAnswer.add(new Answer("Tölvupóstur sem er gerður til að komast yfir viðkvæmar upplýsingar svo sem kortanúmer"));
        tempAnswer.add(new Answer("Tölvupóstur sem sendur var á vitlaust póstfang, svona eins og ef einhver hringir í vitlaust númer"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Tölvupóstur sem er gerður til að komast yfir viðkvæmar upplýsingar svo sem kortanúmer");


        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = "Hvað er nettröll?";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Manneskjur sem eru yfir meðalhæð og nota internetið"));
        tempAnswer.add(new Answer("Manneskjur sem skrifa m.a. athugasemdir í þeim tilgangi að særa eða koma af stað rifrildi"));
        tempAnswer.add(new Answer("Manneskjur sem nota internetið á góðann hátt"));
        tempAnswer.add(new Answer("Manneskjur sem skrifa mikið af athugasemdum"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Manneskjur sem skrifa m.a. athugasemdir í þeim tilgangi að særa eða koma af stað rifrildi");


        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = "Ef myndir eru settar á internetið og síðan eytt get ég verið fullviss um að enginn sjái þær síðar?";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Nei, það sem er sett á internetið getur verið þar lengi, önnur manneskja gæti hafa afritað myndina"));
        tempAnswer.add(new Answer("Já, myndinni er eytt og enginn getur séð hana aftur"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Nei, það sem er sett á internetið getur verið þar lengi, önnur manneskja gæti hafa afritað myndina");


        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);
        //////////////////////////////////////
        tempQuestionString = "Setja má inn myndir af hverjum sem er á facebook?";
        tempAnswer = new HashSet<>();
        tempAnswer.add(new Answer("Nei, passa verður að fylgja persónuverndarlögum. Sérstaklega þegar börn og ungmenni á í hlut"));
        tempAnswer.add(new Answer("Já, auðvitað"));
        tempQuestion = new Question(tempQuestionString,tempAnswer, "Nei, passa verður að fylgja persónuverndarlögum. Sérstaklega þegar börn og ungmenni á í hlut");


        // Tengja svörin við spurninga
        for(Answer answer : tempAnswer){
            answer.setQuestion(tempQuestion);
        }
        // Tengjua spurninguna við quizzið
        tempQuestion.setQuiz(tempQuiz);
        // Safna spurning í spurningalista
        tempQuestions.add(tempQuestion);




        // Að lokum, tengja quiz við allar spurningar
        tempQuiz.setQuestions(tempQuestions);
        quizRepo.save(tempQuiz);


     }
}

