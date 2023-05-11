package com.project.Booktion.controller;

import com.project.Booktion.model.Book;
import com.project.Booktion.service.SearchBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class SearchBooksController {
        private final SearchBookService searchBookService;

        @GetMapping("/main/search")
        public String searchBooks(String keyword, Model model) {
            List<Book> searchList = searchBookService.search(keyword);

            model.addAttribute("searchList", searchList);

            return "searchList";
        }
}
