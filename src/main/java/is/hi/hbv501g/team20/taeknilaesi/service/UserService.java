package is.hi.hbv501g.team20.taeknilaesi.service;

import java.util.ArrayList;
import java.util.List;

import is.hi.hbv501g.team20.taeknilaesi.model.User;
import is.hi.hbv501g.team20.taeknilaesi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
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
	}
}
