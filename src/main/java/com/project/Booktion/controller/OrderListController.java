package com.project.Booktion.controller;

import com.project.Booktion.model.Order;
import com.project.Booktion.service.OrderSearchService;
import com.project.Booktion.service.OrderService;
import com.project.Booktion.service.UsedBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/myPage/order")
@RequiredArgsConstructor
public class OrderListController {
    private final OrderSearchService orderService;
    private final UsedBookService usedBookService;

    @GetMapping("/used")
    public String getUsedOrderList(HttpSession session, Model model){
        log.info("orderListController # getUsedOrderList is start! USER ID : " + (String) session.getAttribute("userId"));
        List<Order> usedOrderList = usedBookService.findMyUsedOrder((String) session.getAttribute("userId"));
        log.info("data check :" + usedOrderList.toString());
        model.addAttribute("usedOrderList", usedOrderList);
        return "/myPage/used/orderList";
    }

    @GetMapping("{userId}")
    public String orderList(@PathVariable String userId, Model model) {  // 주문내역
        return "myPage/user";
    }
}
