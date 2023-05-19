package com.project.Booktion.controller.auctionBook;

import com.project.Booktion.model.AuctionBook;
import com.project.Booktion.model.AuctionOrder;
import com.project.Booktion.model.AuctionOrderForm;
import com.project.Booktion.model.User;
import com.project.Booktion.service.AuctionBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/auction/order")
@SessionAttributes("user")
@RequiredArgsConstructor
public class AuctionOrderFormController {
    // 경매 책 주문 폼 컨트롤러
    private final AuctionBookService auctionS;

    @ModelAttribute("book")
    public AuctionBook createBook() {
        return new AuctionBook();
    }

    @GetMapping("/{bookId}")
    public String newForm(@PathVariable String bookId, Model model){

        AuctionBook book = auctionS.findById(bookId);
        model.addAttribute("book", book);

        return "auction/orderForm";
    }

    @PostMapping("/{bookId}")
    public String submitForm(@SessionAttribute("user") User user, @PathVariable String bookId,
                             @ModelAttribute() AuctionOrderForm form, Model model){
        // 오류 처리 생략
        AuctionOrder auctionOrder = auctionS.newOrder(user.getId(), bookId, form);

        return "redirect:UserController/orderList";
    }


}
