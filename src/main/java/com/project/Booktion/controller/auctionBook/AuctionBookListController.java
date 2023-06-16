package com.project.Booktion.controller.auctionBook;

import com.project.Booktion.model.AuctionBook;
import com.project.Booktion.model.AuctionBookOrder;
import com.project.Booktion.model.Bid;
import com.project.Booktion.repository.AuctionBookOrderRepository;
import com.project.Booktion.repository.AuctionBookRepository;
import com.project.Booktion.service.AuctionBookOrderService;
import com.project.Booktion.service.AuctionBookService;
import com.project.Booktion.service.BiddingService;
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


    private final AuctionBookService auctionBookS;
    private final AuctionBookOrderService auctionBOS;
    private final BiddingService biddingService;


    @GetMapping
    public String bookList(Model model){ // 전체 경매 책 불러오기
        List<AuctionBook> allBooks = auctionBookS.getAllBooks();
        model.addAttribute("auctionBooks", allBooks);
        return "auction/books";
    }


    @GetMapping("/selling") // userId 읽고 user가 경매중인 책
    public String sellingList(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");

        List<AuctionBook> sellingBooks = auctionBookS.getSellingBooks(userId);

        model.addAttribute("auctionBooks", sellingBooks);
        return "myPage/auction/selling";
    }
    @GetMapping("/sold") //  userId 읽고 user가 경매 완료 책
    public String SoldList(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");

        model.addAttribute("auctionBookOrders", auctionBOS.getUserOrder(userId));
        return "myPage/auction/sold";
    }



    @GetMapping("/bids")
    public String myBids( HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return "redirect:/signIn";
        }
        List<Bid> bids = biddingService.myBids(userId);
        model.addAttribute("bids", bids);
        return "myPage/auction/myBids";
    }
}
