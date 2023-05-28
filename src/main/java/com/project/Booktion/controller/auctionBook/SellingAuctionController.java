package com.project.Booktion.controller.auctionBook;

import com.project.Booktion.model.AuctionBook;
import com.project.Booktion.model.User;
import com.project.Booktion.service.AuctionBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

//판매중인 경매 책
@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/user/selling")
@RequiredArgsConstructor
@SessionAttributes("user")
public class SellingAuctionController {

    AuctionBookService auctionS;
    @GetMapping
    public String SellingList(@ModelAttribute User user, Model model){

        List<AuctionBook> sellingBooks = auctionS.findSellingAuctionById(user.getUserId());
        model.addAttribute("sellingBooks", sellingBooks);
        return "/myPage/selling";
    }

}
