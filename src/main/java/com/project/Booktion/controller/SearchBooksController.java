package com.project.Booktion.controller;

import com.project.Booktion.model.AuctionBook;
import com.project.Booktion.model.Book;
import com.project.Booktion.repository.AuctionBookRepository;
import com.project.Booktion.repository.BookRepository;
import com.project.Booktion.repository.UsedBookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchBooksController {
    private final AuctionBookRepository auctionBookRepository;
    private final BookRepository bookRepository;

    @PostMapping
    String searchByBook(HttpServletRequest request, @RequestParam("query") String query, Model model){
        String selectedType = request.getParameter("type");

        // 책타입에 따라 검색
        if (selectedType.equals("book")) {
            // 일반 도서 검색
            List<Book> bookList = bookRepository.findByBookTypeAndTitleContaining(1,query);
            model.addAttribute("bookList", bookList);
            return "book/bookList";
        } else if (selectedType.equals("used")) {
            // 중고 책 검색
            List<Book> bookList = bookRepository.findByBookTypeAndTitleContaining(2,query);
            model.addAttribute("bookList", bookList);
            return "used/searchResult";
        } else if (selectedType.equals("auction")) {
            // 경매 책 검색
            List<AuctionBook> auctionBooks = auctionBookRepository.findByBookTitleContaining(query);
            model.addAttribute("auctionBooks", auctionBooks);
            return "auction/books";
        }
        return "noBook";
    }
}
