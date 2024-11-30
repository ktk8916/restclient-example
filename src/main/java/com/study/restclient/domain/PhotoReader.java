package com.study.restclient.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PhotoReader {

    private final PhotoRepository photoRepository;

    public Optional<Photo> readOptional(long photoId) {
        return photoRepository.findById(photoId);
    }
}
