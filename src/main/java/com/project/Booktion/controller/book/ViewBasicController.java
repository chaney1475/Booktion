package com.project.Booktion.controller.book;

import com.project.Booktion.model.Book;
import com.project.Booktion.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
@SessionAttributes("book")
public class ViewBasicController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public String viewBook(@PathVariable("id") String bookId, Model model) {
        //책 상세페이지로 이동, 책이 없으면 noBook 페이지로 이동
        Book book = bookService.findById(bookId);
        if(book == null) {
            return "noBook";
        }
        model.addAttribute("book", book);
        return "bookDetails";
    }
}
