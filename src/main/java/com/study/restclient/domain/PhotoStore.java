package com.study.restclient.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PhotoStore {

    private final PhotoClient photoClient;
    private final PhotoRepository photoRepository;

    public Photo fetchByIdWithCaching(long photoId) {
        Photo photo = photoClient.fetchByPhotoId(photoId);
        List<Photo> photos = photoClient.fetchByAlbumId(photo.getAlbumId());
        photoRepository.saveAll(photos);
        return photo;
    }

}
