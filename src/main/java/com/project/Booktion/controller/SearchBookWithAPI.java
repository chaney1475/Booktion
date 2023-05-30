package com.project.Booktion.controller;

import com.project.Booktion.model.Book;
import com.project.Booktion.service.BookApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/search")
public class SearchBookWithAPI {
    final private BookApiService bookApiService;
    @GetMapping
    public String searchBook(@RequestParam String keyword, Model model) {
        List<Book> books = bookApiService.getBooksByTitle(keyword);
        for (Book book : books) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("ISBN: " + book.getIsbn());
            System.out.println("Publisher: " + book.getPublisher());
            System.out.println("Publication Date: " + book.getPubDate());
            System.out.println("----------------------------------");
        }
        model.addAttribute("books", books);
        return "searchResultForApi";
    }
}
