package is.hi.hbv501g.team20.taeknilaesi.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import is.hi.hbv501g.team20.taeknilaesi.model.Lesson;
import is.hi.hbv501g.team20.taeknilaesi.service.LessonService;

//ekki notað eins og er, kannski ekki þörf?
@Controller
public class LessonController {
    @Autowired
    LessonService lessonService;

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
}
