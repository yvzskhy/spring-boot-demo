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

    private PhotozService photozService;

    public PhotozController(PhotozService photozService) {
        this.photozService = photozService;
    }


    @GetMapping("/")            //Specify the path which function will be activated
    public String greetings(){
        return "Hello World";
    }

    @GetMapping("/photoz")
    public Collection<Photo> getPhotos(){
        return photozService.getPhotos();
    }

    @GetMapping("/photoz/{id}")
    public Photo getPhotoWithID(@PathVariable String id) throws Throwable {
        Photo photo = photozService.get(id);
        if (photo != null){
            return photo;
        }
        else {
            throw new Throwable(String.valueOf(HttpStatus.NOT_FOUND));
        }
    }

    @DeleteMapping("/photoz/{id}")
    public Photo deletePhotoWithID(@PathVariable String id) throws Throwable {
        Photo photo = photozService.get(id);
        if (photo != null){
            return photozService.remove(id);
        }
        else {
            throw new Throwable(String.valueOf(HttpStatus.NOT_FOUND));
        }
    }

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return photozService.save(file.getOriginalFilename(),file.getContentType(),file.getBytes());
    }
}
