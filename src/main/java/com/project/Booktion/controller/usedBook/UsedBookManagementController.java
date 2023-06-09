package com.project.Booktion.controller.usedBook;

import com.project.Booktion.model.Order;
import com.project.Booktion.model.OrderItem;
import com.project.Booktion.model.UsedBook;
import com.project.Booktion.service.OrderService;
import com.project.Booktion.service.UsedBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j // 로그 찍는 기능
@Controller
@RequiredArgsConstructor
@RequestMapping("/myPage/used")
public class UsedBookManagementController {
    private final UsedBookService usedBookService;
    private final OrderService orderService;

    @GetMapping("/selling")
    public String getSellingUsedBooks(HttpSession session, Model model) {
        //판매중인 중고책 목록을 가져온다.
        log.info("UsedBookManagementController#getSellingUsedBooks start");
        List<UsedBook> bookList = usedBookService.getSellingUsedBooks((String)session.getAttribute("userId"));
        model.addAttribute("usedBookList", bookList);
        return "myPage/used/selling";
    }

    @GetMapping("/sold")
    public String getSoldUsedBooks(HttpSession session, Model model){
        log.info("UsedBookManagementController#getSoldUsedBooks start");
        List<OrderItem> orderList = usedBookService.getSoldUsedBooks((String)session.getAttribute("userId"));
        model.addAttribute("orderList", orderList);
        log.info("orderList : " + orderList.toString());
        return "myPage/used/sold";
    }

    @GetMapping("/sold/{orderId}")
    public String getDetailSoldOrder(@PathVariable long orderId, HttpSession session, Model model){
        log.info("UsedBookManagementController#getDetailSoldOrder start orderId : " + orderId);
        List<OrderItem> orderItemList = usedBookService.getSoldUsedBooks((String)session.getAttribute("userId"));
        OrderItem orderItem = null;
        for (OrderItem oi : orderItemList) {
            if (oi.getOrder().getOrderId() == orderId) {
                orderItem = oi;
                break;
            }
        }
        model.addAttribute("orderItem", orderItem);
        return "myPage/used/detailOrder";
    }

    @GetMapping("/sold/{orderId}/status")
    public String changeStatus(@PathVariable long orderId, @RequestParam int status, HttpSession session, Model model){
        log.info("UsedBookManagementController#changeStatus start orderId : " + orderId);
        Order order = orderService.findByOrderId(orderId);
        order.setStatus(status + 1);
        log.info("status is changed : " + order.getStatus());
        orderService.updateOrder(order);
        return "redirect:/myPage/used/sold/" + orderId;
    }
}
