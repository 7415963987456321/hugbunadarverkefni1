package is.hi.hbv501g.team20.taeknilaesi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.service.UserService;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    // dæmi um hvernig er hægt að sækja notenda
    @GetMapping("/user")
    private void returnAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // getUsername() - Returns the username used to authenticate the user.
        System.out.println("User name: " + userDetails.getUsername());

        //er ekki að nota þetta, kannski seinna
        // getAuthorities() - Returns the authorities granted to the user.
        //System.out.println("User has authorities: " + userDetails.getAuthorities());
    }

    @GetMapping("/login")
    private String getLogin() {
        return "login";
    }

    @GetMapping(value = "/signup")
    public ModelAndView registrationForm() {
        return new ModelAndView("registration", "user", new User());
    }

    @RequestMapping(value = "/register")
    public ModelAndView registerUser(@Valid User user, BindingResult result){
        System.out.println(result.getErrorCount());
        if(result.hasErrors()){
            return new ModelAndView("registration","user",user);
        }
        if (userService.IsEmailAlreadyRegistered(user.getEmail())) {
            result.addError(new FieldError("user", "email", "Tólvupóstur er í notkun"));
            return new ModelAndView("registration", "user", user);
        }
        if (!user.getPassword().equals(user.getPasswordConfirmation())){
            result.addError(new FieldError("user", "password", "Lykilorðin eru ekki eins"));
            return new ModelAndView("registration", "user", user); 
        }
        userService.registerNewUser(user);
        return new ModelAndView("redirect:/login");
    }

}
