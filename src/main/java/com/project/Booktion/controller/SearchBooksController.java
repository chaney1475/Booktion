package com.project.Booktion.controller;

import com.project.Booktion.controller.usedBook.UsedBookRegist;
import com.project.Booktion.model.Book;
import com.project.Booktion.model.UsedBook;
import com.project.Booktion.service.SearchBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchBooksController {
    private final SearchBookService searchBookService;

    @GetMapping("/usedForm")
    public String searchForUsedForm(@RequestParam String type,
                                    @RequestParam String keyword,
                                    @ModelAttribute("usedBookRegist")UsedBookRegist UsedBookRegist){
        List<Book> bookList;
        if(type.equals("제목")){
            bookList = searchBookService.searchByTitle(keyword);
        }
        else if(type.equals("isbn")){
            bookList = searchBookService.searchByIsbn(keyword);
        }

        return "/used/searchResult";
    }
    @PostMapping("/all")
    public String allBookSearch(){
        return "main";
    }
}
