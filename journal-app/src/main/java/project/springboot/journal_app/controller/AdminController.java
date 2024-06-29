package project.springboot.journal_app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.springboot.journal_app.entity.User;
import project.springboot.journal_app.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> findall(){
           List<User> users = userService.getAll();

           if(!users.isEmpty()){
               return new ResponseEntity<>(users, HttpStatus.OK);
           }
           return new ResponseEntity<>("there are no users right now",HttpStatus.NO_CONTENT);
    }

}
