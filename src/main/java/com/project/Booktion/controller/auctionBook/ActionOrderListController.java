package com.project.Booktion.controller.auctionBook;

import com.project.Booktion.repository.AuctionBookRepository;
import com.project.Booktion.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/auction/orders")
@RequiredArgsConstructor
public class ActionOrderListController {
    private final AuctionBookRepository auctionBookR;
    private final BidRepository bidRepository;

}
