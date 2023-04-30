package com.project.Booktion.controller.auctionBook;

import com.project.Booktion.model.Auction;
import com.project.Booktion.model.AuctionBook;
import com.project.Booktion.model.User;
import com.project.Booktion.service.AuctionBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/auction/books")
@RequiredArgsConstructor
@SessionAttributes("user")
public class ViewAuctionController {
    private final AuctionBookService auctionS;

    @GetMapping
    public String bookList(Model model){ // 전체 경매 책 불러오기
        List<AuctionBook> auctionBooks = auctionS.allBook();
        model.addAttribute("auctionBooks", auctionBooks);
        return "/auction/books";
    }
    @GetMapping("/{bookId}") // 경매책 상세 보기
    public String viewBook(@PathVariable String bookId, Model model){
        AuctionBook book = auctionS.findById(bookId);
        model.addAttribute("book", book);
        List<Auction> auctions = auctionS.getAuction();
        model.addAttribute("auctions", auctions);
        return "/auction/books/{bookId}";
    }

    @GetMapping("/selling") // userId 읽고 user가 경매중인 책
    public String SellingList(@ModelAttribute User user, Model model){
        List<AuctionBook> sellingBooks = auctionS.findSellingAuctionById(user.getId());
        model.addAttribute("sellingBooks", sellingBooks);
        return "/auction/books/selling";
    }
    @GetMapping("/sold") //  userId 읽고 user가 경매 완료 책
    public String SoldList(@ModelAttribute User user, Model model){
        List<AuctionBook> soldBooks = auctionS.findSoldAuctionById(user.getId());
        model.addAttribute("sellingBooks", soldBooks);
        return "/auction/books/sold";
    }

}
