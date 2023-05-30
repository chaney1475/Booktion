package com.project.Booktion.service;
import com.project.Booktion.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import java.util.Date;
@Slf4j
@Service
public class BookApiService {
    private static final String API_KEY = "04f157ffde91c9dd2266f533f6259e8f9ec3f237c6b629aaa6abc01c21df6cc4";
    private static final String API_URL = "https://www.nl.go.kr/seoji/SearchApi.do";

    public List<Book> getBooksByTitle(String title)  {
        WebClient client = WebClient.create(API_URL);

        ApiResponse response = client.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("cert_key", API_KEY)
                        .queryParam("result_style", "json")
                        .queryParam("page_no", 1)
                        .queryParam("page_size", 10)
                        .queryParam("title", title)
                        .build())
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .block(); // block()은 Flux를 Mono로 변환하기 위해 사용됩니다.

        if (response != null && response.getDocuments() != null){
            return response.getDocuments().stream()
                    .map(this::mapToBook)
                    .collect(Collectors.toList());
        }else{
            System.out.println("bookList error");
            return null;
        }
    }

    private Book mapToBook(ApiResponse.BookDocument document) {
        Book book = new Book();
        book.setTitle(document.getTitle());
        book.setAuthor(String.join(", ", document.getAuthor()));
        book.setIsbn(document.getIsbn());
        book.setPublisher(document.getPublisher());
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date pubDate = formatter.parse(document.getPublishPredate());
            book.setPubDate(pubDate);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return book;
    }
}
