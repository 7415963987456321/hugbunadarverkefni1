package is.hi.hbv501g.team20.taeknilaesi;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.content.commons.repository.Store;
import org.springframework.content.fs.config.EnableFilesystemStores;
import org.springframework.content.fs.io.FileSystemResourceLoader;
import org.springframework.content.rest.StoreRestResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import is.hi.hbv501g.team20.taeknilaesi.model.Course;
import is.hi.hbv501g.team20.taeknilaesi.model.Lesson;
import is.hi.hbv501g.team20.taeknilaesi.model.Student;
import is.hi.hbv501g.team20.taeknilaesi.repository.CourseRepository;
import is.hi.hbv501g.team20.taeknilaesi.repository.LessonRepository;
import is.hi.hbv501g.team20.taeknilaesi.repository.StudentRepository;

@SpringBootApplication
public class TaeknilaesiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaeknilaesiApplication.class, args);
    }

    //er ekki notað en virkar vel
    @StoreRestResource(path = "videos")
    public interface VideoStore extends Store<String> {
    } 
}


@Component
class DemoCommandLineRunner implements CommandLineRunner {


    @Autowired
    private StudentRepository sr;

    @Autowired
    private CourseRepository cr;

    @Autowired
    private LessonRepository lr;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        sr.save(new Student("unnur", 1935, "unnur@gmail.com"));
        sr.save(new Student("jon", 1935, "jon@gmail.com"));

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

        Course c1 = new Course(1,"Grunnatridi","Þetta námskeið fjallar um grunnotkun á spjaltölvu og utśkýrir mikilvæg hugtök sem þar koma fram.", tmpLessons);
        cr.save(c1);

        tmpLessons.clear();
        tmpLessons.add(l3);
        tmpLessons.add(l4);
        tmpLessons.add(l5);
        tmpLessons.add(l6);
        tmpLessons.add(l7);

        Course c2 = new Course(3, "Internetið", "Hér verður fjallað um internetið, hvað það er, hvernig það varð til og hvernig notar maður það.", tmpLessons);
        cr.save(c2);

    }
}
