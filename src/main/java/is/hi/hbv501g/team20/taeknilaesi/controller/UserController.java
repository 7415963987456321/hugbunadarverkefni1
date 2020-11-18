package is.hi.hbv501g.team20.taeknilaesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import is.hi.hbv501g.team20.taeknilaesi.repository.UserRepository;
import is.hi.hbv501g.team20.taeknilaesi.service.UserService;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository UserRepository;


    // dæmi um hvernig er hægt að sækja notenda
    @GetMapping("/user")
    private void returnAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // getUsername() - Returns the username used to authenticate the user.
        System.out.println("User name: " + userDetails.getUsername());

        //er ekki að nota þetta, kannski seinna
        // getAuthorities() - Returns the authorities granted to the user.
        //System.out.println("User has authorities: " + userDetails.getAuthorities());
    }
}
