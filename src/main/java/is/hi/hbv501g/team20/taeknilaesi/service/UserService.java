package is.hi.hbv501g.team20.taeknilaesi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService
{
    @Autowired
    UserRepository userRepository;
    //getting all student records
    public List<User> getAllUser()
    {
        List<User> students = new ArrayList<User>();
        userRepository.findAll().forEach(student -> students.add(student));
        return students;
    }
    //getting a specific record
    public User getUserById(int id)
    {
        return userRepository.findById(id).get();
    }
    public void saveOrUpdate(User student)
    {
        userRepository.save(student);
    }
    //deleting a specific record
    public void delete(int id)
    {
        userRepository.deleteById(id);
    }
	public void registerNewUser(User user) {
        userRepository.save(user);
	}

    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        // final User user = userRepository.findByEmail(email);
        final User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true,
                true, true, getAuthorities("ROLE_USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }
}
