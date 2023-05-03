package com.project.Booktion.controller.usedBook;

import com.project.Booktion.model.UsedBook;
import com.project.Booktion.service.UsedBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j // 로그 찍는 기능
@Controller
@RequiredArgsConstructor
public class SellingUsedController {
    private final UsedBookService usedBookService;

    @GetMapping("/{userId}/used/selling")
    public String getSellingUsedBooks(@PathVariable("memberId") String memberId, Model model) {
        //판매중인 중고책 목록을 가져온다.
        List<UsedBook> sellingUsedBooks = usedBookService.getSellingUsedBooks(memberId);
        model.addAttribute("sellingUsedBooks", sellingUsedBooks);
        return "sellingUsedBookList";
    }
}
