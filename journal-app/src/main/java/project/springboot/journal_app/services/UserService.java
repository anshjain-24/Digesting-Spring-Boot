package project.springboot.journal_app.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.springboot.journal_app.entity.User;
import project.springboot.journal_app.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getAll (){
        return userRepo.findAll();
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
