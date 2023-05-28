package com.project.Booktion.controller.usedBook;

import com.project.Booktion.model.Order;
import com.project.Booktion.model.User;
import com.project.Booktion.service.UsedBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/used/order")
@RequiredArgsConstructor
public class UsedOrderFormController {
    private final UsedBookService usedBookService;

    @ModelAttribute("usedBookOrder")
    public UsedBookOrder formData(){
        return new UsedBookOrder();
    }

    @GetMapping("/{bookId}")
    public String newForm(@PathVariable long bookId,
                          @ModelAttribute("usedBookOrder") UsedBookOrder usedBookOrder){
        //중고 주문폼 생성
        usedBookOrder.setUsedBookId(bookId);

        return "used/orderForm";
    }

    @PostMapping("/{bookId}")
    public String addForm(@PathVariable long bookId,
                          @SessionAttribute("user") User user,
                          @ModelAttribute("usedBookOrder") UsedBookOrder order){
        //중고 주문폼 제출
        usedBookService.submitOrderForm(user, order);
        return "myPage/orderList";
    }
}
