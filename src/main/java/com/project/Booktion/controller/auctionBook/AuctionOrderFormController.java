package com.project.Booktion.controller.auctionBook;

import com.project.Booktion.model.*;
import com.project.Booktion.repository.AuctionBookOrderRepository;
import com.project.Booktion.service.AuctionBookService;
import com.project.Booktion.service.BiddingService;
import com.project.Booktion.service.UsedBookService;
import com.project.Booktion.service.UserService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/auction/order")
@RequiredArgsConstructor
public class AuctionOrderFormController {
    private final UserService userService;
    // 경매 책 주문 폼 컨트롤러
    private final AuctionBookService auctionS;
    private final BiddingService biddingS;


    @GetMapping("/{tempOrderId}")
    public String newOrder(HttpServletRequest request, @ModelAttribute AuctionOrderForm orderForm,
                           HttpSession session, @PathVariable("tempOrderId") Long tempOrderId, Model model) {//로그인 상태 확인
        String userId = (String) session.getAttribute("userId");
        if(userId == null) {
            log.info("UserOrderFormController#newFrom is fail : user is null!!!!!!!!!!!!!");
            return "/user/signIn";
        }

        // tempOrderId를 사용하여 TempOrder를 조회하고 필요한 데이터를 모델에 담음
        TempOrder tempOrder = biddingS.findTempOrder(tempOrderId);
        model.addAttribute("tempOrder", tempOrder);

        User user = userService.getUser(tempOrder.getUserId());
        session.setAttribute("address", user.getAddress());
        session.setAttribute("name", user.getName());
        session.setAttribute("phoneNumber", user.getPhoneNumber());

        return "auction/orderForm";
    }
    @PostMapping("/{tempOrderId}")
    public String submitOrder(@ModelAttribute AuctionOrderForm orderForm,  @PathVariable("tempOrderId") Long tempOrderId, Model model) {
        TempOrder tempOrder = biddingS.findTempOrder(tempOrderId);
        // TempOrder에서 필요한 정보 추출
        AuctionBookOrder newOrder = auctionS.newOrder(tempOrder, orderForm);

        Order order = newOrder.getOrder();
        model.addAttribute("orderMsg","주문이 완료되었습니다!");
        model.addAttribute("order",order);
        return "myPage/auction/detailOrder";
    }

    // 사용자가 판매하고 있는 책의 경매를 종료하면 임시 주문(tempOrder)만들기
    @PostMapping("/{bookId}/temp")
    public String tempOrder(@PathVariable long bookId, Model model) {
        TempOrder tempOrder = biddingS.createTempOrder(bookId);
        model.addAttribute("price",tempOrder.getBid().getPrice());
        return "auction/biddingComplete";
    }

    @GetMapping("/detail")
    public String getOrderDetail(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");
        if(userId == null) {return "/user/signIn";}
        //model.addAttribute("auctionBookOrders", auctionOrders);
        return "myPage/auctionOrderList";
    }
}
