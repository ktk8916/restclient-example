package com.study.restclient.domain;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository {

    Optional<Photo> findById(long photoId);
    <S extends Photo> List<S> saveAll(Iterable<S> photos);
}
