package com.project.Booktion.controller;


import com.project.Booktion.model.Book;
import com.project.Booktion.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {
    private final BookService bookService;
    @GetMapping
    public String bookList(Model model, HttpSession session){
        String userId = (String)session.getAttribute("userId");
        model.addAttribute("userId",userId);
        log.debug("mainController","worked");
        return "main";
    }
}
