package is.hi.hbv501g.team20.taeknilaesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import is.hi.hbv501g.team20.taeknilaesi.model.ConfirmationToken;
import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.repository.ConfirmationTokenRepository;
import is.hi.hbv501g.team20.taeknilaesi.repository.UserRepository;
import is.hi.hbv501g.team20.taeknilaesi.service.EmailService;
import is.hi.hbv501g.team20.taeknilaesi.service.SecurityService;
import is.hi.hbv501g.team20.taeknilaesi.service.UserService;

@Controller
public class ForgotPasswordController {
    @Autowired
    UserService userService;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository UserRepository;

    @Autowired
    SecurityService securityService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/forgotPassword")
    public ModelAndView forgotPasswordForm(){
        return new ModelAndView("forgotPassword","user", new User());
    }

    @PostMapping("/forgotPassword")
    public ModelAndView forgotPassword(ModelAndView m,User user, BindingResult result){
        if (!userService.IsEmailAlreadyRegistered(user.getEmail())) {
            result.addError(new FieldError("user","email","Tölvupóstur er ekki til"));
            return new ModelAndView("forgotPassword","user",user);
        }

            User registeredUser = userService.findUserByEmail(user.getEmail());
            ConfirmationToken confirmationToken = new ConfirmationToken(registeredUser);

            System.out.println("@@@@@@@@@@@@@@@");
            // Save it
            confirmationTokenRepository.save(confirmationToken);

            // Create the email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(registeredUser.getEmail());
            mailMessage.setSubject("Endurnýna lykilorð!");
            mailMessage.setFrom("taeknilaesifyrirfullordna@gmail.com");
            mailMessage.setText("Smelltu á hlekkinn til að endurnýa lykilorðið: "
                    + "http://localhost:8080/forgotPasswordRestore?token=" + confirmationToken.getConfirmationToken());

            // Send the email
            emailService.sendEmail(mailMessage);
            m.addObject("user", registeredUser);
            m.setViewName("forgotPasswordEmailSent");

        return m;
    }

    @GetMapping("/forgotPasswordRestore")
    public ModelAndView resetUserPasswordView(ModelAndView modelAndView, @RequestParam("token") String confirmationToken){
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
        if(token != null){
            User user = userService.findUserByEmail(token.getUser().getEmail());
            modelAndView.addObject("user", user);
            modelAndView.addObject("email", user.getEmail());
            modelAndView.setViewName("forgotPasswordRestore");
            modelAndView.addObject("token", token.getConfirmationToken());
            System.out.println(modelAndView.getModelMap());
        }
        return modelAndView;
    }

    // @PostMapping("/forgotPasswordRestoreSuccess")
    // public ModelAndView resetUserPassword(@ModelAttribute("userForm") User user) {
        
    //         System.out.println("@@@@@@@@@@EMAIL" + user.getEmail());
    //     // ConfirmationToken token =
    //     // confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    //     ModelAndView modelAndView = new ModelAndView();
    //     if (user.getEmail() != null) {
    //         // use email to find user
    //         // User tokenUser = userService.findUserByEmail(user.getEmail());
    //         // tokenUser.setPassword(passwordEncoder.encode(user.getPassword()));
    //         // System.out.println(tokenUser.getPassword());
    //         // userRepository.save(tokenUser);
    //         modelAndView.addObject("message",
    //                 "Lykilorð hefur verið endurnýað.");
    //         modelAndView.setViewName("forgotPasswordRestoreSuccess");
    //     } else {
    //         // modelAndView.addObject("message", "Villa");
    //         // modelAndView.setViewName("error");
    //         System.out.println("error");
    //     }
        
    //     return modelAndView;
    // }
    @PostMapping("forgotPasswordRestoreSuccess")
    public ModelAndView resetUserPassword(@RequestParam("password") final String password, @RequestParam("passwordConfirmation") final String passwordConfirmation, @RequestParam("token") String token){
        ModelAndView m = new ModelAndView();
        if (!password.equals(passwordConfirmation)) {
            m.addObject("errorMessage","Lykilorð eru ekki eins");
            m.setViewName("forgotPasswordRestore");
            return m;
        }

        ConfirmationToken ct = securityService.getPasswordResetToken(token);

        if (ct != null) {
            User user = ct.getUser();
            System.out.println("@@@@@@@@@@@@@@@@@@");
            userService.changeUserPassword(user, password);
            m.setViewName("forgotPasswordRestoreSuccess");
        }
        return m;
    }
}
