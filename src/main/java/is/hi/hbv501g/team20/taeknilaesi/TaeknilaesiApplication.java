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

        Lesson l1 = new Lesson(1,"Inngangur","Lorem ipsum dolor sit amet, consectetuer adipiscing elit.","1.1_Hvad_er_spjaldtolva.mp4");
        lr.save(l1);

        Lesson l2 = new Lesson(1,"Hvad er spjaldtolva","","");
        lr.save(l2);

        Lesson l3 = new Lesson(2, "Klukkan","","");
        lr.save(l3);

        Lesson l4 = new Lesson(2, "Dagatal","","");
        lr.save(l4);

        ArrayList<Lesson> tmpLessons = new ArrayList<>();
        tmpLessons.add(l1);
        tmpLessons.add(l2);

        Course c1 = new Course(1,"Grunnatridi","Þetta námskeið fjallar um grunnotkun á spjaltölvu og utśkýrir mikilvæg hugtök sem þar koma fram.", tmpLessons);
        cr.save(c1);

        tmpLessons.clear();
        tmpLessons.add(l3);
        tmpLessons.add(l4);

        Course c2 = new Course(2, "Grunnopp", "Hér verða helstu öpp sýnt sem fylgja spjaldtölvu.", tmpLessons);
        cr.save(c2);

    }
}
