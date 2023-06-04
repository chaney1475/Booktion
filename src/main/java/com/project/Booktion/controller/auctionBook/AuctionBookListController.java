package com.project.Booktion.controller.auctionBook;

import com.project.Booktion.model.AuctionBook;
import com.project.Booktion.repository.AuctionBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/auction/books")
@RequiredArgsConstructor
public class AuctionBookListController {

    private final AuctionBookRepository auctionBookR;

    @GetMapping
    public String bookList(Model model){ // 전체 경매 책 불러오기
        List<AuctionBook> auctionBooks = auctionBookR.findAll();
        model.addAttribute("auctionBooks", auctionBooks);
        return "auction/books";
    }


    @GetMapping("/selling") // userId 읽고 user가 경매중인 책
    public String sellingList(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");
        List<AuctionBook> sellingBooks = auctionBookR.findBySellerId(userId);
        model.addAttribute("auctionBooks", sellingBooks);
        return "myPage/selling";
    }
    @GetMapping("/sold") //  userId 읽고 user가 경매 완료 책
    public String SoldList(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");
        List<AuctionBook> soldBooks = auctionBookR.findBySellerId(userId);
        model.addAttribute("sellingBooks", soldBooks);
        return "myPage/sold";
    }
}
