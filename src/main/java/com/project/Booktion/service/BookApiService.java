package com.project.Booktion.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Booktion.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookApiService {
    private final String API_URL = "https://dapi.kakao.com/v3/search/book";
    private final String REST_API_KEY = "965bad6936add0f81a377b0186e1758b";

    public List<Book> getBooksByTitle(String query) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + REST_API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = "https://dapi.kakao.com/v3/search/book?target=title&query=" + query;

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode rootNode = objectMapper.readTree(response.getBody());
                JsonNode documentsNode = rootNode.path("documents");
                TypeReference<List<Book>> typeReference = new TypeReference<List<Book>>() {};
                List<Book> books = objectMapper.readValue(documentsNode.toString(), typeReference);
                return books;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }public Book getBookByISBN(String isbn) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + REST_API_KEY);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = "https://dapi.kakao.com/v3/search/book?target=isbn&query=" + isbn;

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode rootNode = objectMapper.readTree(response.getBody());
                JsonNode documentsNode = rootNode.path("documents");
                if (documentsNode.isArray() && documentsNode.size() > 0) {
                    JsonNode firstDocumentNode = documentsNode.get(0);
                    Book book = objectMapper.treeToValue(firstDocumentNode, Book.class);
                    return book;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
