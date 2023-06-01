package com.project.Booktion.controller.auctionBook;

import com.project.Booktion.model.Bid;
import com.project.Booktion.model.AuctionBook;
import com.project.Booktion.model.Book;
import com.project.Booktion.model.User;
import com.project.Booktion.repository.AuctionBookRepository;
import com.project.Booktion.repository.BidRepository;
import com.project.Booktion.repository.UserRepository;
import com.project.Booktion.service.AuctionBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/auction/books")
@RequiredArgsConstructor
public class ViewAuctionController {
    private final AuctionBookRepository auctionBookR;
    private final BidRepository bidRepository;

    @GetMapping
    public String bookList(Model model){ // 전체 경매 책 불러오기
        List<AuctionBook> auctionBooks = auctionBookR.findAll();
        model.addAttribute("auctionBooks", auctionBooks);
        return "auction/books";
    }

    @GetMapping("/{bookId}") // 경매책 상세 보기
    public String viewBook(@PathVariable Long bookId, Model model){
        AuctionBook auctionBook = auctionBookR.findById(bookId).orElse(null);
        model.addAttribute("auctionBook", auctionBook);
        List<Bid> bids = bidRepository.findByAuctionBookAuctionBookId(bookId);
        if(bids == null){
            System.out.println("bids null");
        }
        model.addAttribute("bids", bids);
        return "auction/book";
    }
    @PostMapping("/{bookId}") // 경매책 상세 보기
    public String bidding(@ModelAttribute AuctionBook auctionBook, @PathVariable Long bookId,
                          @RequestParam int price, HttpSession session, RedirectAttributes redirectAttributes) {
        String userId = (String) session.getAttribute("userId");

        Bid bid = new Bid(auctionBook, userId, price);
        Bid saved = bidRepository.save(bid);
        int savedPrice = saved.getPrice();
        // 알림 메시지 추가
        redirectAttributes.addFlashAttribute("message", savedPrice +"원으로 입찰하였습니다");

        return "redirect:/auction/book/" + bookId;
    }


    @GetMapping("/selling") // userId 읽고 user가 경매중인 책
    public String SellingList(HttpSession session, Model model){
        String userId = (String) session.getAttribute("userId");
        List<AuctionBook> sellingBooks = auctionBookR.findBySellerId(userId);
        model.addAttribute("sellingBooks", sellingBooks);
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
