package is.hi.hbv501g.team20.taeknilaesi.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import is.hi.hbv501g.team20.taeknilaesi.dto.CommentDTO;
import is.hi.hbv501g.team20.taeknilaesi.model.Comment;
import is.hi.hbv501g.team20.taeknilaesi.model.Lesson;
import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.service.CommentService;
import is.hi.hbv501g.team20.taeknilaesi.service.LessonService;
import is.hi.hbv501g.team20.taeknilaesi.service.UserService;

@RestController
@RequestMapping("/course/{id}/{lessonId}/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    LessonService lessonService;

    @PostMapping(value = "/post", consumes = "application/json",
      produces = "application/json")

      public Comment postCustomer(@PathVariable("id") int id, @PathVariable int lessonId, @RequestBody CommentDTO cDto) {
          // System.out.println("@@@@@@@@@" + comment);
         // User user = userService.getUserById(cDto.getUserName());
        // Lesson lesson = lessonService.getLessonById(cDto.getLessonId());
        Comment comment = new Comment(cDto.getLessonId(),cDto.getMessage(),cDto.getUserName());
        commentService.SaveNewPost(comment);
        // Comment comment2 = new Comment();
        // System.out.println("@@@@lessonId: " + cDto.getLessonId());
        return comment;
    }
}
