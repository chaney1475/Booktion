package com.project.Booktion.controller;


import com.project.Booktion.model.Book;
import com.project.Booktion.service.BookService;
import com.project.Booktion.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {
    private final BookService bookService;
    private final ReviewService reviewService;

    @GetMapping
    public String bookList(Model model, HttpSession session){
        String userId = (String)session.getAttribute("userId");
        if(userId != null){
            session.setAttribute("userId",userId);
        }
        //review많은 순 책 list
        List<Long> topReviewedBookIds = reviewService.getTopReviewBooks();
        log.info("topReviewedBookIds list : " + topReviewedBookIds.toString());
        List<Book> topReviewedBooks = new ArrayList<>();
        for(long id : topReviewedBookIds){
            topReviewedBooks.add(bookService.findById(id));
        }
        log.info("topReviewedBooks list : " + topReviewedBooks.toString());
        model.addAttribute("topReviewedBooks", topReviewedBooks);
        //신간도서 list
        List<Book> latestBooks = bookService.getLatestBooks();
        log.info("latestBooks list : " + latestBooks.toString());
        model.addAttribute("latestBooks", latestBooks);
        return "main";
    }
}
