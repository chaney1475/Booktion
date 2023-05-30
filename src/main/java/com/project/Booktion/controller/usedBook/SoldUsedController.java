package com.project.Booktion.controller.usedBook;

import com.project.Booktion.model.Order;
import com.project.Booktion.model.UsedBook;
import com.project.Booktion.service.UsedBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j // 로그 찍는 기능
@Controller
@RequiredArgsConstructor
public class SoldUsedController {
    private final UsedBookService usedBookService;

    @GetMapping("/myPage/used/sold")
    public String getSellingUsedBooks(HttpServletRequest request, Model model) {
        //판매완료된 중고책 목록을 가져온다.
        String userId = (String) request.getAttribute("userId");
        List<Order> orderList = usedBookService.findOrderBySeller(userId);
        model.addAttribute("soldUsedBooks", orderList);
        return "myPage/used/sold";
    }
}
