package is.hi.hbv501g.team20.taeknilaesi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;

@Service
public class UserService implements UserDetailsService
{
    private static final String ROLE_USER = "ROLE_USER";

    @Autowired
    UserRepository userRepository;
       @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User getUserById(int id){
        return userRepository.findById(id);
    }

    //hlutur af spring security
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true, true, getAuthorities(ROLE_USER));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

    public User findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    public Boolean IsEmailAlreadyRegistered(String email){
        final User user = userRepository.findByEmail(email);
        return user != null;
    }

    public void changeUserPassword(final User user, final String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public User findUserByUsername(String email) {
        return userRepository.findByEmail(email);
    }
    // public void save
    // public void autoLogin(String username, String password) {
    //     UserDetails userDetails = loadUserByUsername(username);
    //     UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

    //     authenticationManager.authenticate(usernamePasswordAuthenticationToken);

    //     if (usernamePasswordAuthenticationToken.isAuthenticated()) {
    //         SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    //     }
    // }
}
