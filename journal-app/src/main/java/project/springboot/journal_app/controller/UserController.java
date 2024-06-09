package project.springboot.journal_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.springboot.journal_app.entity.User;
import project.springboot.journal_app.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> finall(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser( @RequestBody User user,@PathVariable("username") String username){
        User userInDB = userService.findByUserName(username);
        if(userInDB != null){
            userInDB.setUsername(user.getUsername());
            userInDB.setPassword(user.getPassword());
            userService.saveUser(userInDB);
            return new ResponseEntity<>(userInDB,HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("User not found with this username",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody String username){
        User user = userService.findByUserName(username);
        userService.deleteUser(user.getId());
        return new ResponseEntity<>("Deleted user with id : " + user.getId(),HttpStatus.ACCEPTED);
    }
}
