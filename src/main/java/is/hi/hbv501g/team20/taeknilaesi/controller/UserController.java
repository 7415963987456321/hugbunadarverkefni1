package is.hi.hbv501g.team20.taeknilaesi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.service.UserService;

@Controller
public class UserController
{
    @Autowired
    UserService userService;

    @GetMapping("/student")
    private List<User> getAllUser()
    {
        return userService.getAllUser();
    }


    @GetMapping("/student/{id}")
    private User getUser(@PathVariable("id") int id)
    {
        return userService.getUserById(id);
    }

    @GetMapping("/login")
    private String getLogin(){
        return "login";
    }


    @GetMapping(value = "signup")
    public ModelAndView registrationForm() {
        return new ModelAndView("registrationPage", "user", new User());
    }


   }