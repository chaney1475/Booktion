package com.project.Booktion.controller;

import com.project.Booktion.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.Order;
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
    private final OrderService orderService;
    @GetMapping("{userId}")
    public String orderList(@PathVariable String userId, Model model) {  // 주문내역
        List<Order> orders = orderService.findById(userId);
        model.addAttribute("orders", orders);
        return "user";
    }
}
