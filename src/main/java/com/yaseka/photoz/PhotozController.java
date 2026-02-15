package com.yaseka.photoz;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController                 //Helping spring boot to understand our controller function
public class PhotozController {

    private Map<String,Photo> db = new HashMap<>(){{
        put("1",new Photo("1","photo1"));
        put("2",new Photo("2","photo2"));
    }};

    @GetMapping("/")            //Specify the path which function will be activated
    public String greetings(){
        return "Hello World";
    }

    @GetMapping("/photoz")
    public Collection<Photo> getPhotos(){
        return db.values();
    }

    @GetMapping("/photoz/{id}")
    public Photo getPhotoWithID(@PathVariable String id) throws Throwable {
        Photo photo = db.get(id);
        if (photo != null){
            return photo;
        }
        else {
            throw new Throwable(String.valueOf(HttpStatus.NOT_FOUND));
        }
    }
}
