package br.com.coffeeandit.graphql.domain;

import br.com.coffeeandit.graphql.model.Info;
import br.com.coffeeandit.graphql.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookInfoService {

    public static final String INFO_URL = "info_url";
    public static final String PREVIEW_URL = "preview_url";
    public static final String THUMBNAIL_URL = "thumbnail_url";
    public static final String BIB_KEY = "bib_key";
    @Value("${books.openlibrary.url}")
    private String url;

    public BookInfoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private RestTemplate restTemplate;

    public Optional<Info> findBookInfo(final Book book) throws JsonProcessingException {

        var entity = restTemplate.getForEntity(url.replaceAll("ISBN_VALUE", book.getIsbn()), String.class);
        if (entity.getStatusCode() == HttpStatus.OK) {

            var entityBody = entity.getBody();
            Info info = getInfo(book, entityBody);
            return Optional.of(info);
        }
        return Optional.empty();
    }

    private Info getInfo(Book book, String entityBody) throws JsonProcessingException {
        var info = new Info();
        var objectMapper = new ObjectMapper();
        try {
            var hashMap = objectMapper.readValue(entityBody, HashMap.class);
            var isbnData = (HashMap<String, String>) hashMap.get("ISBN:" + book.getIsbn());
            info.setId(UUID.randomUUID().toString());
            info.setInfoUrl(isbnData.get(INFO_URL));
            info.setBibKey(isbnData.get(BIB_KEY));
            info.setPreviewUrl(isbnData.get(PREVIEW_URL));
            info.setThumbnailUrl(isbnData.get(THUMBNAIL_URL));
        } finally {
            objectMapper = null;
        }
        return info;
    }
}


