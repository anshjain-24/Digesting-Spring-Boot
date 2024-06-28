package project.springboot.journal_app.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import project.springboot.journal_app.entity.User;
import project.springboot.journal_app.repository.UserRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAll (){
        return userRepo.findAll();
    }

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepo.save(user);
    }

    public void saveUser(User user){
        userRepo.save(user);
    }

    public void deleteUser(ObjectId id){
        userRepo.deleteById(id);
    }

    public Optional<User> getByID(ObjectId id){
        return userRepo.findById(id);
    }

    public User findByUserName(String username) { return userRepo.findByUsername(username); }

}
