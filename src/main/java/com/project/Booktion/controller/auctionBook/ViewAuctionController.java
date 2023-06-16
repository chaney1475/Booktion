package com.project.Booktion.controller.auctionBook;

import com.project.Booktion.model.*;
import com.project.Booktion.repository.AuctionBookRepository;
import com.project.Booktion.repository.BidRepository;
import com.project.Booktion.repository.UserRepository;
import com.project.Booktion.service.AuctionBookOrderService;
import com.project.Booktion.service.AuctionBookService;
import com.project.Booktion.service.BiddingService;
import com.project.Booktion.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/auction/books")
@RequiredArgsConstructor
public class ViewAuctionController {
    private final AuctionBookService auctionBookService;
    private final BiddingService biddingService;
    private final AuctionBookOrderService auctionBookOrderS;

    @ModelAttribute("auctionBook")
    public AuctionBook getAuctionBook(@PathVariable Long bookId) {
        return auctionBookService.findById(bookId);
    }

    @GetMapping("/{bookId}") // 경매책 상세 보기
    public String viewBook(@ModelAttribute AuctionBook auctionBook, @PathVariable Long bookId, HttpSession session, Model model){
        List<Bid> bids = biddingService.getBids(bookId);
        if (bids == null) {
            System.out.println("bids null");
        }
        if(auctionBook.getBook().getUser().getUserId().equals(session.getAttribute("userId"))){
            model.addAttribute("seller","seller");
        }
        if(auctionBookOrderS.findAuctionBook(bookId)){
            model.addAttribute("soldMessage", "도서가 판매 완료되었어요!");
        }
        model.addAttribute("bids", bids);
        return "auction/book";
    }
    @Transactional
    @PostMapping("/{bookId}")
    public String bidding(@ModelAttribute AuctionBook auctionBook, @PathVariable Long bookId,
                          @RequestParam int price, HttpSession session, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");
        if(userId == null){
            return "redirect:/signIn";
        }
        System.out.println(auctionBook.getAuctionBookId() + ", " + auctionBook.getBook().getTitle());
        Bid bid = new Bid(auctionBook, userId, price);
        Bid saved = biddingService.addBid(bid);
        int savedPrice = saved.getPrice();
        // 알림 메시지 추가
        redirectAttributes.addFlashAttribute("message", savedPrice + "원으로 입찰하였습니다");

        return "redirect:/auction/books/" + bookId;
    }

}
