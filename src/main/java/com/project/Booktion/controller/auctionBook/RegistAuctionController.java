package com.project.Booktion.controller.auctionBook;


import com.project.Booktion.model.AuctionBook;
import com.project.Booktion.service.AuctionBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/auction")
@RequiredArgsConstructor
public class RegistAuctionController {

    private final AuctionBookService auctionS;
    @GetMapping("/registForm")
    public String newForm(){
        return "registAuctionForm";
    }

    @PostMapping("/registForm")
    public String registForm(@ModelAttribute AuctionBook book){
        // 검증 코드 생략
        String bookId = auctionS.addNewBook(book);
        return "/auction/books/{bookId}";
    }
}
