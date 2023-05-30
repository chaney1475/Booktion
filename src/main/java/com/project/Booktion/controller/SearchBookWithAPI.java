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

    @GetMapping("/{bookType}")
    public String searchBook( Model model, @PathVariable String bookType) {
        model.addAttribute("bookType",bookType);
        return "searchApi";
    }
    @PostMapping("/{bookType}")
    public String searchBook(@RequestParam String keyword, Model model, @PathVariable String bookType) {
        model.addAttribute(bookType);
        List<Book> books = bookApiService.getBooksByTitle(keyword);
        model.addAttribute("books", books);
        return "searchApi";
    }
}
