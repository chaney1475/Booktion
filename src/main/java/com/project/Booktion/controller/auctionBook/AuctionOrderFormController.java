package com.project.Booktion.controller.auctionBook;

import com.project.Booktion.model.AuctionBook;
import com.project.Booktion.model.Book;
import com.project.Booktion.service.AuctionBookService;
import com.project.Booktion.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j // 로그 찍는 기능
@Controller
@RequestMapping("/auction/order")
@RequiredArgsConstructor
public class AuctionOrderFormController {
    // 경매 책 주문 폼 컨트롤러
    private final AuctionBookService AutionS;

    @GetMapping("/{bookId}")
    public String newForm(@PathVariable String bookId, Model model){

        AuctionBook book = AuctionBookService.findById(bookId);
        model.addAttribute("book", book);
        return "auctionForm";
    }

    @PostMapping("/{bookId}")
    public String addForm(@PathVariable String bookId, Model model){

        Book book = BookService.findById(bookId);
        return "auctionForm";
    }


    @PostMapping("/{bookId}")
    public String addForm(){

        AutionS.submitOrder();

        return "auctionOrder";
    }





}
