package com.study.restclient.domain;

import java.util.List;

public interface PhotoClient {

    Photo fetchByPhotoId(long photoId);

    List<Photo> fetchByAlbumId(long albumId);

}
