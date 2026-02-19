package com.yaseka.photoz;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PhotozService {

    private Map<String,Photo> db = new HashMap<>(){{
        put("1",new Photo("1","photo1"));
        put("2",new Photo("2","photo2"));
    }};

    public Collection<Photo> getPhotos() {
        return db.values();
    }

    public Photo get(String id) {
        return db.get(id);
    }

    public Photo remove(String id) {
        return db.remove(id);
    }

    public Photo save(String filename, @Nullable String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(filename);
        photo.setContentType(contentType);
        photo.setData(data);

        db.put(photo.getId(),photo);

        return photo;

    }


}
