package com.kkdigest.myFirstProject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class first {
    @GetMapping("kk")
    public String greeting() {
        return "Hello KK !";
    }
}
