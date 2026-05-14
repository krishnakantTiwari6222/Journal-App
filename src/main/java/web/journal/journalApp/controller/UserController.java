package web.journal.journalApp.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.journal.journalApp.entity.User;
import web.journal.journalApp.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping ("/User")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        userService.SaveNewUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/id" + "{myId}")
    public Optional<User> findById (@PathVariable ObjectId myId){
        return userService.getById(myId);
    }

    @DeleteMapping("/id" + "{myId}")
    public boolean deletebyId(@PathVariable ObjectId myId){
        userService.deleteaById(myId);
        return true;
    }

    @PutMapping()
    public ResponseEntity<?> updateById(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User update = userService.findByUsername(userName);
        if( update != null){
            update.setUserName(user.getUserName());
            update.setPassword(user.getPassword());
            userService.SaveUser(update);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
