package com.project.Booktion.controller;

import com.project.Booktion.model.AuctionBookOrder;
import com.project.Booktion.model.Order;
import com.project.Booktion.repository.AuctionBookOrderRepository;
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
    private final OrderService orderService;
    private final UsedBookService usedBookService;
    private final AuctionBookOrderRepository auctionBOR;

    @GetMapping("/used")
    public String getUsedOrderList(HttpSession session, Model model){
        log.info("orderListController # getUsedOrderList is start! USER ID : " + (String) session.getAttribute("userId"));
        List<Order> usedOrderList = usedBookService.findMyUsedOrder((String) session.getAttribute("userId"));
        log.info("data check :" + usedOrderList.toString());
        model.addAttribute("usedOrderList", usedOrderList);
        return "/myPage/used/orderList";
    }

    @GetMapping("/basicBook")
    public String getBasicBookOrderList(HttpSession session, Model model) {
        log.info("OrderListController - getBasicBookOrderList is called! User ID: " + (String) session.getAttribute("userId"));
        List<Order> basicBookOrderList = orderService.findMyBasicBookOrder((String) session.getAttribute("userId"));
        log.info("Data check: " + basicBookOrderList.toString());

        if (basicBookOrderList.isEmpty()) {
            model.addAttribute("noOrdersMessage", "주문목록이 없습니다.");
        } else {
            model.addAttribute("basicBookOrderList", basicBookOrderList);
        }

        return "/myPage/basicBook/orderList";
    }
    @GetMapping("/auction")
    public String getBoughtList(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");
        if(userId == null) {return "/user/signIn";}
        List<AuctionBookOrder> auctionOrders = auctionBOR.findByOrderUserUserId(userId);
        model.addAttribute("auctionBookOrders", auctionOrders);
        if(auctionOrders == null){
            System.out.println("no auction orders");
        }
        return "myPage/auction/auctionOrderList";
    }
}
