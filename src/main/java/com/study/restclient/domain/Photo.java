package com.study.restclient.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Photo {

    @Id
    private Long id;
    private Long albumId;
    private String title;
    private String url;
    private String thumbnailUrl;
}
