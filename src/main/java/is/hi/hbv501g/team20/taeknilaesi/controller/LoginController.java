package is.hi.hbv501g.team20.taeknilaesi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.service.SecurityService;
import is.hi.hbv501g.team20.taeknilaesi.service.UserService;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/login")
    private ModelAndView getLogin() {

        return new ModelAndView("login", "user", new User());
    }

   @PostMapping("/loginProcess")
    public ModelAndView loginForm(User user) {
        // if(result.hasErrors()){
        //     return new ModelAndView("login", "user", user);
        // } 
        if(!userService.IsEmailAlreadyRegistered(user.getEmail()) || !securityService.isPasswordCorrect(user.getEmail(),user.getPassword())){
            ModelAndView m = new ModelAndView();
            m.addObject("errorMessage", "Rangt notendanafn eða lykilorð");
            m.addObject("user", new User());
            m.setViewName("login");
            return m;
        }
              securityService.login(user.getEmail(),user.getPassword());
        return new ModelAndView("redirect:/");
    }
}
