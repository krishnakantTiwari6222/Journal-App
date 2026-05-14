package web.journal.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.journal.journalApp.entity.User;
import web.journal.journalApp.service.UserService;

import java.util.List;

@RequestMapping("/public")
@RestController
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health")
    public String check(){
        return "ok";
    }

    @GetMapping("get-all")
    public List<User> getAlluser(){
        return userService.getAll();
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> create(@RequestBody User user){
        userService.SaveNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
