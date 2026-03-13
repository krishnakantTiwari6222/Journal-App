package com.kkdigest.myFirstProject;

import org.springframework.stereotype.Component;

@Component // It is used for the creating a new beam in the IOC (Innversion of Control) Container;
public class Dog {
    // Dog is the type of dependency in for the first class
    public static String fun(){
        return "Wooh Wooh";
    }
}
