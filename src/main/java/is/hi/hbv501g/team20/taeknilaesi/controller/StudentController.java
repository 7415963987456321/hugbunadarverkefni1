package is.hi.hbv501g.team20.taeknilaesi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import is.hi.hbv501g.team20.taeknilaesi.model.Student;
import is.hi.hbv501g.team20.taeknilaesi.service.StudentService;

@Controller
public class StudentController
{
    @Autowired
    StudentService studentService;

    @GetMapping("/student")
    private List<Student> getAllStudent()
    {
        return studentService.getAllStudent();
    }


    @GetMapping("/student/{id}")
    private Student getStudent(@PathVariable("id") int id)
    {
        return studentService.getStudentById(id);
    }


}
