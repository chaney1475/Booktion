package com.project.Booktion.controller;

import com.project.Booktion.model.Order;
import com.project.Booktion.service.OrderSearchService;
import com.project.Booktion.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user/order")
@RequiredArgsConstructor
public class OrderListController {
    private final OrderSearchService orderService;

    @GetMapping("{userId}")
    public String orderList(@PathVariable String userId, Model model) {  // 주문내역
        return "myPage/user";
    }
}
