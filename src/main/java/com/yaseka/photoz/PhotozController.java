package com.yaseka.photoz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                 //Helping spring boot to understand our controller function
public class PhotozController {
    @GetMapping("/")            //Specify the path which function will be activated
    public String greetings(){
        return "Hello World";
    }
}
