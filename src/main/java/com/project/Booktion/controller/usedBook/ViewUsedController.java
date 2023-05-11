package com.project.Booktion.controller.usedBook;

import com.project.Booktion.model.UsedBook;
import com.project.Booktion.service.UsedBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/used")
@RequiredArgsConstructor
public class ViewUsedController {
    private final UsedBookService usedBookService;

    @RequestMapping("/{bookId}")
    public String showBookInfo(@PathVariable String bookId, Model model){
        //책 상세페이지로 이동
        UsedBook book = usedBookService.getUsedBook(bookId);
        if(book == null){
            return "noBook"; //상품이 없다는 페이지로 이동
        }
        model.addAttribute("book", book);
        return "usedBookInfo";
    }
}
