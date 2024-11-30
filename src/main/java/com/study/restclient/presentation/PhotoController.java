package com.study.restclient.presentation;

import com.study.restclient.application.PhotoQueryUseCase;
import com.study.restclient.domain.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PhotoController {

    private final PhotoQueryUseCase photoQueryUseCase;

    @GetMapping("/v1/photos/{photoId}")
    public Photo findById(@PathVariable long photoId) {
        return photoQueryUseCase.findById(photoId);
    }
}
