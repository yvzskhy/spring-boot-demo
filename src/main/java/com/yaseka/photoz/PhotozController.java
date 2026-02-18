package com.yaseka.photoz;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import java.io.IOException;
import java.util.*;

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

    @DeleteMapping("/photoz/{id}")
    public Photo deletePhotoWithID(@PathVariable String id) throws Throwable {
        Photo photo = db.get(id);
        if (photo != null){
            return db.remove(id);
        }
        else {
            throw new Throwable(String.valueOf(HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile photo) throws IOException {
        Photo newPhoto = new Photo();
        newPhoto.setId(UUID.randomUUID().toString());
        newPhoto.setFileName(photo.getOriginalFilename());
        newPhoto.setData(photo.getBytes());

        db.put(newPhoto.getId(),newPhoto);
        return newPhoto;
    }
}
