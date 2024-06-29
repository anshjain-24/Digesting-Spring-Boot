package project.springboot.journal_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.springboot.journal_app.entity.User;
import project.springboot.journal_app.services.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;


    @GetMapping("/health-check")
    public String health(){
        return "OK, Done Hai, Jao KAM KARO";
    }

    @PostMapping("/add-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }


}
