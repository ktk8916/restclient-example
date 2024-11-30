package com.study.restclient.infrastructure;

import com.study.restclient.domain.Photo;
import com.study.restclient.domain.PhotoRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoJpaRepository extends PhotoRepository, JpaRepository<Photo, Long> {
}
