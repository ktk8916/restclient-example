package com.study.restclient.domain;

import java.util.List;

public record Album(
        List<Photo> photos
) {
}
