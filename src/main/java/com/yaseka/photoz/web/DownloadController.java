package com.yaseka.photoz.web;

import com.yaseka.photoz.model.Photo;
import com.yaseka.photoz.service.PhotozService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DownloadController {

    private PhotozService photozService;

    public DownloadController(PhotozService photozService) {
        this.photozService = photozService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable String id){

        Photo photo = photozService.get(id);

        if (photo == null){ throw new ResponseStatusException(HttpStatus.NOT_FOUND);}
        String name = photo.getFileName();
        byte[] data = photo.getData();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(photo.getContentType()));
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(name)
                .build();
        headers.setContentDisposition(contentDisposition);


        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

}
