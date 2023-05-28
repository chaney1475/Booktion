package com.project.Booktion.controller.auctionBook;

import com.project.Booktion.model.*;
import com.project.Booktion.service.AuctionBookService;
import com.project.Booktion.service.BiddingService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/auction/order")
@SessionAttributes("user")
@RequiredArgsConstructor
public class AuctionOrderFormController {
    // 경매 책 주문 폼 컨트롤러
    private final AuctionBookService auctionS;
    private final BiddingService biddingS;

    @ModelAttribute("book")
    public AuctionBook createBook() {
        return new AuctionBook();
    }

    @GetMapping("/{tempOrderId}")
    public String newOrder(HttpServletRequest request, @PathVariable("tempOrderId") Long tempOrderId, Model model) throws NotFoundException {

        // tempOrderId를 사용하여 TempOrder를 조회하고 필요한 데이터를 모델에 담음
        TempOrder tempOrder = biddingS.findTempOrder(tempOrderId);

        model.addAttribute("tempOrder", tempOrder);

        return "auction/orderForm";
    }
    @PostMapping("/{tempOrderId}")
    public String submitOrder(@ModelAttribute("tempOrder") TempOrder tempOrder, AuctionOrderForm form) {
        // TempOrder에서 필요한 정보 추출
        AuctionBookOrder auctionBookOrder = auctionS.newOrder(tempOrder, form);
        return "redirect:/auction/order";
    }

    // 사용자가 판매하고 있는 책의 경매를 종료하면 임시 주문(tempOrder)만들기
    @PostMapping("/temp")
    public String tempOrder(@RequestParam("auctionBookId") long auctionBookId, Model model) {
        TempOrder tempOrder = biddingS.createTempOrder(auctionBookId);
        model.addAttribute("price",tempOrder.getBid().getPrice());
        return "auction/biddingComplete";
    }
    //경매를 완료하는 버튼
    //나중에 구현
}
