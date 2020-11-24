package is.hi.hbv501g.team20.taeknilaesi.controller;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.service.SecurityService;
import is.hi.hbv501g.team20.taeknilaesi.service.UserService;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("/register")
    public ModelAndView registrationForm() {
        return new ModelAndView("registration", "user", new User());
    }

    @PostMapping(value = "/register")
    public ModelAndView registerUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", user);
        }
        if (!isValidEmailAddress(user.getEmail())) {
            result.addError(new FieldError("user", "email", "Tölvupóstur er ekki réttur."));

            return new ModelAndView("registration", "user", user);
        }

        if (userService.IsEmailAlreadyRegistered(user.getEmail())) {
            result.addError(new FieldError("user", "email", "Tölvupóstur er í notkun"));
            return new ModelAndView("registration", "user", user);
        }
        if (!user.getPassword().equals(user.getPasswordConfirmation())) {
            result.addError(new FieldError("user", "password", "Lykilorð eru ekki eins"));
            return new ModelAndView("registration", "user", user);
        }
        userService.registerNewUser(user);
        securityService.login(user.getEmail(), user.getPassword());
        return new ModelAndView("redirect:/");
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}

