package com.project.Booktion.controller;


import com.project.Booktion.model.Book;
import com.project.Booktion.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class MainController {
    private final BookService bookService;
    public String bookList(Model model){
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "main";
    }
}
