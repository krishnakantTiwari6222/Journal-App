package com.kkdigest.myFirstProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class first {

    @Autowired //It is used for the dependency injection it injects the dependencies of another class
    private Dog dog;  // it is the dependency of the dog class as a field

    @GetMapping("/kk")
    public String ok(){
        return Dog.fun();
    }
}
