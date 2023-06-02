package com.project.Booktion.controller.book;

import com.project.Booktion.model.Book;
import com.project.Booktion.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookListController {

    private final BookService bookService;

    @GetMapping("/list")
    public String viewBookList(Model model) {
        List<Book> bookList = bookService.getBooksByBookType(1);
        model.addAttribute("bookList", bookList);
        return "book/bookList";
    }

}