package com.project.Booktion.controller.usedBook;

import com.project.Booktion.service.UsedBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/used/order")
@RequiredArgsConstructor
public class UsedOrderFromController {
    private final UsedBookService usedBookService;

    @GetMapping("/{bookId}")
    public String newForm(@PathVariable String bookId,
                          @ModelAttribute("usedBookOrder") UsedBookOrder usedBookOrder){
        //중고 주문폼 생성
        //고민이 있음 여기서 주문하는 user의 정보를 어떻게 불러오지?
        //usedBookOrder.setBuyerId();
        return "usedForm";
    }

    @PostMapping("/{bookId}")
    public String addForm(@PathVariable String bookId,
                          @ModelAttribute("usedBookOrder") UsedBookOrder usedBookOrder){
        //중고 주문폼 제출
        usedBookService.submitOrderForm(usedBookOrder);
        return "";//주문완료 화면이 어디로 가는지?
    }
}
