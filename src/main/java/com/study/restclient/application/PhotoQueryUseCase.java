package com.study.restclient.application;

import com.study.restclient.domain.Photo;
import com.study.restclient.domain.PhotoReader;
import com.study.restclient.domain.PhotoStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhotoQueryUseCase {

    private final PhotoReader photoReader;
    private final PhotoStore photoStore;

    public Photo findById(long photoId) {
        return photoReader.readOptional(photoId)
                .orElseGet(() -> photoStore.fetchByIdWithCaching(photoId));
    }
}
