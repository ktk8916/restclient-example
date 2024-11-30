package com.study.restclient.infrastructure;

import com.study.restclient.domain.Photo;
import com.study.restclient.domain.PhotoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JsonPlaceholderPhotoClient implements PhotoClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/photos";
    private static final String APPLICATION_JSON = "application/json";

    private final RestClient restClient;

    @Override
    public Photo fetchByPhotoId(long photoId) {
        return restClient.get()
                .uri(BASE_URL + "/{photoId}", photoId)
                .header("Content-Type", APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ExternalApiException(String.format("failed to fetch photo with id %d", photoId));
                })
                .body(Photo.class);
    }

    @Override
    public List<Photo> fetchByAlbumId(long albumId) {
        URI albumUri = UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("albumId", albumId)
                .build()
                .toUri();
        return restClient.get()
                .uri(albumUri)
                .header("Content-Type", "application/json")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new ExternalApiException(String.format("failed to fetch album with id %d", albumId));
                })
                .body(new ParameterizedTypeReference<>() {});
    }
}
